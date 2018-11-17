package com.codeChallange.connectedcities.dao;

import com.codeChallange.connectedcities.constants.Constants;

import lombok.Data;

@Data
public class MSLoggerInfo implements Constants{
	
	private String sessionId=EMPTY_STRING;
	private String entity=EMPTY_STRING;
	private String entityValue=EMPTY_STRING;
	private String currentThreadName=EMPTY_STRING;
	private String uniqueId=EMPTY_STRING;
	private String restMethodName = EMPTY_STRING;
	private String signature=EMPTY_STRING;
	private long startTime = System.currentTimeMillis();
	private long endTime;
	private String hostName=EMPTY_STRING;
	private String port=EMPTY_STRING;
	private String instanceName=EMPTY_STRING;
	private String sourceCalledId=EMPTY_STRING;
	private String paymentSourceId=EMPTY_STRING;
	private String responseType=EMPTY_STRING;
	private Object[] restInput;
	private String[] parameters;
	private Object restOutput;
	private Throwable restThrowable;
	private String errorCode=EMPTY_STRING;
	private String errorMsg=EMPTY_STRING;
	private String errorSeverity=EMPTY_STRING;
	private String extCallTime=EMPTY_STRING;
}
