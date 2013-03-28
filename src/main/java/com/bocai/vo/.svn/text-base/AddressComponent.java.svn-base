package com.bocai.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.bocai.common.constant.AddressComponentType;

@SuppressWarnings("serial")
public class AddressComponent implements Serializable {

	private String longName;
	private String shortName;
	private AddressComponentType[] types;
	
	public String getLongName() {
		return longName;
	}
	
	public void setLongName(String longName) {
		this.longName = longName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public AddressComponentType[] getTypes() {
		return types;
	}
	
	public void setTypes(AddressComponentType[] types) {
		this.types = types;
	}
	
	public String toString(){
		return new ToStringBuilder(this)
		.append(longName)
		.append(shortName)
		.append(types)
		.toString();
	}
}
