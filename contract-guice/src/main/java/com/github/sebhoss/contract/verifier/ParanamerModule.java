/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.google.inject.AbstractModule;
import com.thoughtworks.paranamer.AdaptiveParanamer;
import com.thoughtworks.paranamer.Paranamer;

public class ParanamerModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Paranamer.class).toInstance(new AdaptiveParanamer());
        this.bind(ParameterNamesLookup.class).to(ParanamerBasedParameterNamesLookup.class);
    }

}
