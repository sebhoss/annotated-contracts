package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;

/**
 * TODO: Write documentation!
 * 
 */
public final class NoOpSemanticCheck implements ContractSemanticCheck {

    @Override
    public void validate(final Contract contract, final String[] parameterNames) {
        // No operation required!
    }

}
