/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.Contract;

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