package com.mikostuff.payment.casehandling.cases.model.experimentalsadness;

import com.mikostuff.payment.casehandling.cases.model.Case;
import com.mikostuff.payment.casehandling.cases.model.Type;

public interface CaseAbstraction {

	Type type();

	Case body();

	void executeCommands();

//	void save();
//
//	void resolve(Resolution resolution);
}
