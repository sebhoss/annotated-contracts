/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;

/**
 * Test cases for the default syntax checks.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD, CompilerWarnings.NULL })
public final class DefaultSyntaxCheckTest {

    /** Catches expected exceptions during test execution. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that an {@link IllegalStateException} is thrown for a contract without pre- and postconditions.
     */
    @Test
    public void shouldThrowIllegalStateExceptionWithoutPreAndPostconditions() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        // When
        thrown.expect(IllegalStateException.class);

        // Then
        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that the correct exception message is thrown for a contract without pre- and postconditions.
     */
    @Test
    public void shouldThrowCorrectMessageForMissingClauses() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        // When
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        // Then
        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with a preconditions is validated.
     */
    @Test
    public void shouldAllowContractWithPrecondition() {
        // Given
        final Contract contract = ContractFactory.contractWithPrecondition();

        // When
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        // Then
        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with a postcondition is validated.
     */
    @Test
    public void shouldAllowContractWithPostcondition() {
        // Given
        final Contract contract = ContractFactory.contractWithPostcondition();

        // When
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        // Then
        syntaxCheck.validate(contract);
    }

}
