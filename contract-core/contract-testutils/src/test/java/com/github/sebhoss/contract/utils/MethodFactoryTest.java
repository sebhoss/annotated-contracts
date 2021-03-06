/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org>
 */
package com.github.sebhoss.contract.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for the {@link MethodFactory}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class MethodFactoryTest {

    /**
     * Ensures that a non-<code>null</code> method can be created without any contract.
     */
    @Test
    public void shouldCreateNonNullMethodWithoutContract() {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithoutContract();

        // Then
        Assert.assertNotNull("The createMethodWithEmptyContract() method should never return NULL!", method);
    }

    /**
     * Ensures that the returned method has no contract.
     */
    @Test
    public void shouldCreateMethodWithoutContract() {
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

    /**
     * Ensures that non-<code>null</code> method can be created with an empty contract.
     */
    @Test
    public void shouldCreateNonNullMethodWithEmptyContract() {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithEmptyContract();

        // Then
        Assert.assertNotNull("The createMethodWithEmptyContract() method should never return NULL!", method);
    }

    /**
     * Ensures that the returned method has an non-<code>null</code> contract.
     */
    @Test
    public void shouldCreateNonNullMethodWithEmptyNonNullContract() {
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

    /**
     * Ensures that the returned method has a contract without any preconditions.
     */
    @Test
    public void shouldCreateMethodWithEmptyContractWithoutPreconditions() {
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

    /**
     * Ensures that the returned method has a contract without any postconditions.
     */
    @Test
    public void shouldCreateMethodWithEmptyContractWithoutPostconditions() {
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

    /**
     * Ensures that a non-<code>null</code> method can be created with a precondition.
     */
    @Test
    public void shouldCreateNonNullMethodWithPrecondition() {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPrecondition();

        // Then
        Assert.assertNotNull("The createMethodWithPrecondition() method should never return NULL!", method);
    }

    /**
     * Ensures that the returned method has a non-<code>null</code> contract.
     */
    @Test
    public void shouldCreateNonNullMethodWithNonNullPreconditionContract() {
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

    /**
     * Ensures that the returned method has a contract with non-<code>null</code> preconditions.
     */
    @Test
    public void shouldCreateNonNullMethodWithNonNullPrecondition() {
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

    /**
     * Ensures that the returned method has a contract with a single precondition.
     */
    @Test
    public void shouldCreateMethodWithPrecondition() {
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

    /**
     * Ensures that the returned method has a contract without any postconditions.
     */
    @Test
    public void shouldCreateMethodWithoutPostconditions() {
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

    /**
     * Ensures that a non-<code>null</code> method with a contract which contains postcondition can be created.
     */
    @Test
    public void shouldCreateNonNullMethodWithPostcondition() {
        // Given
        Method method;

        // When
        method = MethodFactory.createMethodWithPostcondition();

        // Then
        Assert.assertNotNull("The createMethodWithPostcondition() method should never return NULL!", method);
    }

    /**
     * Ensures that the returned method has a non-<code>null</code> contract.
     */
    @Test
    public void shouldCreateNonNullMethodWithNonNullPostconditionContract() {
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

    /**
     * Ensures that the returned method has a contract with non-<code>null</code> postconditions.
     */
    @Test
    public void shouldCreateNonNullMethodWithNonNullPostcondition() {
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

    /**
     * Ensures that the returned method has a contract which contains postconditions.
     */
    @Test
    public void shouldCreateMethodWithPostcondition() {
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

    /**
     * Ensures that the returned method has a contract without any preconditions.
     */
    @Test
    public void shouldCreateMethodWithoutPreconditions() {
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

    /**
     * Ensures that the constructor is not invokable.
     */
    @Test
    public void shouldNotBeInvokable() {
        // Given
        final Class<?> clazz = MethodFactory.class;

        // When
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Then
        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse("Utility class should not have an accessible constructor!", constructor.isAccessible());
        }
    }

    /**
     * Ensures that the constructor is invokable via reflection.
     * 
     * @throws Exception
     *             In case something goes wrong during instance creation.
     */
    @Test
    public void shouldBeInvokableViaReflection() throws Exception {
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
