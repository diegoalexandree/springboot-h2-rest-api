package com.springboot.restapi.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.h2.model.DataGraph;

@Repository
public interface DataGraphRepository  extends JpaRepository<DataGraph, Long>{
	
	@Query("FROM DataGraph AS dg WHERE dg.graph.id = ?1")   
	List<DataGraph> findByGraphId(Long id);
	
	@Query("FROM DataGraph AS dg WHERE dg.graph.id = ?1 and dg.source = ?2 and dg.target = ?3")
	List<DataGraph> findByGraphIdAndSourceAndTarget(Long id, String source, String target);
}
