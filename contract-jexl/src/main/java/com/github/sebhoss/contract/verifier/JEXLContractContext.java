/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.JEXL;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;

/**
 * JEXL-based implementation of the {@link ContractContext}.
 */
@JEXL
public final class JEXLContractContext implements ContractContext {

    private final JexlContext jexlContext;
    private final JexlEngine  jexlEngine;

    JEXLContractContext(final JexlContext jexlContext, final JexlEngine jexlEngine) {
        this.jexlContext = jexlContext;
        this.jexlEngine = jexlEngine;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        jexlContext.set(Clause.RETURN + "ed", invocationResult); //$NON-NLS-1$
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Expression expression = jexlEngine.createExpression(clause.value());
        final Object contractValidated = expression.evaluate(jexlContext);

        return Boolean.FALSE.equals(contractValidated);
    }

}
