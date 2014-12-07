/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.inject.Inject;

import org.eclipse.jdt.annotation.Nullable;

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
import com.github.sebhoss.nullanalysis.Nullsafe;

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
    public ContractVerifierBuilder createContractVerifier() {
        return new ContextBasedContractVerifierBuilder();
    }

    /**
     * @return the contractRetrieval
     */
    ContractRetrieval getContractRetrieval() {
        return contractRetrieval;
    }

    /**
     * @return the contractSyntaxCheck
     */
    ContractSyntaxCheck getContractSyntaxCheck() {
        return contractSyntaxCheck;
    }

    /**
     * @return the parameterNamesLookup
     */
    ParameterNamesLookup getParameterNamesLookup() {
        return parameterNamesLookup;
    }

    /**
     * @return the contractSemanticCheck
     */
    ContractSemanticCheck getContractSemanticCheck() {
        return contractSemanticCheck;
    }

    /**
     * @return the contractContextFactory
     */
    ContractContextFactory getContractContextFactory() {
        return contractContextFactory;
    }

    /**
     * @return the contractExceptionFactory
     */
    ContractExceptionFactory getContractExceptionFactory() {
        return contractExceptionFactory;
    }

    /**
     * Builds {@link ContractVerifier}
     */
    @Nullable
    public class ContextBasedContractVerifierBuilder implements ContractVerifierBuilder {

        @Nullable
        private Method          method;

        @Nullable
        private Object          instance;

        @Nullable
        private Object[]        arguments;

        @Nullable
        private Contract        contract;

        @Nullable
        private String[]        parameterNames;

        @Nullable
        private ContractContext context;

        @Override
        public ContractVerifierBuilder method(final Method newMethod) {
            method = newMethod;

            return this;
        }

        @Override
        public ContractVerifierBuilder instance(final Object newInstance) {
            instance = newInstance;

            return this;
        }

        @Override
        public ContractVerifierBuilder arguments(final Object[] newArguments) {
            arguments = Arrays.copyOf(newArguments, newArguments.length);

            return this;
        }

        @Override
        public ContractVerifierBuilder contract(final Contract newContract) {
            contract = newContract;

            return this;
        }

        @Override
        public ContractVerifierBuilder parameterNames(final String[] newParameterNames) {
            parameterNames = Arrays.copyOf(newParameterNames, newParameterNames.length);

            return this;
        }

        @Override
        public ContractVerifierBuilder context(final ContractContext newContext) {
            context = newContext;

            return this;
        }

        @Override
        public ContractVerifier get() {
            if (needsContractRetrieval()) {
                retrieveContract();
            }

            checkContractSyntax();

            if (needsParameterNamesLookup()) {
                lookupParameterNames();
            }

            checkContractSemantic();

            if (missesContractContext()) {
                createContractContext();
            }

            return new ContextBasedContractVerifier(getContract(), getContext(), getContractExceptionFactory());
        }

        private boolean needsContractRetrieval() {
            return contract == null;
        }

        private void retrieveContract() {
            contract = getContractRetrieval().retrieveContract(getMethod());
        }

        private void checkContractSyntax() {
            getContractSyntaxCheck().validate(getContract());
        }

        private boolean needsParameterNamesLookup() {
            return parameterNames == null;
        }

        private void lookupParameterNames() {
            parameterNames = getParameterNamesLookup().lookupParameterNames(getMethod());
        }

        private void checkContractSemantic() {
            getContractSemanticCheck().validate(getContract(), getParameterNames());
        }

        private boolean missesContractContext() {
            return context == null;
        }

        private void createContractContext() {
            context = getContractContextFactory().createContext(getInstance(), getArguments(), getParameterNames());
        }

        private Contract getContract() {
            return Nullsafe.nullsafe(contract);
        }

        private Method getMethod() {
            return Nullsafe.nullsafe(method);
        }

        private String[] getParameterNames() {
            return Nullsafe.nullsafe(parameterNames);
        }

        private Object[] getArguments() {
            return Nullsafe.nullsafe(arguments);
        }

        private Object getInstance() {
            return Nullsafe.nullsafe(instance);
        }

        private ContractContext getContext() {
            return Nullsafe.nullsafe(context);
        }

    }

}
