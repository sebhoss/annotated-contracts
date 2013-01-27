/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import com.github.sebhoss.contract.annotation.Clause;

public final class ELContractContext implements ContractContext {

    private final ELContext         elContext;
    private final ExpressionFactory expressionFactory;

    public ELContractContext(final ELContext elContext, final ExpressionFactory expressionFactory) {
        this.elContext = elContext;
        this.expressionFactory = expressionFactory;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        this.elContext.getVariableMapper().setVariable(Clause.RETURN,
                this.expressionFactory.createValueExpression(invocationResult, invocationResult.getClass()));
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final String contractExpression = String.format("${%s}", clause.value()); //$NON-NLS-1$
        final ValueExpression valueExpression = this.expressionFactory.createValueExpression(this.elContext,
                contractExpression, Boolean.class);
        final Object clauseValid = valueExpression.getValue(this.elContext);

        return Boolean.FALSE.equals(clauseValid);
    }

}
