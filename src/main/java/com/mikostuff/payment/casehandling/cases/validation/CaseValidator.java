package com.mikostuff.payment.casehandling.cases.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.JavaxValidator;
import com.mikostuff.payment.casehandling.validation.ValidationError;
import com.mikostuff.payment.casehandling.validation.Validator;

@Component
public class CaseValidator extends Validator<Case> {

	private static final String BAD_REQUEST = "Case contains invalid parameters";

	private final List<CaseRule> rules;
	private final JavaxValidator<Case> javaxValidator;

	public CaseValidator(JavaxValidator<Case> javaxValidator, List<CaseRule> rules) {
		super(BAD_REQUEST);
		this.javaxValidator = javaxValidator;
		this.rules = rules;
	}

	@Override
	public List<ValidationError> findErrors(Case caseToValidate) {
		List<ValidationError> errors = new ArrayList<>();
		addRuleErrors(caseToValidate, errors);
		addJavaxErrors(caseToValidate, errors);
		return errors;
	}

	private void addJavaxErrors(Case caseToValidate, List<ValidationError> errors) {
		javaxValidator.validate(caseToValidate).forEach(errors::add);
	}

	private void addRuleErrors(Case caseToValidate, List<ValidationError> errors) {
		rules.forEach(rule -> rule.check(caseToValidate).ifPresent(errors::add));
	}
}
