package com.mikostuff.payment.casehandling.cases.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mikostuff.payment.casehandling.cases.repo.entity.PaymentCaseEntity;

@Repository
public interface CaseRepo extends JpaRepository<PaymentCaseEntity, Long> {

}
