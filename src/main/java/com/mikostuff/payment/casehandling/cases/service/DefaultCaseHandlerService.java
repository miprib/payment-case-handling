package com.mikostuff.payment.casehandling.cases.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.CaseRepo;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;
import com.mikostuff.payment.casehandling.exception.NotFoundException;
import com.mikostuff.payment.casehandling.validation.Validator;

@Service
@Primary
public class DefaultCaseHandlerService implements CaseHandlerService {

	private final static String CASE_NOT_FOUND = "Case '%s' doesn't exist.";
	private final static String RESOLUTION_NOT_FOUND = "Case '%s' isn't resolved.";

	private final CaseRepo caseRepo;
	private final CaseResolutionRepo caseResolutionRepo;
	private final Validator<Case> caseValidator;
	private final Validator<Case> resolutionValidator;

	public DefaultCaseHandlerService(CaseRepo defaultCaseRepo, CaseResolutionRepo defaultCaseResolutionRepo,
			Validator<Case> caseValidator, Validator<Case> resolutionValidator) {
		this.caseRepo = defaultCaseRepo;
		this.caseResolutionRepo = defaultCaseResolutionRepo;
		this.caseValidator = caseValidator;
		this.resolutionValidator = resolutionValidator;
	}

	@Override
	public Case find(String caseId) {
		return findCase(caseId);
	}

	@Override
	public Resolution findResolution(String caseId) {
		return caseResolutionRepo.findResolution(caseId)
				.orElseThrow(() -> new NotFoundException(String.format(RESOLUTION_NOT_FOUND, caseId)));
	}

	@Override
	public List<Case> findAll(Resolution resolution) {
		return resolution == null ? caseResolutionRepo.findUnresolvedCases() : caseResolutionRepo.findCases(resolution);
	}

	@Override
	@Transactional
	public Case save(Case unsavedCase) {
		caseValidator.validate(unsavedCase);
		return caseRepo.save(unsavedCase);
	}

	@Override
	@Transactional
	public Case resolve(String caseId, Resolution resolution) {
		Case paymentCase = findCase(caseId);
		resolutionValidator.validate(paymentCase);
		return caseResolutionRepo.saveResolution(paymentCase, resolution);
	}

	private Case findCase(String caseId) {
		return caseRepo.find(caseId).orElseThrow(() -> new NotFoundException(String.format(CASE_NOT_FOUND, caseId)));
	}

}
