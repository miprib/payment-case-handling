package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Resolution;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.AcceptCaseCommand;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.RejectCaseCommand;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.SaveCaseCommand;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.ValidateCaseCommand;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.ValidateResolutionCommand;
import com.mikostuff.payment.casehandling.cases.repo.CaseRepo;
import com.mikostuff.payment.casehandling.cases.repo.CaseResolutionRepo;
import com.mikostuff.payment.casehandling.exception.NotFoundException;
import com.mikostuff.payment.casehandling.validation.Validator;

@Transactional
@Service
public class ExperimentalCaseHandlerService {

	private final static String CASE_BODY_NOT_FOUND = "Case '%s' doesn't exist.";
	private static final String TYPE_NOT_FOUND = "Case type '%s' isn't configured";
	private static final String ACTION_NOT_FOUND = "Case action '%s' isn't configured";

	private final CaseRepo caseRepo;
	private final CaseResolutionRepo caseResolutionRepo;

	private final Validator<Case> caseValidator;
	private final Validator<Case> resolutionValidator;

	public ExperimentalCaseHandlerService(CaseRepo caseRepo, CaseResolutionRepo caseResolutionRepo,
			Validator<Case> caseValidator, Validator<Case> resolutionValidator) {
		this.caseRepo = caseRepo;
		this.caseResolutionRepo = caseResolutionRepo;
		this.caseValidator = caseValidator;
		this.resolutionValidator = resolutionValidator;
	}

	public Case find(String caseId) {
		return findCase(caseId);
	}

	public List<Case> findAll(Resolution resolution) {
		return findAllCases(resolution);
	}

	public void save(Case body) {
		generateBaseCaseAction(body, Action.SAVE).executeCommands();
	}

	public void accept(String caseId) {
		generateBaseCaseAction(findCase(caseId), Action.RESOLVE_ACCEPT).executeCommands();
	}

	public void reject(String caseId) {
		generateBaseCaseAction(findCase(caseId), Action.RESOLVE_REJECT).executeCommands();
	}

	private Case findCase(String caseId) {
		return caseRepo.find(caseId)
				.orElseThrow(() -> new NotFoundException(String.format(CASE_BODY_NOT_FOUND, caseId)));
	}

	private List<Case> findAllCases(Resolution resolution) {
		return resolution == null ? caseResolutionRepo.findUnresolvedCases() : caseResolutionRepo.findCases(resolution);
	}

	private BaseCase generateBaseCaseAction(Case body, Action action) {
		// We could implement a normal external factory here
		switch (body.type()) {
		case DANISH:
			return new DanishCase(body, generateCommands(action));
		case FINNISH:
			return new FinnishCase(body, generateCommands(action));
		case SWEDISH:
			return new SwedishCase(body, generateCommands(action));
		case NORWEGIAN:
			return new NorwegianCase(body, generateCommands(action));
		default:
			throw new NotFoundException(String.format(TYPE_NOT_FOUND, body.type().countryCode()));
		}
	}

	private List<Command<Case>> generateCommands(Action action) {
		// We could implement a normal external factory here
		switch (action) {
		case SAVE:
			return List.of(new ValidateCaseCommand(caseValidator), new SaveCaseCommand(caseRepo));
		case RESOLVE_ACCEPT:
			return List.of(new ValidateResolutionCommand(resolutionValidator),
					new AcceptCaseCommand(caseResolutionRepo));
		case RESOLVE_REJECT:
			return List.of(new ValidateResolutionCommand(resolutionValidator),
					new RejectCaseCommand(caseResolutionRepo));
		default:
			throw new NotFoundException(String.format(ACTION_NOT_FOUND, action.name()));
		}
	}
}
