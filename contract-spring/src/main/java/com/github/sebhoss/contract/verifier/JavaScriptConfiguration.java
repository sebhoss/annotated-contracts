/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.common.annotation.Nullsafe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for a JavaScript based {@link ContractContextFactory}.
 */
@Configuration
public class JavaScriptConfiguration {

    /**
     * @return A JavaScript based {@link ContractContextFactory}.
     */
    @Bean
    public ContractContextFactory contractContextFactory() {
        return new ScriptEngineBasedContractContextFactory(javaScriptEngine());
    }

    /**
     * @return A JavaScript-based ScriptEngine.
     */
    @Bean
    public ScriptEngine javaScriptEngine() {
        return Nullsafe.nullsafe(scriptManager().getEngineByName("JavaScript")); //$NON-NLS-1$
    }

    /**
     * @return A new ScriptEngineManager.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ScriptEngineManager scriptManager() {
        return new ScriptEngineManager();
    }

}
