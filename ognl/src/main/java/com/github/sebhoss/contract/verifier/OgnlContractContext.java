/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractContextException;

public final class OgnlContractContext implements ContractContext {

    private final OgnlContext ognlContext;

    @Inject
    public OgnlContractContext(final OgnlContext ognlContext) {
        this.ognlContext = ognlContext;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        this.ognlContext.put(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        Object contractValidated;

        try {
            contractValidated = Ognl.getValue(clause.value(), this.ognlContext, this.ognlContext.getRoot());
        } catch (final OgnlException exception) {
            throw new ContractContextException(exception);
        }

        return Boolean.TRUE.equals(contractValidated);
    }

}
