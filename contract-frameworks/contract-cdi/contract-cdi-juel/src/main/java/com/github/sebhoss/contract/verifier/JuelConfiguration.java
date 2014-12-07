/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.contract.annotation.JUEL;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configures a JUEL-based contract validation.
 */
public class JuelConfiguration {

    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    ContractContextFactory contextFactory(final @JUEL ContractContextFactory juelFactory) {
        return juelFactory;
    }

}
