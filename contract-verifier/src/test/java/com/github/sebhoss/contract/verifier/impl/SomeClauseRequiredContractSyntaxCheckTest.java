/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;

/**
 * TODO: Write documentation!
 */
@SuppressWarnings({ "static-method", "nls", "null" })
public final class SomeClauseRequiredContractSyntaxCheckTest {

    /** TODO: Write documentation! */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldNotAllowNullInput() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("There is no contract!");

        // When
        syntaxCheck.validate(null);

        // Then
        Assert.fail("NULL should not be valid input!");
    }

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldNotAllowEmptyContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        thrown.expect(IllegalStateException.class);

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("Empty contracts should not be valid input!");
    }

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
