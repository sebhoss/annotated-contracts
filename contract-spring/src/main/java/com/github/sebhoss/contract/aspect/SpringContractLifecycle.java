package com.github.sebhoss.contract.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory.ContractVerifierBuilder;

final class SpringContractLifecycle extends ContractLifecycle {

    private final ProceedingJoinPoint pjp;
    private final Contract            contract;

    SpringContractLifecycle(final ProceedingJoinPoint pjp, final Contract contract,
            final ContractVerifierFactory contractVerifierFactory) {
        super(contractVerifierFactory);
        this.pjp = pjp;
        this.contract = contract;
    }

    @Override
    @SuppressWarnings(CompilerWarnings.NULL)
    protected ContractVerifier createVerifier() {
        final ContractVerifierBuilder builder = contractVerifierFactory.createContractVerifier();
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        builder.method(methodSignature.getMethod());
        builder.parameterNames(methodSignature.getParameterNames());
        builder.instance(pjp.getThis());
        builder.arguments(pjp.getArgs());
        builder.contract(contract);

        return builder.get();
    }

    @Override
    @SuppressWarnings(CompilerWarnings.NULL)
    protected Object executeMethod() throws Throwable {
        return pjp.proceed();
    }

}
