package com.github.sebhoss.contract.utils;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

/**
 * TODO: Write documentation!
 * 
 */
@SuppressWarnings({ "nls", "static-method" })
public final class ContractFactoryContractCreationTest {

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
     */
    @Test
    public void shouldCreateContractWithPreAndPostconditions() {
        // Given
        final Clause[] preconditions = new Clause[] { ContractFactory.alwaysTrueDefaultClause(),
                ContractFactory.alwaysTrueDefaultClause() };
        final Clause[] postconditions = new Clause[] { ContractFactory.alwaysTrueDefaultClause(),
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
