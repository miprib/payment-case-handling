package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;

public class SwedishCase extends BaseCase {

	public SwedishCase(Case body, List<Command<Case>> commands) {
		super(body, commands);
	}

	@Override
	public Type type() {
		return Type.SWEDISH;
	}

	// Override anything else that SWEDISH case might do differently
}