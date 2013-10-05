/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Clause;

/**
 * Creates new exceptions in case a breach of contract occurred.
 */
public interface ContractExceptionFactory {

    /**
     * @param clause
     *            The contract clause which was breached.
     * @return A suitable exception signaling the breach of contract.
     */
    RuntimeException breachOfContract(Clause clause);

}
