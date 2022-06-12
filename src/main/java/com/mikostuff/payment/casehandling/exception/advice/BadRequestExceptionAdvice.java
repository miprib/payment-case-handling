package com.mikostuff.payment.casehandling.exception.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mikostuff.payment.casehandling.exception.BadRequestException;

@RestControllerAdvice
public class BadRequestExceptionAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestExceptionAdvice.class);

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiExceptionResponse> handlerMethod(BadRequestException badRequest) {
		LOGGER.error("Handling BadRequestException", badRequest);

		return ApiExceptionResponse.ofBadRequest(badRequest.getMessage(), badRequest.getErrors()).asResponseEntity();
	}
}
