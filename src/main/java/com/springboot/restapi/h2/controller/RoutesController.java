package com.springboot.restapi.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.h2.model.Graph;
import com.springboot.restapi.h2.service.GraphService;

@RestController
@RequestMapping("routes")
public class RoutesController {

	@Autowired
	private GraphService graphService;
	
	
	@GetMapping("{id}")
	public ResponseEntity<Graph> findByPairOfNodesAndMaxSteps(@PathVariable("id") Long id){
		return new ResponseEntity<Graph>(graphService.findById(id), HttpStatus.OK);
	}


	
}
