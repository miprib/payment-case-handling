package com.mikostuff.payment.casehandling.cases.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.ValidationError;
import com.mikostuff.payment.casehandling.validation.Validator;

@Component
public class ResolutionValidator extends Validator<Case> {

	private static final String BAD_REQUEST = "Case resolution is invalid";

	private final List<ResolutionRule> rules;

	public ResolutionValidator(List<ResolutionRule> rules) {
		super(BAD_REQUEST);
		this.rules = rules;
	}

	@Override
	public List<ValidationError> findErrors(Case caseToValidate) {
		List<ValidationError> errors = new ArrayList<>();
		addRuleErrors(caseToValidate, errors);
		return errors;
	}

	private void addRuleErrors(Case caseToValidate, List<ValidationError> errors) {
		rules.forEach(rule -> rule.check(caseToValidate).ifPresent(errors::add));
	}
}
