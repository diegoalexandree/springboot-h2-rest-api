package com.springboot.restapi.h2.service.impl.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
@Getter 
public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511960180030466116L;
	
	private HttpStatus status;
	
	public BusinessException(HttpStatus status, String menssage) {
		super(menssage);
		this.status = status;
	}
	
	public BusinessException(HttpStatus status, String menssage, Throwable t) {
		super(menssage, t);
		this.status = status;
	}

}
