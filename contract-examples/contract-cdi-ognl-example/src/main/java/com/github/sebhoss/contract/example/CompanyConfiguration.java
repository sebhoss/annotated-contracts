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
import com.github.sebhoss.contract.annotation.OGNL;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

/**
 * Configures a OGNL ContractContextFactory.
 */
public class CompanyConfiguration {

    /**
     * @param ognlFactory
     *            The OGNL context factory.
     * @return A new context factory.
     */
    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractContextFactory contextFactory(final @OGNL ContractContextFactory ognlFactory) {
        return ognlFactory;
    }

    /**
     * @return A set of services to manage. TODO: We only need this for pax-exam
     */
    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public Set<Service> services() {
        return Nullsafe.nullsafe(ImmutableSet.<Service> of());
    }

}
