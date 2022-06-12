package com.mikostuff.payment.casehandling.mother;

import java.math.BigDecimal;

import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.cases.repo.entity.MoneyEntity;
import com.mikostuff.payment.casehandling.cases.repo.entity.PaymentEntity;

public class CaseEntityMother {

	public static CaseEntity validFinnishCase() {
		MoneyEntity money = new MoneyEntity(BigDecimal.valueOf(10_000), "USD");
		PaymentEntity payment = new PaymentEntity(112L, money);
		return new CaseEntity("PCH_1337", "FI", payment);
	}
}
