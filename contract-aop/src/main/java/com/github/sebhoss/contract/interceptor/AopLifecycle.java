package com.github.sebhoss.contract.interceptor;

import org.aopalliance.intercept.MethodInvocation;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory.ContractVerifierBuilder;

final class AopLifecycle extends ContractLifecycle {

    private final MethodInvocation invocation;

    AopLifecycle(final MethodInvocation invocation, final ContractVerifierFactory contractVerifierFactory) {
        super(contractVerifierFactory);
        this.invocation = invocation;
    }

    @Override
    @SuppressWarnings(CompilerWarnings.NULL)
    protected ContractVerifier createVerifier() {
        final ContractVerifierBuilder builder = contractVerifierFactory.createContractVerifier();

        builder.method(invocation.getMethod());
        builder.instance(invocation.getThis());
        builder.arguments(invocation.getArguments());

        return builder.get();
    }

    @Override
    @SuppressWarnings(CompilerWarnings.NULL)
    protected Object executeMethod() throws Throwable {
        return invocation.proceed();
    }

}
