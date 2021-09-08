package com.mikostuff.payment.casehandling.cases.repo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mikostuff.payment.casehandling.cases.model.Type;

@Repository
@Primary
public class DefaultCaseStatisticsRepo implements CaseStatisticsRepo {

	private static final String SUM_UNRESOLVED_CASE_PAYMENT_AMOUNT = "SELECT SUM(c.payment.money.amount) FROM CaseEntity c WHERE NOT EXISTS (FROM CaseResolutionEntity cr WHERE cr.paymentCase = c)";
	private static final String COUNT_CASES_GROUPED_BY_COUNTRY = "SELECT c.country, COUNT(c) FROM CaseEntity c GROUP BY c.country";

	private final EntityManager em;

	public DefaultCaseStatisticsRepo(EntityManager em) {
		this.em = em;
	}

	@Override
	public BigDecimal sumUnresolvedCasePaymentAmount() {
		return em.createQuery(SUM_UNRESOLVED_CASE_PAYMENT_AMOUNT, BigDecimal.class).getSingleResult();
	}

	@Override
	public Map<Type, Long> countCasesForAllTypes() {
		Map<Type, Long> caseCountByType = new HashMap<>();
		em.createQuery(COUNT_CASES_GROUPED_BY_COUNTRY, Object[].class).getResultList().forEach(row -> {
			Type type = Type.find((String) row[0]);
			Long count = (Long) row[1];
			caseCountByType.put(type, count);
		});
		return caseCountByType;
	}
}
