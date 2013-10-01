/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.configuration.DefaultMVELConfiguration;

/**
 * Simple Spring configuration which imports the default MVEL configuration to enable annotation-based contracts and
 * creates an instance of the domain model class.
 */
@Configuration
@Import(DefaultMVELConfiguration.class)
public class CompanySpringConfiguration {

    /**
     * @return A new insurance company, protected by contracts.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public InsuranceCompany company() {
        return new SpringMVELBasedInsuranceCompany();
    }

}
