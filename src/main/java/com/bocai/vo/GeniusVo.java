package com.bocai.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GeniusVo implements Serializable {

	private String[] dishs;
	private int dishCount;
	private String[] places;
	private int placeCount;
	private String[] users;
	private int userCount;
	
	public String[] getDishs() {
		return dishs;
	}
	public void setDishs(String[] dishs) {
		this.dishs = dishs;
	}
	public String[] getPlaces() {
		return places;
	}
	public void setPlaces(String[] places) {
		this.places = places;
	}
	public String[] getUsers() {
		return users;
	}
	public void setUsers(String[] users) {
		this.users = users;
	}
	public int getDishCount() {
		return dishCount;
	}
	public void setDishCount(int dishCount) {
		this.dishCount = dishCount;
	}
	public int getPlaceCount() {
		return placeCount;
	}
	public void setPlaceCount(int placeCount) {
		this.placeCount = placeCount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
}
