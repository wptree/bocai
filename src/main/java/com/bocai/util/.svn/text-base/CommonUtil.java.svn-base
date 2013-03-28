package com.bocai.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.bocai.common.constant.AddressComponentType;
import com.bocai.dao.domain.Location;
import com.bocai.vo.AddressComponent;

import static com.bocai.common.constant.AddressComponentType.COUNTRY;
import static com.bocai.common.constant.AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1;
import static com.bocai.common.constant.AddressComponentType.LOCALITY;
import static com.bocai.common.constant.AddressComponentType.SUBLOCALITY;
import static com.bocai.common.constant.AddressComponentType.ROUTE;
import static com.bocai.common.constant.AddressComponentType.STREET_NUMBER;
import static com.bocai.common.constant.AddressComponentType.POSTAL_CODE;

public class CommonUtil {

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	/**
	 * 随机获取字符串
	 * 
	 * @param length
	 *            随机字符串长度
	 * 
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
				'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
		}
		return stringBuffer.toString();
	}

	/**
	 * 根据指定长度 分隔字符串
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @param length
	 *            分隔长度
	 * 
	 * @return 字符串集合
	 */
	public static List<String> splitString(String str, int length) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length(); i += length) {
			int endIndex = i + length;
			if (endIndex <= str.length()) {
				list.add(str.substring(i, i + length));
			} else {
				list.add(str.substring(i, str.length() - 1));
			}
		}
		return list;
	}

	/**
	 * 将字符串List转化为字符串，以分隔符间隔.
	 * 
	 * @param list
	 *            需要处理的List.
	 *            
	 * @param separator
	 *            分隔符.
	 * 
	 * @return 转化后的字符串
	 */
	public static String toString(List<String> list, String separator) {
		StringBuffer stringBuffer = new StringBuffer();
		for (String str : list) {
			stringBuffer.append(separator + str);
		}
		stringBuffer.deleteCharAt(0);
		return stringBuffer.toString();
	}
	
	private static final String chineseCharacterPtn ="([\\u4e00-\\u9fa5]+)";
	private static final String pinYinCharacterPtn ="([a-zA-Z]+)";

	public static String filterCity(String city){
		String result = StringUtils.trimToNull(city);
		if(result==null) return null;
		Pattern p = Pattern.compile(pinYinCharacterPtn);
		Matcher m = p.matcher(result);
		StringBuilder sb = new StringBuilder();
		while(m.find()){
			sb.append(m.group(0));
		}
		result = StringUtils.trimToNull(sb.toString());
		if(result==null) return null;
		return result;
	}
	
	public static void populateAddress(AddressComponent[] acs, Location loc){
		if(acs ==null || loc ==null) return;
		int len = acs.length;
		for (int i=len-1; i>=0; i--){
			AddressComponent ac = acs[i];
			if(ac.getTypes()!=null){
				for (AddressComponentType type : ac.getTypes()){
					switch(type){
						case COUNTRY:
							loc.setCountry(ac.getLongName());
							break;
						case ADMINISTRATIVE_AREA_LEVEL_1:
							loc.setProvince(ac.getLongName());
							break;
						case LOCALITY:
							loc.setCity(ac.getLongName());
							break;
						case SUBLOCALITY:
							loc.setDistrict(ac.getLongName());
							break;
						case ROUTE:
							StringBuilder sb = new StringBuilder()
									.append(ac.getLongName());
							if(loc.getStreet()!=null){
								sb.append(loc.getStreet());
								
							}
							loc.setStreet(sb.toString());
							break;
						case STREET_NUMBER:
							StringBuilder sbn = new StringBuilder();
							if(loc.getStreet()!=null){
								sbn.append(loc.getStreet());
							}
							sbn.append(ac.getLongName());
							loc.setStreet(sbn.toString());
							break;
						case POSTAL_CODE:
							loc.setPostalCode(ac.getLongName());
							break;
						default:
							break;
					}
				}
			}
		}
	}
}