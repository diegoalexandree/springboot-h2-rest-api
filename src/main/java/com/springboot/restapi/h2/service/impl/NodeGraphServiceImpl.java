package com.springboot.restapi.h2.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.model.Node;
import com.springboot.restapi.h2.service.NodeService;

@Service
public class NodeGraphServiceImpl extends GenericServiceImpl<Node, JpaRepository<Node,Long>> implements NodeService{

}
