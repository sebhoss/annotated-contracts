/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
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
