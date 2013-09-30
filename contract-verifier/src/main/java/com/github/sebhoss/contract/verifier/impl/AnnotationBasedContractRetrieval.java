/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractRetrieval;

/**
 * Retrieves contracts based on annotations.
 */
public final class AnnotationBasedContractRetrieval implements ContractRetrieval {

    @Override
    public Contract retrieveContract(final Method method) {
        return Nullsafe.nullsafe(method.getAnnotation(Contract.class));
    }

}
