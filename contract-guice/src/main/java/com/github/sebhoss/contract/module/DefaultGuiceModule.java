package com.github.sebhoss.contract.module;

import com.github.sebhoss.contract.verifier.ContractModule;
import com.github.sebhoss.contract.verifier.JuelModule;
import com.github.sebhoss.contract.verifier.ParanamerModule;
import com.github.sebhoss.contract.verifier.impl.SyntaxCheckModule;
import com.google.inject.AbstractModule;

public class DefaultGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        this.install(new JuelModule());
        this.install(new ParanamerModule());
        this.install(new SyntaxCheckModule());
        this.install(new ContractModule());
    }

}
