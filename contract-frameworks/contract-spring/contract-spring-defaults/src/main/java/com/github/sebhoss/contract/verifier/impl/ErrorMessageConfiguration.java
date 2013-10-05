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

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the {@link IMessageConveyor}.
 */
@Configuration
public class ErrorMessageConfiguration {

    /**
     * @return English error messages
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public IMessageConveyor english() {
        return new MessageConveyor(Locale.ENGLISH);
    }

}
