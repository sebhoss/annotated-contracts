/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Contract;

/**
 * Performs a semantic check of a contract defition.
 */
public interface ContractSemanticCheck {

    /**
     * @param contract
     *            The contract to check.
     * @param parameterNames
     *            The parameter-names, most likely taken from a method declaration.
     */
    void validate(Contract contract, String[] parameterNames);

}
