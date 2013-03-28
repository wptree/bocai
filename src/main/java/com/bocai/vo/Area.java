package com.bocai.vo;

import java.io.Serializable;

import com.bocai.dao.domain.Location;

@SuppressWarnings("serial")
public class Area implements Serializable {
	
	private LatLng sw;
	private LatLng ne;
	
	public Area(){};
	
	public Area(LatLng sw, LatLng ne){
		this.sw = sw;
		this.ne = ne;
	};
	
	public LatLng getSw() {
		return sw;
	}
	public void setSw(LatLng sw) {
		this.sw = sw;
	}
	
	public LatLng getNe() {
		return ne;
	}
	public void setNe(LatLng ne) {
		this.ne = ne;
	}
	
	public String toString() {
		return "sw: " + sw + "   ne: " + ne;
	}
}
