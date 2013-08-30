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
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * Delegates syntax checks to a set of encapsulated syntax check instances.
 */
public final class DelegatingContractSyntaxCheck implements ContractSyntaxCheck {

    private final Set<ContractSyntaxCheck> checks;

    /**
     * @param checks
     *            The syntax checks to perform.
     */
    @Inject
    public DelegatingContractSyntaxCheck(final Set<ContractSyntaxCheck> checks) {
        this.checks = checks;
    }

    @Override
    public void validate(final Contract contract) {
        for (final ContractSyntaxCheck check : checks) {
            check.validate(contract);
        }
    }

}
