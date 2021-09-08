package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PaymentEntity {

	private Long paymentId;

	@Embedded
	private MoneyEntity money;

	public PaymentEntity(Long paymentId, MoneyEntity money) {
		this.paymentId = paymentId;
		this.money = money;
	}

	public PaymentEntity() {
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public MoneyEntity getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PaymentEntity)) {
			return false;
		}
		PaymentEntity other = (PaymentEntity) o;
		return paymentId != null && paymentId.equals(other.getPaymentId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
