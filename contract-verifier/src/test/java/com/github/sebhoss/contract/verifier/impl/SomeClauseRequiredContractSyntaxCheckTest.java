/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for {@link SomeClauseRequiredContractSyntaxCheck}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public final class SomeClauseRequiredContractSyntaxCheckTest {

    /** Catches expected exceptions during test execution. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that an empty contract can't be validated.
     */
    @Test
    public void shouldNotAllowEmptyContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        // When
        thrown.expect(IllegalStateException.class);

        // Then
        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that the correct exception message is used for an empty contract.
     */
    @Test
    public void shouldPrintMessageForEmptyContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("Empty contracts should not be valid input!");
    }

    /**
     * Ensures that a contract with precondition can be verified.
     */
    @Test
    public void shouldAllowContractWithPrecondition() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        // When
        final Contract contract = ContractFactory.contractWithPrecondition();

        // Then
        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with postcondition can be verified.
     */
    @Test
    public void shouldAllowContractWithPostcondition() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        // When
        final Contract contract = ContractFactory.contractWithPostcondition();

        // Then
        syntaxCheck.validate(contract);
    }

}
