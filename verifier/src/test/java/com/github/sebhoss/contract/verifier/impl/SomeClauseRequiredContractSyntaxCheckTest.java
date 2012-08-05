package com.github.sebhoss.contract.verifier.impl;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;

@SuppressWarnings({ "static-method", "nls" })
public final class SomeClauseRequiredContractSyntaxCheckTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotAllowNullInput() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        this.thrown.expect(NullPointerException.class);
        this.thrown.expectMessage("There is no contract!");

        // When
        syntaxCheck.validate(null);

        // Then
        Assert.fail("NULL should not be valid input!");
    }

    @Test
    public void shouldNotAllowEmptyContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        this.thrown.expect(IllegalStateException.class);

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("Empty contracts should not be valid input!");
    }

    @Test
    public void shouldPrintMessageForEmptyContract() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        this.thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        // When
        syntaxCheck.validate(contract);

        // Then
        Assert.fail("Empty contracts should not be valid input!");
    }

    @Test
    public void shouldAllowContractWithPrecondition() {
        // Given
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        // When
        final Contract contract = ContractFactory.contractWithPrecondition();

        // Then
        syntaxCheck.validate(contract);
    }

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
