package com.bocai.mail.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class VelocityMerger implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(VelocityMerger.class);

    private VelocityEngine engine;
//    private Resource toolBoxConfigLocation;
    private String toolBoxConfigurationPath;

//    public void setToolBoxConfigLocation(Resource toolBoxConfigLocation) {
//        this.toolBoxConfigLocation = toolBoxConfigLocation;
//    }
    
    

    public void afterPropertiesSet() throws IOException {
//        toolBoxConfigurationPath = toolBoxConfigLocation.getFile().getAbsolutePath();
//        if (logger.isInfoEnabled()) {
//            logger.info("Resource loader path '{}' resolved to file '{}'", toolBoxConfigLocation.getURI(), toolBoxConfigurationPath);
//        }
    }
    
    public String merge(String templateName, Map<String, Object> model) {
        return VelocityEngineUtils.mergeTemplateIntoString(engine, templateName, model, toolBoxConfigurationPath);
    }

    public String merge(String templateName, String templateEncoding, Map<String, Object> model) {
        return VelocityEngineUtils.mergeTemplateIntoString(engine, templateName, templateEncoding, model,
                toolBoxConfigurationPath);
    }

    public void mergeToWriter(String templateName, Map<String, Object> model, Writer writer) {
        VelocityEngineUtils.mergeTemplate(engine, templateName, model, writer, toolBoxConfigurationPath);
    }

    public void mergeToWriter(String templateName, String templateEncoding, Map<String, Object> model, Writer writer) {
        VelocityEngineUtils.mergeTemplate(engine, templateName, templateEncoding, model, writer,
                toolBoxConfigurationPath);
    }
    
    public void setEngine(VelocityEngine engine) {
        this.engine = engine;
    }

	public void setToolBoxConfigurationPath(String toolBoxConfigurationPath) {
		this.toolBoxConfigurationPath = toolBoxConfigurationPath;
	}
}