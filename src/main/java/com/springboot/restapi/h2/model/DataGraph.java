package com.springboot.restapi.h2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;

@Entity

public class DataGraph implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950205583473889738L;
	
	public DataGraph() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Long id;
	
	@Getter @Setter private String source;
	
	@Getter @Setter private String target;
	
	@Getter @Setter private Integer distance;
	
	@ManyToOne
	@Setter private Graph graph;

}
