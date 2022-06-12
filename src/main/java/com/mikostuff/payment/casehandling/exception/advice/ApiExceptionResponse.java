package com.mikostuff.payment.casehandling.exception.advice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mikostuff.payment.casehandling.validation.ValidationError;

public class ApiExceptionResponse {

	private final Integer statusCode;
	private final LocalDateTime timestamp;
	private final String reason;
	private final List<ValidationError> errors;

	public ApiExceptionResponse(Integer statusCode, LocalDateTime timestamp, String reason,
			List<ValidationError> errors) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.reason = reason;
		this.errors = errors;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getReason() {
		return reason;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public static ApiExceptionResponse ofBadRequest(String reason, List<ValidationError> errors) {
		return new ApiExceptionResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), reason, errors);
	}

	public static ApiExceptionResponse ofBadRequest(String reason) {
		return ofBadRequest(reason, List.of());
	}

	public static ApiExceptionResponse ofInternalServerError(String reason) {
		return new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), reason,
				List.of());
	}

	public static ApiExceptionResponse ofRequestTimeout(String reason) {
		return new ApiExceptionResponse(HttpStatus.REQUEST_TIMEOUT.value(), LocalDateTime.now(), reason, List.of());
	}

	public static ApiExceptionResponse ofNotFound(String reason) {
		return new ApiExceptionResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), reason, List.of());
	}

	public ResponseEntity<ApiExceptionResponse> asResponseEntity() {
		return new ResponseEntity<>(this, HttpStatus.valueOf(this.getStatusCode()));
	}
}
