/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;

/**
 * Configuration for the {@link ContractSemanticCheck}.
 */
@Configuration
public class ContractSemanticCheckConfiguration {

    /**
     * @return A simple no-op semantic check.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractSemanticCheck noSemanticChecks() {
        return new DelegatingContractSemanticCheck(new HashSet<ContractSemanticCheck>());
    }

}
