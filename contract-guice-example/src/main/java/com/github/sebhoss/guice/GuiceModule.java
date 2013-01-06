package com.github.sebhoss.guice;

import com.github.sebhoss.contract.module.DefaultGuiceModule;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        this.install(new DefaultGuiceModule());

        this.bind(InsuranceCompany.class).asEagerSingleton();
    }

}
