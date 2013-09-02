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

import com.github.sebhoss.common.annotation.CompilerWarnings;

/**
 * TODO: Write documentation!
 */
@SuppressWarnings({ CompilerWarnings.STATIC_METHOD, CompilerWarnings.NLS })
public final class ContractFactoryTest {

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldNotBeInvocable() {
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
     * @throws Exception
     *             TODO: Write documentation!
     */
    @Test
    public void shouldBeInvocableViaReflection() throws Exception {
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
