package com.mikostuff.payment.casehandling.cases.fixture;

import java.math.BigDecimal;
import java.util.List;

import com.mikostuff.payment.casehandling.cases.api.CaseResponse;
import com.mikostuff.payment.casehandling.cases.api.CountryCode;

public class CaseResponseFixture {

	public static List<CaseResponse> expectedCases() {
		return List.of(new CaseResponse("PCH_1", new BigDecimal("100.00"), "EUR", 1L, CountryCode.DK),
				new CaseResponse("PCH_2", new BigDecimal("100.00"), "EUR", 2L, CountryCode.DK),
				new CaseResponse("PCH_3", new BigDecimal("100.00"), "EUR", 3L, CountryCode.FI),
				new CaseResponse("PCH_4", new BigDecimal("100.00"), "EUR", 4L, CountryCode.FI),
				new CaseResponse("PCH_5", new BigDecimal("100.00"), "EUR", 5L, CountryCode.NO),
				new CaseResponse("PCH_6", new BigDecimal("100.00"), "EUR", 6L, CountryCode.NO),
				new CaseResponse("PCH_7", new BigDecimal("100.00"), "EUR", 7L, CountryCode.SE),
				new CaseResponse("PCH_8", new BigDecimal("100.00"), "EUR", 8L, CountryCode.SE),
				new CaseResponse("PCH_9", new BigDecimal("100.00"), "EUR", 9L, CountryCode.SE),
				new CaseResponse("PCH_10", new BigDecimal("100.00"), "EUR", 10L, CountryCode.SE));
	}

	public static List<CaseResponse> expectedAcceptedCases() {
		return List.of(new CaseResponse("PCH_1", new BigDecimal("100.00"), "EUR", 1L, CountryCode.DK),
				new CaseResponse("PCH_5", new BigDecimal("100.00"), "EUR", 5L, CountryCode.NO),
				new CaseResponse("PCH_8", new BigDecimal("100.00"), "EUR", 8L, CountryCode.SE));
	}

	public static List<CaseResponse> expectedRejectedCases() {
		return List.of(new CaseResponse("PCH_3", new BigDecimal("100.00"), "EUR", 3L, CountryCode.FI),
				new CaseResponse("PCH_7", new BigDecimal("100.00"), "EUR", 7L, CountryCode.SE));
	}

	public static List<CaseResponse> expectedUnresolvedCases() {
		return List.of(new CaseResponse("PCH_2", new BigDecimal("100.00"), "EUR", 2L, CountryCode.DK),
				new CaseResponse("PCH_4", new BigDecimal("100.00"), "EUR", 4L, CountryCode.FI),
				new CaseResponse("PCH_6", new BigDecimal("100.00"), "EUR", 6L, CountryCode.NO),
				new CaseResponse("PCH_9", new BigDecimal("100.00"), "EUR", 9L, CountryCode.SE),
				new CaseResponse("PCH_10", new BigDecimal("100.00"), "EUR", 10L, CountryCode.SE));
	}
}
