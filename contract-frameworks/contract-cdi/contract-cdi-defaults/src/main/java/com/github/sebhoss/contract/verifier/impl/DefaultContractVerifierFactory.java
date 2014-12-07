/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ParameterNamesLookup;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configures the default ContractVerifierFactory.
 */
public class DefaultContractVerifierFactory {

    /**
     * @param contractRetrieval
     *            The contract retrieval to use.
     * @param contractSyntaxCheck
     *            The contract syntax checks to use.
     * @param parameterNamesLookup
     *            The parameter names lookup to use.
     * @param contractSemanticCheck
     *            The contract semantic checks to use.
     * @param contractContextFactory
     *            The contract context factory to use.
     * @param contractExceptionFactory
     *            The contract exception factory to use.
     * @return Builds a context based contract verifier factory.
     */
    @Produces
    @Default
    @SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
    public ContractVerifierFactory contextBased(final ContractRetrieval contractRetrieval,
            final ContractSyntaxCheck contractSyntaxCheck, final ParameterNamesLookup parameterNamesLookup,
            final ContractSemanticCheck contractSemanticCheck, final ContractContextFactory contractContextFactory,
            final ContractExceptionFactory contractExceptionFactory) {
        return new ContextBasedContractVerifierFactory(contractRetrieval, contractSyntaxCheck, parameterNamesLookup,
                contractSemanticCheck, contractContextFactory, contractExceptionFactory);
    }

}
