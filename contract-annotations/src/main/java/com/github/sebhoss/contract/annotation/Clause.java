/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.annotation;

import com.github.sebhoss.common.annotation.CompilerWarnings;

/**
 * A single clause of a method contract.
 */
@SuppressWarnings(CompilerWarnings.NLS)
public @interface Clause {

    /** Identifier for the enclosing instance whose method is called. */
    public static final String THIS   = "this";

    /** Identifier for the returned value of the method call (if any). */
    public static final String RETURN = "return";

    /**
     * @return The boolean expression to check.
     */
    String value();

    /**
     * @return The error message to use.
     */
    String message() default "";

    /**
     * @return The exception to throw.
     */
    Class<? extends RuntimeException> exception() default IllegalArgumentException.class;

}
