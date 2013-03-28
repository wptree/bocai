package com.bocai.dao.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bocai.common.constant.ActivityType;

@Entity
@Table(name="BC_FEED")
public class Feed implements Serializable {

	private static final long serialVersionUID = -443170830327513308L;

	private Long id;
	
	private Long sourceUserId;
	
	private String sourceUserName;
	
	private String sourceUserAvatar;
	
	private String dishName;
	
	private String placeName;
	
	private String placeSecondaryName;
	
	/*
	 * place.locaiton.city + place.location.street
	 */
	private String placeAddress;
	
	private String comment;
	
	private Boolean connected;
	
	private ActivityType type; // 想吃，喜欢，上传，评论
	
	/**
	 * <ul>
	 * 		<li>if {@type=ActivityType.SPOT_UPLOAD}, targetId will be new Spot's Id;</li>
	 * 		<li>if {@type=ActivityType.SPOT_WANTED || ActivityType.SPOT_NOMMED}, targetId will be target AggregatedSpot's Id</li>
	 * 		<li>if {@type=ActivityType.COMMENT_SUMMIT || ActivityType.COMMENT_GREAT || ActivityType.COMMENT_GOOD}, targetId will be target Spot's Id</li>
	 * </ul>
	 */
	private Long targetId;
	
	private Long targetUserId;
	
	private Long targetUserAvatar;
	
	private String targetUserName;
	
	private String imgType;
	
	private String postWay;
	
	private Long receiverId;
	
	private Long timeline;
	
	private String timelineStr;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "TIMELINE")
	public Long getTimeline() {
		return timeline;
	}
	public void setTimeline(Long timeline) {
		this.timeline = timeline;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	@Column(name = "TYPE")
	public ActivityType getType() {
		return type;
	}
	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	@Column(name = "SRC_UID")
	public Long getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}
	@Column(name = "SRC_UNAME")
	public String getSourceUserName() {
		return sourceUserName;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	@Column(name = "RECEIVER_UID")
	public Long getReceiverId() {
		return receiverId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	@Column(name = "TAR_ID")
	public Long getTargetId() {
		return targetId;
	}
	
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}
	@Column(name = "CONNECTED")
	@Type(type="yes_no")
	public Boolean getConnected() {
		return connected;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name = "COMMENT")
	public String getComment() {
		return comment;
	}
	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}
	@Column(name = "PLACE_ADDRESS")
	public String getPlaceAddress() {
		return placeAddress;
	}
	public void setPlaceSecondaryName(String placeSecondaryName) {
		this.placeSecondaryName = placeSecondaryName;
	}
	@Column(name = "PLACE_SEC_NAME")
	public String getPlaceSecondaryName() {
		return placeSecondaryName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Column(name = "PLACE_NAME")
	public String getPlaceName() {
		return placeName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	@Column(name = "DISH_NAME")
	public String getDishName() {
		return dishName;
	}
	public void setSourceUserAvatar(String userAvatar) {
		this.sourceUserAvatar = userAvatar;
	}
	@Column(name = "USER_AVATAR")
	public String getSourceUserAvatar() {
		return sourceUserAvatar;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	@Column(name = "IMG_TYPE")
	public String getImgType() {
		return imgType;
	}
	public void setPostWay(String postWay) {
		this.postWay = postWay;
	}
	@Column(name = "POST_WAY")
	public String getPostWay() {
		return postWay;
	}
	
	public void setTimelineStr(String timelineStr) {
		this.timelineStr = timelineStr;
	}
	public String getTimelineStr() {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(getTimeline()!=null){
			return formate.format(new Date(getTimeline()));
		}else{
			return formate.format(new Date(System.currentTimeMillis()));
		}
	}
	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}
	@Column(name = "TAR_USER_NAME")
	public String getTargetUserName() {
		return targetUserName;
	}
	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}
	@Column(name = "TAR_USER_ID")
	public Long getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserAvatar(Long targetUserAvatar) {
		this.targetUserAvatar = targetUserAvatar;
	}
	@Column(name = "TAR_USER_AVATAR")
	public Long getTargetUserAvatar() {
		return targetUserAvatar;
	}
}
