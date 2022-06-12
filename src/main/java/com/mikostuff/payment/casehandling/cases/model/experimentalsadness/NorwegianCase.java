package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import java.util.List;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;
import com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command.Command;

public class NorwegianCase extends BaseCase {

	public NorwegianCase(Case body, List<Command<Case>> commands) {
		super(body, commands);
	}

	@Override
	public Type type() {
		return Type.NORWEGIAN;
	}

	// Override anything else that NORWEGIAN case might do differently
}
