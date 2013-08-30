/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

/**
 * Looks-up the used parameter-names on a method declaration.
 */
public interface ParameterNamesLookup {

    /**
     * @param method
     *            The method to investigate.
     * @return The found parameter-names.
     */
    String[] lookupParameterNames(Method method);

}
