package com.mikostuff.payment.casehandling.cases.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Case {

	@Min(1)
	private final String id;

	@NotNull
	@Valid
	private final Payment payment;

	@NotNull
	private final Type type;

	public Case(String id, Type type, Payment payment) {
		this.id = id;
		this.type = type;
		this.payment = payment;
	}

	public String id() {
		return id;
	}

	public Type type() {
		return type;
	}

	public Payment payment() {
		return payment;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", payment=" + payment + ", type=" + type + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Case)) {
			return false;
		}
		Case other = (Case) o;
		return id != null && id.equals(other.id());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	// Case related calculations would go here.
}
