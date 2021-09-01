package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentCaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // TODO: make this String with custom sequence e.g.: "LT_00001"

	@Embedded
	private Money money;

	@Embedded
	private Payment payment;

	public PaymentCaseEntity(Long id, Money money, Payment payment) {
		this.id = id;
		this.money = money;
		this.payment = payment;
	}

	public PaymentCaseEntity() {
	}

	public Long getId() {
		return id;
	}

	public Money getMoney() {
		return money;
	}

	public Payment getPayment() {
		return payment;
	}
}
