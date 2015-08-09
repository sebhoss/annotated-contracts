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

import ch.qos.cal10n.IMessageConveyor;
import com.github.sebhoss.contract.annotation.Clause;
import com.github.sebhoss.contract.verifier.ContractExceptionFactory;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.github.sebhoss.contract.verifier.impl.ExceptionFactoryErrors.*;
import static java.util.Objects.requireNonNull;

/**
 * Creates contract violation exceptions with the help of reflection.
 */
public final class ReflectionBasedContractExceptionFactory implements ContractExceptionFactory {

    private final IMessageConveyor messages;

    /**
     * @param messages
     *            The cal10n message conveyor to use.
     */
    @Inject
    public ReflectionBasedContractExceptionFactory(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public RuntimeException breachOfContract(final Clause clause) {
        final String message = requireNonNull(clause.message(), message(MESSAGE_IS_NULL));
        final Class<? extends RuntimeException> exceptionToThrow = requireNonNull(clause.exception(), message(EXCEPTION_IS_NULL));

        try {
            RuntimeException contractException;

            if (requiresStringConstructor(clause)) {
                final Constructor<? extends RuntimeException> constructor = requireNonNull(
                        exceptionToThrow.getConstructor(String.class), message(NO_MATCHING_CONSTRUCTOR));
                contractException = constructor.newInstance(message);
            } else {
                final Constructor<? extends RuntimeException> constructor = requireNonNull(
                        exceptionToThrow.getConstructor(), message(NO_MATCHING_CONSTRUCTOR));
                contractException = constructor.newInstance();
            }

            return contractException;
        } catch (final InstantiationException exception) {
            throw new IllegalArgumentException(message(TYPE_IS_ABSTRACT), exception);
        } catch (final IllegalAccessException exception) {
            throw new IllegalArgumentException(message(NO_ACCESSIBLE_CONSTRUCTOR), exception);
        } catch (final NoSuchMethodException exception) {
            if (requiresStringConstructor(clause)) {
                throw new IllegalArgumentException(message(NO_STRING_CONSTRUCTOR, clause.toString()), exception);
            }

            throw new IllegalArgumentException(message(NO_DEFAULT_CONSTRUCTOR, clause.toString()), exception);
        } catch (final InvocationTargetException exception) {
            throw new IllegalArgumentException(message(CONSTRUCTOR_EXCEPTION, exception.getLocalizedMessage()), exception);
        }
    }

    private String message(final Enum<?> key, final Object... args) {
        return messages.getMessage(key, args);
    }

    private static boolean requiresStringConstructor(final Clause clause) {
        return requiresStringConstructor(clause.message());
    }

    private static boolean requiresStringConstructor(final String message) {
        return !message.isEmpty();
    }

}
