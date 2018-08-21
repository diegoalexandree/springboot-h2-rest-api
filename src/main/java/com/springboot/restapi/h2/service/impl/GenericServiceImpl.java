package com.springboot.restapi.h2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapi.h2.service.GenericService;

public class GenericServiceImpl<T,R extends JpaRepository<T,Long>> implements GenericService<T>{

	@Autowired
	private R r;
	@Override
	public T save(T t) {
		return r.save(t);
	}

	@Override
	public List<T> findAll() {
		return r.findAll();
	}

	@Override
	public void delete(Long id) {
		r.delete(id);
	}

}
