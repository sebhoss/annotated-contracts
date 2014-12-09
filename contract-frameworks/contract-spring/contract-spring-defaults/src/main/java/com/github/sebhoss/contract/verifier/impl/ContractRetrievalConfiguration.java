/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.warnings.CompilerWarnings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the {@link ContractRetrieval}.
 */
@Configuration
public class ContractRetrievalConfiguration {

    /**
     * @return A annotation based contract retrieval implementation.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractRetrieval annotationBasedRetrieval() {
        return new AnnotationBasedContractRetrieval();
    }

}
