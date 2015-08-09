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

import java.lang.reflect.Method;

import javax.inject.Inject;

import com.thoughtworks.paranamer.Paranamer;

/**
 * Parameters-Names lookup based on ThoughtWorks Paranamer.
 * 
 * @see <a href="http://paranamer.codehaus.org/">http://paranamer.codehaus.org/</a>
 */
public final class ParanamerBasedParameterNamesLookup implements ParameterNamesLookup {

    private final Paranamer namer;

    /**
     * @param namer
     *            The {@link Paranamer} to use.
     */
    @Inject
    public ParanamerBasedParameterNamesLookup(final Paranamer namer) {
        this.namer = namer;
    }

    @Override
    public String[] lookupParameterNames(final Method method) {
        final String[] lookupParameterNames = namer.lookupParameterNames(method);

        if (lookupParameterNames == null) {
            throw new NullPointerException("could not look up parameters"); //$NON-NLS-1$
        }

        return lookupParameterNames;
    }

}
