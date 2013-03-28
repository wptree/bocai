package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 纠错信息
 * @author wpeng
 *
 */

@Entity
@Table(name="BC_CORRECTION")
public class Correction implements Serializable{
	private static final long serialVersionUID = 2708440497844873963L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	private Long id;

	@Column(name = "CREATE_BY")
	private Long createBy;
	
	@Column(name = "CREATE_BY_NAME")
	private String createByName;
	
	@Column(name = "CREATE_AT")
	private Long createAt;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "AGG_SPOT_ID")
	private Long aggSpotId;
	
	@Column(name = "DISH_ID")
	private Long dishId;
	
	@Column(name = "DISH_NAME")
	private String dishName;
	
	@Column(name = "PLACE_ID")
	private Long placeId;
	
	@Column(name = "PLACE_NAME")
	private String placeName;

	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "STATUS")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Long createAt) {
		this.createAt = createAt;
	}

	public Long getAggSpotId() {
		return aggSpotId;
	}

	public void setAggSpotId(Long aggSpotId) {
		this.aggSpotId = aggSpotId;
	}

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
