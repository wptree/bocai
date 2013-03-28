package com.bocai.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;

@SuppressWarnings("serial")
public class CommentVO implements Serializable {
	private Long id;
	private String avatar;
	private String content;
	private String name;
	private Long userId;
	private Date commentAt;
	private String dishName;
	private String placeName;
	private Long spotId;
	private String spotByName;
	private Long spotById;
	private String spotByAvatar;
	private String placeSecondaryName;
	private String placeFullName;
	private String spotImgType;
	private String spotPostBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCommentAt() {
		return commentAt;
	}
	public void setCommentAt(Date commentAt) {
		this.commentAt = commentAt;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(id)
		.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof CommentVO)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((CommentVO)obj).getId())
				.isEquals();
	}
	
	public static CommentVO fromCommentBase(Comment comment){
		if(comment == null) return null;
		CommentVO vo = new CommentVO();
		vo.setId(comment.getId());
		vo.setContent(comment.getContent());
		vo.setCommentAt(comment.getCreatedAt());
		if(comment.getCommentBy()!=null){
			vo.setAvatar(comment.getCommentBy().getAvatar());
			vo.setName(comment.getCommentBy().getName());
			vo.setUserId(comment.getCommentBy().getId());
		}
		return vo;
	}
	
	public static CommentVO fromComment(Comment comment){
		CommentVO vo = fromCommentBase(comment);
		if(vo==null) return null;
		if(comment.getPost()!=null){
			Spot spot = (Spot)comment.getPost();
			vo.setDishName(spot.getDish().getName());
			vo.setPlaceName(spot.getPlace().getName());
			vo.setPlaceSecondaryName(spot.getPlace().getSecondaryName());
			vo.setPlaceFullName(spot.getPlace().getFullName());
			vo.setSpotId(spot.getId());
			vo.setSpotImgType(spot.getImgType());
			vo.setSpotById(spot.getSpotedBy().getId());
			vo.setSpotByName(spot.getSpotedBy().getName());
			vo.setSpotByAvatar(spot.getSpotedBy().getAvatar());
			vo.setSpotPostBy(spot.getPostBy());
		}
		return vo;
	}
	public void setPlaceSecondaryName(String placeSecondaryName) {
		this.placeSecondaryName = placeSecondaryName;
	}
	public String getPlaceSecondaryName() {
		return placeSecondaryName;
	}
	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}
	public Long getSpotId() {
		return spotId;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishName() {
		return dishName;
	}
	public void setSpotImgType(String spotImgType) {
		this.spotImgType = spotImgType;
	}
	public String getSpotImgType() {
		return spotImgType;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setSpotByName(String spotByName) {
		this.spotByName = spotByName;
	}
	public String getSpotByName() {
		return spotByName;
	}
	public void setSpotById(Long spotById) {
		this.spotById = spotById;
	}
	public Long getSpotById() {
		return spotById;
	}
	public void setSpotByAvatar(String spotByAvatar) {
		this.spotByAvatar = spotByAvatar;
	}
	public String getSpotByAvatar() {
		return spotByAvatar;
	}
	public void setSpotPostBy(String spotPostBy) {
		this.spotPostBy = spotPostBy;
	}
	public String getSpotPostBy() {
		return spotPostBy;
	}
	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}
	public String getPlaceFullName() {
		return placeFullName;
	}
}
