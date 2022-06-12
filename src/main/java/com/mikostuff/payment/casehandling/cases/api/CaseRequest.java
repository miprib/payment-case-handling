package com.mikostuff.payment.casehandling.cases.api;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CaseRequest {

	@NotNull
	private final BigDecimal amount;

	@NotNull
	private final String currency;

	@NotNull
	private final Long paymentId;

	@NotNull
	private final CountryCode countryCode;

	public CaseRequest(BigDecimal amount, String currency, Long paymentId, CountryCode countryCode) {
		this.amount = amount;
		this.currency = currency;
		this.paymentId = paymentId;
		this.countryCode = countryCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	@Override
	public String toString() {
		return "CaseRequest [amount=" + amount + ", currency=" + currency + ", paymentId=" + paymentId
				+ ", countryCode=" + countryCode + "]";
	}
}
