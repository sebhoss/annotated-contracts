/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ParameterNamesLookup;

/**
 * Creates {@link ContractVerifier} which are based on a {@link ContractContext}.
 */
public final class ContextBasedContractVerifierFactory implements ContractVerifierFactory {

    private final ContractRetrieval        contractRetrieval;
    private final ContractSyntaxCheck      contractSyntaxCheck;
    private final ParameterNamesLookup     parameterNamesLookup;
    private final ContractSemanticCheck    contractSemanticCheck;
    private final ContractContextFactory   contractContextFactory;
    private final ContractExceptionFactory contractExceptionFactory;

    /**
     * @param contractRetrieval
     *            The instance to use for contract retrieval.
     * @param contractSyntaxCheck
     *            The contract syntax checks to perform.
     * @param parameterNamesLookup
     *            The instance to look up parameter names.
     * @param contractSemanticCheck
     *            The contract semantic checks to perform.
     * @param contractContextFactory
     *            The factory to create a contract context.
     * @param contractExceptionFactory
     *            The factory to create contract violation exceptions.
     */
    @Inject
    public ContextBasedContractVerifierFactory(final ContractRetrieval contractRetrieval,
            final ContractSyntaxCheck contractSyntaxCheck, final ParameterNamesLookup parameterNamesLookup,
            final ContractSemanticCheck contractSemanticCheck, final ContractContextFactory contractContextFactory,
            final ContractExceptionFactory contractExceptionFactory) {
        this.contractRetrieval = contractRetrieval;
        this.contractSyntaxCheck = contractSyntaxCheck;
        this.parameterNamesLookup = parameterNamesLookup;
        this.contractSemanticCheck = contractSemanticCheck;
        this.contractContextFactory = contractContextFactory;
        this.contractExceptionFactory = contractExceptionFactory;
    }

    @Override
    public ContractVerifier createContractVerifier(final Object instance, final Method method, final Object[] arguments) {
        final Contract contract = contractRetrieval.retrieveContract(method);
        contractSyntaxCheck.validate(contract);
        final String[] parameterNames = parameterNamesLookup.lookupParameterNames(method);
        contractSemanticCheck.validate(contract, parameterNames);
        final ContractContext context = contractContextFactory.createContext(instance, arguments, parameterNames);

        return new ContextBasedContractVerifier(contract, context, contractExceptionFactory);
    }

}
