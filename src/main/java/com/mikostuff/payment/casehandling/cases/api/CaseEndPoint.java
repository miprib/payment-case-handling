package com.mikostuff.payment.casehandling.cases.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikostuff.payment.casehandling.cases.repo.entity.PaymentCaseEntity;
import com.mikostuff.payment.casehandling.cases.service.CaseService;

@RestController
@RequestMapping(path = "api/case", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CaseEndPoint {

	private final CaseService caseService;

	public CaseEndPoint(CaseService caseService) {
		this.caseService = caseService;
	}

	@GetMapping("{id}")
	public PaymentCaseEntity findById(@PathVariable @Min(1) Long id) {
		return caseService.findById(id);
	}

	@GetMapping
	public List<PaymentCaseEntity> getAll() {
		return caseService.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public PaymentCaseEntity save(@RequestBody @Valid PaymentCaseEntity paymentCaseEntity) {
		return caseService.save(paymentCaseEntity);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable @Min(1) Long id) {
		caseService.delete(id);
	}
}
