package com.springboot.restapi.h2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.restapi.h2.dto.Routes;
import com.springboot.restapi.h2.model.DataGraph;
import com.springboot.restapi.h2.service.DataGraphService;
import com.springboot.restapi.h2.service.RoutesService;
import com.springboot.restapi.h2.service.impl.exception.BusinessException;


@Service
public class RoutesServiceImpl implements RoutesService{
 
	@Autowired
	private DataGraphService dataGraphService;


	@Override
	public List<Routes> getByFilters(Routes routeFilters) {
		List<Routes> routes = new ArrayList<>();
		List<DataGraph> sourceDatas = dataGraphService
				.findByGraphIdAndSource(routeFilters.getGraphId(), routeFilters.getSourceTown())
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Source not found in that graph. "));
		
		sourceDatas.forEach(data -> {
			
			if(data.getTarget().equals(routeFilters.getTargetTown())){
				addRouteHigthLevel(routeFilters, routes, data);
				
			} else {
				StringBuilder routeString = new StringBuilder(data.getSource());
				List<DataGraph> resultDatas = dataGraphService
						.findByGraphIdAndSourceAndTargetNotEqualsThan(routeFilters.getGraphId(), data.getTarget(),
								data.getSource())
						.orElse(new ArrayList<DataGraph>());
				if (resultDatas.isEmpty())
					return;
				addRouteLowLevel(resultDatas,routes,routeFilters,routeString);
			}
		});
		return routes;
	}

	private void addRouteHigthLevel(Routes routeFilters, List<Routes> routes, DataGraph data) {
		String route = data.getSource() + data.getTarget();
		if(routeFilters.getStops() >= (route.length() - 1) ) {
			routes.add(Routes.builder().route(route).stops(route.length() - 1).build());
		}
	}

	public void addRouteLowLevel(List<DataGraph> sourceList, List<Routes> routes, Routes routeFilters, StringBuilder route) {
		if(isValidRoute(routeFilters, route) ){
			sourceList.forEach(data -> {
				if(data.getTarget().equals(routeFilters.getSourceTown())) {
					return;
				}
				
				if(route.toString().contains(data.getSource())){
					return;
				}
				addRoute(routes, routeFilters, route, data);
			});
		}

	}

	private void addRoute(List<Routes> routes, Routes routesFilter, StringBuilder route, DataGraph data) {
		route.append(data.getSource());
		if(data.getTarget().equals(routesFilter.getTargetTown())){
			route.append(data.getTarget());
			if(routesFilter.getStops() >= (route.length() - 1) ) {
				routes.add(Routes.builder().route(route.toString()).stops(route.length() - 1).build());
			}
			
		} else {				
			List<DataGraph> sourceDatas = dataGraphService
					.findByGraphIdAndSourceAndTargetNotEqualsThan(routesFilter.getGraphId(), data.getTarget(),
							data.getSource())
					.orElse(new ArrayList<DataGraph>());
			if (sourceDatas.isEmpty())
				return;
			addRouteLowLevel(sourceDatas,routes,routesFilter,route);
		}
	}

	private boolean isValidRoute(Routes routesFilter, StringBuilder route) {
		return !route.toString().contains(routesFilter.getTargetTown());
	}
	
	

}
