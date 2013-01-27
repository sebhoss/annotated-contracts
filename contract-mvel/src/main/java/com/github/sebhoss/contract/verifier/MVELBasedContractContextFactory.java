/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Clause;

public final class MVELBasedContractContextFactory implements ContractContextFactory {

    private final Map<String, Object> constants;

    @Inject
    public MVELBasedContractContextFactory(final Map<String, Object> constants) {
        this.constants = constants;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final Map<String, Object> tokens = new HashMap<>();

        for (int index = 0; index < arguments.length; index++) {
            tokens.put(parameterNames[index], arguments[index]);
        }
        tokens.put(Clause.THIS, instance);
        tokens.putAll(this.constants);

        return new MVELContractContext(tokens);
    }

}
