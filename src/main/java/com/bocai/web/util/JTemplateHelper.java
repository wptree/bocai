package com.bocai.web.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;

@Component("jtemplateHelper")
public class JTemplateHelper implements InitializingBean {
	
	private static final Logger logger = 
		LoggerFactory.getLogger(JTemplateHelper.class);   
	private static Map<String, String> templateMap;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		templateMap = new HashMap<String, String>();
		String pathStr = SystemConfig.jtemplatePath();
		final String ext = AppConstant.JTEMPLATE_EXTENSION;
		File path = new File(pathStr);
		logger.info("Start to load jtemplate");
		if(path.isDirectory()){
			File[] files = path.listFiles(new FilenameFilter(){
				@Override
				public boolean accept(File dir, String name) {
					if(name!=null && name.endsWith(ext)){
						return true;
					}else{
						return false;
					}
				}
			});
			if(files!=null){
				for (File file : files){
					String fileName = file.getName();
					templateMap.put(fileName.substring(0, fileName.length()-ext.length()),
							FileUtils.readFileToString(file, "UTF-8"));
				}
			}
		}
		if(logger.isDebugEnabled()){
			for (Map.Entry<String, String> entry : templateMap.entrySet()){
				logger.debug("load jtemplate - " + entry.getKey());
			}
		}
	}
	
	public String getTemplate(String name){
		if(templateMap!=null){
			return templateMap.get(name);
		}else{
			return null;
		}
	}
}
