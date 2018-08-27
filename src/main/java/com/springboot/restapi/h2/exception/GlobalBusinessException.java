package com.springboot.restapi.h2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.restapi.hr.service.impl.exception.BusinessException;

@ControllerAdvice
public class GlobalBusinessException {

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Object handleBusinessException(BusinessException exception) {
		Map<String, Object> exceptionHandle = new HashMap<>();
		exceptionHandle.put("message", exception.getMessage());
		return new ResponseEntity<>(exceptionHandle, exception.getStatus());
	}
}
