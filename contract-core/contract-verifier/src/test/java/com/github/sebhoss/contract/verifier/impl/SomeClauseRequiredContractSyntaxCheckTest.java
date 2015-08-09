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
