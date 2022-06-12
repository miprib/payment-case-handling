package com.mikostuff.payment.casehandling.cases.service;

import java.math.BigDecimal;
import java.util.Map;

import com.mikostuff.payment.casehandling.cases.model.Type;

public interface ManagerService {

	BigDecimal calculateTotalAmountOfUnresolvedPayments();

	Map<Type, Long> findCaseNumberForAllTypes();
}
