package com.mikostuff.payment.casehandling.cases.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.repo.CaseStatisticsRepo;

@Service
@Primary
public class DefaultManagerService implements ManagerService {

	private final CaseStatisticsRepo caseStatisticsRepo;

	public DefaultManagerService(CaseStatisticsRepo defaultCaseStatisticsRepo) {
		this.caseStatisticsRepo = defaultCaseStatisticsRepo;
	}

	public BigDecimal calculateTotalAmountOfUnresolvedPayments() {
		return caseStatisticsRepo.sumUnresolvedCasePaymentAmount();
	}

	public Map<Type, Long> findCaseNumberForAllTypes() {
		return caseStatisticsRepo.countCasesForAllTypes();
	}
}
