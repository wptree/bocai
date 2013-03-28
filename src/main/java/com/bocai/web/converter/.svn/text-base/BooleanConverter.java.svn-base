package com.bocai.web.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

@SuppressWarnings("rawtypes")
public class BooleanConverter extends StrutsTypeConverter {
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		boolean ok = false;
        String bl = values[0];
        if (bl.equals("0")) {
                ok = true;
                return false;
        } else if (bl.equals("1")) {
                ok = true;
                return true;
        }
        if (!ok) {
                throw new TypeConversionException("invalid boolean value");
        }
        return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		 Boolean bl = (Boolean) o;
         return bl ? "1" : "0";
	}

}
