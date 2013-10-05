/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Contract;

/**
 * Performs a syntax check on contracts.
 */
public interface ContractSyntaxCheck {

    /**
     * @param contract
     *            The contract to check.
     */
    void validate(Contract contract);

}
