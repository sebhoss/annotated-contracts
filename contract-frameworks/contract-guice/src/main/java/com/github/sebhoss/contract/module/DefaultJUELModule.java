/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.module;

import com.github.sebhoss.contract.verifier.JuelModule;

/**
 * Guice module for the default behavior for annotated contracts. Uses JUEL and Paranamer for contract checking.
 */
public class DefaultJUELModule extends AbstractContractModule {

    @Override
    protected void configure() {
        super.configure();

        install(new JuelModule());
    }

}