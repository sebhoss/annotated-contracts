/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import ognl.OgnlContext;

import com.github.sebhoss.contract.annotation.Clause;

public final class OgnlBasedContractContextFactory implements ContractContextFactory {

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final OgnlContext ognlContext = new OgnlContext();

        for (int index = 0; index < arguments.length; index++) {
            ognlContext.put(parameterNames[index], arguments[index]);
        }
        ognlContext.put(Clause.THIS, instance);

        return new OgnlContractContext(ognlContext);
    }

}
