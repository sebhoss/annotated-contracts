/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import org.springframework.core.ParameterNameDiscoverer;

/**
 * Spring-based {@link ParameterNamesLookup}.
 */
public class SpringBasedParameterNamesLookup implements ParameterNamesLookup {

    private final ParameterNameDiscoverer nameDiscoverer;

    /**
     * @param nameDiscoverer
     *            The name discoverer to use.
     */
    public SpringBasedParameterNamesLookup(final ParameterNameDiscoverer nameDiscoverer) {
        this.nameDiscoverer = nameDiscoverer;
    }

    @Override
    public String[] lookupParameterNames(final Method method) {
        final String[] parameterNames = nameDiscoverer.getParameterNames(method);

        if (parameterNames == null) {
            throw new NullPointerException("Discovered parameter names are NULL"); //$NON-NLS-1$
        }

        return parameterNames;
    }

}
