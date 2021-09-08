package com.mikostuff.payment.casehandling.cases.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseResolutionEntity;
import com.mikostuff.payment.casehandling.mapper.repo.RepoMapper;

@Repository
@Primary
public class DefaultCaseResolutionRepo implements CaseResolutionRepo {

	private static final String SELECT_CASES_BY_RESOLUTION = "SELECT c FROM CaseResolutionEntity cr JOIN cr.paymentCase c WHERE cr.resolution = :resolution";
	private static final String SELECT_CASES_UNRESOLVED = "SELECT c FROM CaseEntity c WHERE NOT EXISTS (FROM CaseResolutionEntity cr WHERE cr.paymentCase = c)";
	private static final String SELECT_CASERESOLUTION_BY_CASEID = "SELECT cr FROM CaseResolutionEntity cr WHERE cr.paymentCase.id = :caseId";

	private final EntityManager em;
	private final RepoMapper<CaseEntity, Case> repoMapper;

	public DefaultCaseResolutionRepo(EntityManager em, RepoMapper<CaseEntity, Case> caseRepoMapper) {
		this.em = em;
		this.repoMapper = caseRepoMapper;
	}

	@Override
	public List<Case> findCases(Resolution resolution) {
		List<CaseEntity> cases = em.createQuery(SELECT_CASES_BY_RESOLUTION, CaseEntity.class)
				.setParameter("resolution", resolution.name()).getResultList();
		return repoMapper.toModels(cases);
	}

	@Override
	public List<Case> findUnresolvedCases() {
		List<CaseEntity> unresolvedCases = em.createQuery(SELECT_CASES_UNRESOLVED, CaseEntity.class).getResultList();
		return repoMapper.toModels(unresolvedCases);
	}

	@Override
	public Optional<Resolution> findResolution(String caseId) {
		List<CaseResolutionEntity> result = em.createQuery(SELECT_CASERESOLUTION_BY_CASEID, CaseResolutionEntity.class)
				.setParameter("caseId", caseId).getResultList();
		return Optional.ofNullable(result.isEmpty() ? null : Resolution.find(result.get(0).getResolution()));
	}

	@Override
	public Case saveResolution(Case caseToResolve, Resolution resolution) {
		CaseEntity caseEntity = em.merge(repoMapper.toEntity(caseToResolve));
		CaseResolutionEntity caseResolution = new CaseResolutionEntity(caseEntity, resolution.name());
		em.persist(caseResolution);
		return repoMapper.toModel(caseResolution.getPaymentCase());
	}
}
