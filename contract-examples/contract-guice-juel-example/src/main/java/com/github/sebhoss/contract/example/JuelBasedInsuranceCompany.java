/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

class JuelBasedInsuranceCompany implements InsuranceCompany {

    @Contract(preconditions = {
            @Clause(value = "${damage > 0}", message = "Reported damage must be positive!", exception = IllegalStateException.class),
            @Clause("${damage <= 5000}") }, postconditions = { @Clause("${return >= 0}"), @Clause("${return <= 2000}") })
    @Override
    public double calculateCover(final double damage) {
        return damage * 0.5;
    }

}
