package com.springboot.restapi.h2.service;

import java.util.List;

import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.model.Node;

public interface NodeService extends GenericService<Node>{
	
	List<Node> findByGraph(Graph graph);

}
