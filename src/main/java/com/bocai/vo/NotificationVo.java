package com.bocai.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.bocai.common.constant.ActivityType;
import com.bocai.dao.domain.Notification;
import com.bocai.util.StringUtil;

@SuppressWarnings("serial")
public class NotificationVo implements Serializable {
	
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
	private int status;
	private String baseCmt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	public String getSourceUserName() {
		return sourceUserName;
	}
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public Long getTimeline() {
		return timeline;
	}
	public void setTimeline(Long timeline) {
		this.timeline = timeline;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceSecondaryName() {
		return placeSecondaryName;
	}
	public void setPlaceSecondaryName(String placeSecondaryName) {
		this.placeSecondaryName = placeSecondaryName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBaseCmt() {
		return baseCmt;
	}
	public void setBaseCmt(String baseCmt) {
		this.baseCmt = baseCmt;
	}
	
	
	public String getTimelineStr() {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(getTimeline()!=null){
			return formate.format(new Date(timeline));
		}else{
			return formate.format(new Date(System.currentTimeMillis()));
		}
	}
	
	public static NotificationVo toBasic(Notification noti){
		if(noti==null)return null;
		NotificationVo vo = new NotificationVo();
		vo.setComment(noti.getComment());
		vo.setDishName(noti.getDishName());
		vo.setId(noti.getId());
		vo.setPlaceName(noti.getPlaceName());
		vo.setPlaceSecondaryName(noti.getPlaceSecondaryName());
		vo.setPlaceFullName(noti.getPlaceFullName());
		vo.setReceiverId(noti.getReceiverId());
		vo.setSourceUserId(noti.getSourceUserId());
		vo.setSourceUserName(noti.getSourceUserName());
		vo.setTargetId(noti.getTargetId());
		vo.setTimeline(noti.getTimeline());
		vo.setType(noti.getType());
		vo.setUserAvatar(noti.getUserAvatar());
		vo.setStatus(noti.getStatus()!=null?noti.getStatus():0);
		if(noti.getBaseCmt()!=null){
			vo.setBaseCmt(StringUtil.htmlCut(noti.getBaseCmt(), 16, "..."));
		}
		return vo;
	}
	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}
	public String getPlaceFullName() {
		return placeFullName;
	}
}
