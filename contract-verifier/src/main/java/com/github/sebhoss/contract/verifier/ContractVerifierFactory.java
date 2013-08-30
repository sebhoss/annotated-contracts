/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

/**
 * Creates {@link ContractVerifier}s.
 */
public interface ContractVerifierFactory {

    /**
     * @param targetObject
     *            The calling instance.
     * @param calledMethod
     *            The called method on the instance.
     * @param givenArguments
     *            The given method arguments
     * @return A suitable contract verifier for the method invocation.
     */
    ContractVerifier createContractVerifier(Object targetObject, Method calledMethod, Object[] givenArguments);

}
