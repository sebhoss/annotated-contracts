/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.verifier.impl.ContractAspectConfiguration;
import com.github.sebhoss.contract.verifier.impl.ContractExceptionFactoryConfiguration;
import com.github.sebhoss.contract.verifier.impl.ContractRetrievalConfiguration;
import com.github.sebhoss.contract.verifier.impl.ContractSemanticCheckConfiguration;
import com.github.sebhoss.contract.verifier.impl.ContractSyntaxCheckConfiguration;
import com.github.sebhoss.contract.verifier.impl.ContractVerifierConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Meta-configuration for several contract interfaces.
 */
@Configuration
@Import({ ContractExceptionFactoryConfiguration.class, ContractRetrievalConfiguration.class,
        ContractSemanticCheckConfiguration.class, ContractSyntaxCheckConfiguration.class,
        ContractVerifierConfiguration.class, ContractAspectConfiguration.class })
public class ContractConfiguration {

    // Meta-configuration

}
