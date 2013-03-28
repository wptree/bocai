package com.bocai.rest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tag2Text {

	 private static final String REGEX = "(#[^#]*#)|(>[^<#]*)";

	public static String stripTag2Text(String tagText) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(tagText);

		while (m.find()) {
			String str = m.group();
			if(str.equals(tagText)){
				continue;
			}
		
			str = str.replaceAll("#", "");
			str = str.replaceAll(">", "");
			if(str.length() != 0){
				sb.append(str);
			}
		}
		
		if(sb.length() == 0){
			return tagText;
		}
		return sb.toString();
	}

}
