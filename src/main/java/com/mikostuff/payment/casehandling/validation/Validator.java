package com.mikostuff.payment.casehandling.validation;

import java.util.ArrayList;
import java.util.List;

import com.mikostuff.payment.casehandling.exception.BadRequestException;

public abstract class Validator<T> {

	private final String badRequestMessage;

	public Validator(String badRequestMessage) {
		this.badRequestMessage = badRequestMessage;
	}

	public abstract List<ValidationError> findErrors(T t);

	public void validate(T t) {
		List<ValidationError> errors = new ArrayList<>();
		errors.addAll(findErrors(t));
		if (!errors.isEmpty()) {
			throw new BadRequestException(badRequestMessage, errors);
		}
	}
}