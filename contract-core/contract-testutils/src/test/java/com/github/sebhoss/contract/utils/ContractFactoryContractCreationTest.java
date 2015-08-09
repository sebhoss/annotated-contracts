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
package com.github.sebhoss.contract.utils;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for the {@link ContractFactory}'s creation of new {@link Contract} instances.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public final class ContractFactoryContractCreationTest {

    /**
     * Ensures that an empty contract can be created.
     */
    @Test
    public void shouldCreateEmptyContract() {
        // Given
        Contract contract;

        // When
        contract = ContractFactory.emptyContract();

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
    }

    /**
     * Ensures that an empty contract doesn't contains any preconditions.
     */
    @Test
    public void shouldCreateEmptyContractWithoutPreconditions() {
        // Given
        Contract contract;

        // When
        contract = ContractFactory.emptyContract();

        // Then
        Assert.assertTrue("The empty contract has unknown preconditions!", contract.preconditions().length == 0);
        Assert.assertTrue("The empty contract has unknown postconditions!", contract.postconditions().length == 0);
        Assert.assertEquals("The created contract has the wrong annotation type!", contract.annotationType(),
                Contract.class);
    }

    /**
     * Ensures that a contract with a precondition can be created.
     */
    @Test
    public void shouldCreateContractWithPrecondition() {
        // Given
        Contract contract;

        // When
        contract = ContractFactory.contractWithPrecondition();

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
        Assert.assertTrue("The created contract has no precondition!", contract.preconditions().length == 1);
    }

    /**
     * Ensures that a contract with preconditions can be created.
     */
    @Test
    public void shouldCreateContractWithPreconditions() {
        // Given
        final Clause precondition1 = ContractFactory.alwaysTrueDefaultClause();
        final Clause precondition2 = ContractFactory.alwaysTrueDefaultClause();

        // When
        final Contract contract = ContractFactory.contractWithPreconditions(precondition1, precondition2);

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
        Assert.assertTrue("The created contract has not all given preconditions!", contract.preconditions().length == 2);
    }

    /**
     * Ensures that a contract with a postcondition can be created.
     */
    @Test
    public void shouldCreateContractWithPostcondition() {
        // Given
        Contract contract;

        // When
        contract = ContractFactory.contractWithPostcondition();

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
        Assert.assertTrue("The created contract has unknown preconditions!", contract.preconditions().length == 0);
        Assert.assertTrue("The created contract has no postcondition!", contract.postconditions().length == 1);
    }

    /**
     * Ensures that a contract with postconditions can be created.
     */
    @Test
    public void shouldCreateContractWithPostconditions() {
        // Given
        final Clause postcondition1 = ContractFactory.alwaysTrueDefaultClause();
        final Clause postcondition2 = ContractFactory.alwaysTrueDefaultClause();

        // When
        final Contract contract = ContractFactory.contractWithPostconditions(postcondition1, postcondition2);

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
        Assert.assertTrue("The created contract has unknown preconditions!", contract.preconditions().length == 0);
        Assert.assertTrue("The created contract has not all given postconditions!",
                contract.postconditions().length == 2);
    }

    /**
     * Ensures that a contract with pre- and postconditions can be created.
     */
    @Test
    public void shouldCreateContractWithPreAndPostconditions() {
        // Given
        final Clause[] preconditions = { ContractFactory.alwaysTrueDefaultClause(),
                ContractFactory.alwaysTrueDefaultClause() };
        final Clause[] postconditions = { ContractFactory.alwaysTrueDefaultClause(),
                ContractFactory.alwaysTrueDefaultClause() };

        // When
        final Contract contract = ContractFactory.contract(preconditions, postconditions);

        // Then
        Assert.assertNotNull("The created contract is NULL!", contract);
        Assert.assertTrue("The created contract has not all given preconditions!", contract.preconditions().length == 2);
        Assert.assertTrue("The created contract has not all given postconditions!",
                contract.postconditions().length == 2);
    }

}
