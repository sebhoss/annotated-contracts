/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

/**
 * Abstract insurance company, used as the base for all language implementations.
 */
public abstract class AbstractInsuranceCompany implements InsuranceCompany {

    @Override
    public final double getRemainingMoney() {
        return 2000;
    }

    @Override
    public final double getMaximumReportableDamage() {
        return 5000;
    }

}
