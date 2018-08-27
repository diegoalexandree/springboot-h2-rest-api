package com.springboot.restapi.h2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Graph implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5903337909688789924L;
	
	public Graph() {
	}
	
	public Graph(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id;
	
	@OneToMany(mappedBy="graph", fetch=FetchType.LAZY)
	private List<Node> nodes;
	

}
