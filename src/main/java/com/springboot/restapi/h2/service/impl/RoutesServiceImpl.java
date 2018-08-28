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
				.findByGraphIdAndSource(routesFilter.getGraphId(), routesFilter.getSourceTown()).orElseThrow(() -> 
					new BusinessException(HttpStatus.NOT_FOUND, "Source not found in that graph. ")
				);

		List<DataGraph> targetDatas = dataGraphService
				.findByGraphIdAndSource(routesFilter.getGraphId(), routesFilter.getSourceTown()).orElseThrow(() -> 
					new BusinessException(HttpStatus.NOT_FOUND, "Target not found in that graph. ")
				 );
		
		sourceDatas.forEach(data -> {
			if(data.getTarget().equals(routesFilter.getTargetTown())){
				String route = data.getSource() + data.getTarget();
				routes.add(Routes.builder().route(route).stops(route.length()).build());
			} else {
				//String route = data.getTarget();
				targetDatas.forEach(targetData ->{
					if(!targetData.getTarget().equals(routesFilter.getTargetTown())) {
						
					}
				});
			}
		});
		return routes;
	}

}
