package com.springboot.restapi.h2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.h2.model.Node;
import com.springboot.restapi.h2.service.NodeService;

@RestController
@RequestMapping("nodes")
public class NodeController {

	@Autowired
	private NodeService nodeService;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<Node> save(@RequestBody @Valid Node node){
		return new ResponseEntity<Node>(nodeService.save(node), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Node>> findAll(){
		return new ResponseEntity<List<Node>>(nodeService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id){
		nodeService.delete(id);
	}


	
}
