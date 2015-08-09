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
