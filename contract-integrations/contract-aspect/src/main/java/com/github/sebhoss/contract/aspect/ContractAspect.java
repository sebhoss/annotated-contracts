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
package com.github.sebhoss.contract.aspect;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.lifecycle.ContractLifecycle;
import com.github.sebhoss.contract.lifecycle.AspectContractLifecycle;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * AspectJ aspect which verifies a method contract.
 */
@Aspect
public final class ContractAspect {

    private final ContractVerifierFactory contractVerifierFactory;

    /**
     * @param contractVerifierFactory
     *            The factory to use.
     */
    public ContractAspect(final ContractVerifierFactory contractVerifierFactory) {
        this.contractVerifierFactory = contractVerifierFactory;
    }

    /**
     * @param pjp
     *            The captured join point.
     * @param contract
     *            The captured contract
     * @return The captured method invocation result
     * @throws Throwable
     *             In case the join point could not proceed.
     */
    @Around("@annotation(contract)")
    public Object verifyContract(final ProceedingJoinPoint pjp, final Contract contract) throws Throwable {
        final ContractLifecycle lifecycle = new AspectContractLifecycle(pjp, contract, contractVerifierFactory);

        return lifecycle.performLifecycle();
    }

}
