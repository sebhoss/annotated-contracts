package com.github.sebhoss.contract.verifier.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.github.sebhoss.contract.annotation.Contract;
import com.github.sebhoss.contract.verifier.ContractSyntaxCheck;

/**
 * TODO: Write documentation!
 * 
 */
@SuppressWarnings("static-method")
public final class DelegatingSyntaxCheckTest {

    /**
     * TODO: Write documentation!
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
     * TODO: Write documentation!
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
