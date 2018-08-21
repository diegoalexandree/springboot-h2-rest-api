package com.springboot.restapi.h2.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.service.GraphService;


@Service
public class GraphServiceImpl extends GenericServiceImpl<Graph, JpaRepository<Graph,Long>> implements GraphService{

}
