package com.springboot.restapi.h2.service;

import java.util.List;
import java.util.Optional;

import com.springboot.restapi.h2.dto.Routes;
import com.springboot.restapi.h2.model.DataGraph;

public interface DataGraphService extends GenericService<DataGraph>{
	
	List<DataGraph> findByGraphId(Long id);

	Optional<List<DataGraph>> findByRouterFilter(Routes routesFilter);

}
