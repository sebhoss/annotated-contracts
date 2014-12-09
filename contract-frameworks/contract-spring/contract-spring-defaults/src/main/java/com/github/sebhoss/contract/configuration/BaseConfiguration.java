/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.configuration;

import com.github.sebhoss.contract.verifier.ContractConfiguration;
import com.github.sebhoss.contract.verifier.ParameterNamesLookupConfiguration;
import com.github.sebhoss.contract.verifier.impl.ErrorMessageConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Base configuration for Spring-based projects. Extend this class and @Import a specific language configuration.
 */
@Configuration
@Import({ AspectConfiguration.class, ParameterNamesLookupConfiguration.class,
    ContractConfiguration.class, ErrorMessageConfiguration.class })
public abstract class BaseConfiguration {

    // Meta-configuration

}
