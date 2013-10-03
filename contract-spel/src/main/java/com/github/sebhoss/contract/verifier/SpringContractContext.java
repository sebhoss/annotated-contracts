/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.SpEL;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;

/**
 * Spring-based implementation of the {@link ContractContext}.
 */
@SpEL
public class SpringContractContext implements ContractContext {

    private final ExpressionParser  parser;
    private final EvaluationContext context;

    SpringContractContext(final ExpressionParser parser, final EvaluationContext context) {
        this.parser = parser;
        this.context = context;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        context.setVariable(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Expression expression = parser.parseExpression(clause.value());

        return Boolean.FALSE.equals(expression.getValue(context, Boolean.class));
    }

}
