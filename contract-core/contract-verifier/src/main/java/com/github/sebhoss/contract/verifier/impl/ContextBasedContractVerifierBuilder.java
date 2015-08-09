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
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.*;

import java.lang.reflect.Method;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Builds {@link ContractVerifier}
 */
final class ContextBasedContractVerifierBuilder implements ContractVerifierBuilder {

    private final ContractRetrieval         contractRetrieval;
    private final ContractSyntaxCheck       contractSyntaxCheck;
    private final ParameterNamesLookup      parameterNamesLookup;
    private final ContractSemanticCheck     contractSemanticCheck;
    private final ContractContextFactory    contractContextFactory;
    private final ContractExceptionFactory  contractExceptionFactory;

    private Method                          method;
    private Object                          instance;
    private Object[]                        arguments;
    private Contract                        contract;
    private String[]                        parameterNames;
    private ContractContext                 context;

    ContextBasedContractVerifierBuilder(
            final ContractRetrieval contractRetrieval,
            final ContractSyntaxCheck contractSyntaxCheck,
            final ParameterNamesLookup parameterNamesLookup,
            final ContractSemanticCheck contractSemanticCheck,
            final ContractContextFactory contractContextFactory,
            final ContractExceptionFactory contractExceptionFactory) {
        this.contractRetrieval = requireNonNull(contractRetrieval);
        this.contractSyntaxCheck = requireNonNull(contractSyntaxCheck);
        this.parameterNamesLookup = requireNonNull(parameterNamesLookup);
        this.contractSemanticCheck = requireNonNull(contractSemanticCheck);
        this.contractContextFactory = requireNonNull(contractContextFactory);
        this.contractExceptionFactory = requireNonNull(contractExceptionFactory);
    }

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
        if (requiresContractRetrieval()) {
            retrieveContract();
        }

        validateContractSyntax();

        if (requiresParameterNamesLookup()) {
            lookupParameterNames();
        }

        validateContractSemantic();

        if (requiresContractContext()) {
            createContractContext();
        }

        return new ContextBasedContractVerifier(contract, context, contractExceptionFactory);
    }

    private boolean requiresContractRetrieval() {
        return null == contract;
    }

    private void retrieveContract() {
        contract = contractRetrieval.retrieveContract(method);
    }

    private void validateContractSyntax() {
        contractSyntaxCheck.validate(contract);
    }

    private boolean requiresParameterNamesLookup() {
        return null == parameterNames;
    }

    private void lookupParameterNames() {
        parameterNames = parameterNamesLookup.lookupParameterNames(method);
    }

    private void validateContractSemantic() {
        contractSemanticCheck.validate(contract, parameterNames);
    }

    private boolean requiresContractContext() {
        return null == context;
    }

    private void createContractContext() {
        context = contractContextFactory.createContext(instance, arguments, parameterNames);
    }

}
