/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractContext;

public final class JEXLContractContext implements ContractContext {

    private final JexlContext jexlContext;
    private final JexlEngine  jexlEngine;

    @Inject
    public JEXLContractContext(final JexlContext jexlContext, final JexlEngine jexlEngine) {
        this.jexlContext = jexlContext;
        this.jexlEngine = jexlEngine;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        this.jexlContext.set(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Expression expression = this.jexlEngine.createExpression(clause.value());
        final Object contractValidated = expression.evaluate(this.jexlContext);

        return Boolean.TRUE.equals(contractValidated);
    }

}
