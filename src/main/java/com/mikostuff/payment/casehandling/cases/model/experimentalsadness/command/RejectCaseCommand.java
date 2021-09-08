package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;

@Component
public class RejectCaseCommand extends ResolutionCommand {

	public RejectCaseCommand(CaseResolutionRepo defaultCaseResolutionRepo) {
		super(defaultCaseResolutionRepo);
	}

	@Override
	public Resolution resolution() {
		return Resolution.REJECTED;
	}
}
