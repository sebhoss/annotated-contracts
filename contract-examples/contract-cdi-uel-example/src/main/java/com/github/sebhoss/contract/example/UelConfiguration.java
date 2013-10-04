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
import com.github.sebhoss.contract.annotation.UEL;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
class UelConfiguration {

    @Produces
    @Default
    ContractContextFactory contextFactory(final @UEL ContractContextFactory uelFactory) {
        return uelFactory;
    }

    // TODO: We only need this for pax-exam
    @Produces
    @Default
    Set<Service> services() {
        return Nullsafe.nullsafe(ImmutableSet.<Service> of());
    }

}