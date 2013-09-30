/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import com.github.sebhoss.contract.annotation.Contract;

/**
 * Creates {@link ContractVerifierBuilder}s.
 */
public interface ContractVerifierFactory {

    /**
     * @return A new {@link ContractVerifierBuilder}.
     */
    ContractVerifierBuilder createContractVerifier();

    /**
     * Builds {@link ContractVerifier}.
     */
    public interface ContractVerifierBuilder {

        /**
         * @param newMethod
         *            The method to use.
         * @return The current builder.
         */
        ContractVerifierBuilder method(final Method newMethod);

        /**
         * @param newInstance
         *            The instance to use.
         * @return The current builder.
         */
        ContractVerifierBuilder instance(final Object newInstance);

        /**
         * @param newArguments
         *            The arguments to use.
         * @return The current builder.
         */
        ContractVerifierBuilder arguments(final Object[] newArguments);

        /**
         * @param newContract
         *            The contract to use.
         * @return The current builder.
         */
        ContractVerifierBuilder contract(final Contract newContract);

        /**
         * @param newParameterNames
         *            The parameter names to use.
         * @return The current builder.
         */
        ContractVerifierBuilder parameterNames(final String[] newParameterNames);

        /**
         * @param newContext
         *            The context to use.
         * @return The current builder.
         */
        ContractVerifierBuilder context(final ContractContext newContext);

        /**
         * @return A new contract verifier
         */
        ContractVerifier get();

    }

}
