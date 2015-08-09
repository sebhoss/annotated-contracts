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
