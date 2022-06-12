package com.mikostuff.payment.casehandling.cases.validation.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.CasePaymentRepo;
import com.mikostuff.payment.casehandling.cases.validation.CaseRule;
import com.mikostuff.payment.casehandling.validation.ValidationError;

@Component
public class OneCasePerPaymentCaseRule implements CaseRule {

	private static final String PAYMENT_FIELD = "paymentId";
	private static final String ERROR_MESSAGE = "Case '%s' already has the provided payment '%d'";

	private final CasePaymentRepo casePaymentRepo;

	public OneCasePerPaymentCaseRule(CasePaymentRepo defaultCasePaymentRepo) {
		this.casePaymentRepo = defaultCasePaymentRepo;
	}

	@Override
	public Optional<ValidationError> check(Case caseToCheck) {
		return casePaymentRepo.find(caseToCheck.payment().id()).map(this::createError);
	}

	private ValidationError createError(Case paymentCase) {
		return new ValidationError(PAYMENT_FIELD,
				String.format(ERROR_MESSAGE, paymentCase.id(), paymentCase.payment().id()));
	}
}
