/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;

/**
 * Defines a method-level contract between a caller and a called instance.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Contract {

    /**
     * @return Set of preconditions which must be fulfilled before the method can be run.
     */
    @Nonnull
    Clause[] preconditions() default {};

    /**
     * @return Set of postconditions which must be fulfilled before the method can return.
     */
    @Nonnull
    Clause[] postconditions() default {};

}
