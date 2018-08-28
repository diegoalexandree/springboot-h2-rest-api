package com.springboot.restapi.h2.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.model.DataGraph;
import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.service.DataGraphService;
import com.springboot.restapi.h2.service.GraphService;
import com.springboot.restapi.h2.service.impl.exception.BusinessException;

import lombok.NonNull;


@Service
public class GraphServiceImpl extends GenericServiceImpl<Graph, JpaRepository<Graph,Long>> implements GraphService{
	
	@Autowired
	private DataGraphService dataGraphService;
	
	@Override
	public Graph save(Graph requestGraph) {
		Graph newGraph = super.save(requestGraph);
		newGraph.setData(saveNodesFirstByNewGraph(requestGraph, newGraph));
		return newGraph;
	}

	private List<DataGraph> saveNodesFirstByNewGraph(Graph requestGraph, Graph managedGraph) {
		List<DataGraph> newDatasGraph = requestGraph.getData().stream().map(data -> {
			data.setGraph(managedGraph);
			DataGraph newDataGraph = dataGraphService.save(data);
			newDataGraph.setGraph(new Graph(managedGraph.getId()));
			return newDataGraph;
		}).collect(Collectors.toList());
		return newDatasGraph;
	}

	@Override
	public Graph findById(@NonNull Long id) {
				
		Graph graph = Optional.ofNullable(getReporsitory().findOne(id))
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Graph not found -> " + id));
		graph.setData(dataGraphService.findByGraphId(graph.getId()));
		return graph;
	}
}
