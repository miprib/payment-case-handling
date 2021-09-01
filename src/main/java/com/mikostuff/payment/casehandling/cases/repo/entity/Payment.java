package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Payment {

	private Long paymentId;

	public Payment(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Payment() {
	}

	public Long getPaymentId() {
		return paymentId;
	}
}
