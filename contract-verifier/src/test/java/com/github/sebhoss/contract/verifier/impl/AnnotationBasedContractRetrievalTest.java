/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.MethodFactory;

/**
 * TODO: Write documentation!
 * 
 */
@SuppressWarnings({ "static-method", "nls" })
public final class AnnotationBasedContractRetrievalTest {

    /** TODO: Write documentation! */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * @throws Exception
     *             TODO: Write documentation!
     */
    @Test
    public void shouldThrowNPEForNullMethod() throws Exception {
        // Given
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        this.thrown.expect(NullPointerException.class);

        // When
        retrieval.retrieveContract(null);

        // Then
        Assert.fail("Contract-retrieval should not work with NULL!");
    }

    /**
     * @throws Exception
     *             TODO: Write documentation!
     */
    @Test
    public void shouldThrowISEForMethodWithoutContract() throws Exception {
        // Given
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithoutContract();
        this.thrown.expect(IllegalStateException.class);

        // When
        retrieval.retrieveContract(method);

        // Then
        Assert.fail("Annotation-based contract-retrieval can only work on methods with an contract annotation.");
    }

    /**
     * @throws Exception
     *             TODO: Write documentation!
     */
    @Test
    public void shouldFindContract() throws Exception {
        // Given
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithEmptyContract();

        // When
        final Contract contract = retrieval.retrieveContract(method);

        // Then
        Assert.assertNotNull("The retrieved contract should never be NULL!", contract);
    }

}
