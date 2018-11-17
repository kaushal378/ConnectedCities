package com.codeChallange.connectedcities.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MSExceptionHandler extends RuntimeException{
	public MSExceptionHandler(String exception) {
		super(exception);
	}
}
