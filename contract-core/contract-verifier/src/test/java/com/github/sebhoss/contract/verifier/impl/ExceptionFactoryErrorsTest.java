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

import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import ch.qos.cal10n.verifier.Cal10nError;
import ch.qos.cal10n.verifier.IMessageKeyVerifier;
import ch.qos.cal10n.verifier.MessageKeyVerifier;

import com.github.sebhoss.warnings.CompilerWarnings;

/**
 * Checks the properties behind {@link ExceptionFactoryErrors}.
 */
@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
public class ExceptionFactoryErrorsTest {

    /**
     * Ensures that English translations are found.
     */
    @Test
    public void shouldHaveEnglishTranslations() {
        final IMessageKeyVerifier mkv = new MessageKeyVerifier(ExceptionFactoryErrors.class);

        final List<Cal10nError> errorList = mkv.verify(Locale.ENGLISH);

        for (final Cal10nError error : errorList) {
            System.out.println(error);
        }
        Assert.assertEquals(0, errorList.size());
    }

}
