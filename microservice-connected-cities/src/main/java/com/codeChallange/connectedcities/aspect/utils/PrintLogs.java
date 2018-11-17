package com.codeChallange.connectedcities.aspect.utils;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.codeChallange.connectedcities.aspect.IPrintLogs;
import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.MSLoggerInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class PrintLogs implements IPrintLogs, Constants{
	private static Logger logger = LogManager.getLogger();

	private static Logger restLogger = LogManager.getLogger(REST_LOGGER_NAME);

	private static Logger slaLogger = LogManager.getLogger(SLA_LOGGER_NAME);

	@Autowired
	private Environment env;

	private  String printToFileEnabled = null;

	private  String printReqResToFileEnabled = null;  

	@Override
	public void printLogToFile(MSLoggerInfo loggerInfo) {
		String restInputString = EMPTY_STRING;
		if(loggerInfo.getRestOutput() != null){

		}else if(loggerInfo.getRestThrowable() !=null) {

		}else if(loggerInfo.getRestThrowable() == null)
		{
			loggerInfo.setResponseType(SUCCESS_STR);
		}

		if(loggerInfo.getRestInput() != null && loggerInfo.getRestInput().length > 0)
		{
			restInputString = getJSONString(loggerInfo.getRestInput());
		}
		

		if(isPrintingToFileEnabled())
		{
			printSlaDataInLogFile(loggerInfo);
		}

		if(isPrintingReqResToFileEnabled())
		{
			printRestIO(loggerInfo, restInputString);
		}
	}

	private boolean isPrintingToFileEnabled()
	{
		if(printToFileEnabled == null)
		{
			printToFileEnabled = env.getProperty(PRINT_IN_FILE_ENABLED);
		}
		return (printToFileEnabled != null && Y_STRING == printToFileEnabled.charAt(0));
	}

	private boolean isPrintingReqResToFileEnabled()
	{
		if(printReqResToFileEnabled == null)
		{
			printReqResToFileEnabled = env.getProperty(PRINT_REQRES_IN_FILE_ENABLED);
		}
		return (printReqResToFileEnabled != null && Y_STRING == printReqResToFileEnabled.charAt(0));
	}

	private void printRestIO(MSLoggerInfo loggerInfo, String restInput) {

		try{
			StringBuilder builder = null;
			try
			{
				builder = new StringBuilder();
				ThreadContext.put(CUSTOM_TIMESTAMP, getNewDateTimeStamp(loggerInfo.getEndTime()));
				builder.append(loggerInfo.getRestMethodName()).append(SINGLE_SPACE).append(HYPHEN).append(SINGLE_SPACE);
				builder.append(REQUEST_STRING).append(SINGLE_SPACE).append(HYPHEN).append(SINGLE_SPACE);
				builder.append(restInput);
				if(loggerInfo.getResponseType().equals(FAILURE_STR))
				{
					restLogger.error(builder.toString());
				}
				else
				{
					restLogger.debug(builder.toString());
				}
			}
			finally
			{
				ThreadContext.clearMap();
			}

			try
			{
				builder = new StringBuilder();
				ThreadContext.put(CUSTOM_TIMESTAMP, getNewDateTimeStamp(loggerInfo.getEndTime()));
				if(loggerInfo.getRestThrowable() != null)
				{
					builder.append(loggerInfo.getRestMethodName()).append(SINGLE_SPACE).append(HYPHEN).append(SINGLE_SPACE);

					restLogger.error(builder.toString(), loggerInfo.getRestThrowable());
				}
				else{
					builder.append(loggerInfo.getRestMethodName()).append(SINGLE_SPACE).append(HYPHEN).append(SINGLE_SPACE);
					builder.append(RESPONSE_STRING).append(SINGLE_SPACE).append(HYPHEN).append(SINGLE_SPACE);
					String restOutPut = getJSONString(loggerInfo.getRestOutput());
					if(restOutPut != null )
					{
						builder.append(restOutPut);
						if(loggerInfo.getResponseType().equals(FAILURE_STR))
						{
							restLogger.error(builder.toString());
						}
						else
						{
							restLogger.debug(builder.toString());
						}
					}else{
						builder.append(restOutPut);
						if(loggerInfo.getResponseType().equals(FAILURE_STR))
						{
							restLogger.error(builder.toString());
						}
						else
						{
							restLogger.debug(builder.toString());
						}
					}
				}
			}
			finally
			{
				ThreadContext.clearMap();
			}
		}
		catch(Throwable th)
		{
			logger.error("@printRestIO:: Failed to Print Rest IO in file");
			logger.error(th.getMessage(),th);
		}
	}
	
	private void printSlaDataInLogFile(MSLoggerInfo loggerInfo)
	{
		try
		{
			long elapsedTime = loggerInfo.getEndTime()-loggerInfo.getStartTime();
			StringBuilder builder1 = new StringBuilder();
			builder1.append(loggerInfo.getSessionId()).append(COMMA);
			builder1.append(loggerInfo.getCurrentThreadName()).append(COMMA);
			builder1.append(loggerInfo.getSignature()).append(COMMA);
			builder1.append(getNewDateTimeStamp(loggerInfo.getStartTime())).append(COMMA);
			builder1.append(getNewDateTimeStamp(loggerInfo.getEndTime())).append(COMMA);
			builder1.append(elapsedTime).append(COMMA).append(loggerInfo.getResponseType()).append(COMMA);
			builder1.append(loggerInfo.getHostName()).append(COMMA);
			builder1.append(loggerInfo.getPort()).append(COMMA);
			builder1.append(loggerInfo.getInstanceName());
			long totalServiceTime = 0;
			builder1.append(COMMA).append(TOTAL_REST_TIME).append(DOUBLE_COLON).append(elapsedTime-totalServiceTime);
			
			slaLogger.info(builder1.toString());
		}
		catch(Throwable th)
		{
			log.error("@printSlaDataInLogFile:: Failed to Print Log Data in File");
			log.error(th.getMessage(),th);
		}
	}

	public String getNewDateTimeStamp(long time){
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date(time));
	}
	
	public static String getJSONString(Object[] obj)
	{
		String jsonContent = EMPTY_STRING;
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			jsonContent = mapper.writeValueAsString(obj);
		} 
		catch (JsonProcessingException e) 
		{
			logger.warn("@getJSONString:: Failed to Convert Java Object to JSON");
			logger.error(e.getMessage(), e);

		}
		return jsonContent;
	}
	
	public static String getJSONString(Object obj){
		String jsonContent = EMPTY_STRING;
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			Object[] object = new Object[1];
			object[0] = obj;
			jsonContent = mapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) 
		{
			logger.warn("@getJSONString:: Failed to Convert Java Object to JSON");
			logger.error(e.getMessage(), e);

		}
		return jsonContent;
	}

}
