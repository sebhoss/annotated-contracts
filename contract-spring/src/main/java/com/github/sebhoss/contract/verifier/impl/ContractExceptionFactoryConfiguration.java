/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.cal10n.IMessageConveyor;

import com.github.sebhoss.contract.verifier.ContractExceptionFactory;

/**
 * Configures the {@link ContractExceptionFactory}.
 */
@Configuration
public class ContractExceptionFactoryConfiguration {

    @Inject
    // @Nullable required for FindBugs analysis
    @Nullable
    IMessageConveyor messages;

    /**
     * @return A reflection-based {@link ContractExceptionFactory}.
     */
    @Bean
    public ContractExceptionFactory reflectionBasedExceptionFactory() {
        return new ReflectionBasedContractExceptionFactory(messages);
    }

}
