/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import java.lang.annotation.Annotation;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * Factory which creates {@link Contract} instances.
 */
public final class ContractFactory {

    /**
     * @return An empty contract without any pre- or postconditions.
     */
    public static Contract emptyContract() {
        return ContractFactory.contract(new Clause[] {}, new Clause[] {});
    }

    /**
     * @return A contract with a precondition.
     */
    public static Contract contractWithPrecondition() {
        return ContractFactory.contract(new Clause[] { ContractFactory.alwaysTrueDefaultClause() }, new Clause[] {});
    }

    /**
     * @return A contract with a postcondition.
     */
    public static Contract contractWithPostcondition() {
        return ContractFactory.contract(new Clause[] {}, new Clause[] { ContractFactory.alwaysTrueDefaultClause() });
    }

    /**
     * @param clauses
     *            The preconditions to use.
     * @return A contract with the given preconditions.
     */
    public static Contract contractWithPreconditions(final Clause... clauses) {
        return ContractFactory.contract(clauses, new Clause[] {});
    }

    /**
     * @param clauses
     *            The postconditions to use.
     * @return A contract with the given postconditions.
     */
    public static Contract contractWithPostconditions(final Clause... clauses) {
        return ContractFactory.contract(new Clause[] {}, clauses);
    }

    /**
     * @param preconditions
     *            The preconditions to use.
     * @param postconditions
     *            The postconditions to use.
     * @return A contract with the given pre- and postconditions.
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
     * @return A clause which always validates as <code>true</code>.
     */
    public static Clause alwaysTrueDefaultClause() {
        return ContractFactory.clause("true"); //$NON-NLS-1$
    }

    /**
     * @param condition
     *            The clause condition to use.
     * @return A clause with the given condition.
     */
    public static Clause clause(final String condition) {
        return ContractFactory.clause(condition, ""); //$NON-NLS-1$
    }

    /**
     * @param condition
     *            The clause condition to use.
     * @param message
     *            The clause message to use.
     * @return A clause with the given condition and message.
     */
    public static Clause clause(final String condition, final String message) {
        return ContractFactory.clause(condition, message, IllegalArgumentException.class);
    }

    /**
     * @param condition
     *            The clause condition to use.
     * @param message
     *            The clause message to use.
     * @param exception
     *            The clause exception to use.
     * @return A clause with the given condition, message and exception.
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

    private ContractFactory() {
        // Utility class
    }

}
