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
package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;
import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Test cases for the {@link DelegatingContractSyntaxCheck}.
 */
@SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
public final class DelegatingSyntaxCheckTest {

    /**
     * Ensures that the given syntax check are called.
     */
    @Test
    public void shouldCallGivenSyntaxCheck() {
        final ContractSyntaxCheck mockedCheck = Mockito.mock(ContractSyntaxCheck.class);
        final Set<ContractSyntaxCheck> checks = new HashSet<>();
        checks.add(mockedCheck);
        final DelegatingContractSyntaxCheck syntaxCheck = new DelegatingContractSyntaxCheck(checks);

        syntaxCheck.validate(null);

        Mockito.verify(mockedCheck).validate(Matchers.<Contract> any());
    }

    /**
     * Ensures that all given syntax checks are called.
     */
    @Test
    public void shouldCallGivenSyntaxChecks() {
        final ContractSyntaxCheck firstCheck = Mockito.mock(ContractSyntaxCheck.class);
        final ContractSyntaxCheck secondCheck = Mockito.mock(ContractSyntaxCheck.class);
        final Set<ContractSyntaxCheck> checks = new HashSet<>();
        checks.add(firstCheck);
        checks.add(secondCheck);
        final DelegatingContractSyntaxCheck syntaxCheck = new DelegatingContractSyntaxCheck(checks);

        syntaxCheck.validate(null);

        Mockito.verify(firstCheck).validate(Matchers.<Contract> any());
        Mockito.verify(secondCheck).validate(Matchers.<Contract> any());
    }

}
