package com.github.sebhoss.contract.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

@SuppressWarnings({ "static-method", "nls" })
public final class MethodFactoryTest {

    @Test
    public void shouldCreateNonNullMethodWithoutContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithoutContract();

        // Then
        Assert.assertNotNull("The createMethodWithEmptyContract() method should never return NULL!", method);
    }

    @Test
    public void shouldCreateMethodWithoutContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithoutContract();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertNull(
                "The createMethodWithEmptyContract() method should only create other methods that do not have an contract annotation!",
                contract);
    }

    @Test
    public void shouldCreateNonNullMethodWithEmptyContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithEmptyContract();

        // Then
        Assert.assertNotNull("The createMethodWithEmptyContract() method should never return NULL!", method);
    }

    @Test
    public void shouldCreateNonNullMethodWithEmptyNonNullContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithEmptyContract();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertNotNull(
                "The createMethodWithEmptyContract() method should only create other methods that do have an contract annotation!",
                contract);
    }

    @Test
    public void shouldCreateMethodWithEmptyContractWithoutPreconditions() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithEmptyContract();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertTrue(
                "The createMethodWithEmptyContract() method should only create other methods that do not have preconditions!",
                contract.preconditions().length == 0);
    }

    @Test
    public void shouldCreateMethodWithEmptyContractWithoutPostconditions() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithEmptyContract();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertTrue(
                "The createMethodWithEmptyContract() method should only create other methods that do not have postconditions!",
                contract.postconditions().length == 0);
    }

    @Test
    public void shouldCreateNonNullMethodWithPrecondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();

        // Then
        Assert.assertNotNull("The createMethodWithPrecondition() method should never return NULL!", method);
    }

    @Test
    public void shouldCreateNonNullMethodWithNonNullPreconditionContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertNotNull(
                "The createMethodWithPrecondition() method should only create other methods that do have a contract!",
                contract);
    }

    @Test
    public void shouldCreateNonNullMethodWithNonNullPrecondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] preconditions = contract.preconditions();

        // Then
        Assert.assertNotNull(
                "The createMethodWithPrecondition() method should only create other methods that do have preconditions!",
                preconditions);
    }

    @Test
    public void shouldCreateMethodWithPrecondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] preconditions = contract.preconditions();

        // Then
        Assert.assertTrue(
                "The createMethodWithPrecondition() method should only create other methods that do have preconditions!",
                preconditions.length == 1);
    }

    @Test
    public void shouldCreateMethodWithoutPostconditions() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] postconditions = contract.postconditions();

        // Then
        Assert.assertTrue(
                "The createMethodWithPostcondition() method should only create other methods that do not have postconditions!",
                postconditions.length == 0);
    }

    @Test
    public void shouldCreateNonNullMethodWithPostcondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();

        // Then
        Assert.assertNotNull("The createMethodWithPostcondition() method should never return NULL!", method);
    }

    @Test
    public void shouldCreateNonNullMethodWithNonNullPostconditionContract() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();
        final Contract contract = method.getAnnotation(Contract.class);

        // Then
        Assert.assertNotNull(
                "The createMethodWithPostcondition() method should only create other methods that do have a contract!",
                contract);
    }

    @Test
    public void shouldCreateNonNullMethodWithNonNullPostcondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] postconditions = contract.postconditions();

        // Then
        Assert.assertNotNull(
                "The createMethodWithPostcondition() method should only create other methods that do have postconditions!",
                postconditions);
    }

    @Test
    public void shouldCreateMethodWithPostcondition() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] postconditions = contract.postconditions();

        // Then
        Assert.assertTrue(
                "The createMethodWithPostcondition() method should only create other methods that do have postconditions!",
                postconditions.length == 1);
    }

    @Test
    public void shouldCreateMethodWithoutPreconditions() throws Exception {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();
        final Contract contract = method.getAnnotation(Contract.class);
        final Clause[] preconditions = contract.preconditions();

        // Then
        Assert.assertTrue(
                "The createMethodWithPrecondition() method should only create other methods that do not have preconditions!",
                preconditions.length == 0);
    }

    @Test
    public void shouldNotBeInvocable() {
        // Given
        final Class<?> clazz = MethodFactory.class;

        // When
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Then
        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse("Utility class should not have an accessible constructor!", constructor.isAccessible());
        }
    }

    @Test
    public void shouldBeInvocableViaReflection() throws Exception {
        // given
        final Class<?> clazz = MethodFactory.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        // when
        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        // then
        Assert.assertNotNull("No instance could be created!", instance);
    }

}
