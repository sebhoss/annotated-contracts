/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.verifier;

/**
 * Generic runtime exception to capture checked exceptions.
 */
public final class ContractContextException extends RuntimeException {

    private static final long serialVersionUID = -599669645574466540L;

    ContractContextException(final Exception exception) {
        super(exception);
    }

}
