package com.bocai.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HQLContainer{
	private static Logger logger = Logger.getLogger(HQLContainer.class);
	private static final long serialVersionUID = 8535319143051869639L;
	
	private static Properties hqllines = new Properties();
	
	static {
		try {
			hqllines.load(HQLContainer.class.getResourceAsStream("/hql-global.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized String getHql(String key){
		if(hqllines!=null){
			return hqllines.getProperty(key);
		}else{
			return "";
		}
	}


}
