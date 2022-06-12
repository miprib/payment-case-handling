package com.mikostuff.payment.casehandling.cases.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.mapper.repo.RepoMapper;
import com.mikostuff.payment.casehandling.mother.CaseEntityMother;
import com.mikostuff.payment.casehandling.mother.CaseModelMother;

class CaseRepoMapperTest {

	private static RepoMapper<CaseEntity, Case> caseRepoMapper;

	@BeforeAll
	static void setup() {
		caseRepoMapper = new CaseRepoMapper();
	}

	@Test
	void toModel_givenNull_thenReturnNull() {
		assertThat(caseRepoMapper.toModel(null)).isNull();
	}

	@Test
	void toModel_givenCaseEntity_thenReturnEquivalentModel() {
		Case expectedCase = CaseModelMother.validFinnishCase();
		Case actualCase = caseRepoMapper.toModel(CaseEntityMother.validFinnishCase());
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	@Test
	void toModels_givenNull_thenReturnNull() {
		assertThat(caseRepoMapper.toModels(null)).isNull();
	}

	@Test
	void toModels_givenCaseEntities_thenReturnEquivalentModels() {
		List<Case> expectedCases = List.of(CaseModelMother.validFinnishCase(), CaseModelMother.validFinnishCase());

		List<CaseEntity> caseEntities = List.of(CaseEntityMother.validFinnishCase(),
				CaseEntityMother.validFinnishCase());
		List<Case> actualCases = caseRepoMapper.toModels(caseEntities);

		assertThat(actualCases).usingRecursiveComparison().isEqualTo(expectedCases);
		assertThat(actualCases).hasSize(expectedCases.size());
	}

	@Test
	void toEntity_givenNull_thenReturnNull() {
		assertThat(caseRepoMapper.toEntity(null)).isNull();
	}

	@Test
	void toEntity_givenCaseModel_thenReturnEquivalentEntity() {
		CaseEntity expectedCase = CaseEntityMother.validFinnishCase();
		CaseEntity actualCase = caseRepoMapper.toEntity(CaseModelMother.validFinnishCase());
		assertThat(actualCase).usingRecursiveComparison().isEqualTo(expectedCase);
	}

	@Test
	void toEntities_givenNull_thenReturnNull() {
		assertThat(caseRepoMapper.toEntities(null)).isNull();
	}

	@Test
	void toEntities_givenCaseModels_thenReturnEquivalentEntities() {
		List<CaseEntity> expectedCases = List.of(CaseEntityMother.validFinnishCase(),
				CaseEntityMother.validFinnishCase());

		List<Case> caseModels = List.of(CaseModelMother.validFinnishCase(), CaseModelMother.validFinnishCase());
		List<CaseEntity> actualCases = caseRepoMapper.toEntities(caseModels);

		assertThat(actualCases).usingRecursiveComparison().isEqualTo(expectedCases);
		assertThat(actualCases).hasSize(expectedCases.size());
	}
}
