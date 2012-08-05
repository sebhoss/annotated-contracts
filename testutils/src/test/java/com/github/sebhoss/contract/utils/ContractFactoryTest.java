package com.github.sebhoss.contract.utils;

import java.lang.reflect.Constructor;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({ "static-method", "nls" })
public final class ContractFactoryTest {

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
