/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;

/**
 * Performs no semantic check on a contract.
 */
public final class NoOpSemanticCheck implements ContractSemanticCheck {

    @Override
    public void validate(final Contract contract, final String[] parameterNames) {
        // No operation required!
    }

}
