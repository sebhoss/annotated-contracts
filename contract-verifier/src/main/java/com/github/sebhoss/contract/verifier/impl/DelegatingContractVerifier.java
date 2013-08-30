/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.ContractVerifier;

/**
 * TODO: Write documentation!
 */
public final class DelegatingContractVerifier implements ContractVerifier {

    private final Contract                 contract;

    private final ContractContext          context;

    private final ContractExceptionFactory exceptionFactory;

    /**
     * @param contract
     *            TODO: Write documentation!
     * @param context
     *            TODO: Write documentation!
     * @param exceptionFactory
     *            TODO: Write documentation!
     */
    public DelegatingContractVerifier(final Contract contract, final ContractContext context,
            final ContractExceptionFactory exceptionFactory) {
        this.contract = contract;
        this.context = context;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public boolean hasPreconditions() {
        return contract.preconditions().length > 0;
    }

    @Override
    public boolean hasPostconditions() {
        return contract.postconditions().length > 0;
    }

    @Override
    public void verifyPreconditions() {
        checkClauses(contract.preconditions());
    }

    @Override
    public void verifyPostconditions(final Object invocationResult) {
        context.setInvocationResult(invocationResult);

        checkClauses(contract.postconditions());
    }

    @SuppressWarnings(CompilerWarnings.NULL)
    private void checkClauses(final Clause[] clauses) {
        for (final Clause clause : clauses) {
            if (context.isInViolationWith(clause)) {
                throw exceptionFactory.breachOfContract(clause);
            }
        }
    }
}
