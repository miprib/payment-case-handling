package com.mikostuff.payment.casehandling.mother;

import java.math.BigDecimal;

import com.mikostuff.payment.casehandling.cases.api.CaseRequest;
import com.mikostuff.payment.casehandling.cases.api.CountryCode;

public class CaseRequestMother {

	public static CaseRequest validFinnishCase() {
		return new CaseRequest(BigDecimal.valueOf(10_000), "USD", 112L, CountryCode.FI);
	}
}
