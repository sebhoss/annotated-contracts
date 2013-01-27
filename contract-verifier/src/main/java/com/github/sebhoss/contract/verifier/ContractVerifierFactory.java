/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

/**
 * TODO: Write documentation!
 * 
 */
public interface ContractVerifierFactory {

    /**
     * @param targetObject
     * @param calledMethod
     * @param givenArguments
     * @return TODO: Write documentation!
     */
    ContractVerifier createContractVerifier(Object targetObject, Method calledMethod, Object[] givenArguments);

}
