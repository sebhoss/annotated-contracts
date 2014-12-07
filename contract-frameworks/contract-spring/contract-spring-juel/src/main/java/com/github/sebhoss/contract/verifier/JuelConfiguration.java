/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.el.ExpressionFactory;

import com.github.sebhoss.nullanalysis.Nullsafe;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configuration for a JUEL based {@link ContractContextFactory}.
 */
@Configuration
public class JuelConfiguration {

    /**
     * @return A JUEL based {@link ContractContextFactory}.
     */
    @Bean
    public ContractContextFactory contractContextFactory() {
        return new JuelBasedContractContextFactory(expressionFactory());
    }

    /**
     * @return A new ExpressionFactory.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ExpressionFactory expressionFactory() {
        return Nullsafe.nullsafe(ExpressionFactory.newInstance());
    }

}
