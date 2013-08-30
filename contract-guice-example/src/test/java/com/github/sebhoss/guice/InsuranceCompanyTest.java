/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.guice;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

@SuppressWarnings("static-method")
public class InsuranceCompanyTest {

    @Test
    public void shouldNoticePrecondition() {
        // Given
        final Injector injector = Guice.createInjector(new GuiceModule());
        final InsuranceCompany instance = injector.getInstance(InsuranceCompany.class);

        // When
        final double result = instance.calculateCover(10);

        // Then
        Assert.assertEquals(5.0, result, 0d);
    }

}
