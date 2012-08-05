/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.annotation;

public @interface Clause {

    public static final String THIS   = "this";  //$NON-NLS-1$

    public static final String RETURN = "return"; //$NON-NLS-1$

    String value();

    String message() default "";

    Class<? extends RuntimeException> exception() default IllegalArgumentException.class;

}
