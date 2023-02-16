package com.ssafy.ws.util;

public class DupException extends RuntimeException {
	
	private static final long serialVersionUID = -2502745819100513826L;
	
	private String code;
	
	public DupException(String code,String msg) {
		super(msg);		
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	

}
