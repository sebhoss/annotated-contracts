/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

/**
 * Creates instances of a {@link ContractContext}.
 */
public interface ContractContextFactory {

    /**
     * @param instance
     *            The called instance.
     * @param arguments
     *            The given parameters.
     * @param parameterNames
     *            The given parameter names.
     * @return A context which validates a contract with the given inputs.
     */
    ContractContext createContext(Object instance, Object[] arguments, String[] parameterNames);

}
