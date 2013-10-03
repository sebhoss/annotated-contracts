/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.thoughtworks.paranamer.AdaptiveParanamer;
import com.thoughtworks.paranamer.Paranamer;

/**
 * Configures the default ParameterNamesLookup.
 */
public class DefaultParameterNamesLookup {

    /**
     * @param namer
     *            The paranamer to use.
     * @return Builds a paranamer based parameter names lookup.
     */
    @Produces
    @Default
    @SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
    public ParameterNamesLookup paranamerBased(final Paranamer namer) {
        return new ParanamerBasedParameterNamesLookup(namer);
    }

    /**
     * @return Builds an adaptive paranamer.
     */
    @Produces
    @Default
    @SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
    public Paranamer adaptiveParanamer() {
        return new AdaptiveParanamer();
    }

}
