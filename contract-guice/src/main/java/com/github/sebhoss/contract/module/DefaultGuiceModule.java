/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
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
