/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.util.Map;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Clause;

import org.mvel2.MVEL;

/**
 * MVEL-based implementation of the {@link ContractContext}.
 */
public final class MVELContractContext implements ContractContext {

    private final Map<String, Object> tokens;

    /**
     * @param tokens
     *            The MVEL tokens to check agains.
     */
    @Inject
    public MVELContractContext(final Map<String, Object> tokens) {
        this.tokens = tokens;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        tokens.put(Clause.RETURN + "ed", invocationResult); //$NON-NLS-1$
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Boolean clauseValid = MVEL.evalToBoolean(clause.value(), tokens);

        return Boolean.FALSE.equals(clauseValid);
    }

}
