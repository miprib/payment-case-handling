package com.mikostuff.payment.casehandling.cases.repo;

import java.math.BigDecimal;
import java.util.Map;

import com.mikostuff.payment.casehandling.cases.model.Type;

public interface CaseStatisticsRepo {

	BigDecimal sumUnresolvedCasePaymentAmount();

	Map<Type, Long> countCasesForAllTypes();
}
