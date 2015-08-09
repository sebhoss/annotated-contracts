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

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.annotation.UEL;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.StandardELContext;
import javax.inject.Inject;

/**
 * UEL-based implementation of the {@link ContractContextFactory}.
 */
@UEL
public class UelBasedContractContextFactory extends AbstractContractContextFactory {

    private final ExpressionFactory expressionFactory;

    /**
     * @param expressionFactory
     *            The expression factory to use.
     */
    @Inject
    public UelBasedContractContextFactory(final ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    public ContractContext createContext(final Object instance, final Object[] arguments, final String[] parameterNames) {
        final ELContext elContext = new StandardELContext(expressionFactory);

        setParameters(parameterNames, arguments, (name, value) -> elContext.getVariableMapper().setVariable(name,
                expressionFactory.createValueExpression(value, value.getClass())));
        elContext.getVariableMapper().setVariable(Clause.THIS,
                expressionFactory.createValueExpression(instance, instance.getClass()));

        return new ELContractContext(elContext, expressionFactory);
    }

}
