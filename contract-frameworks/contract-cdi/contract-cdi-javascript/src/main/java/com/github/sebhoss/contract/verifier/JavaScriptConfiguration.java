/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.JavaScript;

/**
 * Configures a JavaScript-based contract validation.
 */
@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
public class JavaScriptConfiguration {

    @Produces
    @Default
    @SuppressWarnings(CompilerWarnings.NULL)
    ScriptEngine javaScriptEngine() {
        return new ScriptEngineManager().getEngineByName("JavaScript"); //$NON-NLS-1$
    }

    @Produces
    @Default
    ContractContextFactory contextFactory(final @JavaScript ContractContextFactory scriptFactory) {
        return scriptFactory;
    }

}
