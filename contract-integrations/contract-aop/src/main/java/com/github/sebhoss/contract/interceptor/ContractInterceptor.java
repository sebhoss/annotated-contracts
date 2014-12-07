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

import com.github.sebhoss.contract.lifecycle.AopLifecycle;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * AOP interceptor which verifies a method contract.
 */
public final class ContractInterceptor implements MethodInterceptor {

    @Inject
    private ContractVerifierFactory contractVerifierFactory;

    @Override
    @SuppressWarnings(CompilerWarnings.NULL)
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final ContractLifecycle lifecycle = new AopLifecycle(invocation, contractVerifierFactory);

        return lifecycle.performLifecycle();
    }

}
