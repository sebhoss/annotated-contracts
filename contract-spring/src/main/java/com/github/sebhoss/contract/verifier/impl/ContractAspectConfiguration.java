/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import javax.annotation.Nullable;
import javax.inject.Inject;

import com.github.sebhoss.contract.aspect.ContractAspect;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the {@link ContractAspect}.
 */
@Configuration
public class ContractAspectConfiguration {

    @Inject
    // @Nullable required for FindBugs analysis
    @Nullable
    private ContractVerifierFactory verifierFactory;

    /**
     * @return A AspectJ-based contract aspect.
     */
    @Bean
    public ContractAspect contractAspect() {
        final ContractVerifierFactory nullsafeVerifierFactory = verifierFactory;

        if (nullsafeVerifierFactory != null) {
            return new ContractAspect(nullsafeVerifierFactory);
        }

        throw new IllegalStateException();
    }

}
