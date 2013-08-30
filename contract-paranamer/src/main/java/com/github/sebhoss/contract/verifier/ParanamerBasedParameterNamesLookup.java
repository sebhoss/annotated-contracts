/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.thoughtworks.paranamer.Paranamer;

/**
 * Parameters-Names lookup based on ThoughtWorks Paranamer.
 * 
 * @see <a href="http://paranamer.codehaus.org/">http://paranamer.codehaus.org/</a>
 */
public final class ParanamerBasedParameterNamesLookup implements ParameterNamesLookup {

    private final Paranamer namer;

    /**
     * @param namer
     *            The {@link Paranamer} to use.
     */
    @Inject
    public ParanamerBasedParameterNamesLookup(final Paranamer namer) {
        this.namer = namer;
    }

    @Override
    public String[] lookupParameterNames(final Method method) {
        final String[] lookupParameterNames = namer.lookupParameterNames(method);

        if (lookupParameterNames == null) {
            throw new NullPointerException("could not look up parameters"); //$NON-NLS-1$
        }

        return lookupParameterNames;
    }

}
