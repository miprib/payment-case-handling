package com.mikostuff.payment.casehandling.cases.api;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Money;
import com.mikostuff.payment.casehandling.cases.model.Payment;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.mapper.api.ApiMapper;

@Component
class CaseApiMapper extends ApiMapper<CaseRequest, CaseResponse, Case> {

	@Override
	protected Case safeToModel(CaseRequest caseRequest) {
		return new Case(noId(), toCaseType(caseRequest.getCountryCode()), toPayment(caseRequest));
	}

	@Override
	protected CaseResponse safeToResponse(Case caseModel) {
		return new CaseResponse(caseModel.id(), caseModel.payment().money().amount(),
				caseModel.payment().money().currency(), caseModel.payment().id(), toCountryCode(caseModel.type()));
	}

	private String noId() {
		return null;
	}

	private Money toMoney(CaseRequest caseRequest) {
		return new Money(caseRequest.getAmount(), caseRequest.getCurrency());
	}

	private Payment toPayment(CaseRequest caseRequest) {
		return new Payment(caseRequest.getPaymentId(), toMoney(caseRequest));
	}

	private Type toCaseType(CountryCode countryCode) {
		return Type.find(countryCode.name());
	}

	private CountryCode toCountryCode(Type caseType) {
		return CountryCode.find(caseType.countryCode());
	}
}
