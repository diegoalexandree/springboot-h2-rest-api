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

import com.springboot.restapi.h2.model.DataGraph;
import com.springboot.restapi.h2.service.DataGraphService;

@RestController
@RequestMapping("nodes")
public class DataGraphController {

	@Autowired
	private DataGraphService nodeService;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<DataGraph> save(@RequestBody @Valid DataGraph node){
		return new ResponseEntity<DataGraph>(nodeService.save(node), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<DataGraph>> findAll(){
		return new ResponseEntity<List<DataGraph>>(nodeService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id){
		nodeService.delete(id);
	}


	
}
