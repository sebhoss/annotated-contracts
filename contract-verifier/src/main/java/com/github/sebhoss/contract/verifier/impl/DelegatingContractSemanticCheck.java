package com.github.sebhoss.contract.verifier.impl;

import java.util.Set;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;

/**
 * TODO: Write documentation!
 * 
 */
public final class DelegatingContractSemanticCheck implements ContractSemanticCheck {

    private final Set<ContractSemanticCheck> checks;

    /**
     * @param checks
     *            TODO: Write documentation!
     */
    @Inject
    public DelegatingContractSemanticCheck(final Set<ContractSemanticCheck> checks) {
        this.checks = checks;
    }

    @Override
    public void validate(final Contract contract, final String[] parameterNames) {
        for (final ContractSemanticCheck check : this.checks) {
            check.validate(contract, parameterNames);
        }
    }

}
