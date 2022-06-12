package com.mikostuff.payment.casehandling.cases.repo.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;

@Repository
public interface CaseJpaRepo extends JpaRepository<CaseEntity, String> {
}
