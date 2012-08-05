package com.github.sebhoss.contract.utils;

import java.lang.annotation.Annotation;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

public final class ContractFactory {

    private ContractFactory() {
        // Utility class
    }

    public static Contract emptyContract() {
        return contract(new Clause[] {}, new Clause[] {});
    }

    public static Contract contractWithPrecondition() {
        return contract(new Clause[] { alwaysTrueDefaultClause() }, new Clause[] {});
    }

    public static Contract contractWithPostcondition() {
        return contract(new Clause[] {}, new Clause[] { alwaysTrueDefaultClause() });
    }

    public static Contract contractWithPreconditions(final Clause... clauses) {
        return contract(clauses, new Clause[] {});
    }

    public static Contract contractWithPostconditions(final Clause... clauses) {
        return contract(new Clause[] {}, clauses);
    }

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

    public static Clause alwaysTrueDefaultClause() {
        return clause("true", "", IllegalArgumentException.class); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static Clause clause(final String condition) {
        return clause(condition, "", IllegalArgumentException.class); //$NON-NLS-1$
    }

    public static Clause clause(final String condition, final String message) {
        return clause(condition, message, IllegalArgumentException.class);
    }

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
