/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;

public final class DelegatingContractVerifier implements ContractVerifier {

    private final Contract                 contract;

    private final ContractContext          context;

    private final ContractExceptionFactory exceptionFactory;

    public DelegatingContractVerifier(final Contract contract, final ContractContext context,
            final ContractExceptionFactory exceptionFactory) {
        this.contract = contract;
        this.context = context;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public boolean hasPreconditions() {
        return this.contract.preconditions().length > 0;
    }

    @Override
    public boolean hasPostconditions() {
        return this.contract.postconditions().length > 0;
    }

    @Override
    public void verifyPreconditions() {
        this.checkClauses(this.contract.preconditions());
    }

    @Override
    public void verifyPostconditions(final Object invocationResult) {
        this.context.setInvocationResult(invocationResult);

        this.checkClauses(this.contract.postconditions());
    }

    private void checkClauses(final Clause[] clauses) {
        for (int index = 0; index < clauses.length; index++) {
            final Clause clause = clauses[index];

            if (this.context.isInViolationWith(clause)) {
                throw this.exceptionFactory.breachOfContract(clause);
            }
        }
    }

}
