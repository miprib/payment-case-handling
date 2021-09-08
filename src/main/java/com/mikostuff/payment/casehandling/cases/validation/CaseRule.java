package com.mikostuff.payment.casehandling.cases.validation;

import java.util.Optional;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.validation.Rule;
import com.mikostuff.payment.casehandling.validation.ValidationError;

public interface CaseRule extends Rule<Case> {

	Optional<ValidationError> check(Case caseToCheck);
}
