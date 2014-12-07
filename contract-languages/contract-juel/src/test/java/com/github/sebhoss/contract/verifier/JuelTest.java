/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.warnings.CompilerWarnings;

import de.odysseus.el.util.SimpleContext;

/**
 * Test cases for JUEL-usage.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class JuelTest {

    /** Catches thrown expected exceptions inside of tests. */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    /**
     * Tests how an object can be accessed.
     */
    @Test
    public void allowObjectAccess() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final SimpleContext context = new SimpleContext();
        final String instance = "whatever";

        // When
        context.setVariable("foo", factory.createValueExpression(instance, String.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo}", String.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(instance, retrieved);
    }

    /**
     * Tests how an instance method can be accessed.
     */
    @Test
    public void allowInstanceMethodAccess() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final SimpleContext context = new SimpleContext();
        final String instance = "whatever";

        // When
        context.setVariable("foo", factory.createValueExpression(instance, String.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo.toUpperCase()}", String.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(instance.toUpperCase(), retrieved);
    }

    /**
     * Tests how an EL context can be used.
     */
    @Test
    public void useELContext() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();
        final String instance = "whatever";

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(instance, String.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo}", String.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(instance, retrieved);
    }

    /**
     * Tests an true boolean expression.
     */
    @Test
    public void trueBooleanExpression() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();
        final Integer instance = Integer.valueOf(5);

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(instance, Integer.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo > 1}", Boolean.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(Boolean.TRUE, retrieved);
    }

    /**
     * Tests an false boolean expression.
     */
    @Test
    public void falseBooleanExpression() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();
        final Integer instance = Integer.valueOf(5);

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(instance, Integer.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo >= 10}", Boolean.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(Boolean.FALSE, retrieved);
    }

    /**
     * Tests that a <code>null</code> comparison is <code>true</code>.
     */
    @Test
    public void nullComparisonTrue() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();
        final String instance = "whatever";

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(instance, String.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo != null}", Boolean.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(Boolean.TRUE, retrieved);
    }

    /**
     * Tests that a <code>null</code> comparison is <code>false</code>
     */
    @Test
    public void nullComparisonFalse() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(null, Object.class));

        // Then
        final ValueExpression expression = factory.createValueExpression(context, "${foo != null}", Boolean.class);
        final Object retrieved = expression.getValue(context);

        Assert.assertEquals(Boolean.FALSE, retrieved);
    }

    /**
     * Tests that expressions are case sensitive.
     */
    @Test
    public void expressionIsCaseSensitive() {
        // Given
        final ExpressionFactory factory = ExpressionFactory.newInstance();
        final ELContext context = new SimpleContext();
        final String instance = "whatever";
        thrown.expect(PropertyNotFoundException.class);
        thrown.expectMessage("Cannot find property FOO");

        // When
        context.getVariableMapper().setVariable("foo", factory.createValueExpression(instance, String.class));
        final ValueExpression expression = factory.createValueExpression(context, "${FOO != null}", Boolean.class);
        expression.getValue(context);

        // Then
        Assert.fail("Expression is not case sensitive!");
    }

}
