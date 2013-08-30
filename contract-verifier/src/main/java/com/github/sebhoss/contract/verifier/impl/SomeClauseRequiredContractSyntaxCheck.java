/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * Ensures that at least of pre- or postcondition is written into a contract.
 */
public final class SomeClauseRequiredContractSyntaxCheck implements ContractSyntaxCheck {

    @Override
    public void validate(final Contract contract) {
        if (contract.preconditions().length == 0 && contract.postconditions().length == 0) {
            throw new IllegalStateException("Don't use @Contract without any Pre- or Postconditions!"); //$NON-NLS-1$
        }
    }

}
