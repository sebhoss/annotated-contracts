/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import org.apache.commons.jexl2.JexlEngine;

import com.github.sebhoss.contract.annotation.JEXL;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configures a JEXL-based contract validation.
 */
@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
public class JexlConfiguration {

    @Produces
    @Default
    JexlEngine jexlEngine() {
        return new JexlEngine();
    }

    @Produces
    @Default
    ContractContextFactory contextFactory(final @JEXL ContractContextFactory jexlFactory) {
        return jexlFactory;
    }

}
