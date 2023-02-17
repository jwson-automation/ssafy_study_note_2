package com.ssafy.ws.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.ws.util.DupException;
import com.ssafy.ws.util.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorResponse DupException(DupException e) {
		ErrorResponse response = new ErrorResponse(e.getCode(), e.getMessage());
		
		return response;

	}

}
