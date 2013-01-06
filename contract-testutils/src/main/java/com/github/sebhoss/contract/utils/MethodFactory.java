package com.github.sebhoss.contract.utils;

import java.lang.reflect.Method;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * TODO: Write documentation!
 * 
 */
@SuppressWarnings("nls")
public final class MethodFactory {

    private MethodFactory() {
        // Utility class
    }

    /**
     * @return TODO: Write documentation!
     * @throws NoSuchMethodException
     *             TODO: Write documentation!
     * @throws SecurityException
     *             TODO: Write documentation!
     */
    public static Method createMethodWithoutContract() throws NoSuchMethodException, SecurityException {
        final Method declaredMethod = Methods.class.getDeclaredMethod("methodWithoutContract", new Class[] {});

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

        return declaredMethod;
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
