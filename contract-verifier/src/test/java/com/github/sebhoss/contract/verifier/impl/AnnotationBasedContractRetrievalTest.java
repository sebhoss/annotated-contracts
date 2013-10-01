/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Method;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.MethodFactory;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the {@link AnnotationBasedContractRetrieval}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public final class AnnotationBasedContractRetrievalTest {

    /** Catches expected exceptions in a test case. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that a {@link NullPointerException} is thrown for a <code>null</code> reference.
     */
    @Test
    public void shouldThrowNPEForNullMethod() {
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();

        thrown.expect(NullPointerException.class);

        retrieval.retrieveContract(null);
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown for a method without contract.
     */
    @Test
    public void shouldThrowNPEForMethodWithoutContract() {
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithoutContract();

        thrown.expect(NullPointerException.class);

        retrieval.retrieveContract(method);
    }

    /**
     * Ensures that a contract can be found on a method with an empty contract.
     */
    @Test
    public void shouldFindContract() {
        final AnnotationBasedContractRetrieval retrieval = new AnnotationBasedContractRetrieval();
        final Method method = MethodFactory.createMethodWithEmptyContract();

        final Contract contract = retrieval.retrieveContract(method);

        Assert.assertNotNull("The retrieved contract should never be NULL!", contract);
    }

}
