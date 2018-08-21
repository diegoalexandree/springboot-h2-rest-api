package com.springboot.restapi.h2.service;

import java.util.List;

public interface GenericService<T> {

	public T save(T t);
	public List<T> findAll();
	public void delete(Long id);
}
