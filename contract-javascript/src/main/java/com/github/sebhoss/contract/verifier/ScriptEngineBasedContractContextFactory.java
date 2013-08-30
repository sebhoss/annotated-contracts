/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;
import javax.script.ScriptEngine;

import com.github.sebhoss.contract.annotation.Clause;

public final class ScriptEngineBasedContractContextFactory implements ContractContextFactory {

    private final ScriptEngine engine;

    @Inject
    public ScriptEngineBasedContractContextFactory(final ScriptEngine engine) {
        this.engine = engine;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        for (int index = 0; index < arguments.length; index++) {
            engine.put(parameterNames[index], arguments[index]);
        }
        engine.put(Clause.THIS, instance);

        return new ScriptEngineContractContext(engine);
    }

}
