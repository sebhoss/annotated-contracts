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
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

/**
 * MVEL-based implementation of the {@link ContractContext}.
 */
@com.github.sebhoss.contract.annotation.MVEL
public final class MVELContractContext implements ContractContext {

    private final Map<String, Object> tokens;

    MVELContractContext(final Map<String, Object> tokens) {
        this.tokens = new HashMap<>(tokens);
    }

    @Override
    public void setInvocationResult(final Object invocationResult) {
        tokens.put("_" + Clause.RETURN, invocationResult); //$NON-NLS-1$
    }

    @Override
    public boolean isInViolationWith(final Clause clause) {
        final Boolean clauseValid = MVEL.evalToBoolean(clause.value(), tokens);

        return Boolean.FALSE.equals(clauseValid);
    }

}
