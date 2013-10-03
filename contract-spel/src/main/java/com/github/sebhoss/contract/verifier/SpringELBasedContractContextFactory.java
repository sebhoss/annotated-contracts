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
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Spring-based implementation of the {@link ContractContextFactory}.
 */
@SpEL
public class SpringELBasedContractContextFactory implements ContractContextFactory {

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final ExpressionParser parser = new SpelExpressionParser();
        final EvaluationContext context = new StandardEvaluationContext();

        for (int index = 0; index < arguments.length; index++) {
            context.setVariable(parameterNames[index], arguments[index]);
        }
        context.setVariable(Clause.THIS, instance);

        return new SpringContractContext(parser, context);
    }

}
