package com.bocai.rest.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.config.SystemConfig;
import com.bocai.rest.constant.RestConstants;

@Controller
public class ClientUpgradeController {

	private static final Logger logger = Logger.getLogger(ClientUpgradeController.class);
	
	@RequestMapping(method=RequestMethod.GET, value="/app_update.json")
	public ModelAndView checkUpdate(@RequestParam("client") String client){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(RestConstants.JSON_VIEW_NAME);	
		if("android".equals(client)){
			mv.addAllObjects(checkAndroid());
		}else{
			mv.addObject("error", "unsupported client-" + client);
		}
		return mv;
	}
	
	/**
	 * 1.please place the property file under /client/android/upgrade.properties
	 * 2.provide upgrade url in the file mention above
	 * @return
	 */
	
	private Map<String,Object> checkAndroid(){
		Map<String,Object> upgradeParams = new HashMap<String,Object>();
		String fileName = SystemConfig.getStaticRoot() + "/client/android/upgrade.properties";
		try {
			FileInputStream inputStream = new FileInputStream(fileName);
			Properties props = new Properties();
			props.load(inputStream);
			for(String key : props.stringPropertyNames()){
				upgradeParams.put(key, props.getProperty(key));
			}
		} catch (Exception e) {
			logger.error("error when check android upgrade-" + e.getMessage());
			upgradeParams.put("error", "系统出错,请稍候再试");
		}
		return upgradeParams;
	}
	
	
}
