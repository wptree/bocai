package com.bocai.util;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import org.apache.commons.beanutils.PropertyUtils;


public class CopyUtil {
	
	/**
	 * Copy properties of orig to dest Exception the Entity and Collection Type
	 * 
	 * @param dest
	 * @param orig
	 * @return the dest bean
	 */
	@SuppressWarnings("rawtypes")
	public static Object copyProperties(Object dest, Object orig) {
		if (dest == null || orig == null) {
			return dest;
		}
		PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				Class destType = destDesc[i].getPropertyType();
				Class origType = PropertyUtils.getPropertyType(orig,destDesc[i].getName());
				if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						try {
							Object value = PropertyUtils.getProperty(orig,destDesc[i].getName());
							PropertyUtils.setProperty(dest,destDesc[i].getName(), value);
						} catch (Exception ex) {
						}
					}
				}
			}
			return dest;
		} catch (Exception ex) {
			return dest;
		}
		
	}
	
	public static Object copyPropertiesDeep(Object dest, Object orig){
		if (dest == null || orig == null) {
			return dest;
		}
		PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
		
		try {
			for (int i = 0; i < destDesc.length; i++) {
				Class destType = destDesc[i].getPropertyType();
				Class origType = PropertyUtils.getPropertyType(orig,destDesc[i].getName());
				if (destType != null && !destType.equals(Class.class)) {
					if(origType!=null&&!Collection.class.isAssignableFrom(origType)){
						if(!destType.equals(origType)){
							Object desObj = destType.newInstance();
							Object origObj = PropertyUtils.getProperty(orig,destDesc[i].getName());
							copyPropertiesDeep(desObj,origObj);
							PropertyUtils.setProperty(dest,destDesc[i].getName(), desObj);
						}else{
							Object value = PropertyUtils.getProperty(orig,destDesc[i].getName());
							PropertyUtils.setProperty(dest,destDesc[i].getName(), value);
						}
					}
				}
			}
			return dest;
		} catch (Exception ex) {
			ex.printStackTrace();
			return dest;
		}
	}
	/**
	 * Copy properties of orig to dest Exception the Entity and Collection Type
	 * 
	 * @param dest
	 * @param orig
	 * @param ignores
	 * @return the dest bean
	 */
	@SuppressWarnings("rawtypes")
	public static Object copyPropertiesWithIgnores(Object dest, Object orig, String[] ignores) {
		if (dest == null || orig == null) {
			return dest;
		}
		PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				if (contains(ignores, destDesc[i].getName())) {
					continue;
				}
				Class destType = destDesc[i].getPropertyType();
				Class origType = PropertyUtils.getPropertyType(orig,destDesc[i].getName());
				if (destType != null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
						PropertyUtils.setProperty(dest, destDesc[i].getName(),value);
					}
				}
			}
			return dest;
		} catch (Exception ex) {
			return dest;
		}
	}
	
	/**
	 * Copy selected field
	 * @param dest
	 * @param orig
	 * @param properties
	 * @return
	 */
	public static Object copyPropertiesSelected(Object dest, Object orig, String[] properties){
		if (dest == null || orig == null) {
			return dest;
		}
		if(properties != null){
			try{
				for(String prop : properties){
					Object value = PropertyUtils.getProperty(orig,prop);
					PropertyUtils.setProperty(dest,prop, value);
				}
				return dest;
			}catch(Exception ex) {
				ex.printStackTrace();
				return dest;
			}
		}else{
			return dest;
		}
	}

	static boolean contains(String[] ignores, String name) {
		boolean ignored = false;
		if(ignores != null){
			for(String prop : ignores){
				if(prop.equals(name)){
					ignored = true;
					break;
				}
			}
		}else{
			ignored = false;
		}
		return ignored;
	}
	
	public static void main(String[] args){
		
	}
}
