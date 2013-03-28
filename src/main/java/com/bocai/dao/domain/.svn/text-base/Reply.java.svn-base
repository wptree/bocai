package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_REPLY")
@PrimaryKeyJoinColumn(name="ID")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reply extends Comment implements Serializable {
	
	private User replyTo;
	private Comment onComment;
	
	@ManyToOne
	@JoinColumn(name = "REPLY_TO", nullable = false)
	public User getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(User replyTo) {
		this.replyTo = replyTo;
	}
	
	@ManyToOne
	@JoinColumn(name = "ON_COMMENT", nullable = false)
	public Comment getOnComment() {
		return onComment;
	}
	public void setOnComment(Comment onComment) {
		this.onComment = onComment;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Reply)){
			return false;
		}
		return new EqualsBuilder()
				.append(getId(), ((Reply)obj).getId())
				.isEquals();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString())
			.append("Actullay it's comment\n")
			.append("	{ onComment=").append(onComment).append(" }\n");
		return sb.toString();
	}
}
