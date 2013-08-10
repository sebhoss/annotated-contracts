/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
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
