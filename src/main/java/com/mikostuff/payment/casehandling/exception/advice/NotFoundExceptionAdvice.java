package com.mikostuff.payment.casehandling.exception.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mikostuff.payment.casehandling.exception.NotFoundException;

@RestControllerAdvice
public class NotFoundExceptionAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundExceptionAdvice.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiExceptionResponse> handlerMethod(NotFoundException notFound) {
		LOGGER.error("Handling NotFoundException", notFound);

		return ApiExceptionResponse.ofNotFound(notFound.getMessage()).asResponseEntity();
	}
}