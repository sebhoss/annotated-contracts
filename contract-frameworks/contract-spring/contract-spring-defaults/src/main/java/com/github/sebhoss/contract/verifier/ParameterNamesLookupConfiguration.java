/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configuration for the {@link ParameterNamesLookup}.
 */
@Configuration
public class ParameterNamesLookupConfiguration {

    /**
     * @return A parameter name discoverer which uses the local variable table (debug-informations) to retrieve
     *         parameter names.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ParameterNamesLookup localVariableTableLookup() {
        return new SpringBasedParameterNamesLookup(new LocalVariableTableParameterNameDiscoverer());
    }

}
