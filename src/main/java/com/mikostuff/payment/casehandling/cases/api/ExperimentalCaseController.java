package com.mikostuff.payment.casehandling.cases.api;

import java.util.List;

import javax.validation.Valid;

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
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.ExperimentalCaseHandlerService;
import com.mikostuff.payment.casehandling.mapper.api.ApiMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/experimental/cases", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ExperimentalCaseController {

	private final ExperimentalCaseHandlerService caseService;
	private final ApiMapper<CaseRequest, CaseResponse, Case> apiMapper;

	public ExperimentalCaseController(ExperimentalCaseHandlerService caseService,
			ApiMapper<CaseRequest, CaseResponse, Case> caseApiMapper) {
		this.caseService = caseService;
		this.apiMapper = caseApiMapper;
	}

	@ApiOperation("Find a case")
	@GetMapping("{caseId}")
	public CaseResponse findCase(@PathVariable String caseId) {
		return apiMapper.toResponse(caseService.find(caseId));
	}

	@ApiOperation(value = "Find all cases by resolution", notes = "'CaseResolution' not provided - unresolved cases \n'CaseResolution.ACCEPTED' - accepted cases \n'CaseResolution.DENIED' - denied cases")
	@GetMapping
	public List<CaseResponse> findAllCases(@RequestParam(required = false) CaseResolutionState caseResolution) {
		List<Case> cases = caseService.findAll(caseResolution == null ? null : toResolution(caseResolution));
		return apiMapper.toResponses(cases);
	}

	@ApiOperation("Create a case")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCase(@RequestBody @Valid CaseRequest caseRequest) {
		Case newCase = apiMapper.toModel(caseRequest);
		caseService.save(newCase);
	}

	@ApiOperation("Accept a case")
	@PostMapping("/accept/{caseId}")
	public void acceptCase(@PathVariable String caseId) {
		caseService.accept(caseId);
	}

	@ApiOperation("Reject a case")
	@PostMapping("/reject/{caseId}")
	public void rejectCase(@PathVariable String caseId) {
		caseService.reject(caseId);
	}

	private Resolution toResolution(CaseResolutionState caseResolution) {
		return Resolution.find(caseResolution.name());
	}
}
