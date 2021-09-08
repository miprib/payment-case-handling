package com.mikostuff.payment.casehandling.cases.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.mapper.api.ApiMapper;
import com.mikostuff.payment.casehandling.mother.CaseModelMother;
import com.mikostuff.payment.casehandling.mother.CaseRequestMother;
import com.mikostuff.payment.casehandling.mother.CaseResponseMother;

class CaseApiMapperTest {

	// We could reuse some mapping tests with an abstract class
	private static ApiMapper<CaseRequest, CaseResponse, Case> caseApiMapper;

	@BeforeAll
	static void setup() {
		caseApiMapper = new CaseApiMapper();
	}

	@Test
	void toModel_givenNull_thenReturnNull() {
		assertThat(caseApiMapper.toModel(null)).isNull();
	}

	@Test
	void toModel_givenCaseRequest_thenReturnEquivalentModel() {
		Case expectedCase = CaseModelMother.validFinnishCaseWithNoId();
		Case actualCase = caseApiMapper.toModel(CaseRequestMother.validFinnishCase());
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	@Test
	void toResponse_givenNull_thenReturnNull() {
		assertThat(caseApiMapper.toResponse(null)).isNull();
	}

	@Test
	void toResponse_givenCaseModel_thenReturnEquivalentResponse() {
		CaseResponse expectedCase = CaseResponseMother.validFinnishCase();
		CaseResponse actualCase = caseApiMapper.toResponse(CaseModelMother.validFinnishCase());
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	@Test
	void toResponses_givenNull_thenReturnNull() {
		assertThat(caseApiMapper.toResponses(null)).isNull();
	}

	@Test
	void toResponses_givenCaseModels_thenReturnEquivalentResponses() {
		List<CaseResponse> expectedCases = List.of(CaseResponseMother.validFinnishCase(),
				CaseResponseMother.validFinnishCase());

		List<Case> caseModels = List.of(CaseModelMother.validFinnishCase(), CaseModelMother.validFinnishCase());
		List<CaseResponse> actualCases = caseApiMapper.toResponses(caseModels);

		assertThat(actualCases).usingRecursiveComparison().isEqualTo(expectedCases);
		assertThat(actualCases).hasSize(expectedCases.size());
	}
}
