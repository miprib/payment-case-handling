package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;

public abstract class BaseCase implements CaseAbstraction {

	private static final String RESOLUTION_COMMAND_NOT_FOUND = "Resolution '%s' doesn't have a command configured";
	private static final String ACCEPT_CASE_COMMAND = "acceptCaseCommand";
	private static final String REJECT_CASE_COMMAND = "rejectCaseCommand";

	private final Case body;

//	private final Command<Case> saveCaseCommand;
//	private final Map<String, ResolutionCommand> resolutionCommands;

	private final List<Command<Case>> commands;

	public BaseCase(Case body, List<Command<Case>> commands) {
		this.body = body;
//		this.saveCaseCommand = saveCaseCommand;
//		this.resolutionCommands = resolutionCommands;
		this.commands = commands;
	}

	@Override
	public abstract Type type();

	@Override
	public Case body() {
		return body;
	}

	@Override
	public void executeCommands() {
		commands.forEach(command -> command.execute(body));
	}

//	@Override
//	public void save() {
//		saveCaseCommand.execute(body);
//	}
//
//	@Override
//	public void resolve(Resolution resolution) {
//		findResolutionCommand(resolution).execute(body);
//	}
//
//	private ResolutionCommand findResolutionCommand(Resolution resolution) {
//		// We could implement a normal external factory here
//		switch (resolution) {
//		case ACCEPTED:
//			return resolutionCommands.get(ACCEPT_CASE_COMMAND);
//		case REJECTED:
//			return resolutionCommands.get(REJECT_CASE_COMMAND);
//		default:
//			throw new NotFoundException(String.format(RESOLUTION_COMMAND_NOT_FOUND, resolution.name()));
//		}
//	}
}
