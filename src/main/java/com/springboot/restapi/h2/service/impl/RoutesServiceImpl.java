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
import com.springboot.restapi.hr.service.impl.exception.BusinessException;


@Service
public class RoutesServiceImpl implements RoutesService{

	@Autowired
	private DataGraphService dataGraphService;


	@Override
	public List<Routes> getRoutesByFilters(Routes routersFilter) {
		List<Routes> routes = new ArrayList<>();
		List<DataGraph> datas = dataGraphService.findByRouterFilter(routersFilter).orElseThrow(
				() -> new BusinessException(HttpStatus.NOT_FOUND, "Datas with these filters, not found. "));
		datas.forEach(data -> {
			//continua nos próximos epsódios...
		});
		return routes;
	}
	



}
