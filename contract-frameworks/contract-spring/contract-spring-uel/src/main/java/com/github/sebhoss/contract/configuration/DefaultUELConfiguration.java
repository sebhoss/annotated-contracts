/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.configuration;

import com.github.sebhoss.contract.verifier.UelConfiguration;

/**
 * Default configuration for UEL-based contracts.
 */
@Configuration
@Import(UelConfiguration.class)
public class DefaultUELConfiguration extends BaseConfiguration {

    // Meta-configuration

}
