/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.github.sebhoss.contract.verifier.ParameterNamesLookup;
import com.thoughtworks.paranamer.Paranamer;

public final class ParanamerBasedParameterNamesLookup implements ParameterNamesLookup {

    private final Paranamer namer;

    @Inject
    public ParanamerBasedParameterNamesLookup(final Paranamer namer) {
        this.namer = namer;
    }

    @Override
    public String[] lookupParameterNames(final Method method) {
        return this.namer.lookupParameterNames(method);
    }

}
