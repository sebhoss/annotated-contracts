/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.OGNL;

import ognl.OgnlContext;

/**
 * OGNL-based implementation of the {@link ContractContextFactory}.
 */
@OGNL
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
