/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

/**
 * Example business interface, modeling a simple insurance company.
 */
public interface InsuranceCompany {

    /**
     * Calculates the cover for a given damage.
     * 
     * @param damage
     *            The reported damage.
     * @return The cover this insurance company is going to pay.
     */
    double calculateCover(double damage);

}
