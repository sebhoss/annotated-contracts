/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.github.sebhoss.contract.annotation.Clause;

public final class JEXLBasedContractContextFactory implements ContractContextFactory {

    private final JexlEngine jexlEngine;

    @Inject
    JEXLBasedContractContextFactory(final JexlEngine jexlEngine) {
        this.jexlEngine = jexlEngine;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final JexlContext context = new MapContext();

        for (int index = 0; index < arguments.length; index++) {
            context.set(parameterNames[index], arguments[index]);
        }
        context.set(Clause.THIS, instance);

        return new JEXLContractContext(context, this.jexlEngine);
    }

}
