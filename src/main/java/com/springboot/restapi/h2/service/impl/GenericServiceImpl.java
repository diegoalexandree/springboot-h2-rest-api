package com.springboot.restapi.h2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapi.h2.service.GenericService;

import lombok.Getter;

public class GenericServiceImpl<T,R extends JpaRepository<T,Long>> implements GenericService<T>{

	@Autowired
	@Getter private R reporsitory;
	
	@Override
	public T save(T t) {
		return reporsitory.save(t);
	}

	@Override
	public List<T> findAll() {
		return reporsitory.findAll();
	}

	@Override
	public void delete(Long id) {
		reporsitory.delete(id);
	}
	
	

}
