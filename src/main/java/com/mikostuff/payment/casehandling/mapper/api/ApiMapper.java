package com.mikostuff.payment.casehandling.mapper.api;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <R1> - request
 * @param <R1> - response
 * @param <M>  - model
 */
public abstract class ApiMapper<R1, R2, M> {

	protected abstract M safeToModel(R1 request);

	protected abstract R2 safeToResponse(M model);

	public List<R2> toResponses(List<M> models) {
		if (models == null) {
			return null;
		}
		return models.stream().map(this::toResponse).collect(Collectors.toList());
	}

	public M toModel(R1 request) {
		return request == null ? null : safeToModel(request);
	}

	public R2 toResponse(M model) {
		return model == null ? null : safeToResponse(model);
	}
}
