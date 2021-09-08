package com.mikostuff.payment.casehandling.cases.service;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.ValidationError;
import com.mikostuff.payment.casehandling.validation.Validator;

public class FakeResolutionValidator extends Validator<Case> {

	private static final String BAD_REQUEST = "Case resolution is invalid";

	public FakeResolutionValidator() {
		super(BAD_REQUEST);
	}

	@Override
	public List<ValidationError> findErrors(Case caseToValidate) {
		return List.of();
	}
}