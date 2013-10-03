/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.common.annotation.Nullsafe;
import com.github.sebhoss.contract.annotation.JEXL;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

import org.apache.commons.jexl2.JexlEngine;

/**
 * Configures a JEXL ContractContextFactory.
 */
public class CompanyConfiguration {

    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    JexlEngine jexlEngine() {
        return new JexlEngine();
    }

    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    ContractContextFactory contextFactory(final @JEXL ContractContextFactory jexlFactory) {
        return jexlFactory;
    }

    // TODO: We only need this for pax-exam
    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    Set<Service> services() {
        return Nullsafe.nullsafe(ImmutableSet.<Service> of());
    }

}
