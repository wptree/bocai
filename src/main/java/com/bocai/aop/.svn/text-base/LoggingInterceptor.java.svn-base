package com.bocai.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class LoggingInterceptor {

	private static final Logger logger = 
			LoggerFactory.getLogger(LoggingInterceptor.class);   
	
	@Around("execution(public * com.bocai.manager..*.*(..))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
		String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();   
        Object[] arguments = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder()
		    	.append(targetName)
		    	.append(".")
		    	.append(methodName)
		    	.append("(");
	    if(arguments!=null && arguments.length!=0){
	    	for (int i=0; i<arguments.length; i++ ){
	    		sb.append(arguments[i]);
	    		if(i!=arguments.length-1){
	    		  sb.append(", ");
	    		}
	    	}
	    }
	    sb.append(")");
	    logger.info("Start method - " + sb.toString());
	    long st = System.currentTimeMillis();
	    
        try{
        	return joinPoint.proceed();
        }finally{
        	logger.info("End method - " + sb.toString() 
        		+ ". Total execution time: "
        		+ (System.currentTimeMillis() - st) + "(ms)" );
        }
	}

}
