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
package com.github.sebhoss.contract.example;

import com.github.sebhoss.warnings.CompilerWarnings;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the {@link InsuranceCompany}.
 */
@SuppressWarnings({ CompilerWarnings.NLS })
public abstract class InsuranceCompanyTest {

    /** Catches expected exceptions */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private InsuranceCompany insurance;

    protected InsuranceCompany getInsuranceCompany() {
        return insurance;
    }

    protected void setInsuranceCompany(final InsuranceCompany insurance) {
        this.insurance = insurance;
    }

    /**
     * Ensures that the insurance company can calculate payouts based on a positive damage input.
     */
    @Test
    public void shouldAcceptPositiveDamages() {
        final double result = getInsuranceCompany().calculateCover(10.0);

        Assert.assertEquals(5.0, result, 0.0d);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on negative input.
     */
    @Test
    public void shouldNotAcceptNegativeDamages() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Reported damage must be positive!");

        final double result = getInsuranceCompany().calculateCover(-10.0);

        Assert.assertEquals(-5.0, result, 0.0d);
    }

    /**
     * Ensures that the insurance company cannot calculate payouts based on high inputs.
     */
    @Test
    public void shouldNotAcceptHighDamages() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("We won't pay that!");

        final double result = getInsuranceCompany().calculateCover(5001.0);

        Assert.assertEquals(2500.0, result, 0.0d);
    }

    /**
     * Ensures that the insurance company cannot pay a high amount of money.
     */
    @Test
    public void shouldNotPerformHighPayout() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("We can't pay that much!");

        final double result = getInsuranceCompany().calculateCover(5000.0);

        Assert.assertEquals(2500.0, result, 0.0d);
    }

}
