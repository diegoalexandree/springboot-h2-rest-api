package com.springboot.restapi.h2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Routes{
	
	@Getter @Setter private String route;
	
	@Getter @Setter private Integer stops;
	
	@JsonIgnore
	@Getter @Setter private Long graphId;
	
	@JsonIgnore
	@Getter @Setter private String sourceTown;
	
	@JsonIgnore
	@Getter @Setter private String targetTown;
	

}
