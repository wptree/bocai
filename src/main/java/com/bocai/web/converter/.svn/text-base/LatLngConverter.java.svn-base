package com.bocai.web.converter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.util.StrutsTypeConverter;

import com.bocai.vo.LatLng;

@SuppressWarnings("rawtypes") 
public class LatLngConverter extends StrutsTypeConverter {
	
	private static final String PATTERN = "^(-?\\d+)(\\.\\d+)?_(-?\\d+)(\\.\\d+)?$";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String latLngStr = values[0];
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(latLngStr);
		if(m.matches()){
			String lat = latLngStr.substring(0, latLngStr.indexOf("_"));
			String lng = latLngStr.substring(latLngStr.indexOf("_")+1, latLngStr.length());
			LatLng result = new LatLng();
			result.setLat(Double.parseDouble(lat));
			result.setLng(Double.parseDouble(lng));
			return result;
		}else{
			return null;
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		return o.toString();
	}
	
	public static void main(String[] args){
		LatLngConverter llc = new LatLngConverter();
		System.out.println(llc.convertFromString(null, new String[]{"51.21017569650982_-171.32166093749993"}, LatLng.class));
	}

}
