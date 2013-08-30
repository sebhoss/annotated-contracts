/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.contract.annotation.Contract;

/**
 * Verifies a {@link Contract}.
 */
public interface ContractVerifier {

    /**
     * @return <code>true</code> if the contract has {@link Contract#preconditions() preconditions}, <code>false</code>
     *         otherwise.
     */
    boolean hasPreconditions();

    /**
     * @return <code>true</code> if the contract has {@link Contract#postconditions() postconditions},
     *         <code>false</code> otherwise.
     */
    boolean hasPostconditions();

    /**
     * Verifies the {@link Contract#preconditions() preconditions} of a contract.
     */
    void verifyPreconditions();

    /**
     * Verifies the {@link Contract#postconditions() postconditions} of a contract under consideration of a given method
     * result.
     * 
     * @param invocationResult
     *            The result of the method call. <b>May be <code>null</code></b>. TODO: Use @Nullable annotation
     */
    void verifyPostconditions(Object invocationResult);

}
