/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import javax.inject.Inject;

import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ParameterNamesLookupConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the {@link ContractVerifierFactory}.
 */
@Configuration
public class ContractVerifierConfiguration {

    @Inject
    // @Nullable required for FindBugs analysis
    ContractRetrievalConfiguration        retrieval;

    @Inject
    // @Nullable required for FindBugs analysis
    ContractSyntaxCheckConfiguration      syntaxCheck;

    @Inject
    // @Nullable required for FindBugs analysis
    ParameterNamesLookupConfiguration     namesLookup;

    @Inject
    // @Nullable required for FindBugs analysis
    ContractSemanticCheckConfiguration    semanticCheck;

    @Inject
    // @Nullable required for FindBugs analysis
    ContractContextFactory                contextFactory;

    @Inject
    // @Nullable required for FindBugs analysis
    ContractExceptionFactoryConfiguration exceptionFactory;

    /**
     * @return A context-based {@link ContractVerifierFactory}.
     */
    @Bean
    public ContractVerifierFactory contextBasedFactory() {
        return new ContextBasedContractVerifierFactory(retrieval.annotationBasedRetrieval(),
                syntaxCheck.noSyntaxChecks(), namesLookup.localVariableTableLookup(), semanticCheck.noSemanticChecks(),
                contextFactory, exceptionFactory.reflectionBasedExceptionFactory());
    }

}
