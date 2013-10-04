/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.annotation.MVEL;
import com.github.sebhoss.contract.annotation.Script;

@Script
@MVEL
class JavaScriptMvelInsuranceCompany extends AbstractInsuranceCompany {

    @Contract(
            preconditions = {
                    @Clause(value = "damage > 0", message = "Reported damage must be positive!",
                            exception = IllegalStateException.class),
                    @Clause(value = "damage <= _this.maximumReportableDamage", message = "We won't pay that!",
                            exception = IllegalStateException.class) },
            postconditions = {
                    @Clause(value = "_return >= 0", message = "We won't take any more!"),
                    @Clause(value = "_return <= _this.remainingMoney", message = "We can't pay that much!") })
    @Override
    public double calculateCover(final double damage) {
        return damage * 0.5;
    }

}