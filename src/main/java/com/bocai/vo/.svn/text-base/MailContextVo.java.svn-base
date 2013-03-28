package com.bocai.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MailContextVo implements Serializable {

	private String name;
	private String web;
	private String link;
	private Date createAt;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(link)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		else if(!(obj instanceof MailContextVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(link, ((MailContextVo)obj).getLink())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(name)
				.append(" ")
				.append(link)
				.toString();
	}
}
