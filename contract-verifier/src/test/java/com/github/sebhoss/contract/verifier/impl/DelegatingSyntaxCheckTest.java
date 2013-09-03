/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * Test cases for the {@link DelegatingContractSyntaxCheck}.
 */
@SuppressWarnings({ "static-method", "null" })
public final class DelegatingSyntaxCheckTest {

    /**
     * Ensures that the given syntax check are called.
     */
    @Test
    public void shouldCallGivenSyntaxCheck() {
        // Given
        final ContractSyntaxCheck mockedCheck = Mockito.mock(ContractSyntaxCheck.class);
        final Set<ContractSyntaxCheck> checks = new HashSet<>();
        checks.add(mockedCheck);
        final DelegatingContractSyntaxCheck syntaxCheck = new DelegatingContractSyntaxCheck(checks);

        // When
        syntaxCheck.validate(null);

        // Then
        Mockito.verify(mockedCheck).validate(Matchers.<Contract> any());
    }

    /**
     * Ensures that all given syntax checks are called.
     */
    @Test
    public void shouldCallGivenSyntaxChecks() {
        // Given
        final ContractSyntaxCheck firstCheck = Mockito.mock(ContractSyntaxCheck.class);
        final ContractSyntaxCheck secondCheck = Mockito.mock(ContractSyntaxCheck.class);
        final Set<ContractSyntaxCheck> checks = new HashSet<>();
        checks.add(firstCheck);
        checks.add(secondCheck);
        final DelegatingContractSyntaxCheck syntaxCheck = new DelegatingContractSyntaxCheck(checks);

        // When
        syntaxCheck.validate(null);

        // Then
        Mockito.verify(firstCheck).validate(Matchers.<Contract> any());
        Mockito.verify(secondCheck).validate(Matchers.<Contract> any());
    }

}
