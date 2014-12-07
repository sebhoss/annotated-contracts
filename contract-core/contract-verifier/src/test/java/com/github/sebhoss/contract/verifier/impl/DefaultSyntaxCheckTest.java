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

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.utils.ContractFactory;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for the default syntax checks.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class DefaultSyntaxCheckTest {

    /** Catches expected exceptions during test execution. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that an {@link IllegalStateException} is thrown for a contract without pre- and postconditions.
     */
    @Test
    public void shouldThrowIllegalStateExceptionWithoutPreAndPostconditions() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        thrown.expect(IllegalStateException.class);

        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that the correct exception message is thrown for a contract without pre- and postconditions.
     */
    @Test
    public void shouldThrowCorrectMessageForMissingClauses() {
        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();
        final Contract contract = ContractFactory.emptyContract();

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Don't use @Contract without any Pre- or Postconditions!");

        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with a preconditions is validated.
     */
    @Test
    public void shouldAllowContractWithPrecondition() {
        final Contract contract = ContractFactory.contractWithPrecondition();

        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        syntaxCheck.validate(contract);
    }

    /**
     * Ensures that a contract with a postcondition is validated.
     */
    @Test
    public void shouldAllowContractWithPostcondition() {
        final Contract contract = ContractFactory.contractWithPostcondition();

        final SomeClauseRequiredContractSyntaxCheck syntaxCheck = new SomeClauseRequiredContractSyntaxCheck();

        syntaxCheck.validate(contract);
    }

}
