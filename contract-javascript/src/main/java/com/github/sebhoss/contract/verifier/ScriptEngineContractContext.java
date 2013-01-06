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
