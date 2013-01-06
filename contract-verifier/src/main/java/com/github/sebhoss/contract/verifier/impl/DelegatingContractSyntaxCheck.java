/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import java.util.Set;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * TODO: Write documentation!
 * 
 */
public final class DelegatingContractSyntaxCheck implements ContractSyntaxCheck {

    private final Set<ContractSyntaxCheck> checks;

    /**
     * @param checks
     *            TODO: Write documentation!
     */
    @Inject
    public DelegatingContractSyntaxCheck(final Set<ContractSyntaxCheck> checks) {
        this.checks = checks;
    }

    @Override
    public void validate(final Contract contract) {
        for (final ContractSyntaxCheck check : this.checks) {
            check.validate(contract);
        }
    }

}
