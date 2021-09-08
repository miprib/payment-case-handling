package com.mikostuff.payment.casehandling.cases.repo;

import org.springframework.stereotype.Component;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Money;
import com.mikostuff.payment.casehandling.cases.model.Payment;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.repo.entity.CaseEntity;
import com.mikostuff.payment.casehandling.cases.repo.entity.MoneyEntity;
import com.mikostuff.payment.casehandling.cases.repo.entity.PaymentEntity;
import com.mikostuff.payment.casehandling.mapper.repo.RepoMapper;

@Component
class CaseRepoMapper extends RepoMapper<CaseEntity, Case> {

	@Override
	protected Case safeToModel(CaseEntity caseEntity) {
		return new Case(caseEntity.getId(), toCaseType(caseEntity.getCountry()),
				toPaymentModel(caseEntity.getPayment()));
	}

	@Override
	protected CaseEntity safeToEntity(Case caseModel) {
		return new CaseEntity(caseModel.id(), caseModel.type().countryCode(), toPaymentEntity(caseModel.payment()));
	}

	private Money toMoneyModel(MoneyEntity moneyEntity) {
		return new Money(moneyEntity.getAmount(), moneyEntity.getCurrency());
	}

	private MoneyEntity toMoneyEntity(Money moneyModel) {
		return new MoneyEntity(moneyModel.amount(), moneyModel.currency());
	}

	private Payment toPaymentModel(PaymentEntity paymentEntity) {
		return new Payment(paymentEntity.getPaymentId(), toMoneyModel(paymentEntity.getMoney()));
	}

	private PaymentEntity toPaymentEntity(Payment paymentModel) {
		return new PaymentEntity(paymentModel.id(), toMoneyEntity(paymentModel.money()));
	}

	private Type toCaseType(String countryCode) {
		return Type.find(countryCode);
	}
}
