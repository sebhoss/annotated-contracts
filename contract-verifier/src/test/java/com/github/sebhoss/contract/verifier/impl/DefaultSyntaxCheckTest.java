/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;

/**
 * TODO: Write documentation!
 * 
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD, CompilerWarnings.NULL })
public final class DefaultSyntaxCheckTest {

    /** TODO: Write documentation! */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldThrowNullPointerExceptionForNullContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        this.thrown.expect(NullPointerException.class);

        // When
        syntaxCheck.validate(null);

        // Then
        Assert.fail("NULL contracts should not validate!");
    }

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldThrowCorrectMessageForNullContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        this.thrown.expect(NullPointerException.class);
        this.thrown.expectMessage("There is no contract!");

        // When
        syntaxCheck.validate(null);

        // Then
        Assert.fail("NULL contracts should not validate!");
    }

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldThrowIllegalStateExceptionWithoutPreAndPostconditions() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        this.thrown.expect(IllegalStateException.class);

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("Contracts without neither pre- nor postconditions should not validate!");
    }

    /**
     * TODO: Write documentation!
     */
    @Test
    public void shouldThrowCorrectMessageForMissingClauses() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        this.thrown.expect(IllegalStateException.class);
        this.thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("The exception-message is not correct!");
    }

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
