package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.impl.ReflectionBasedContractExceptionFactory;
import com.google.inject.AbstractModule;

public final class ContractExceptionModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ContractExceptionFactory.class).to(ReflectionBasedContractExceptionFactory.class);
    }

}
