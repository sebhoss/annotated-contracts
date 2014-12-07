/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import java.lang.reflect.Constructor;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for the {@link ContractFactory}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class ContractFactoryTest {

    /**
     * Ensures that the constructor of the {@link ContractFactory} is not invokable.
     */
    @Test
    public void shouldNotBeInvokable() {
        // Given
        final Class<?> clazz = ContractFactory.class;

        // When
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Then
        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse("Utility class should not have an accessible constructor!", constructor.isAccessible());
        }
    }

    /**
     * Ensures that the constructor of the {@link ContractFactory} is invokable via reflection.
     * 
     * @throws Exception
     *             In case an exceptions occurs during instance creation.
     */
    @Test
    public void shouldBeInvokableViaReflection() throws Exception {
        // given
        final Class<?> clazz = ContractFactory.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        // when
        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        // then
        Assert.assertNotNull("No instance could be created!", instance);
    }

}
