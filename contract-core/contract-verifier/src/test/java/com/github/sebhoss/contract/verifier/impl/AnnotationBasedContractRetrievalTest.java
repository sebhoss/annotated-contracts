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

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.MethodFactory;
import com.github.sebhoss.contract.verifier.ContractRetrieval;
import com.github.sebhoss.warnings.CompilerWarnings;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;

/**
 * Test cases for the {@link AnnotationBasedContractRetrieval}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class AnnotationBasedContractRetrievalTest {

    /** Catches expected exceptions in a test case. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that a {@link NullPointerException} is thrown for a {@code null} reference.
     */
    @Test
    public void shouldThrowNPEForNullMethod() {
        final ContractRetrieval retrieval = new AnnotationBasedContractRetrieval();

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Cannot retrieve contract from NULL method");

        retrieval.retrieveContract(null);
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown for a method without contract.
     */
    @Test
    public void shouldThrowNPEForMethodWithoutContract() {
        final ContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithoutContract();

        thrown.expect(NullPointerException.class);

        retrieval.retrieveContract(method);
    }

    /**
     * Ensures that a contract can be found on a method with an empty contract.
     */
    @Test
    public void shouldFindContract() {
        final ContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithEmptyContract();

        final Contract contract = retrieval.retrieveContract(method);

        Assert.assertNotNull("The retrieved contract should never be NULL!", contract);
    }

}
