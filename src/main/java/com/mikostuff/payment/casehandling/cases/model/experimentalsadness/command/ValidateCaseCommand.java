package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.Validator;

@Component
public class ValidateCaseCommand implements Command<Case> {

	private final Validator<Case> validator;

	public ValidateCaseCommand(Validator<Case> caseValidator) {
		this.validator = caseValidator;
	}

	@Override
	public void execute(Case body) {
		validator.validate(body);
	}
}