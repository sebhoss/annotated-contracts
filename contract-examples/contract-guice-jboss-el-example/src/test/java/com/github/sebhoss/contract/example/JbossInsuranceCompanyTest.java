/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Test cases for the {@link InsuranceCompany}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public class JbossInsuranceCompanyTest {

    /** Catches expected exceptions */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that the insurance company can calculate payouts based on a positive damage input.
     */
    @Test
    public void shouldAcceptPositiveDamages() {
        // Given
        final Injector injector = Guice.createInjector(new CompanyModule());
        final InsuranceCompany instance = injector.getInstance(InsuranceCompany.class);

        // When
        final double result = instance.calculateCover(10);

        // Then
        Assert.assertEquals(5.0, result, 0d);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on negative input.
     */
    @Test
    public void shouldNotAcceptNegativeDamages() {
        // Given
        final Injector injector = Guice.createInjector(new CompanyModule());
        final InsuranceCompany instance = injector.getInstance(InsuranceCompany.class);

        // When
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Reported damage must be positive!");

        // Then
        instance.calculateCover(-10);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on high inputs.
     */
    @Test
    public void shouldNotAcceptHighDamages() {
        // Given
        final Injector injector = Guice.createInjector(new CompanyModule());
        final InsuranceCompany instance = injector.getInstance(InsuranceCompany.class);

        // When
        thrown.expect(IllegalArgumentException.class);

        // Then
        instance.calculateCover(5001);
    }

    /**
     * Ensures that the insurance company cannot pay a high amount of money.
     */
    @Test
    public void shouldNotPerformHighPayout() {
        // Given
        final Injector injector = Guice.createInjector(new CompanyModule());
        final InsuranceCompany instance = injector.getInstance(InsuranceCompany.class);

        // When
        thrown.expect(IllegalArgumentException.class);

        // Then
        instance.calculateCover(5000);
    }

}
