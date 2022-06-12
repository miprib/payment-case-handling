package com.mikostuff.payment.casehandling.cases.repo;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.cases.repo.jparepo.CaseJpaRepo;
import com.mikostuff.payment.casehandling.mapper.repo.RepoMapper;

@Component
@Primary
class DefaultCaseRepo implements CaseRepo {

	private final CaseJpaRepo caseJpaRepo;
	private final RepoMapper<CaseEntity, Case> repoMapper;

	public DefaultCaseRepo(CaseJpaRepo caseJpaRepo, RepoMapper<CaseEntity, Case> caseRepoMapper) {
		this.caseJpaRepo = caseJpaRepo;
		this.repoMapper = caseRepoMapper;
	}

	@Override
	public Optional<Case> find(String id) {
		return caseJpaRepo.findById(id).map(repoMapper::toModel);
	}

	@Override
	public Case save(Case unsavedCase) {
		CaseEntity savedCase = caseJpaRepo.save(repoMapper.toEntity(unsavedCase));
		return repoMapper.toModel(savedCase);
	}
}
