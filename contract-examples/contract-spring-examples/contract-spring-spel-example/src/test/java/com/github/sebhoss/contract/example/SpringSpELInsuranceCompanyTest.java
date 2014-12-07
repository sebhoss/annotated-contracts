/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import org.junit.Before;

import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for a SpEL {@link InsuranceCompany}.
 */
public class SpringSpELInsuranceCompanyTest extends InsuranceCompanyTest {

    /**
     * Creates a new insurance company for each test.
     */
    @Before
    @SuppressWarnings(CompilerWarnings.RESOURCE)
    public void createCompany() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(SpringSpELConfiguration.class);
        insurance = context.getBean(InsuranceCompany.class);
    }

}
