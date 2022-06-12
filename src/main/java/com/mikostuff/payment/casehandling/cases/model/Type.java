package com.mikostuff.payment.casehandling.cases.model;

import java.util.HashMap;
import java.util.Map;

import com.mikostuff.payment.casehandling.exception.NotFoundException;

public enum Type {
	NORWEGIAN("NO"), SWEDISH("SE"), DANISH("DK"), FINNISH("FI");

	private static final String TYPE_NOT_FOUND = "Case type '%s' doesn't exist.";

	private final String countryCode;

	private Type(String countryCode) {
		this.countryCode = countryCode;
	}

	public String countryCode() {
		return countryCode;
	}

	private static final Map<String, Type> types = new HashMap<>();
	static {
		for (Type type : Type.values()) {
			types.put(type.countryCode, type);
		}
	}

	public static Type find(String countryCode) {
		Type type = types.get(countryCode);
		if (type == null) {
			throw new NotFoundException(String.format(TYPE_NOT_FOUND, countryCode));
		}
		return type;
	}
}