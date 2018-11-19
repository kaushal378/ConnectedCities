package com.codeChallange.connectedcities.constants;

import org.springframework.stereotype.Component;

@Component
public interface Constants {
	int INPUT_LENGTH = 2;
	String COMMA = ",";
	String REST_LOGGER_NAME = "RestLog";
	String EMPTY_STRING = ""; 
	String DOUBLE_COLON = "::";
	String PERIOD = ":";
	String SUCCESS_STR = "SUCCESS";
	String FAILURE_STR = "FAILURE";
	String INPUT_STRING = "INPUT";
	String OUTPUT_STRING = "OUTPUT";
	String ERROR_STRING = "ERROR";
	String FATAL_STRING = "FATAL";
	String NULL_POINTER_EXCEPTION = "NullPointerException";
	String SLA_LOGGER_NAME = "SLALog";
	String PRINT_IN_FILE_ENABLED = "print.in.file.enabled";
	String PRINT_REQRES_IN_FILE_ENABLED = "print.reqres.in.file.enabled";
	char Y_BYTE = 'Y';
	String SINGLE_SPACE = " ";
	String HYPHEN = "-";
	String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	String CUSTOM_TIMESTAMP="custom.timestamp";
	String REQUEST_STRING = "request";
	String RESPONSE_STRING = "response";
	String TOTAL_REST_TIME = "Total-Rest-Time";
	String FILE_DELIMITER = ",";
	String YES_STRING = "yes";
	String NO_STRING = "no";
	String INPUT_READ_FROM_FILE = "read.inputs.from.file";
	String Y_STRING = "Y";
}
