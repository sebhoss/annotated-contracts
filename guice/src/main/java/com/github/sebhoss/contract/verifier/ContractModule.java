/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier;

import org.aopalliance.intercept.MethodInterceptor;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.interceptor.ContractInterceptor;
import com.github.sebhoss.contract.verifier.impl.ContractCheckModule;
import com.github.sebhoss.contract.verifier.impl.ContractExceptionModule;
import com.github.sebhoss.contract.verifier.impl.ContractRetrievalModule;
import com.github.sebhoss.contract.verifier.impl.ContractSemanticCheckModule;
import com.github.sebhoss.contract.verifier.impl.SyntaxCheckModule;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public final class ContractModule extends AbstractModule {

    @Override
    protected void configure() {
        this.install(new ContractExceptionModule());
        this.install(new ContractRetrievalModule());
        this.install(new ContractSemanticCheckModule());
        this.install(new SyntaxCheckModule());
        this.install(new ContractCheckModule());

        final MethodInterceptor interceptor = new ContractInterceptor();
        this.requestInjection(interceptor);
        this.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Contract.class), interceptor);
    }

}
