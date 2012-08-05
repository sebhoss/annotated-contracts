package com.github.sebhoss.guice;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

public class InsuranceCompany {

    private final double coverRate = 0.5d;

    @Contract(preconditions = {
            @Clause(value = "damage > 0", message = "Reported damage must be positive!", exception = IllegalStateException.class),
            @Clause("damage <= 5000")
    },
              postconditions = {
                      @Clause("return >= 0"),
                      @Clause("return <= 2000")
              })
    public double calculateCover(final double damage) {
        return this.coverRate * damage;
    }

}
