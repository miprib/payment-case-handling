package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.Validator;

public class ValidateResolutionCommand implements Command<Case> {

	private final Validator<Case> validator;

	public ValidateResolutionCommand(Validator<Case> resolutionValidator) {
		this.validator = resolutionValidator;
	}

	@Override
	public void execute(Case caseBody) {
		validator.validate(caseBody);
	}
}
