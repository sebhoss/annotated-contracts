/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * Guice module which configures the semantic contract checks. Additional semantic checks can be provided by declaring a
 * new Guice module which binds to {@link ContractSemanticCheck}.
 * 
 * @see <a href="https://code.google.com/p/google-guice/wiki/Multibindings">Guice Multibindings</a>
 */
public final class ContractSemanticCheckModule extends AbstractModule {

    @Override
    protected void configure() {
        final Multibinder<ContractSemanticCheck> syntaxCheckBinder = Multibinder.newSetBinder(binder(),
                ContractSemanticCheck.class);
        syntaxCheckBinder.addBinding().to(NoOpSemanticCheck.class);

        this.bind(ContractSemanticCheck.class).to(DelegatingContractSemanticCheck.class);
    }

}
