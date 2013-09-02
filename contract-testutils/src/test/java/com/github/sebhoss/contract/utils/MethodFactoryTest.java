/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * TODO: Write documentation!
 */
@SuppressWarnings({ CompilerWarnings.STATIC_METHOD, CompilerWarnings.NLS })
public final class MethodFactoryTest {

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
     */
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

    /**
     * TODO: Write documentation!
     * 
     * @throws Exception
     *             In case <code>constructor.newInstance()</code> fails.
     */
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
