/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import javax.inject.Inject;

import ch.qos.cal10n.IMessageConveyor;

import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.nullanalysis.Nullsafe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the {@link ContractExceptionFactory}.
 */
@Configuration
public class ContractExceptionFactoryConfiguration {

    @Inject
    // @Nullable required for FindBugs analysis
    IMessageConveyor messages;

    /**
     * @return A reflection-based {@link ContractExceptionFactory}.
     */
    @Bean
    public ContractExceptionFactory reflectionBasedExceptionFactory() {
        return new ReflectionBasedContractExceptionFactory(Nullsafe.nullsafe(messages));
    }

}
