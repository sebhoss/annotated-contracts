/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.List;
import java.util.Locale;

import ch.qos.cal10n.verifier.Cal10nError;
import ch.qos.cal10n.verifier.IMessageKeyVerifier;
import ch.qos.cal10n.verifier.MessageKeyVerifier;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Test;

/**
 * Checks the properties behind {@link ExceptionFactoryErrors}.
 */
@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
public class ExceptionFactoryErrorsTest {

    /**
     * Ensures that English translations are found.
     */
    @Test
    public void shouldHaveEnglishTranslations() {
        final IMessageKeyVerifier mkv = new MessageKeyVerifier(ExceptionFactoryErrors.class);

        final List<Cal10nError> errorList = mkv.verify(Locale.ENGLISH);

        for (final Cal10nError error : errorList) {
            System.out.println(error);
        }
        Assert.assertEquals(0, errorList.size());
    }

}
