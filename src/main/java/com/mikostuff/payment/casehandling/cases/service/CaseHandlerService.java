package com.mikostuff.payment.casehandling.cases.service;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;

public interface CaseHandlerService {

	Case find(String caseId);

	Resolution findResolution(String caseId);

	List<Case> findAll(Resolution resolution);

	Case resolve(String caseId, Resolution resolution);

	Case save(Case unsavedCase);
}
