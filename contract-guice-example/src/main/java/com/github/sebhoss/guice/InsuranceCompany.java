/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.guice;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

public class InsuranceCompany {

    private final double coverRate = 0.5d;

    @Contract(preconditions = {
            @Clause(value = "damage > 0", message = "Reported damage must be positive!", exception = IllegalStateException.class),
            @Clause("damage <= 5000") }, postconditions = { @Clause("return >= 0"), @Clause("return <= 2000") })
    public double calculateCover(final double damage) {
        return this.coverRate * damage;
    }

}
