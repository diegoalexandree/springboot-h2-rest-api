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
	public List<Routes> getRoutesByFilters(Routes routesFilter) {
		List<Routes> routes = new ArrayList<>();
		List<DataGraph> sourceDatas = dataGraphService
				.findByGraphIdAndSource(routesFilter.getGraphId(), routesFilter.getSourceTown())
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Source not found in that graph. "));
		
		sourceDatas.forEach(data -> {
			
			if(data.getTarget().equals(routesFilter.getTargetTown())){
				String route = data.getSource() + data.getTarget();
				if(routesFilter.getStops() >= (route.length() - 1) ) {
					routes.add(Routes.builder().route(route).stops(route.length() - 1).build());
				}
				
			} else {
				StringBuilder routeString = new StringBuilder(data.getSource());
				List<DataGraph> resultDatas = dataGraphService
						.findByGraphIdAndSourceAndTargetNotEqualsThan(routesFilter.getGraphId(), data.getTarget(),
								data.getSource())
						.orElse(new ArrayList<DataGraph>());
				if (resultDatas.isEmpty())
					return;
				getRoutes(resultDatas,routes,routesFilter,routeString);
			}
		});
		return routes;
	}

	public void getRoutes(List<DataGraph> sourceList, List<Routes> routes, Routes routesFilter, StringBuilder route) {
		if(isValidRoute(routesFilter, route) ){
			sourceList.forEach(data -> {
				if(data.getTarget().equals(routesFilter.getSourceTown())) {
					return;
				}
				
				if(!route.toString().contains(data.getSource())){
					route.append(data.getSource());
				}
				
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
					getRoutes(sourceDatas,routes,routesFilter,route);
				}
			});
		}

	}

	private boolean isValidRoute(Routes routesFilter, StringBuilder route) {
		return !route.toString().contains(routesFilter.getTargetTown());
	}
	
	

}
