package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;

public class FinnishCase extends BaseCase {

	public FinnishCase(Case body, List<Command<Case>> commands) {
		super(body, commands);
	}

	@Override
	public Type type() {
		return Type.FINNISH;
	}

	// Override anything else that FINNISH case might do differently
}