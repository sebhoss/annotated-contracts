/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * TODO: Write documentation!
 * 
 */
public final class NoOpSyntaxCheck implements ContractSyntaxCheck {

    @Override
    public void validate(final Contract contract) {
        // No operation required!
    }

}
