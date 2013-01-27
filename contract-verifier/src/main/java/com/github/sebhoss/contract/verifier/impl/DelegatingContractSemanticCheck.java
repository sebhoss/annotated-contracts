/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
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
