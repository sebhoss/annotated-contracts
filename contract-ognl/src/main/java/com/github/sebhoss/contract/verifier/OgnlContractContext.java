/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.OGNL;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * OGNL-based implementation of the {@link ContractContext}.
 */
@OGNL
public final class OgnlContractContext implements ContractContext {

    private final OgnlContext ognlContext;

    /**
     * @param ognlContext
     *            The backing OGNL context to use.
     */
    @Inject
    public OgnlContractContext(final OgnlContext ognlContext) {
        this.ognlContext = ognlContext;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        ognlContext.put(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        Object contractValidated;

        try {
            final Object expression = Ognl.parseExpression(clause.value());
            contractValidated = Ognl.getValue(expression, ognlContext, ognlContext.getCurrentObject());
        } catch (final OgnlException exception) {
            throw new ContractContextException(exception);
        }

        return Boolean.FALSE.equals(contractValidated);
    }

}
