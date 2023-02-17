package com.ssafy.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.ws.util.DupException;
import com.ssafy.ws.util.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {
	
	/*
	 * @ExceptionHandler(Exception.class) public @ResponseBody ErrorResponse
	 * DupException(DupException e) { ErrorResponse response = new
	 * ErrorResponse(e.getCode(), e.getMessage());
	 * 
	 * return response; }
	 */
	
	@ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> DuplicateIDExceptionHandler(DupException e) {
//        return new ErrorResponse(e.getCode(),e.getMessage());
        ErrorResponse er =new ErrorResponse(e.getCode(),e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

}
