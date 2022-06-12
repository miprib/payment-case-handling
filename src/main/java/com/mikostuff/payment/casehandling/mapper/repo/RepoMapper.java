package com.mikostuff.payment.casehandling.mapper.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;

public abstract class RepoMapper<E, M> {

	protected abstract M safeToModel(E entity);

	protected abstract E safeToEntity(M model);

	public List<M> toModels(List<E> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(this::toModel).collect(Collectors.toList());
	}

	public List<E> toEntities(List<M> models) {
		if (models == null) {
			return null;
		}
		return models.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public M toModel(E entity) {
		if (entity == null || isNotInitialized(entity)) {
			return null;
		}
		return safeToModel(entity);
	}

	public E toEntity(M model) {
		return model == null ? null : safeToEntity(model);
	}

	private boolean isNotInitialized(E entity) {
		return !Hibernate.isInitialized(entity);
	}
}
