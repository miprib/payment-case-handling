package com.mikostuff.payment.casehandling.cases.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Payment {

	@NotNull
	@Min(1)
	private Long id;

	@NotNull
	@Valid
	private final Money money;

	public Payment(Long id, Money money) {
		this.id = id;
		this.money = money;
	}

	public Long id() {
		return id;
	}

	public Money money() {
		return money;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", money=" + money + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Payment)) {
			return false;
		}
		Payment other = (Payment) o;
		return id != null && id.equals(other.id());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	// Payment related calculations would go here.
}
