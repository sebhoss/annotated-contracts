package com.github.sebhoss.contract.lifecycle;

import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

/**
 * Performs the contract validation lifecycle. Contains the following phases:
 * <ul>
 * <li>Create Verifier</li>
 * <li>Precondition Check</li>
 * <li>Method execution</li>
 * <li>Postcondition Check</li>
 * <li>Return method execution result</li>
 * </ul>
 */
public abstract class ContractLifecycle {

    protected final ContractVerifierFactory contractVerifierFactory;

    protected ContractLifecycle(final ContractVerifierFactory contractVerifierFactory) {
        this.contractVerifierFactory = contractVerifierFactory;
    }

    /**
     * @return The return value of the encapsulated method call.
     * @throws Throwable
     *             In case the method executed throws.
     */
    public final Object performLifecycle() throws Throwable {
        final ContractVerifier contractVerifier = createVerifier();

        if (contractVerifier.hasPreconditions()) {
            contractVerifier.verifyPreconditions();
        }

        final Object result = executeMethod();

        if (contractVerifier.hasPostconditions()) {
            contractVerifier.verifyPostconditions(result);
        }

        return result;
    }

    protected abstract ContractVerifier createVerifier();

    protected abstract Object executeMethod() throws Throwable;

}
