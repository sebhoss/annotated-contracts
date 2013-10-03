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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.common.annotation.Nullsafe;
import com.github.sebhoss.contract.annotation.JavaScript;
import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

/**
 * Configures a JavaScript ContractContextFactory.
 */
public class CompanyConfiguration {

    /**
     * @return A new script engine.
     */
    @Produces
    @Default
    @SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
    public ScriptEngine scriptEngine() {
        return new ScriptEngineManager().getEngineByName("JavaScript"); //$NON-NLS-1$
    }

    /**
     * @param javaScriptFactory
     *            The JavaScript context factory.
     * @return A new context factory.
     */
    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractContextFactory contextFactory(final @JavaScript ContractContextFactory javaScriptFactory) {
        return javaScriptFactory;
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
