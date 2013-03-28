package com.bocai.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bocai.dao.domain.User;

/**
 * 将分页需要显示的数据放在vo里面，防止json序列化的时候触发hibernate关联查询
 *
 */
@SuppressWarnings("serial")
public class UserVo implements Serializable{
	private Long id;
	private String name;
	private String firstEmail;
	private Integer sexy;
	private String avatar;
	private String cityName;
	private String selfDescription;
	private String source;
	private String signature;
	private Integer score = 0;//波币
	private Integer followedByNumber;
	private Integer followToNumber;
	private Integer totalSpotCount;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getSelfDescription() {
		return selfDescription;
	}
	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		if(signature == null){
			signature = "这个家伙很懒什么也没留下";
		}
		this.signature = signature;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getFollowedByNumber() {
		return followedByNumber;
	}
	public void setFollowedByNumber(Integer followedByNumber) {
		this.followedByNumber = followedByNumber;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setTotalSpotCount(Integer totalSpotCount) {
		this.totalSpotCount = totalSpotCount;
	}
	public Integer getTotalSpotCount() {
		return totalSpotCount;
	}
	public String getFirstEmail() {
		return firstEmail;
	}
	public void setFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
	}
	public Integer getSexy() {
		return sexy;
	}
	public void setSexy(Integer sexy) {
		this.sexy = sexy;
	}
	public Integer getFollowToNumber() {
		return followToNumber;
	}
	public void setFollowToNumber(Integer followToNumber) {
		this.followToNumber = followToNumber;
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
		}
		else if(!(obj instanceof UserVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((UserVo)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(" ")
				.append(name)
				.toString();
	}
	
	public static UserVo fromUserMin(User user){
		if(user == null) return null;
		UserVo vo = new UserVo();
		vo.setAvatar(user.getAvatar());
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setSexy(user.getSexy());
		vo.setCityName(user.getCityName());
		vo.setSignature(user.getSignature());
		vo.setSelfDescription(user.getSelfDescription());
		vo.setFirstEmail(user.getFirstEmail());
		return vo;
	}
	
	public static UserVo fromUserBasic(User user){
		UserVo vo = UserVo.fromUserMin(user);
		if(vo==null) return vo;
		vo.setSource(user.getSource());
		vo.setFollowedByNumber(user.getFollowedByNumber());
		vo.setScore(user.getScore());
		vo.setTotalSpotCount(user.getTotalSpotCount());
		vo.setFollowToNumber(user.getFollowToNumber());
		return vo;
	}	
}
