/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.github.sebhoss.contract.verifier.impl.DelegatingContractSemanticCheck;
import com.github.sebhoss.contract.verifier.impl.NoOpSemanticCheck;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public final class ContractSemanticCheckModule extends AbstractModule {

    @Override
    protected void configure() {
        final Multibinder<ContractSemanticCheck> syntaxCheckBinder = Multibinder.newSetBinder(this.binder(),
                ContractSemanticCheck.class);
        syntaxCheckBinder.addBinding().to(NoOpSemanticCheck.class);

        this.bind(ContractSemanticCheck.class).to(DelegatingContractSemanticCheck.class);
    }

}
