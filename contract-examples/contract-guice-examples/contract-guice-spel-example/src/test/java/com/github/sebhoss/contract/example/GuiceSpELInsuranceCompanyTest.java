/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.Before;

/**
 * Test cases for a SpEL {@link InsuranceCompany}.
 */
public class GuiceSpELInsuranceCompanyTest extends InsuranceCompanyTest {

    /**
     * Creates a new InsuranceCompany for each test.
     */
    @Before
    public void createCompany() {
        final Injector injector = Guice.createInjector(new GuiceSpELModule());
        insurance = injector.getInstance(InsuranceCompany.class);
    }

}