package com.mikostuff.payment.casehandling.cases.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.service.CaseHandlerService;
import com.mikostuff.payment.casehandling.mapper.api.ApiMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/cases", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaseController.class);

	private final CaseHandlerService caseService;
	private final ApiMapper<CaseRequest, CaseResponse, Case> apiMapper;

	public CaseController(CaseHandlerService defaultCaseHandlerCaseService,
			ApiMapper<CaseRequest, CaseResponse, Case> caseApiMapper) {
		this.caseService = defaultCaseHandlerCaseService;
		this.apiMapper = caseApiMapper;
	}

	@ApiOperation("Find a case")
	@GetMapping("{caseId}")
	public CaseResponse findCase(@PathVariable String caseId) {
		LOGGER.info("Find a case request. Case id: {}", caseId);

		return apiMapper.toResponse(caseService.find(caseId));
	}

	@ApiOperation("Find all cases by resolution")
	@GetMapping("resolution/{caseResolution}")
	public List<CaseResponse> findAllCases(@PathVariable CaseResolutionState caseResolution) {
		LOGGER.info("Find all cases by resolution request. Case resolution: {}", caseResolution);

		Resolution resolution = caseResolution.equals(CaseResolutionState.UNRESOLVED) ? null
				: Resolution.find(caseResolution.name());
		List<Case> cases = caseService.findAll(resolution);
		return apiMapper.toResponses(cases);
	}

	@ApiOperation("Create a case")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createCase(@RequestBody @Valid CaseRequest caseRequest) {
		LOGGER.info("Create a case request. New case request: {}", caseRequest);

		Case newCase = apiMapper.toModel(caseRequest);
		Case savedCase = caseService.save(newCase);
		return savedCase.id();
	}

	@ApiOperation("Resolve a case")
	@PostMapping("/resolve/{caseId}")
	public String resolveCase(@PathVariable String caseId, @RequestParam CaseResolution caseResolution) {
		LOGGER.info("Resolve a case request. Case id: {}, resolution: {}", caseId,
				Resolution.find(caseResolution.name()));

		Case resolvedCase = caseService.resolve(caseId, Resolution.find(caseResolution.name()));
		return resolvedCase.id();
	}
}
