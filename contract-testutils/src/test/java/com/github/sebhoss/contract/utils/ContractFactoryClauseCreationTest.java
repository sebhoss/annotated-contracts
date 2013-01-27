/**
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.utils;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.contract.annotation.Clause;

/**
 * TODO: Write documentation!
 */
@SuppressWarnings({ "nls", "static-method" })
public class ContractFactoryClauseCreationTest {

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
