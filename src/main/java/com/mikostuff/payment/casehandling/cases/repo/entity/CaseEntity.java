package com.mikostuff.payment.casehandling.cases.repo.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.mikostuff.payment.casehandling.idgenerator.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name = "payment_case", schema = "payment")
public class CaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_sequence")
	@GenericGenerator(name = "case_sequence", strategy = "com.mikostuff.payment.casehandling.idgenerator.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PCH_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
	private String id;

	@Embedded
	private PaymentEntity payment;

	private String country;

	public CaseEntity(String id, String country, PaymentEntity payment) {
		this.id = id;
		this.country = country;
		this.payment = payment;
	}

	public CaseEntity() {
	}

	public String getId() {
		return id;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CaseEntity)) {
			return false;
		}
		CaseEntity other = (CaseEntity) o;
		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
