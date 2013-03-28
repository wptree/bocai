package com.bocai.vo;

import java.io.File;

import com.bocai.dao.domain.User;

public class SpotUploadRequest {
	private String dishImageRoot;
	private File spotImg;	
	private String spotDishName;
	private String spotPlaceId;
	private String spotDishTypeName;
	private String spotDishTagName;
	private String spotPrice;
	private String description;
	private String newSpotDishName;
	private String newSpotDishTypeName;
	private String newSpotDishTagName;
	private String newSpotPlaceId;
	private User currentUser;
	private boolean send2Sina;
	private int[] imgSizeArray;
	private String postBy;  //web, Android, iPhone
	
	public String getDishImageRoot() {
		return dishImageRoot;
	}
	public void setDishImageRoot(String dishImageRoot) {
		this.dishImageRoot = dishImageRoot;
	}
	public File getSpotImg() {
		return spotImg;
	}
	public void setSpotImg(File spotImg) {
		this.spotImg = spotImg;
	}
	public String getSpotDishName() {
		return spotDishName;
	}
	public void setSpotDishName(String spotDishName) {
		this.spotDishName = spotDishName;
	}
	public String getSpotPlaceId() {
		return spotPlaceId;
	}
	public void setSpotPlaceId(String spotPlaceId) {
		this.spotPlaceId = spotPlaceId;
	}
	public String getSpotDishTypeName() {
		return spotDishTypeName;
	}
	public void setSpotDishTypeName(String spotDishTypeName) {
		this.spotDishTypeName = spotDishTypeName;
	}
	public String getSpotDishTagName() {
		return spotDishTagName;
	}
	public void setSpotDishTagName(String spotDishTagName) {
		this.spotDishTagName = spotDishTagName;
	}
	public String getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(String spotPrice) {
		this.spotPrice = spotPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNewSpotDishName() {
		return newSpotDishName;
	}
	public void setNewSpotDishName(String newSpotDishName) {
		this.newSpotDishName = newSpotDishName;
	}
	public String getNewSpotDishTypeName() {
		return newSpotDishTypeName;
	}
	public void setNewSpotDishTypeName(String newSpotDishTypeName) {
		this.newSpotDishTypeName = newSpotDishTypeName;
	}
	public String getNewSpotDishTagName() {
		return newSpotDishTagName;
	}
	public void setNewSpotDishTagName(String newSpotDishTagName) {
		this.newSpotDishTagName = newSpotDishTagName;
	}
	public String getNewSpotPlaceId() {
		return newSpotPlaceId;
	}
	public void setNewSpotPlaceId(String newSpotPlaceId) {
		this.newSpotPlaceId = newSpotPlaceId;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setImgSizeArray(int[] imgSizeArray) {
		this.imgSizeArray = imgSizeArray;
	}
	public int[] getImgSizeArray() {
		return imgSizeArray;
	}
	public void setSend2Sina(boolean send2Sina) {
		this.send2Sina = send2Sina;
	}
	public boolean isSend2Sina() {
		return send2Sina;
	}
	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}
	public String getPostBy() {
		if(postBy == null || "".equals(postBy)){
			return "web";
		}
		return postBy;
	}
	

}
