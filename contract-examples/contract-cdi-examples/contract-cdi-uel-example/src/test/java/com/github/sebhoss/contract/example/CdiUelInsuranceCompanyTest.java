/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import javax.inject.Inject;

import com.github.sebhoss.contract.annotation.UEL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;

/**
 * Test cases for a UEL {@link InsuranceCompany}.
 */
@RunWith(PaxExam.class)
@Ignore
public class CdiUelInsuranceCompanyTest extends InsuranceCompanyTest {

    @Inject
    @UEL
    private InsuranceCompany injectedCompany;

    /**
     * Bind the injected insurance company into the test
     */
    @Before
    public void bind() {
        setInsuranceCompany(injectedCompany);
    }

}
