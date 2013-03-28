package com.bocai.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PinBoxVo implements Serializable {
	
	private Long aggSpotId;
	private String imageUrl;
	private String fullName;
	private int spottedNum = 0;
	private int nommedNum = 0;
	private int tastedNum = 0;
	private int viewedNum = 0;
	
	public Long getAggSpotId() {
		return aggSpotId;
	}
	public void setAggSpotId(Long aggSpotId) {
		this.aggSpotId = aggSpotId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getSpottedNum() {
		return spottedNum;
	}
	public void setSpottedNum(int spottedNum) {
		this.spottedNum = spottedNum;
	}
	public int getNommedNum() {
		return nommedNum;
	}
	public void setNommedNum(int nommedNum) {
		this.nommedNum = nommedNum;
	}
	public int getTastedNum() {
		return tastedNum;
	}
	public void setTastedNum(int tastedNum) {
		this.tastedNum = tastedNum;
	}
	public int getViewedNum() {
		return viewedNum;
	}
	public void setViewedNum(int viewedNum) {
		this.viewedNum = viewedNum;
	}
}