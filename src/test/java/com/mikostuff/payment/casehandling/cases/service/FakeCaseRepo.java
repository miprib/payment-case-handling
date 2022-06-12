package com.mikostuff.payment.casehandling.cases.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.CaseRepo;

public class FakeCaseRepo implements CaseRepo {

	private List<Case> fakeCases = new ArrayList<>();

	@Override
	public Optional<Case> find(String caseId) {
		return fakeCases.stream().filter(c -> c.id().equals(caseId)).findFirst();
	}

	@Override
	public Case save(Case caseToSave) {
		Case savedCase = new Case(caseToSave.id(), caseToSave.type(), caseToSave.payment());
		fakeCases.add(savedCase);
		return savedCase;
	}

	public List<Case> findAll() {
		return fakeCases;
	}
}
