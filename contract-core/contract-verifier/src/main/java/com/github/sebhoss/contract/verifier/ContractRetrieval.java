/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import com.github.sebhoss.contract.annotation.Contract;

/**
 * Retrieves the contract definition from a method declaration. Note that contract do not have to be defined by
 * annotations necessarily.
 */
public interface ContractRetrieval {

    /**
     * @param method
     *            The method to create a contract for.
     * @return A suitable contract for the given method.
     */
    Contract retrieveContract(Method method);

}
