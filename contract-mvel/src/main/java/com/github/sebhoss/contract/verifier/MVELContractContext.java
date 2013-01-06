/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

import java.util.Map;

import javax.inject.Inject;

import org.mvel2.MVEL;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractContext;

public final class MVELContractContext implements ContractContext {

    private final Map<String, Object> tokens;

    @Inject
    public MVELContractContext(final Map<String, Object> tokens) {
        this.tokens = tokens;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        this.tokens.put(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Boolean clauseValid = MVEL.evalToBoolean(clause.value(), this.tokens);

        return Boolean.TRUE.equals(clauseValid);
    }

}
