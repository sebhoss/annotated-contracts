/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;

import com.github.sebhoss.contract.annotation.Clause;

/**
 * JEXL-based implementation of the {@link ContractContext}.
 */
public final class JEXLContractContext implements ContractContext {

    private final JexlContext jexlContext;
    private final JexlEngine  jexlEngine;

    /**
     * @param jexlContext
     *            The JEXL context to use.
     * @param jexlEngine
     *            The JEXL engine to use.
     */
    @Inject
    public JEXLContractContext(final JexlContext jexlContext, final JexlEngine jexlEngine) {
        this.jexlContext = jexlContext;
        this.jexlEngine = jexlEngine;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        jexlContext.set(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Expression expression = jexlEngine.createExpression(clause.value());
        final Object contractValidated = expression.evaluate(jexlContext);

        return Boolean.TRUE.equals(contractValidated);
    }

}
