/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractRetrieval;

/**
 * TODO: Write documentation!
 * 
 */
public final class AnnotationBasedContractRetrieval implements ContractRetrieval {

    @Override
    public Contract retrieveContract(final Method method) {
        final Contract contract = method.getAnnotation(Contract.class);

        if (contract == null) {
            throw new IllegalStateException();
        }

        return contract;
    }

}
