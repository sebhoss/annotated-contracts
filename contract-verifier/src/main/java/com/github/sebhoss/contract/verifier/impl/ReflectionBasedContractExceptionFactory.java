/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.contract.verifier.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;

/**
 * TODO: Write documentation!
 * 
 */
public final class ReflectionBasedContractExceptionFactory implements ContractExceptionFactory {

    @Override
    public RuntimeException breachOfContract(final Clause clause) {
        final boolean needsStringConstructor = clause.message().isEmpty();

        try {
            RuntimeException contractException;

            if (needsStringConstructor) {
                final Constructor<? extends RuntimeException> constructors = clause.exception().getConstructor(
                        new Class[] { String.class });
                contractException = constructors.newInstance(clause.message());
            } else {
                final Constructor<? extends RuntimeException> constructors = clause.exception().getConstructor(
                        new Class[] {});
                contractException = constructors.newInstance();
            }

            return contractException;
        } catch (final InstantiationException exception) {
            throw new IllegalArgumentException(
                    "The specified exception type is abstract. You can not create an instance of an abstrac class!"); //$NON-NLS-1$
        } catch (final IllegalAccessException exception) {
            throw new IllegalArgumentException("The specified exception type has no accessible constructor!"); //$NON-NLS-1$
        } catch (final NoSuchMethodException exception) {
            if (needsStringConstructor) {
                throw new IllegalArgumentException(
                        "The specified exception type has no String-constructor but a message was provided! Either create an appropriate constructor or delete the message from the contract clause. Clause: " + clause.toString()); //$NON-NLS-1$
            }

            throw new IllegalArgumentException(
                    "The specified exception type has no default constructor! Either create an appropriate constructor or try adding a message. Clase: " //$NON-NLS-1$
                            + clause.toString());
        } catch (final SecurityException exception) {
            throw exception;
        } catch (final IllegalArgumentException exception) {
            throw exception;
        } catch (final InvocationTargetException exception) {
            throw new IllegalArgumentException(
                    "Exception while creating contract-exception: " + exception.getLocalizedMessage()); //$NON-NLS-1$
        }

    }

}
