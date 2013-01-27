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
import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Clause;

import de.odysseus.el.util.SimpleContext;

public final class JuelBasedContractContextFactory implements ContractContextFactory {

    final ExpressionFactory expressionFactory;

    @Inject
    public JuelBasedContractContextFactory(final ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final ELContext elContext = new SimpleContext();

        for (int index = 0; index < arguments.length; index++) {
            elContext.getVariableMapper().setVariable(parameterNames[index],
                    this.expressionFactory.createValueExpression(arguments[index], arguments[index].getClass()));
        }
        elContext.getVariableMapper().setVariable(Clause.THIS,
                this.expressionFactory.createValueExpression(instance, instance.getClass()));

        return new ELContractContext(elContext, this.expressionFactory);
    }

}
