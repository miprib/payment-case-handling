package com.mikostuff.payment.casehandling.cases.model;

import java.util.HashMap;
import java.util.Map;

import com.mikostuff.payment.casehandling.exception.NotFoundException;

public enum Resolution {
	ACCEPTED, REJECTED;

	private static final String RESOLUTION_NOT_FOUND = "Resolution '%s' doesn't exist.";

	private static final Map<String, Resolution> resolutions = new HashMap<>();
	static {
		for (Resolution resolution : Resolution.values()) {
			resolutions.put(resolution.name(), resolution);
		}
	}

	public static Resolution find(String name) {
		Resolution resolution = resolutions.get(name);
		if (resolution == null) {
			throw new NotFoundException(String.format(RESOLUTION_NOT_FOUND, resolution));
		}
		return resolution;
	}
}