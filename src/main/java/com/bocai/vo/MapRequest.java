package com.bocai.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MapRequest implements Serializable {
	
	private Area area;
	private String type;
	private int pageAt;
	private Long userId;
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getPageAt() {
		return pageAt;
	}
	public void setPageAt(int pageAt) {
		this.pageAt = pageAt;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String toString() {
		return "Area: " + area + " type: " + type + " pageAt: " + pageAt; 
	}
	
}
