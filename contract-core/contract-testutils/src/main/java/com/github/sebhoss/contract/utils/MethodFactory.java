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

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.warnings.CompilerWarnings;

import java.lang.reflect.Method;

/**
 * Factory for {@link Method} instances.
 */
@SuppressWarnings(CompilerWarnings.NLS)
public final class MethodFactory {

    /**
     * @return Method without a contract.
     */
    public static Method createMethodWithoutContract() {
        return createMethod("methodWithoutContract");
    }

    /**
     * @return Method with an empty contract.
     */
    public static Method createMethodWithEmptyContract() {
        return createMethod("methodWithEmptyContract");
    }

    /**
     * @return Method with a contract which contains a precondition.
     */
    public static Method createMethodWithPrecondition() {
        return createMethod("methodWithPrecondition", int.class);
    }

    /**
     * @return Method with a contract which contains a postcondition.
     */
    public static Method createMethodWithPostcondition() {
        return createMethod("methodWithPostcondition");
    }

    private static Method createMethod(final String methodName, final Class<?>... parameterTypes) {
        try {
            return Methods.class.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException | SecurityException exception) {
            throw new IllegalArgumentException(methodName, exception);
        }
    }

    private MethodFactory() {
        // Utility class
    }

    private interface Methods {

        void methodWithoutContract();

        @Contract
        void methodWithEmptyContract();

        @Contract(preconditions = { @Clause("parameter > 0") })
        void methodWithPrecondition(int parameter);

        @Contract(postconditions = { @Clause("return > 0") })
        int methodWithPostcondition();

    }

}
