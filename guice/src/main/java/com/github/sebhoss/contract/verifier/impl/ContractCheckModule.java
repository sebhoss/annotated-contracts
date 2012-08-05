package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.impl.InjectableContractVerifierFactory;
import com.google.inject.AbstractModule;

public final class ContractCheckModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ContractVerifierFactory.class).to(InjectableContractVerifierFactory.class);
    }

}
