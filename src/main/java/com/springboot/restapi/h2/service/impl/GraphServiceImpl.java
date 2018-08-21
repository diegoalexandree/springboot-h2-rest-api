package com.springboot.restapi.h2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.model.Node;
import com.springboot.restapi.h2.service.GraphService;
import com.springboot.restapi.h2.service.NodeService;


@Service
public class GraphServiceImpl extends GenericServiceImpl<Graph, JpaRepository<Graph,Long>> implements GraphService{
	
	@Autowired
	private NodeService nodeService;
	
	@Override
	public Graph save(Graph requestGraph) {
		Graph newGraph = super.save(requestGraph);
		newGraph.setNodes(saveNodesFirstByNewGraph(requestGraph, newGraph));
		return newGraph;
	}

	private List<Node> saveNodesFirstByNewGraph(Graph requestGraph, Graph managedGraph) {
		List<Node> newNodes = requestGraph.getNodes().stream().map(node -> {
			node.setGraph(managedGraph);
			Node newNode = nodeService.save(node);
			newNode.setGraph(null);
			return newNode;
		}).collect(Collectors.toList());
		return newNodes;
	}
}
