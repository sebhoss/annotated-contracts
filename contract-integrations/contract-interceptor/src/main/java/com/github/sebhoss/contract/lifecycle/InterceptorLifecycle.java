/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.lifecycle;

import javax.interceptor.InvocationContext;

import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory.ContractVerifierBuilder;

/**
 * CDI intercept based implementation of the ContractLifecycle.
 */
public class InterceptorLifecycle extends ContractLifecycle {

    private final InvocationContext context;

    /**
     * @param context
     *            The invocation context to use.
     * @param contractVerifierFactory
     *            The verifier factory to use.
     */
    public InterceptorLifecycle(final InvocationContext context, final ContractVerifierFactory contractVerifierFactory) {
        super(contractVerifierFactory);
        this.context = context;
    }

    @Override
    protected ContractVerifier createVerifier() {
        final ContractVerifierBuilder builder = getContractVerifierFactory().createContractVerifier();

        builder.method(context.getMethod());
        builder.instance(context.getTarget());
        builder.arguments(context.getParameters());

        return builder.get();
    }

    @Override
    protected Object executeMethod() throws Throwable {
        return context.proceed();
    }

}
