/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.verifier.impl.ContractVerifierModule;
import com.github.sebhoss.contract.verifier.impl.ContractExceptionModule;
import com.github.sebhoss.contract.verifier.impl.ContractRetrievalModule;
import com.github.sebhoss.contract.verifier.impl.ContractSemanticCheckModule;
import com.github.sebhoss.contract.verifier.impl.ContractInterceptorModule;
import com.github.sebhoss.contract.verifier.impl.ContractSyntaxCheckModule;
import com.google.inject.AbstractModule;

/**
 * Guice module which configures all injections around contracts.
 */
public final class ContractModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ContractExceptionModule());
        install(new ContractRetrievalModule());
        install(new ContractSemanticCheckModule());
        install(new ContractSyntaxCheckModule());
        install(new ContractVerifierModule());
        install(new ContractInterceptorModule());
    }

}
