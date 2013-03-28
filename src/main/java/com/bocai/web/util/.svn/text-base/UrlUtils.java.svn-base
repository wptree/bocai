package com.bocai.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

public class UrlUtils {
	
	public static String genQstr(Map<String, Object> args){
		StringBuilder result = new StringBuilder("#/");
		try {
			if(args!=null){
				String[] keys = args.keySet().toArray(new String[]{});
				for (int i=0; i<keys.length; i++){
					String key = keys[i];
					if(key==null) continue;
					
						result.append(StringUtils.trim(key))
							  .append("=")
							  .append(URLEncoder.encode(StringUtils.trim(args.get(key)!=null?args.get(key)+"":""), 
									  "utf-8"));
					
					if(i != keys.length-1){
						result.append("&");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			result = new StringBuilder("#/");
		}
		return result.toString();
	}
	
}
