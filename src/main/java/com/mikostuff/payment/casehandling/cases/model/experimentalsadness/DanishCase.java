package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;

public class DanishCase extends BaseCase {

	public DanishCase(Case body, List<Command<Case>> commands) {
		super(body, commands);
	}

	@Override
	public Type type() {
		return Type.DANISH;
	}

	// Override anything else that DANISH case might do differently
}
