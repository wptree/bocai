package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.common.constant.ActivityType;

@Entity
@Table(name="BC_NOTIFICATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Notification implements Serializable{
	private static final long serialVersionUID = 5626413918635160874L;
	
	private Long id;
	private Long sourceUserId;
	private String sourceUserName;
	private Long receiverId;
	private String userAvatar;
	private ActivityType type;
	private Long targetId;
	private Long timeline;
	private String dishName;
	private String placeName;
	private String placeSecondaryName;
	private String placeFullName;
	private String comment;
	private String baseCmt;
	private Integer status;
	
	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = false)
	public Long getId() {
		return id;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}
	@Column(name="TYPE")
	public ActivityType getType() {
		return type;
	}
	
	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	@Column(name="SRC_UID")
	public Long getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}
	@Column(name="SRC_UNAME")
	public String getSourceUserName() {
		return sourceUserName;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	@Column(name="TAR_ID")
	public Long getTargetId() {
		return targetId;
	}
	public void setTimeline(Long timeline) {
		this.timeline = timeline;
	}
	@Column(name="TIMELINE")
	public Long getTimeline() {
		return timeline;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	@Column(name="USER_AVATAR")
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	@Column(name="DISH_NAME")
	public String getDishName() {
		return dishName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Column(name="PLACE_NAME")
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceSecondaryName(String placeSecondaryName) {
		this.placeSecondaryName = placeSecondaryName;
	}
	@Column(name="PLACE_SEC_NAME")
	public String getPlaceSecondaryName() {
		return placeSecondaryName;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="COMMENT")
	public String getComment() {
		return comment;
	}
	
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	@Column(name="REC_UID")
	public Long getReceiverId() {
		return receiverId;
	}
	
	@Column(name="STATUS")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}
	public String getPlaceFullName() {
		return placeFullName;
	}
	
	@Column(name="BASE_CMT")
	public String getBaseCmt() {
		return baseCmt;
	}
	public void setBaseCmt(String baseCmt) {
		this.baseCmt = baseCmt;
	}
	
}
