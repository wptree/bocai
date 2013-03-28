package com.bocai.rest.resolver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.bocai.rest.constant.RestConstants;

@Service("jsonExceptionResolver")
public class JsonExceptionResolver implements HandlerExceptionResolver {
	
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                             Object handler, Exception exception ) {
            
    	    exception.printStackTrace();
    	    ModelAndView mav = new ModelAndView();
            mav.setViewName(RestConstants.JSON_VIEW_NAME);
            mav.addObject("success",false);
            mav.addObject("errorMsg",exception.getMessage());
            mav.addObject("data",new ArrayList<Object>());
            mav.addObject("total",0);
          
			try {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				PrintStream p = new PrintStream( b );
				exception.printStackTrace( p );
				p.flush();
				System.out.println(b.toString());
			} catch (Exception e) {
				
			}
         
            return mav;
    }


}
