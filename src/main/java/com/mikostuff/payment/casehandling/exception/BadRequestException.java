package com.mikostuff.payment.casehandling.exception;

import java.util.List;

import com.mikostuff.payment.casehandling.validation.ValidationError;

public class BadRequestException extends RuntimeException {

	private final List<ValidationError> errors;

	public BadRequestException(String message) {
		super(message);
		this.errors = List.of();
	}

	public BadRequestException(String message, List<ValidationError> errors) {
		super(message);
		this.errors = errors;
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		this.errors = List.of();
	}

	public List<ValidationError> getErrors() {
		return this.errors;
	}
}
