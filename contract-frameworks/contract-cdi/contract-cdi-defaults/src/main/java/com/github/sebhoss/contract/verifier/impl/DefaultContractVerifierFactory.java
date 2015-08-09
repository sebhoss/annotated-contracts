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
package com.github.sebhoss.contract.verifier.impl;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.contract.verifier.ContractContextFactory;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;
import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.contract.verifier.ContractSemanticCheck;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.github.sebhoss.contract.verifier.ContractVerifierFactory;
import com.github.sebhoss.contract.verifier.ParameterNamesLookup;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Configures the default ContractVerifierFactory.
 */
public class DefaultContractVerifierFactory {

    /**
     * @param contractRetrieval
     *            The contract retrieval to use.
     * @param contractSyntaxCheck
     *            The contract syntax checks to use.
     * @param parameterNamesLookup
     *            The parameter names lookup to use.
     * @param contractSemanticCheck
     *            The contract semantic checks to use.
     * @param contractContextFactory
     *            The contract context factory to use.
     * @param contractExceptionFactory
     *            The contract exception factory to use.
     * @return Builds a context based contract verifier factory.
     */
    @Produces
    @Default
    @SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
    public ContractVerifierFactory contextBased(final ContractRetrieval contractRetrieval,
            final ContractSyntaxCheck contractSyntaxCheck, final ParameterNamesLookup parameterNamesLookup,
            final ContractSemanticCheck contractSemanticCheck, final ContractContextFactory contractContextFactory,
            final ContractExceptionFactory contractExceptionFactory) {
        return new ContextBasedContractVerifierFactory(contractRetrieval, contractSyntaxCheck, parameterNamesLookup,
                contractSemanticCheck, contractContextFactory, contractExceptionFactory);
    }

}
