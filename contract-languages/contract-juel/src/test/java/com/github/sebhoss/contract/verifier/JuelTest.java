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
