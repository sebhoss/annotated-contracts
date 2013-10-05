/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.Set;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;

/**
 * Delegates semantic checks to a set of encapsulated semantic check instances.
 */
public final class DelegatingContractSemanticCheck implements ContractSemanticCheck {

    private final Set<ContractSemanticCheck> checks;

    /**
     * @param checks
     *            The semantic checks to perform.
     */
    @Inject
    public DelegatingContractSemanticCheck(final Set<ContractSemanticCheck> checks) {
        this.checks = checks;
    }

    @Override
    public void validate(final Contract contract, final String[] parameterNames) {
        for (final ContractSemanticCheck check : checks) {
            check.validate(contract, parameterNames);
        }
    }

}
