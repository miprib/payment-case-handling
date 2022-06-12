package com.mikostuff.payment.casehandling.exception;

public class CaseResolutionException extends RuntimeException {

	public CaseResolutionException(String message) {
		super(message);
	}

	public CaseResolutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
