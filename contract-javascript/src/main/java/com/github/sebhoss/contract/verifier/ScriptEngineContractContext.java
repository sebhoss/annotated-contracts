/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractContext;
import com.github.sebhoss.contract.verifier.ContractContextException;

public final class ScriptEngineContractContext implements ContractContext {

    private final ScriptEngine engine;

    @Inject
    public ScriptEngineContractContext(final ScriptEngine engine) {
        this.engine = engine;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        this.engine.put(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        Object contractValidated;

        try {
            contractValidated = this.engine.eval(clause.value());
        } catch (final ScriptException exception) {
            throw new ContractContextException(exception);
        }

        return Boolean.TRUE.equals(contractValidated);
    }

}
