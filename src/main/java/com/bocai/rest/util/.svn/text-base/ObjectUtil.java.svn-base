package com.bocai.rest.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ObjectUtil {

	private static final String emptyString = "";
	
	public static boolean isNull(Object obj){
		return obj == null || obj.toString().equals(emptyString);
	}
	
	public static String toString(Object obj){
		return obj ==null ? "":obj.toString();
	}
	
	public static boolean isDouble(Object obj){
		String str = toString(obj);
		try{
			Double.parseDouble(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static double toDouble(Object obj){
		double d;
		String str = toString(obj);
		try{
			d = Double.parseDouble(str);
			return d;
		}catch(Exception e){
			return Double.NaN;
		}
	}
	
	public static boolean isInt(Object obj){
		String str = toString(obj);
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static int toInt(Object obj){
		int i;
		String str = toString(obj);
		try{
			i = Integer.parseInt(str);
			return i;
		}catch(Exception e){
			return Integer.MIN_VALUE;
		}
	}
	
	
	public static String toDecodedString(Object obj){
		String str = toString(obj);
		try {
			return URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
		
	}
	
}
