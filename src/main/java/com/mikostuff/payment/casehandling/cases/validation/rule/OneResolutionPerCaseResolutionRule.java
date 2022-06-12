package com.mikostuff.payment.casehandling.cases.validation.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;
import com.mikostuff.payment.casehandling.cases.validation.ResolutionRule;
import com.mikostuff.payment.casehandling.validation.ValidationError;

@Component
public class OneResolutionPerCaseResolutionRule implements ResolutionRule {

	private static final String PAYMENT_FIELD = "resolution";
	private static final String ERROR_MESSAGE = "Case '%s' was already resolved as '%s'";

	private final CaseResolutionRepo caseResolutionRepo;

	public OneResolutionPerCaseResolutionRule(CaseResolutionRepo defaultCaseResolutionRepo) {
		this.caseResolutionRepo = defaultCaseResolutionRepo;
	}

	@Override
	public Optional<ValidationError> check(Case caseToCheck) {
		return caseResolutionRepo.findResolution(caseToCheck.id())
				.map(resolution -> createError(caseToCheck, resolution));
	}

	private ValidationError createError(Case caseToCheck, Resolution resolution) {
		return new ValidationError(PAYMENT_FIELD, String.format(ERROR_MESSAGE, caseToCheck.id(), resolution.name()));
	}
}
