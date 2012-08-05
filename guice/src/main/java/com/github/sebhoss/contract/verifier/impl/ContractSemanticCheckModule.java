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
