package com.mikostuff.payment.casehandling.mother;

import java.math.BigDecimal;

import com.mikostuff.payment.casehandling.cases.api.CaseResponse;
import com.mikostuff.payment.casehandling.cases.api.CountryCode;

public class CaseResponseMother {

	public static CaseResponse validFinnishCase() {
		return new CaseResponse("PCH_1337", BigDecimal.valueOf(10_000), "USD", 112L, CountryCode.FI);
	}
}
