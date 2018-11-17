package com.codeChallange.connectedcities.utils;

public class StringUtils {
	public boolean isEmpty(String str) {
		if(str==null && "".equals(str))
			return true;
		
		return false;
	}
}
