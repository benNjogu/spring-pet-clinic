package com.keytech.Services.Map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.keytech.Model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

	Map<Long, T> map = new HashMap<>();

	Set<T> findAll() {

		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	T save(T object) {
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextID());
			}

			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("object cannot be null");
		}

//		map.put(id, object);
		return object;
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}

	private Long getNextID() {
		Long nextId = null;
		try {
			nextId = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			nextId = 1L;
		}

		return nextId;
	}

}
