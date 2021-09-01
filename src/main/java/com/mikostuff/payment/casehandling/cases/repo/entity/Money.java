package com.mikostuff.payment.casehandling.cases.repo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Money {

	// TODO: Implement normal money representation like
	// https://www.joda.org/joda-money/

	private BigDecimal amount;

	@Column(length = 3)
	private String currency;

	public Money() {
	}

	public Money(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}
}
