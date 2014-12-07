/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configuration for a MVEL based {@link ContractContextFactory}.
 */
@Configuration
public class MvelConfiguration {

    /**
     * @return A MVEL based {@link ContractContextFactory}.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractContextFactory contractContextFactory() {
        return new MVELBasedContractContextFactory();
    }

}
