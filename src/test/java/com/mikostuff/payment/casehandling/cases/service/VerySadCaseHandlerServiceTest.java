package com.mikostuff.payment.casehandling.cases.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.exception.NotFoundException;
import com.mikostuff.payment.casehandling.mother.CaseModelMother;

class VerySadCaseHandlerServiceTest {

	private CaseHandlerService caseHandlerService;

	private FakeCaseRepo caseRepo;
	private FakeCaseResolutionRepo caseResolutionRepo;
	private FakeCaseValidator caseValidator;
	private FakeResolutionValidator resolutionValidator;

	@BeforeEach
	void setup() {
		caseRepo = new FakeCaseRepo();
		caseResolutionRepo = new FakeCaseResolutionRepo(caseRepo);
		caseValidator = new FakeCaseValidator();
		resolutionValidator = new FakeResolutionValidator();

		caseHandlerService = new DefaultCaseHandlerService(caseRepo, caseResolutionRepo, caseValidator,
				resolutionValidator);
	}

	@Test
	void find_givenExistingCaseId_thenReturnThatCase() {
		// Tests are written in the given, when, then pattern:
		// Given
		Case expectedCase = caseRepo.save(CaseModelMother.validFinnishCase());

		// When
		Case actualCase = caseHandlerService.find(expectedCase.id());

		// Then
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	@Test
	void find_givenNonExistingCaseId_thenThrowNotFoundException() {
		Throwable thrown = catchThrowable(() -> {
			caseHandlerService.find("THE ANSWER TO LIFE, THE UNIVERSE AND EVERYTHING");
		});

		assertThat(thrown).isInstanceOf(NotFoundException.class);
	}

	@ParameterizedTest
	@EnumSource(Resolution.class)
	void findResolution_givenResolvedCaseId_thenReturnResolution(Resolution resolution) {
		Case validFinnishCase = caseRepo.save(CaseModelMother.validFinnishCase());
		caseResolutionRepo.saveResolution(validFinnishCase, resolution);

		Resolution actualResolution = caseHandlerService.findResolution(validFinnishCase.id());

		assertThat(actualResolution).isEqualTo(resolution);
	}

	@ParameterizedTest
	@EnumSource(Resolution.class)
	void findResolution_givenUnresolvedCaseId_thenThrowNotFoundException(Resolution resolution) {
		Case validFinnishCase = caseRepo.save(CaseModelMother.validFinnishCase());
		Case validDanishCase = caseRepo.save(CaseModelMother.validDanishCase());
		caseResolutionRepo.saveResolution(validFinnishCase, resolution);

		Throwable thrown = catchThrowable(() -> {
			caseHandlerService.findResolution(validDanishCase.id());
		});

		assertThat(thrown).isInstanceOf(NotFoundException.class);
	}

	@ParameterizedTest
	@EnumSource(Resolution.class)
	void findAll_givenResolution_thenReturnCasesOfThatResolution(Resolution resolution) {
		Map<String, Case> caseMap = createCaseMap();
		caseMap.forEach((key, value) -> caseRepo.save(value));
		caseResolutionRepo.saveResolution(caseMap.get("validFinnishCase"), resolution);
		caseResolutionRepo.saveResolution(caseMap.get("validDanishCase"), resolution);
		List<Case> expectedCases = List.of(caseMap.get("validFinnishCase"), caseMap.get("validDanishCase"));

		List<Case> actualCases = caseHandlerService.findAll(resolution);

		assertThat(actualCases).containsAll(expectedCases).hasSize(expectedCases.size());
	}

	@ParameterizedTest
	@EnumSource(Resolution.class) // To see if unresolved means no resolution at all
	void findAll_givenNull_thenReturnUnresolvedCases(Resolution resolution) {
		Map<String, Case> caseMap = createCaseMap();
		caseMap.forEach((key, value) -> caseRepo.save(value));
		caseResolutionRepo.saveResolution(caseMap.get("validFinnishCase"), resolution);
		caseResolutionRepo.saveResolution(caseMap.get("validDanishCase"), resolution);
		List<Case> expectedCases = List.of(caseMap.get("validNorwegianCase"), caseMap.get("validSwedishCase"));

		List<Case> actualCases = caseHandlerService.findAll(null);

		assertThat(actualCases).containsAll(expectedCases).hasSize(expectedCases.size());
	}

	@ParameterizedTest
	@EnumSource(Resolution.class)
	void resolve_givenResolution_thenShouldResolve(Resolution resolution) {
		Case expectedCase = caseRepo.save(CaseModelMother.validNorwegianCase());

		Case actualCase = caseHandlerService.resolve(expectedCase.id(), resolution); // ????????????
		Resolution actualResolution = caseResolutionRepo.findResolution(actualCase.id()).orElseThrow();

		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
		assertThat(actualResolution).isEqualTo(resolution);
	}

	@Test
	void save_givenValidCase_thenReturnSavedCase() {
		Case expectedCase = caseRepo.save(CaseModelMother.validNorwegianCase());
		Case actualCase = caseHandlerService.save(expectedCase);
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	private Map<String, Case> createCaseMap() {
		Map<String, Case> caseMap = new HashMap<>();
		caseMap.put("validFinnishCase", CaseModelMother.validFinnishCase());
		caseMap.put("validDanishCase", CaseModelMother.validDanishCase());
		caseMap.put("validNorwegianCase", CaseModelMother.validNorwegianCase());
		caseMap.put("validSwedishCase", CaseModelMother.validSwedishCase());
		return caseMap;
	}
}
