package com.mikostuff.payment.casehandling.cases.repo;

import java.util.List;
import java.util.Optional;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;

public interface CaseResolutionRepo {
	Optional<Resolution> findResolution(String caseId);

	List<Case> findCases(Resolution resolution);

	List<Case> findUnresolvedCases();

	Case saveResolution(Case caseToResolve, Resolution resolution);
}
