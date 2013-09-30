/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;
import java.util.Set;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * Test cases for the {@link DelegatingContractSyntaxCheck}.
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
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
