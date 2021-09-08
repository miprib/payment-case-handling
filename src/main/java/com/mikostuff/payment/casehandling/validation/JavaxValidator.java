package com.mikostuff.payment.casehandling.validation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class JavaxValidator<T> {

	private final Validator javaxValidator;

	public JavaxValidator(Validator javaxValidator) {
		this.javaxValidator = javaxValidator;
	}

	public List<ValidationError> validate(T t) {
		Set<ConstraintViolation<T>> constraintViolations = javaxValidator.validate(t);
		return constraintViolations.stream().map(this::toError).collect(Collectors.toList());
	}

	private ValidationError toError(ConstraintViolation<T> violation) {
		return new ValidationError(violation.getPropertyPath().toString(), violation.getMessage());
	}
}