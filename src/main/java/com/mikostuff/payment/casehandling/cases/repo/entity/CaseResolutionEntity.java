package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class CaseResolutionEntity {

	public enum Status {
		ACCEPTED, REJECTED
	}

	@Id
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private PaymentCaseEntity paymentCase;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Status resolution;

	public CaseResolutionEntity(Long id, PaymentCaseEntity paymentCase, Status resolution) {
		this.id = id;
		this.paymentCase = paymentCase;
		this.resolution = resolution;
	}

	public CaseResolutionEntity() {
	}

	public Long getId() {
		return id;
	}

	public PaymentCaseEntity getPaymentCase() {
		return paymentCase;
	}

	public Status getResolution() {
		return resolution;
	}
}