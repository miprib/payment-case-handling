package com.mikostuff.payment.casehandling.cases.repo;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.cases.repo.jparepo.CasePaymentJpaRepo;
import com.mikostuff.payment.casehandling.mapper.repo.RepoMapper;

@Component
@Primary
public class DefaultCasePaymentRepo implements CasePaymentRepo {

	private final CasePaymentJpaRepo casePaymentJpaRepo;
	private final RepoMapper<CaseEntity, Case> repoMapper;

	public DefaultCasePaymentRepo(CasePaymentJpaRepo casePaymentJpaRepo, RepoMapper<CaseEntity, Case> caseRepoMapper) {
		this.casePaymentJpaRepo = casePaymentJpaRepo;
		this.repoMapper = caseRepoMapper;
	}

	@Override
	public Optional<Case> find(Long paymentId) {
		return casePaymentJpaRepo.findByPaymentPaymentId(paymentId).map(repoMapper::toModel);
	}
}
