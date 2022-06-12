package com.mikostuff.payment.casehandling.mother;

import java.math.BigDecimal;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Money;
import com.mikostuff.payment.casehandling.cases.model.Payment;
import com.mikostuff.payment.casehandling.cases.model.Type;

public class CaseModelMother {

	public static Case validFinnishCase() {
		Money money = new Money(BigDecimal.valueOf(10_000), "USD");
		Payment payment = new Payment(112L, money);
		return new Case("PCH_1337", Type.FINNISH, payment);
	}

	public static Case validFinnishCaseWithNoId() {
		Money money = new Money(BigDecimal.valueOf(10_000), "USD");
		Payment payment = new Payment(112L, money);
		return new Case(null, Type.FINNISH, payment);
	}

	public static Case validDanishCase() {
		Money money = new Money(BigDecimal.valueOf(10_000), "USD");
		Payment payment = new Payment(112L, money);
		return new Case("PCH_112", Type.DANISH, payment);
	}

	public static Case validSwedishCase() {
		Money money = new Money(BigDecimal.valueOf(10_000), "USD");
		Payment payment = new Payment(112L, money);
		return new Case("PCH_911", Type.SWEDISH, payment);
	}

	public static Case validNorwegianCase() {
		Money money = new Money(BigDecimal.valueOf(10_000), "USD");
		Payment payment = new Payment(112L, money);
		return new Case("PCH_777", Type.NORWEGIAN, payment);
	}
}
