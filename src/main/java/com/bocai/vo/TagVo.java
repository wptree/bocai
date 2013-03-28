package com.bocai.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bocai.dao.domain.Tag;

@SuppressWarnings("serial")
public class TagVo implements Serializable {
	
	private Long id;
	private String tagName;
	private String iconPath;
	private Integer weight;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public static TagVo fromTag(Tag tag){
		if(tag==null) return null;
		TagVo vo = new TagVo();
		vo.setId(tag.getId());
		vo.setIconPath(tag.getIconPath());
		vo.setTagName(tag.getTagName());
		vo.setWeight(tag.getWeight());
		return vo;
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
		else if(!(obj instanceof TagVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((TagVo)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(" ")
				.append(tagName)
				.toString();
	}
}
