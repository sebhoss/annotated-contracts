/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.lifecycle.InterceptorLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

/**
 * Interceptor for @Contract annotated method.
 */
@Interceptor
@Contract
public class ContractInterceptor {

    private final ContractVerifierFactory contractVerifierFactory;

    /**
     * @param contractVerifierFactory
     *            Set the verifier factory to use.
     */
    @Inject
    public ContractInterceptor(final ContractVerifierFactory contractVerifierFactory) {
        this.contractVerifierFactory = contractVerifierFactory;
    }

    /**
     * @param context
     *            The current invocation context.
     * @return The method invocation result.
     * @throws Throwable
     *             In case the method invocation throws.
     */
    @AroundInvoke
    public Object checkContract(final InvocationContext context) throws Throwable {
        final ContractLifecycle lifecycle = new InterceptorLifecycle(context, contractVerifierFactory);

        return lifecycle.performLifecycle();
    }

}
