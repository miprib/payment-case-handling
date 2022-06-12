package com.mikostuff.payment.casehandling.validation;

import java.util.Optional;

public interface Rule<T> {

	Optional<ValidationError> check(T t);
}
