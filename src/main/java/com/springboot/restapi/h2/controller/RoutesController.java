package com.springboot.restapi.h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.h2.dto.Routes;
import com.springboot.restapi.h2.service.RoutesService;

@RestController
@RequestMapping("routes")
public class RoutesController {

	@Autowired
	private RoutesService routesService;
	
	@GetMapping("/{graphId}/from/{sourceTown}/to/{targetTown}/")
	public ResponseEntity<List<Routes>> findByPairOfNodesAndMaxSteps(@PathVariable("graphId") Long graphId,
																	 @PathVariable("sourceTown") String sourceTown,
																	 @PathVariable("targetTown") String targetTown,
																	 @RequestParam("maxStops") Integer maxStops){
		Routes routeFilter = Routes.builder()
									.sourceTown(sourceTown)
									.targetTown(targetTown)
									.stops(maxStops)
									.graphId(graphId)
									.build();
		return new ResponseEntity<List<Routes>>(routesService.getRoutesByFilters(routeFilter), HttpStatus.OK);
	}



	
}
