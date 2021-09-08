package com.mikostuff.payment.casehandling.cases.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.service.DefaultManagerService;
import com.mikostuff.payment.casehandling.cases.service.ManagerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/cases/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CaseStatisticsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaseStatisticsController.class);

	private final ManagerService managerService;

	public CaseStatisticsController(DefaultManagerService defaultManagerService) {
		this.managerService = defaultManagerService;
	}

	@ApiOperation("Calculate the total amount of from unresolved payments")
	@GetMapping("/unresolved-payments")
	public BigDecimal findCase() {
		LOGGER.info("Calculate the total amount of from unresolved payments request");

		return managerService.calculateTotalAmountOfUnresolvedPayments();
	}

	@ApiOperation("Find the amount of cases for each country")
	@GetMapping("/country-cases")
	public Map<CountryCode, Long> findCases() {
		LOGGER.info("Find the amount of cases for each country request");

		return toCountryCodeMap(managerService.findCaseNumberForAllTypes());
	}

	private Map<CountryCode, Long> toCountryCodeMap(Map<Type, Long> caseCountByType) {
		Map<CountryCode, Long> caseCountByCountry = new HashMap<>();
		for (CountryCode countryCode : CountryCode.values()) {
			caseCountByCountry.put(countryCode, findValueByCountry(caseCountByType, countryCode));
		}
		return caseCountByCountry;
	}

	private Long findValueByCountry(Map<Type, Long> caseCountByType, CountryCode countryCode) {
		Long caseCount = caseCountByType.get(Type.find(countryCode.name()));
		return caseCount == null ? 0L : caseCount;
	}
}
