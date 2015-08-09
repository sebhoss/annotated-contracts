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
