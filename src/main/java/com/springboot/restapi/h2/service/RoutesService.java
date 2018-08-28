package com.springboot.restapi.h2.service;

import java.util.List;

import com.springboot.restapi.h2.dto.Routes;

public interface RoutesService{

	List<Routes> getRoutesByFilters(Routes routersFilter);


}
