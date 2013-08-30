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
     * @throws NoSuchMethodException
     *             TODO: Write documentation!
     * @throws SecurityException
     *             TODO: Write documentation!
     */
    public static Method createMethodWithoutContract() throws NoSuchMethodException, SecurityException {
        final Method declaredMethod = Methods.class.getDeclaredMethod("methodWithoutContract", new Class[] {});

        if (declaredMethod == null) {
            throw new NullPointerException("Could not create method!");
        }

        return declaredMethod;
    }

    /**
     * @return TODO: Write documentation!
     * @throws NoSuchMethodException
     *             TODO: Write documentation!
     * @throws SecurityException
     *             TODO: Write documentation!
     */
    public static Method createMethodWithEmptyContract() throws NoSuchMethodException, SecurityException {
        final Method declaredMethod = Methods.class.getDeclaredMethod("methodWithEmptyContract", new Class[] {});

        if (declaredMethod == null) {
            throw new NullPointerException("Could not create method!");
        }

        return declaredMethod;
    }

    /**
     * @return TODO: Write documentation!
     * @throws NoSuchMethodException
     *             TODO: Write documentation!
     * @throws SecurityException
     *             TODO: Write documentation!
     */
    public static Method createMethodWithPrecondition() throws NoSuchMethodException, SecurityException {
        final Method declaredMethod = Methods.class.getDeclaredMethod("methodWithPrecondition",
                new Class[] { int.class });

        if (declaredMethod == null) {
            throw new NullPointerException("Could not create method!");
        }

        return declaredMethod;
    }

    /**
     * @return TODO: Write documentation!
     * @throws NoSuchMethodException
     *             TODO: Write documentation!
     * @throws SecurityException
     *             TODO: Write documentation!
     */
    public static Method createMethodWithPostcondition() throws NoSuchMethodException, SecurityException {
        final Method declaredMethod = Methods.class.getDeclaredMethod("methodWithPostcondition", new Class[] {});

        if (declaredMethod == null) {
            throw new NullPointerException("Could not create method!");
        }

        return declaredMethod;
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
