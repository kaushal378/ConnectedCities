package com.codeChallange.connectedcities.aspect;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.codeChallange.connectedcities.constants.Constants;
import com.codeChallange.connectedcities.dao.MSLoggerInfo;

@Aspect
@Component
public class MSLoggerAspect implements Constants{
	
	@Autowired(required = true)
	private HttpServletRequest request;
	
	@Autowired
	private IPrintLogs print;
	
	@Autowired
	Environment env;
	
	private static String hostName = EMPTY_STRING;
	private static Logger restLogger = LogManager.getLogger(Constants.REST_LOGGER_NAME);
	
	@Pointcut("!within(com.codeChallange.connectedcities.aspect.*) && within(com.codeChallange.connectedcities.controller.*Controller) && execution(public * *(..))")
	public void logAround() {
		//do nothing
	}
	@Around("logAround()")
	public Object restLogger(ProceedingJoinPoint jointPoint){
		MSLoggerInfo loggerInfo = new MSLoggerInfo();
		StringBuilder fullyQualifiedName = new StringBuilder();
		Object result = null;
		try {
			
			String className = jointPoint.getSignature().getDeclaringTypeName();
			String methodName = jointPoint.getSignature().getName();
			loggerInfo.setRestMethodName(methodName);
			CodeSignature codeSignature = (CodeSignature)jointPoint.getSignature();
			
			loggerInfo.setParameters(codeSignature.getParameterNames());
			fullyQualifiedName.append(className.substring(className.lastIndexOf(PERIOD)+1)).append(DOUBLE_COLON).append(methodName);
			loggerInfo.setSignature(fullyQualifiedName.toString());
			loggerInfo.setRestInput(jointPoint.getArgs());
			loggerInfo.setPort(env.getProperty("server.port"));
			loggerInfo.setHostName(getHostName());
			String jsessionId = request.getSession().getId() ;
			if(jsessionId != null && jsessionId.length() > 0){
				loggerInfo.setSessionId(jsessionId);
			}else{
				loggerInfo.setSessionId(EMPTY_STRING);
			}
			result = jointPoint.proceed();
			loggerInfo.setRestOutput(result);

		}catch(NullPointerException npe){
			setNullException(loggerInfo, npe);
			throw npe;
		}catch(Throwable t){
			setThrowable(loggerInfo, t);
			//throw t;
		}
		finally{
			loggerInfo.setEndTime(System.currentTimeMillis());
			print.printLogToFile(loggerInfo);
		}
		return result;
	}
	
	private void setThrowable(MSLoggerInfo loggerInfo, Throwable t) {
		if(loggerInfo != null)
		{
			loggerInfo.setResponseType(FAILURE_STR);
			loggerInfo.setErrorSeverity(ERROR_STRING);
			loggerInfo.setRestThrowable(t);
			if(t.getMessage() != null)
			{
				loggerInfo.setErrorMsg(t.getMessage());
			}
		}
	}
	
	private void setNullException(MSLoggerInfo loggerInfo, NullPointerException npe) {
		if(loggerInfo != null)
		{
			loggerInfo.setResponseType(FAILURE_STR);
			loggerInfo.setErrorMsg(NULL_POINTER_EXCEPTION);
			loggerInfo.setErrorSeverity(FATAL_STRING);
			loggerInfo.setRestThrowable(npe);
		}
	}
	
	public synchronized String getHostName() 
	{
		try 
		{
			if(hostName == null || hostName.length() <= 0)
			{
				InetAddress localAddress = InetAddress.getLocalHost();
				hostName = localAddress.getHostName();
			}
		} 
		catch (UnknownHostException e) 
		{
			restLogger.warn("@getHostName:: Failed to get Hostname");
			restLogger.error(e.getMessage(), e);

		}
		return hostName;		
	}
}
