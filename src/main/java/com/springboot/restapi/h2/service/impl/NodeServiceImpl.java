package com.springboot.restapi.h2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.model.Node;
import com.springboot.restapi.h2.repository.NodeRepository;
import com.springboot.restapi.h2.service.NodeService;

@Service
public class NodeServiceImpl extends GenericServiceImpl<Node, JpaRepository<Node,Long>> implements NodeService{
	@Autowired
	private NodeRepository nodeRepository;
	
	@Override
	public List<Node> findByGraph(Graph id) {
		return nodeRepository.findByGraph(id);
	}

}
