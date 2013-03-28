package com.bocai.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class ContentUtil {
	
	private static final String TAG_PATTERN = "#([^(\\s||#)]+)#";
	
	public static String htmlReply(Map<String, Object> params){
		String content = (String)params.get("content");
		String name = (String)params.get("name");
		Long id = (Long) params.get("id");
		String contextPath = (String)params.get("contextPath");
		return content.replaceAll("@"+name, new StringBuilder()
				.append("<a href='")
				.append(contextPath).append("/user/profile.bc?id=").append(id)
				.append("'>")
				.append("@").append(name)
				.append("</a>")
				.toString());
	}
	
	public static List<String> parseTag(String content){
		if(!StringUtils.hasLength(content)) return null;
		List<String> result = null;
		Pattern p = Pattern.compile(TAG_PATTERN);
		Matcher m = p.matcher(content);
		String target = content;
		while(m.find()){
			if(result==null){
				result = new ArrayList<String>();
			}
			String matchedStr = m.group();
			result.add(matchedStr.substring(1, matchedStr.length()-1));
			int end = m.end();
			target = target.substring(end);
			m.reset(target);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String htmlTag(Map<String, Object> params){
		String content = (String) params.get("content");
		Map<String, Long> tags = (Map<String, Long>)params.get("tags");
		String contextPath = (String)params.get("contextPath");
		if(!StringUtils.hasLength(content) ||
				tags==null || tags.isEmpty()) return null;
		Pattern p = Pattern.compile(TAG_PATTERN);
		Matcher m = p.matcher(content);
		String target = content;
		while(m.find()){
			String matchedStr = m.group();
			Long id = tags.get(matchedStr.substring(1, matchedStr.length()-1));
			if(id == null) continue;
			content = content.replaceFirst(matchedStr, new StringBuilder()
				.append("<a href='")
				.append(contextPath).append("/guide.bc?id=").append(id)
				.append("'>")
				.append(matchedStr)
				.append("</a>")
				.toString());
			int end = m.end();
			target = target.substring(end);
			m.reset(target);
		}
		return content;
	}

	
	public static void main(String[] args){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contextPath", "/bocai");
		params.put("content", "#222##111#223# #3###");
		Map<String, Long> tags = new HashMap<String, Long>();
		tags.put("222", 123L);
		tags.put("111", 19L);
		tags.put("3", 5L);
		params.put("tags", tags);
		System.out.println(ContentUtil.htmlTag(params));
	}

}
