package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;

public abstract class ResolutionCommand implements Command<Case> {

	private final CaseResolutionRepo caseResolutionRepo;

	public ResolutionCommand(CaseResolutionRepo defaultCaseResolutionRepo) {
		this.caseResolutionRepo = defaultCaseResolutionRepo;
	}

	public abstract Resolution resolution();

	@Override
	public void execute(Case caseBody) {
		caseResolutionRepo.saveResolution(caseBody, resolution());
	}
}
