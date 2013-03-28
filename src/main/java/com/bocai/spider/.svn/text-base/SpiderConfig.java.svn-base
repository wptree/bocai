package com.bocai.spider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class SpiderConfig{
	private static final long serialVersionUID = 8535319143051869639L;
	
	private static Properties configProperties = new Properties();
	
	static {
		try {
			configProperties.load(SpiderConfig.class.getResourceAsStream("/spider.properties"));
			for (Object key : configProperties.keySet()) {
	            String value = configProperties.getProperty(key.toString());
	            String goodValue = new String(value.getBytes("iso8859-1"), "utf-8");
	            configProperties.setProperty(key.toString(), goodValue);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized String ImageDownloadDir(){
		return configProperties.getProperty("image.download.temporary.dir", "E:\\Workspace\\Temp");
	}
	
	public static synchronized String[] RandomSpotPrice(){
		return configProperties.getProperty("spot.price.list", "20,25,18,35,40,55,12,30,32,42,19,50,16,22,26,33").split(",");
	}
	
	public static synchronized String[] RandomUserIDList(){
		return configProperties.getProperty("random.user.id.list", "1,2,3,4,5,6,7,8,9,10").split(",");
	}
	
	public static synchronized String[] RandomSpotDesc(){
		return configProperties.getProperty("random.spot.desc", "这道菜很好吃，强烈推荐大家去吃").split(",");
	}
	
	public static synchronized String get(String key){
		if(configProperties!=null){
			return configProperties.getProperty(key);
		}else{
			return "";
		}
	}


}
