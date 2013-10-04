/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import javax.annotation.Nullable;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test cases for the {@link InsuranceCompany}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.NULL, CompilerWarnings.RESOURCE })
public class SpringJUELInsuranceCompanyTest {

    /** Catches expected exceptions */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Nullable
    private InsuranceCompany insurance;

    /**
     * Creates a new insurance company for each test.
     */
    @Before
    public void createCompany() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(CompanySpringConfiguration.class);
        insurance = context.getBean(InsuranceCompany.class);
    }

    /**
     * Ensures that the insurance company can calculate payouts based on a positive damage input.
     */
    @Test
    public void shouldAcceptPositiveDamages() {
        final double result = insurance.calculateCover(10);

        Assert.assertEquals(5.0, result, 0d);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on negative input.
     */
    @Test
    public void shouldNotAcceptNegativeDamages() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Reported damage must be positive!");

        insurance.calculateCover(-10);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on high inputs.
     */
    @Test
    public void shouldNotAcceptHighDamages() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("We won't pay that!");

        insurance.calculateCover(5001);
    }

    /**
     * Ensures that the insurance company cannot pay a high amount of money.
     */
    @Test
    public void shouldNotPerformHighPayout() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("We can't pay that much!");

        insurance.calculateCover(5000);
    }

}
