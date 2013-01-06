/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

/**
 * TODO: Write documentation!
 * 
 */
public interface ContractVerifier {

    /**
     * @return TODO: Write documentation!
     */
    boolean hasPreconditions();

    /**
     * @return TODO: Write documentation!
     */
    boolean hasPostconditions();

    /**
     * TODO: Write documentation!
     */
    void verifyPreconditions();

    /**
     * @param invocationResult
     */
    void verifyPostconditions(Object invocationResult);

}
