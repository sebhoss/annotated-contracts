/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.util.HashMap;
import java.util.Map;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.MVEL;

/**
 * MVEL-based implementation of the {@link ContractContextFactory}.
 */
@MVEL
public final class MVELBasedContractContextFactory implements ContractContextFactory {

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final Map<String, Object> tokens = new HashMap<>();

        for (int index = 0; index < arguments.length; index++) {
            tokens.put(parameterNames[index], arguments[index]);
        }
        tokens.put("_" + Clause.THIS, instance); //$NON-NLS-1$

        return new MVELContractContext(tokens);
    }

}
