package com.bocai.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class SignUpWizardVo implements Serializable {

	private Long userId;
	private String userName;
	private LocationVo location;
	private int sexy;
	private List<Long> tags;
	private List<Long> followingUsers;
	private List<Long> followingPlaces;
	private List<Long> followingDishs;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocationVo getLocation() {
		return location;
	}
	public void setLocation(LocationVo location) {
		this.location = location;
	}
	public int getSexy() {
		return sexy;
	}
	public void setSexy(int sexy) {
		this.sexy = sexy;
	}
	public List<Long> getTags() {
		return tags;
	}
	public void setTags(List<Long> tags) {
		this.tags = tags;
	}
	public List<Long> getFollowingUsers() {
		return followingUsers;
	}
	public void setFollowingUsers(List<Long> followingUsers) {
		this.followingUsers = followingUsers;
	}
	public List<Long> getFollowingPlaces() {
		return followingPlaces;
	}
	public void setFollowingPlaces(List<Long> followingPlaces) {
		this.followingPlaces = followingPlaces;
	}
	public List<Long> getFollowingDishs() {
		return followingDishs;
	}
	public void setFollowingDishs(List<Long> followingDishs) {
		this.followingDishs = followingDishs;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(userId)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof SignUpWizardVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(userId, ((SignUpWizardVo)obj).getUserId())
				.isEquals();
	}
	
}
