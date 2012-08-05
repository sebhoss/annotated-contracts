/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.github.sebhoss.contract.verifier.ParameterNamesLookup;

public final class InjectableContractVerifierFactory implements ContractVerifierFactory {

    private final ContractRetrieval        contractRetrieval;
    private final ContractSyntaxCheck      contractSyntaxCheck;
    private final ParameterNamesLookup     parameterNamesLookup;
    private final ContractSemanticCheck    contractSemanticCheck;
    private final ContractContextFactory   contractContextFactory;
    private final ContractExceptionFactory contractExceptionFactory;

    @Inject
    public InjectableContractVerifierFactory(final ContractRetrieval contractRetrieval,
            final ContractSyntaxCheck contractSyntaxCheck,
            final ParameterNamesLookup parameterNamesLookup, final ContractSemanticCheck contractSemanticCheck,
            final ContractContextFactory contractContextFactory,
            final ContractExceptionFactory contractExceptionFactory) {
        this.contractRetrieval = contractRetrieval;
        this.contractSyntaxCheck = contractSyntaxCheck;
        this.parameterNamesLookup = parameterNamesLookup;
        this.contractSemanticCheck = contractSemanticCheck;
        this.contractContextFactory = contractContextFactory;
        this.contractExceptionFactory = contractExceptionFactory;
    }

    @Override
    public ContractVerifier createContractVerifier(final Object instance, final Method method,
            final Object[] arguments) {
        final Contract contract = this.contractRetrieval.retrieveContract(method);
        this.contractSyntaxCheck.validate(contract);
        final String[] parameterNames = this.parameterNamesLookup.lookupParameterNames(method);
        this.contractSemanticCheck.validate(contract, parameterNames);
        final ContractContext context = this.contractContextFactory.createContext(instance, arguments, parameterNames);

        return new DelegatingContractVerifier(contract, context, this.contractExceptionFactory);
    }

}
