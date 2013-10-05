/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Guice module which configures a JavaScript-based {@link ScriptEngine} for contract clause validation.
 */
public class JavaScriptModule extends ScriptEngineModule {

    @Override
    protected void configure() {
        this.bind(ScriptEngine.class).toInstance(new ScriptEngineManager().getEngineByName("JavaScript")); //$NON-NLS-1$

        super.configure();
    }

}
