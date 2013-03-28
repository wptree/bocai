package com.bocai.config;
import java.util.Properties;

public class UserConfig {
	private static Properties defaultProperty;
	  
	public static final String USER_SOURCE_BOCAI = "bocai";
	public static final String USER_SOURCE_SINA = "sina";
	public static final String USER_SOURCE_RENREN = "renren";
	public static final String USER_SOURCE_TENCENT = "tencent";
	public static final String USER_SOURCE_DOUBAN = "douban";
	public static final String USER_AVATAR_SOURCE_TYPE_INTERNET = "internet";
	public static final String USER_AVATAR_SOURCE_TYPE_LOCAL = "local";
	public static final String USER_PHOTO_ORIGINAL_FOLDER="/upload/original";
	public static final String USER_PHOTO_MIDDLE_FOLDER="/upload/middle";
	public static final String USER_PHOTO_SMALL_FOLDER="/upload/small";
	
	public static final String COOKIE_SINA_WEIBO = "weibo";
	public static final String COOKIE_SINA_WEIBO_DOMAIN = "t.sina.com.cn";
	
	public static final String COOKIE_USER_FEED_TIMELINE_PREFIX = "bocai_feed_timeline_";
	public static final String COOKIE_USER_NOTIFY_TIMELINE_PREFIX = "bocai_notify_timeline_";
	public static final String COOKIE_USER_REMINDER_TIMELINE_PREFIX = "bocai_reminder_timeline_";
	public static final String COOKIE_USER_PRIVATE_LETTER_TIMELINE_PREFIX = "bocai_private_letter_timeline_";
	
	public static final int COOKIE_MAX_AGE = 60*60*24*365*2; 
	

	    static {
	        init();
	    }
	    
	    private static void init(){
	    	defaultProperty = new Properties();
	    }
	    
//	    private static boolean loadProperties(Properties props, String path) {
//	        try {
//	            File file = new File(path);
//	            if(file.exists() && file.isFile()){
//	                props.load(new FileInputStream(file));
//	                return true;
//	            }
//	        } catch (Exception ignore) {
//	        }
//	        return false;
//	    }
//
//	    private static boolean loadProperties(Properties props, InputStream is) {
//	        try {
//	            props.load(is);
//	            return true;
//	        } catch (Exception ignore) {
//	        }
//	        return false;
//	    }
	
	

}
