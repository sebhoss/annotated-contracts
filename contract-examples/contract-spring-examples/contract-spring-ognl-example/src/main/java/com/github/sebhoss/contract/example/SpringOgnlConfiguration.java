/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import com.github.sebhoss.contract.configuration.DefaultOGNLConfiguration;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Simple Spring configuration which imports the default OGNL configuration to enable annotation-based contracts and
 * creates an instance of the domain model class.
 */
@Configuration
@Import(DefaultOGNLConfiguration.class)
public class SpringOgnlConfiguration {

    /**
     * @return A new insurance company, protected by contracts.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public InsuranceCompany company() {
        return new OgnlSpELInsuranceCompany();
    }

}
