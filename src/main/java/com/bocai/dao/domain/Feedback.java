package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 反馈
 * @author wpeng
 *
 */

@Entity
@Table(name="BC_FEEDBACK")
public class Feedback implements Serializable{
	private static final long serialVersionUID = -4397948918770389109L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	private Long id;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "CREATE_BY")
	private Long createBy;
	
	@Column(name = "REPLY_EMAIL")
	private String replayEmail;
	
	@Column(name = "CREATE_BY_NAME")
	private String createByName;
	
	@Column(name = "CREATE_AT")
	private Date createAt;

	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "STATUS")
	private Integer status;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setReplayEmail(String replayEmail) {
		this.replayEmail = replayEmail;
	}

	public String getReplayEmail() {
		return replayEmail;
	}
	

}
