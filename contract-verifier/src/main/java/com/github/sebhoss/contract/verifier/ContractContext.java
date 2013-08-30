/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Clause;

/**
 * A contract context encapsulates most likely a method invocation and therefore knows about the parameter-names and
 * -values. It is used by an instance of {@link ContractVerifier}.
 */
public interface ContractContext {

    /**
     * @param invocationResult
     *            The result of the encapsulated method invocation.
     */
    void setInvocationResult(Object invocationResult);

    /**
     * @param clause
     *            The clause to check against.
     * @return <code>true</code> if this context is in violation with the given clause, <code>false</code> otherwise.
     */
    boolean isInViolationWith(Clause clause);

}
