/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;
import javax.script.ScriptEngine;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractContextFactory;

public final class ScriptEngineBasedContractContextFactory implements ContractContextFactory {

    private final ScriptEngine engine;

    @Inject
    public ScriptEngineBasedContractContextFactory(final ScriptEngine engine) {
        this.engine = engine;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        for (int index = 0; index < arguments.length; index++) {
            this.engine.put(parameterNames[index], arguments[index]);
        }
        this.engine.put(Clause.THIS, instance);

        return new ScriptEngineContractContext(this.engine);
    }

}
