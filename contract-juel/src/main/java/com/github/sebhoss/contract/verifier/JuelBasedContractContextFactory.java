/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Clause;

import de.odysseus.el.util.SimpleContext;

/**
 * JUEL-based implementation of the {@link ContractContextFactory}.
 */
public final class JuelBasedContractContextFactory implements ContractContextFactory {

    final ExpressionFactory expressionFactory;

    /**
     * @param expressionFactory
     *            The expression factory to use.
     */
    @Inject
    public JuelBasedContractContextFactory(final ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final ELContext elContext = new SimpleContext();

        for (int index = 0; index < arguments.length; index++) {
            elContext.getVariableMapper().setVariable(parameterNames[index],
                    expressionFactory.createValueExpression(arguments[index], arguments[index].getClass()));
        }
        elContext.getVariableMapper().setVariable(Clause.THIS,
                expressionFactory.createValueExpression(instance, instance.getClass()));

        return new ELContractContext(elContext, expressionFactory);
    }

}
