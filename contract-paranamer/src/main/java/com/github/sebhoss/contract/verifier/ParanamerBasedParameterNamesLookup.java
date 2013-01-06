package com.github.sebhoss.contract.verifier;

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.github.sebhoss.contract.verifier.ParameterNamesLookup;
import com.thoughtworks.paranamer.Paranamer;

public final class ParanamerBasedParameterNamesLookup implements ParameterNamesLookup {

    private final Paranamer namer;

    @Inject
    public ParanamerBasedParameterNamesLookup(final Paranamer namer) {
        this.namer = namer;
    }

    @Override
    public String[] lookupParameterNames(final Method method) {
        return this.namer.lookupParameterNames(method);
    }

}
