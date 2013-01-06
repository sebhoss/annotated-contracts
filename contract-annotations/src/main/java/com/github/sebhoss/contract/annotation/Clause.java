/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.annotation;

/**
 * A single clause of a method contract.
 */
public @interface Clause {

    /** TODO: Write documentation! */
    public static final String THIS   = "this";  //$NON-NLS-1$

    /** TODO: Write documentation! */
    public static final String RETURN = "return"; //$NON-NLS-1$

    /**
     * @return TODO: Write documentation!
     */
    String value();

    /**
     * @return TODO: Write documentation!
     */
    String message() default "";

    /**
     * @return TODO: Write documentation!
     */
    Class<? extends RuntimeException> exception() default IllegalArgumentException.class;

}
