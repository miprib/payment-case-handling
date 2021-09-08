package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.CaseRepo;

@Component
public class SaveCaseCommand implements Command<Case> {

	private final CaseRepo caseRepo;

	public SaveCaseCommand(CaseRepo defaultCaseRepo) {
		this.caseRepo = defaultCaseRepo;
	}

	@Override
	public void execute(Case body) {
		caseRepo.save(body);
	}
}
