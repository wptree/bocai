package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="BC_COMMENT")
@PrimaryKeyJoinColumn(name="ID")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comment extends Post implements Serializable, Cloneable {

	private static final long serialVersionUID = 950730793492883967L;
	private Post post;
	private User commentBy;
	
	public Comment(){};

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMENT_BY", nullable = false)
	public User getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(User commentBy) {
		this.commentBy = commentBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID", nullable = false)
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	public String genDigest(int length) {
		if (getContent() == null || getContent().trim().equals("")) {
			return "";
		}

		String str = getContent().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Comment)){
			return false;
		}
		return new EqualsBuilder()
				.append(getId(), ((Comment)obj).getId())
				.isEquals();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString())
			.append("Actullay it's comment\n")
			.append("	{ post=").append(post).append(" }\n");
		return sb.toString();
	}
	
	public Comment clone(){
		Comment comment = new Comment();
		comment.setId(getId());
		comment.setContent(getContent());
		comment.setPost(getPost());
		comment.setStatus(getStatus());
		comment.setPostBy(getPostBy());
		comment.setTitle(getTitle());
		comment.setViewTimes(getViewTimes());
		comment.setCommentBy(getCommentBy());
		comment.setCreatedBy(getCreatedBy());
		comment.setCreatedAt(getCreatedAt());
		comment.setUpdatedBy(getUpdatedBy());
		comment.setUpdatedAt(getUpdatedAt());
		return comment;
	}
	
}
