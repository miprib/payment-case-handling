package com.mikostuff.payment.casehandling.cases.repo.jparepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;

@Repository
public interface CasePaymentJpaRepo extends JpaRepository<CaseEntity, String> {

	// Spring automagically generates queries with JpaRepository but it's not always
	// that obvious.
	// PaymentPaymentId translates to CaseEntity -> PaymentEntity.paymentId
	Optional<CaseEntity> findByPaymentPaymentId(Long paymentId);
}
