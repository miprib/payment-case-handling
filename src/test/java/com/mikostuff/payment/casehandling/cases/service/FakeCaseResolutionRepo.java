package com.mikostuff.payment.casehandling.cases.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;

public class FakeCaseResolutionRepo implements CaseResolutionRepo {

	private final FakeCaseRepo fakeCaseRepo;
	private Map<Case, Resolution> caseResolutions = new HashMap<>();

	public FakeCaseResolutionRepo(FakeCaseRepo fakeCaseRepo) {
		this.fakeCaseRepo = fakeCaseRepo;
	}

	@Override
	public Optional<Resolution> findResolution(String caseId) {
		return caseResolutions.entrySet().stream().filter(entry -> entry.getKey().id().equals(caseId))
				.map(Map.Entry::getValue).findFirst();
	}

	@Override
	public List<Case> findCases(Resolution resolution) {
		return caseResolutions.entrySet().stream().filter(entry -> entry.getValue().equals(resolution))
				.map(Map.Entry::getKey).collect(Collectors.toList());
	}

	@Override
	public List<Case> findUnresolvedCases() {
		return fakeCaseRepo.findAll().stream().filter(c -> caseResolutions.get(c) == null).collect(Collectors.toList());
	}

	@Override
	public Case saveResolution(Case caseToResolve, Resolution resolution) {
		caseResolutions.put(caseToResolve, resolution);
		return caseToResolve;
	}

}
