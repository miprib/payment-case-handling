package com.mikostuff.payment.casehandling.cases.service;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.ValidationError;
import com.mikostuff.payment.casehandling.validation.Validator;

public class FakeCaseValidator extends Validator<Case> {

	private static final String BAD_REQUEST = "Case contains invalid parameters";

	public FakeCaseValidator() {
		super(BAD_REQUEST);
	}

	@Override
	public List<ValidationError> findErrors(Case caseToValidate) {
		return List.of();
	}
}
