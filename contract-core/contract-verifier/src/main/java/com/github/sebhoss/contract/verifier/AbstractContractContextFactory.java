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

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/**
 * Abstract implementation of the ContractContextFactory that offers its subclasses an utility method to set method
 * call parameters.
 */
public abstract class AbstractContractContextFactory implements ContractContextFactory {

    protected static void setParameters(final String[] parameterNames, final Object[] arguments,
                                        final BiConsumer<String, Object> parameters) {
        IntStream.range(0, arguments.length)
                .mapToObj(index -> new ParameterNameAndValue(parameterNames[index], arguments[index]))
                .forEach(parameter -> parameters.accept(parameter.getName(), parameter.getValue()));
    }

    // We don't have 'Tuple' or something similar in Java yet..
    private static final class ParameterNameAndValue {

        private final String name;
        private final Object value;

        ParameterNameAndValue(final String name, final Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

    }

}
