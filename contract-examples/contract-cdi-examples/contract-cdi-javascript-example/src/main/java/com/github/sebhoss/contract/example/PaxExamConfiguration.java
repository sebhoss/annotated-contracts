/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.contract.example;

import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import com.github.sebhoss.nullanalysis.Nullsafe;
import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

// TODO: We only need this for pax-exam
class PaxExamConfiguration {

	@Produces
	@Default
	@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
	Set<Service> services() {
		return Nullsafe.nullsafe(ImmutableSet.<Service> of());
	}

}
