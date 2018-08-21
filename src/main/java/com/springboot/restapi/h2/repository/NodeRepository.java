package com.springboot.restapi.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.h2.model.Node;

@Repository
public interface NodeRepository  extends JpaRepository<Node, Long>{

}
