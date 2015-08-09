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
import com.github.sebhoss.contract.annotation.OGNL;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * OGNL-based implementation of the {@link ContractContext}.
 */
@OGNL
public final class OgnlContractContext implements ContractContext {

    private final OgnlContext ognlContext;

    OgnlContractContext(final OgnlContext ognlContext) {
        this.ognlContext = ognlContext;
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        ognlContext.put(Clause.RETURN, invocationResult);
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        try {
            final Object expression = Ognl.parseExpression(clause.value());
            final Object contractValidated = Ognl.getValue(expression, ognlContext, ognlContext.getCurrentObject());
            return Boolean.FALSE.equals(contractValidated);
        } catch (final OgnlException exception) {
            throw new ContractContextException(exception);
        }
    }

}
