/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.verifier.impl.ContractCheckModule;
import com.github.sebhoss.contract.verifier.impl.ContractExceptionModule;
import com.github.sebhoss.contract.verifier.impl.ContractRetrievalModule;
import com.github.sebhoss.contract.verifier.impl.ContractSemanticCheckModule;
import com.github.sebhoss.contract.verifier.impl.InterceptorModule;
import com.github.sebhoss.contract.verifier.impl.SyntaxCheckModule;
import com.google.inject.AbstractModule;

public final class ContractModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ContractExceptionModule());
        install(new ContractRetrievalModule());
        install(new ContractSemanticCheckModule());
        install(new SyntaxCheckModule());
        install(new ContractCheckModule());
        install(new InterceptorModule());
    }

}
