package com.springboot.restapi.h2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.dto.Routes;
import com.springboot.restapi.h2.model.DataGraph;
import com.springboot.restapi.h2.repository.DataGraphRepository;
import com.springboot.restapi.h2.service.DataGraphService;

@Service
public class DataGraphServiceImpl extends GenericServiceImpl<DataGraph, JpaRepository<DataGraph,Long>> implements DataGraphService{
	@Autowired
	private DataGraphRepository dataGraphRepository;
	
	@Override
	public List<DataGraph> findByGraphId(Long id) {
		return dataGraphRepository.findByGraphId(id);
	}

	@Override
	public Optional<List<DataGraph>> findByRouterFilter(Routes routesFilter) {
		
		return Optional.ofNullable(dataGraphRepository.findByGraphIdAndSourceAndTarget(routesFilter.getGraphId(),
				routesFilter.getSourceTown(), routesFilter.getTargetTown()));
	}

}
