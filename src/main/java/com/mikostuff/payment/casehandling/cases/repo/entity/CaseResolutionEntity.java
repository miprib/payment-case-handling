package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "case_resolution", schema = "payment")
public class CaseResolutionEntity {

	@Id
	private String id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private CaseEntity paymentCase;

	@Column(length = 10)
	private String resolution;

	public CaseResolutionEntity(CaseEntity paymentCase, String resolution) {
		this.paymentCase = paymentCase;
		this.resolution = resolution;
	}

	public CaseResolutionEntity() {
	}

	public String getId() {
		return id;
	}

	public CaseEntity getPaymentCase() {
		return paymentCase;
	}

	public String getResolution() {
		return resolution;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CaseResolutionEntity)) {
			return false;
		}
		CaseResolutionEntity other = (CaseResolutionEntity) o;
		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}