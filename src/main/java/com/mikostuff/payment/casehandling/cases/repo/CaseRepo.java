package com.mikostuff.payment.casehandling.cases.repo;

import java.util.Optional;

import com.mikostuff.payment.casehandling.cases.model.Case;

public interface CaseRepo {

	Optional<Case> find(String caseId);

	Case save(Case caseToSave);
}
