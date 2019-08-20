package com.ay.wewin.api.util;

public class Util {
	
	public static boolean isEmpty(String inString)
	{
		return (inString==null || "".equals(inString) || "".equals(inString.trim()));
	}
	
	public static boolean isNotEmpty(String inString)
	{
		return !isEmpty(inString);
	}
	
	public static String getCharForNumber(int i) {
	    return i > -1 && i < 26 ? String.valueOf((char)(i + 'A')) : null;
	}
	

}
