package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.contract.verifier.impl.AnnotationBasedContractRetrieval;
import com.google.inject.AbstractModule;

public final class ContractRetrievalModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ContractRetrieval.class).to(AnnotationBasedContractRetrieval.class);
    }

}
