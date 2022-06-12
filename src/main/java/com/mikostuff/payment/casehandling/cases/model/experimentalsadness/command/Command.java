package com.mikostuff.payment.casehandling.cases.model.experimentalsadness.command;

public interface Command<T> {

	void execute(T t);
}
