package com.mikostuff.payment.casehandling.cases.api;

import java.util.HashMap;
import java.util.Map;

import com.mikostuff.payment.casehandling.exception.NotFoundException;

public enum CountryCode {
	NO, SE, DK, FI;

	private static final String COUNTRY_NOT_FOUND = "Country code '%s' doesn't exist.";

	private static final Map<String, CountryCode> countryCodes = new HashMap<>();
	static {
		for (CountryCode countryCode : CountryCode.values()) {
			countryCodes.put(countryCode.name(), countryCode);
		}
	}

	public static CountryCode find(String name) {
		CountryCode countryCode = countryCodes.get(name);
		if (countryCode == null) {
			throw new NotFoundException(String.format(COUNTRY_NOT_FOUND, countryCode));
		}
		return countryCode;
	}
}
