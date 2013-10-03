/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * Configures the default syntax checks.
 */
public class DefaultSyntaxCheck {

    /**
     * @return The default syntax checks.
     */
    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public Set<ContractSyntaxCheck> syntaxChecks() {
        final Set<ContractSyntaxCheck> checks = new HashSet<>();
        checks.add(new SomeClauseRequiredContractSyntaxCheck());
        return checks;
    }

}
