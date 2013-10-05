/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.el.ExpressionFactory;

import com.google.inject.AbstractModule;

/**
 * Guice module which configures UEL for contract clause validation.
 */
public class UelModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ExpressionFactory.class).toInstance(ExpressionFactory.newInstance());
        this.bind(ContractContextFactory.class).to(UelBasedContractContextFactory.class);
    }

}
