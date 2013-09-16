/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.lifecycle.SpringContractLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

/**
 * AspectJ aspect which verifies a method contract.
 */
@Aspect
public class ContractAspect {

    private final ContractVerifierFactory contractVerifierFactory;

    ContractAspect(final ContractVerifierFactory contractVerifierFactory) {
        this.contractVerifierFactory = contractVerifierFactory;
    }

    /**
     * @param pjp
     *            The captured join point.
     * @param contract
     *            The captured contract
     * @return The captured method invocation result
     * @throws Throwable
     *             In case the join point could not proceed.
     */
    @Around("@annotation(com.github.sebhoss.contract.annotation.Contract)")
    public Object verifyContract(final ProceedingJoinPoint pjp, final Contract contract) throws Throwable {
        final ContractLifecycle lifecycle = new SpringContractLifecycle(pjp, contract, contractVerifierFactory);

        return lifecycle.performLifecycle();
    }

}
