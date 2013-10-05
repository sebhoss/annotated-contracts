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

/**
 * Test cases for the {@link ContractFactory}'s creation of new {@link Clause} instances.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class ContractFactoryClauseCreationTest {

    /**
     * Ensures that a clause which validates to <code>true</code> is not <code>null</code>.
     */
    @Test
    public void shouldCreateAlwaysTrueClause() {
        // Given
        Clause clause;

        // When
        clause = ContractFactory.alwaysTrueDefaultClause();

        // Then
        Assert.assertNotNull("The created clause is NULL!", clause);
    }

    /**
     * Ensures that a clause which validates to <code>true</code> can be created.
     */
    @Test
    public void shouldCreateAlwaysTrueClauseWithTrueCondition() {
        // Given
        Clause clause;

        // When
        clause = ContractFactory.alwaysTrueDefaultClause();

        // Then
        Assert.assertEquals("The created clause has a wrong condition!", clause.value(), "true");
    }

    /**
     * Ensures that a clause which validates to <code>true</code> has an empty message.
     */
    @Test
    public void shouldCreateAlwaysTrueClauseWithEmptyMessage() {
        // Given
        Clause clause;

        // When
        clause = ContractFactory.alwaysTrueDefaultClause();

        // Then
        Assert.assertTrue("The created clause has a wrong message!", clause.message().isEmpty());
    }

    /**
     * Ensures that a clause which validates to <code>true</code> throws an {@link IllegalArgumentException} in case
     * something goes wrong.
     */
    @Test
    public void shouldCreateAlwaysTrueClauseWithCorrectException() {
        // Given
        Clause clause;

        // When
        clause = ContractFactory.alwaysTrueDefaultClause();

        // Then
        Assert.assertEquals("The created clause has a wrong exception", clause.exception(),
                IllegalArgumentException.class);
    }

    /**
     * Ensures that a returned clause is not <code>null</code>.
     */
    @Test
    public void shouldNotCreateNullClause() {
        // Given
        final String condition = "";

        // When
        final Clause clause = ContractFactory.clause(condition);

        // Then
        Assert.assertNotNull("The created clause is NULL!", clause);
    }

    /**
     * Ensures that the returned clause contains the given condition.
     */
    @Test
    public void shouldCreateClauseWithGivenCondition() {
        // Given
        final String condition = "age >= 0";

        // When
        final Clause clause = ContractFactory.clause(condition);

        // Then
        Assert.assertEquals("The created clause has a wrong condition", clause.value(), condition);
        Assert.assertEquals("The created clause has a wrong message", clause.message(), "");
        Assert.assertEquals("The created clause has a wrong exception", clause.exception(),
                IllegalArgumentException.class);
        Assert.assertEquals("The created clause has a wrong annotation type", clause.annotationType(), Clause.class);
    }

    /**
     * Ensures that the returned clause contains the given condition and message.
     */
    @Test
    public void shouldCreateClauseWithGivenConditionAndMessage() {
        // Given
        final String condition = "age >= 0";
        final String message = "persons age cannot be negative";

        // When
        final Clause clause = ContractFactory.clause(condition, message);

        // Then
        Assert.assertEquals("The created clause has a wrong condition", clause.value(), condition);
        Assert.assertEquals("The created clause has a wrong message", clause.message(), message);
        Assert.assertEquals("The created clause has a wrong exception", clause.exception(),
                IllegalArgumentException.class);
        Assert.assertEquals("The created clause has a wrong annotation type", clause.annotationType(), Clause.class);
    }

    /**
     * Ensures that the returned clause contains the given condition, message and exception.
     */
    @Test
    public void shouldCreateClauseWithGivenConditionAndMessageAndException() {
        // Given
        final String condition = "age >= 0";
        final String message = "persons age cannot be negative";
        final Class<? extends RuntimeException> exception = IllegalStateException.class;

        // When
        final Clause clause = ContractFactory.clause(condition, message, exception);

        // Then
        Assert.assertEquals("The created clause has a wrong condition", clause.value(), condition);
        Assert.assertEquals("The created clause has a wrong message", clause.message(), message);
        Assert.assertEquals("The created clause has a wrong exception", clause.exception(), exception);
        Assert.assertEquals("The created clause has a wrong annotation type", clause.annotationType(), Clause.class);
    }

}
