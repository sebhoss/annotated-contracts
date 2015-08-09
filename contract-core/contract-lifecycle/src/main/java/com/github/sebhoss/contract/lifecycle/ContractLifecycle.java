/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org>
 */
package com.github.sebhoss.contract.lifecycle;

import com.github.sebhoss.contract.verifier.ContractVerifier;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

/**
 * Performs the contract validation lifecycle. Contains the following phases:
 * <ol>
 * <li>Create Verifier</li>
 * <li>Precondition Check</li>
 * <li>Method execution</li>
 * <li>Postcondition Check</li>
 * <li>Return method execution result</li>
 * </ol>
 */
public abstract class ContractLifecycle {

    private final ContractVerifierFactory contractVerifierFactory;

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

    protected final ContractVerifierFactory getContractVerifierFactory() {
        return contractVerifierFactory;
    }

}
