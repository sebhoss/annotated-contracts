/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public final class SyntaxCheckModule extends AbstractModule {

    @Override
    protected void configure() {
        final Multibinder<ContractSyntaxCheck> syntaxCheckBinder = Multibinder.newSetBinder(binder(),
                ContractSyntaxCheck.class);
        syntaxCheckBinder.addBinding().to(NoOpSyntaxCheck.class);

        this.bind(ContractSyntaxCheck.class).to(DelegatingContractSyntaxCheck.class);
    }

}
