package com.bocai.web.converter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bocai.common.constant.AddressComponentType;
import com.bocai.vo.AddressComponent;

@SuppressWarnings("rawtypes")
public class AddressComponentConverter extends StrutsTypeConverter {

	private static final Logger logger = 
		LoggerFactory.getLogger(AddressComponentConverter.class);   
	private JSONParser parser=new JSONParser();
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(values==null || values.length==0) return null;
		return convertToAddressComponent(values[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		try {
			AddressComponent ac = (AddressComponent)o;
			JSONObject obj = new JSONObject();
			StringWriter out = new StringWriter();
			obj.put("short_name", ac.getShortName());
			obj.put("long_name", ac.getLongName());
			if(ac.getTypes()!=null){
				JSONArray arr = new JSONArray();
				for (AddressComponentType type : ac.getTypes()){
					arr.add((type+"").toLowerCase());
				}
				obj.put("types", arr);
			}
			obj.writeJSONString(out);
			return out.toString();
		} catch (Exception e) {
			return "{}";
		}
	}
	
	private AddressComponent convertToAddressComponent(String acStr){
		try {
			JSONObject obj = (JSONObject)parser.parse(acStr);
			AddressComponent ac = new AddressComponent();
			ac.setLongName(obj.get("long_name") !=null ? obj.get("long_name")+"" : null);
			ac.setShortName(obj.get("short_name") !=null ? obj.get("short_name")+"" : null);
			JSONArray arr = (JSONArray)obj.get("types");
			if(arr!=null){
				List<AddressComponentType> types = 
					new ArrayList<AddressComponentType>();
				for (Object o : arr) {
					if (o == null)
						continue;
					try{
						AddressComponentType type = 
								AddressComponentType.valueOf((o + "")
								.toUpperCase());
						if(type!=null){
							types.add(type);
						}
					}catch(RuntimeException re){
						logger.warn("AddressComponentType" + o + 
								" parameter not correct. " + 
								re.getMessage());
					}
				}
				ac.setTypes(types.toArray(new AddressComponentType[]{}));
			}
			return ac;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return null;
		}
	}
	
	public static void main(String[] args){
		AddressComponent ac = new AddressComponent();
		ac.setLongName("杭州市");
		ac.setShortName("杭州");
		AddressComponentType[] types = new AddressComponentType[]{
				AddressComponentType.LOCALITY,
				AddressComponentType.POLITICAL,
				AddressComponentType.ROUTE
		};
		ac.setTypes(types);
		
		AddressComponentConverter cvt = new AddressComponentConverter();
		String s = cvt.convertToString(null, ac);
		System.out.println(s);
		System.out.println(cvt.convertFromString(null, new String[]{s}, AddressComponent.class));
	}

}
