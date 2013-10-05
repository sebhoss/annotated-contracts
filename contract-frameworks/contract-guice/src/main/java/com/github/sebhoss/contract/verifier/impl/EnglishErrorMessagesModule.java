/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.Locale;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

import com.google.inject.AbstractModule;

/**
 * Binds an implementation of the {@link IMessageConveyor} to the English language.
 */
public class EnglishErrorMessagesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IMessageConveyor.class).toInstance(new MessageConveyor(Locale.ENGLISH));
    }

}
