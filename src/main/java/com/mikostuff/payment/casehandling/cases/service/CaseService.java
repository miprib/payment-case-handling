package com.mikostuff.payment.casehandling.cases.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikostuff.payment.casehandling.cases.repo.CaseRepo;
import com.mikostuff.payment.casehandling.cases.repo.entity.PaymentCaseEntity;
import com.mikostuff.payment.casehandling.exception.NotFoundException;

@Service
public class CaseService {

	private final static String NOT_FOUND = "Case '%d' doesn't exist.";

	private final CaseRepo caseRepo;

	public CaseService(CaseRepo caseRepo) {
		this.caseRepo = caseRepo;
	}

	public PaymentCaseEntity findById(Long id) {
		return caseRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND, id)));
	}

	public List<PaymentCaseEntity> findAll() {
		return caseRepo.findAll();
	}

	public PaymentCaseEntity save(PaymentCaseEntity paymentCaseEntity) {
		return caseRepo.save(paymentCaseEntity);
	}

	public void delete(Long id) {
		caseRepo.deleteById(id);
	}
}
