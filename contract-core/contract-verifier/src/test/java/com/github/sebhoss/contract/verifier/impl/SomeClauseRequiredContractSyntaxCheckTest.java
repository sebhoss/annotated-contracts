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
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for {@link SomeClauseRequiredContractSyntaxCheck}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class SomeClauseRequiredContractSyntaxCheckTest {

    /** Catches expected exceptions during test execution. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that an empty contract can't be validated.
     */
    @Test
    public void shouldNotAllowEmptyContract() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        thrown.expect(IllegalStateException.class);

        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that the correct exception message is used for an empty contract.
     */
    @Test
    public void shouldPrintMessageForEmptyContract() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();
        thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        syntaxCheck.validate(contract);

        Assert.fail("Empty contracts should not be valid input!");
    }

    /**
     * Ensures that a contract with precondition can be verified.
     */
    @Test
    public void shouldAllowContractWithPrecondition() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        final Contract contract = ContractFactory.contractWithPrecondition();

        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with postcondition can be verified.
     */
    @Test
    public void shouldAllowContractWithPostcondition() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        final Contract contract = ContractFactory.contractWithPostcondition();

        syntaxCheck.validate(contract);
    }

}
