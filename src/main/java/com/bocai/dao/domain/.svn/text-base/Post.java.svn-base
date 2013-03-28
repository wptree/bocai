package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="BC_POST")
@Inheritance(strategy=InheritanceType.JOINED)
public class Post implements Serializable {

	private static final long serialVersionUID = 3408923737886890991L;
	private Long id;
	private String title;
	private String content;
	private Integer status;
	private String postBy;
	private User createdBy;
	private Date createdAt;
	private User updatedBy;
	private Date updatedAt;
	private Integer viewTimes = 0;
	private Integer commentNum = 0;
	private Set<Comment> comments;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, 
			insertable = true, updatable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "POST_BY")
	public String getPostBy() {
		return postBy;
	}
	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", nullable = false, updatable = false)
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(name = "VIEW_TIMES")
	public Integer getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}
	
	@Column(name = "COMMENT_NUM")
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	
	@OneToMany( mappedBy="post")
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public String genDigest(int length) {
		if (content == null || content.trim().equals("")) {
			return "";
		}

		String str = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
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
	
	public String truncTitle(int length){
		if(title == null || content.trim().equals("")){
			return "";
		}
		String str = "";
		int len = title.length();
		if(len <= length){
			return title;
		}else{
			str = title.substring(0, length);
			str += "...";
		}
		return str;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Post)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Post)obj).getId())
				.isEquals();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Post Detail:\n")
			.append("	{ id=").append(id).append(" }\n")
			.append("	{ content=").append(content).append(" }\n")
			.append("	{ createdBy=").append(createdBy).append(" }\n")
			.append("	{ createdAt=").append(createdAt).append(" }\n");
		return sb.toString();
	}
	
}
