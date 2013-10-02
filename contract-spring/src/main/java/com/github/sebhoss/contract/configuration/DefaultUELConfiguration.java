/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.configuration;

import com.github.sebhoss.contract.verifier.ContractConfiguration;
import com.github.sebhoss.contract.verifier.ParameterNamesLookupConfiguration;
import com.github.sebhoss.contract.verifier.UelConfiguration;
import com.github.sebhoss.contract.verifier.impl.ErrorMessageConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Default configuration for UEL-based contracts.
 */
@Configuration
@Import({ AspectConfiguration.class, ParameterNamesLookupConfiguration.class, UelConfiguration.class,
        ContractConfiguration.class, ErrorMessageConfiguration.class })
public class DefaultUELConfiguration {

    // Meta-configuration

}