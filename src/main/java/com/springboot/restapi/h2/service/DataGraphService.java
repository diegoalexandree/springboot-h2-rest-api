package com.springboot.restapi.h2.service;

import java.util.List;
import java.util.Optional;

import com.springboot.restapi.h2.model.DataGraph;

public interface DataGraphService extends GenericService<DataGraph>{
	
	List<DataGraph> findByGraphId(Long id);

	Optional<List<DataGraph>> findByGraphIdAndSource(Long graphId, String source);
	
	Optional<List<DataGraph>> findByGraphIdAndTagert(Long graphId, String target);

}
