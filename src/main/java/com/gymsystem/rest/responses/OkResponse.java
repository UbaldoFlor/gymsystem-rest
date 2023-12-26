package com.gymsystem.rest.responses;

import org.springframework.http.HttpStatus;

public class OkResponse {
	
	private final HttpStatus httpStatus = HttpStatus.OK;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
