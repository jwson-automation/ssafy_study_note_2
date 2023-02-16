package com.ssafy.ws.util;

public class ErrorResponse {
	private String errorCode;
	private String errorMessage;
	
	
	public ErrorResponse() {

	}
	
	public ErrorResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
