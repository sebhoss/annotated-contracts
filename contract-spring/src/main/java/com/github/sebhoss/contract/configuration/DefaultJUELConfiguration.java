package com.github.sebhoss.contract.configuration;

import com.github.sebhoss.contract.verifier.JuelConfiguration;
import com.github.sebhoss.contract.verifier.ParameterNamesLookupConfiguration;
import com.github.sebhoss.contract.verifier.impl.ErrorMessageConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Default configuration for JEXL-based contracts.
 */
@Configuration
@Import({ AspectConfiguration.class, ParameterNamesLookupConfiguration.class, JuelConfiguration.class,
        ErrorMessageConfiguration.class })
public class DefaultJUELConfiguration {

    // Meta-configuration

}
