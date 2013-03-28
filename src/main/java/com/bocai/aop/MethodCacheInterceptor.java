package com.bocai.aop;

import java.io.Serializable;   

import javax.annotation.Resource;

import net.sf.ehcache.Cache;   
import net.sf.ehcache.Element;   
  
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;   
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;   
  

public class MethodCacheInterceptor implements InitializingBean   
{   
    private static final Logger logger = LoggerFactory.getLogger(MethodCacheInterceptor.class);   
  
    @Resource(name="ehDefaultCache")
    private Cache cache;
  
    public void setCache(Cache cache) {   
        this.cache = cache;   
    }
  
    public MethodCacheInterceptor() {   
        super();   
    }   
  
    /**  
     * 拦截Service/DAO的方法，并查找该结果是否存在，如果存在就返回cache中的值，  
     * 否则，返回数据库查询结果，并将查询结果放入cache  
     * @throws Throwable 
     */
    @Around("execution(public * com.bocai.manager..*.*(..))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();   
        Object[] arguments = joinPoint.getArgs();   
        Object result;   
        logger.debug("Find object from cache is " + cache.getName());   
           
        String cacheKey = getCacheKey(targetName, methodName, arguments);
        logger.info("Get method cache key:" + cacheKey);
        
        Element element = cache.get(cacheKey);   
        if (element == null) {   
            logger.info("Hold up method , get method result and create cache........!");   
            result = joinPoint.proceed();   
            element = new Element(cacheKey, (Serializable) result);   
            cache.put(element);   
        }else{
        	logger.info("Return directly from cache");
        }
        return element.getObjectValue();
    }   
  
    /**  
     * 获得cache key的方法，cache key是Cache中一个Element的唯一标识  
     * cache key包括 包名+类名+方法名，如com.co.cache.service.UserServiceImpl.getAllUser  
     */  
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {   
        StringBuffer sb = new StringBuffer();   
        sb.append(targetName).append(".").append(methodName);   
        if ((arguments != null) && (arguments.length != 0)) {   
            for (int i = 0; i < arguments.length; i++) {   
                sb.append(".").append(arguments[i]);   
            }   
        }   
        return sb.toString();   
    }   
       
    /**  
     * implement InitializingBean，检查cache是否为空  
     */  
    public void afterPropertiesSet() throws Exception {   
        Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");   
    }   
  
} 