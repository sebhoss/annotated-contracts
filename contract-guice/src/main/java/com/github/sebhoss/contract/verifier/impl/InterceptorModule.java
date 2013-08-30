/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import org.aopalliance.intercept.MethodInterceptor;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.interceptor.ContractInterceptor;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class InterceptorModule extends AbstractModule {

    @Override
    protected void configure() {
        final MethodInterceptor interceptor = new ContractInterceptor();
        requestInjection(interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Contract.class), interceptor);
    }

}
