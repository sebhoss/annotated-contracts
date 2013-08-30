/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.interceptor;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

/**
 * AOP interceptor which verifies a method contract.
 */
public final class ContractInterceptor implements MethodInterceptor {

    @Inject
    private ContractVerifierFactory contractVerifierFactory;

    @SuppressWarnings(CompilerWarnings.NULL)
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final ContractVerifier contractVerifier = contractVerifierFactory.createContractVerifier(invocation.getThis(),
                invocation.getMethod(), invocation.getArguments());

        if (contractVerifier.hasPreconditions()) {
            contractVerifier.verifyPreconditions();
        }

        final Object result = invocation.proceed();

        if (contractVerifier.hasPostconditions()) {
            contractVerifier.verifyPostconditions(result);
        }

        return result;
    }

}
