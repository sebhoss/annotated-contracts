/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import java.lang.reflect.Method;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * TODO: Write documentation!
 */
@SuppressWarnings(CompilerWarnings.NLS)
public final class MethodFactory {

    /**
     * @return TODO: Write documentation!
     */
    public static Method createMethodWithoutContract() {
        return createMethod("methodWithoutContract");
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Method createMethodWithEmptyContract() {
        return createMethod("methodWithEmptyContract");
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Method createMethodWithPrecondition() {
        return createMethod("methodWithPrecondition", int.class);
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Method createMethodWithPostcondition() {
        return createMethod("methodWithPostcondition");
    }

    private static Method createMethod(final String methodName, final Class<?>... parameterTypes) {
        try {
            final Method declaredMethod = Methods.class.getDeclaredMethod(methodName, parameterTypes);

            if (declaredMethod == null) {
                throw new NullPointerException();
            }

            return declaredMethod;
        } catch (NoSuchMethodException | SecurityException exception) {
            throw new IllegalArgumentException(methodName);
        }
    }

    private MethodFactory() {
        // Utility class
    }

    private static interface Methods {

        void methodWithoutContract();

        @Contract
        void methodWithEmptyContract();

        @Contract(preconditions = { @Clause("parameter > 0") })
        void methodWithPrecondition(int parameter);

        @Contract(postconditions = { @Clause("return > 0") })
        int methodWithPostcondition();

    }

}
