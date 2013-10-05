/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.Locale;

import javax.enterprise.inject.Produces;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

import com.github.sebhoss.common.annotation.CompilerWarnings;

/**
 * Configures English error messages.
 */
public class EnglishErrorMessages {

    /**
     * @return Message conveyor for English messages.
     */
    @Produces
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public IMessageConveyor messages() {
        return new MessageConveyor(Locale.ENGLISH);
    }

}
