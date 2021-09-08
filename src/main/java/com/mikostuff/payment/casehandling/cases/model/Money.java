package com.mikostuff.payment.casehandling.cases.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Money {

	@NotNull
	@Min(0)
	private BigDecimal amount;

	@Size(min = 3, max = 3)
	private String currency;

	public Money(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal amount() {
		return amount;
	}

	public String currency() {
		return currency;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + ", currency=" + currency + "]";
	}

	// Money related calculations would go here. E.g. if we needed to convert
	// between currencies
}
