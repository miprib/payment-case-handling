package com.mikostuff.payment.casehandling.cases.api;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class CaseResponse {

	@NotNull
	private final String id;

	@NotNull
	private final BigDecimal amount;

	@NotNull
	private final String currency;

	@NotNull
	private final Long paymentId;

	@NotNull
	private final CountryCode countryCode;

	public CaseResponse(String id, BigDecimal amount, String currency, Long paymentId, CountryCode countryCode) {
		this.id = id;
		this.amount = amount;
		this.currency = currency;
		this.paymentId = paymentId;
		this.countryCode = countryCode;
	}

	public String getId() {
		return id;
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
		return "CaseResponse [id=" + id + ", amount=" + amount + ", currency=" + currency + ", paymentId=" + paymentId
				+ ", countryCode=" + countryCode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, countryCode, currency, id, paymentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CaseResponse)) {
			return false;
		}
		CaseResponse other = (CaseResponse) obj;
		return Objects.equals(amount, other.amount) && countryCode == other.countryCode
				&& Objects.equals(currency, other.currency) && Objects.equals(id, other.id)
				&& Objects.equals(paymentId, other.paymentId);
	}
}
