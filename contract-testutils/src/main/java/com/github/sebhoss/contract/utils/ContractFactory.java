/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import java.lang.annotation.Annotation;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * TODO: Write documentation!
 */
public final class ContractFactory {

    private ContractFactory() {
        // Utility class
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Contract emptyContract() {
        return ContractFactory.contract(new Clause[] {}, new Clause[] {});
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Contract contractWithPrecondition() {
        return ContractFactory.contract(new Clause[] { ContractFactory.alwaysTrueDefaultClause() }, new Clause[] {});
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Contract contractWithPostcondition() {
        return ContractFactory.contract(new Clause[] {}, new Clause[] { ContractFactory.alwaysTrueDefaultClause() });
    }

    /**
     * @param clauses
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Contract contractWithPreconditions(final Clause... clauses) {
        return ContractFactory.contract(clauses, new Clause[] {});
    }

    /**
     * @param clauses
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Contract contractWithPostconditions(final Clause... clauses) {
        return ContractFactory.contract(new Clause[] {}, clauses);
    }

    /**
     * @param preconditions
     *            TODO: Write documentation!
     * @param postconditions
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Contract contract(final Clause[] preconditions, final Clause[] postconditions) {
        return new Contract() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Contract.class;
            }

            @Override
            public Clause[] preconditions() {
                return preconditions;
            }

            @Override
            public Clause[] postconditions() {
                return postconditions;
            }

        };
    }

    /**
     * @return TODO: Write documentation!
     */
    public static Clause alwaysTrueDefaultClause() {
        return ContractFactory.clause("true", "", IllegalArgumentException.class); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @param condition
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Clause clause(final String condition) {
        return ContractFactory.clause(condition, "", IllegalArgumentException.class); //$NON-NLS-1$
    }

    /**
     * @param condition
     *            TODO: Write documentation!
     * @param message
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Clause clause(final String condition, final String message) {
        return ContractFactory.clause(condition, message, IllegalArgumentException.class);
    }

    /**
     * @param condition
     *            TODO: Write documentation!
     * @param message
     *            TODO: Write documentation!
     * @param exception
     *            TODO: Write documentation!
     * @return TODO: Write documentation!
     */
    public static Clause clause(final String condition, final String message,
            final Class<? extends RuntimeException> exception) {
        return new Clause() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Clause.class;
            }

            @Override
            public String value() {
                return condition;
            }

            @Override
            public String message() {
                return message;
            }

            @Override
            public Class<? extends RuntimeException> exception() {
                return exception;
            }

        };
    }

}
