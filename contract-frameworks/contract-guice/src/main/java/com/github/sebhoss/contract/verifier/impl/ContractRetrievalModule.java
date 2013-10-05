/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.google.inject.AbstractModule;

/**
 * Guice module which configures the contract retrieval.
 */
public final class ContractRetrievalModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ContractRetrieval.class).to(AnnotationBasedContractRetrieval.class);
    }

}