package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bocai.common.constant.UserTokenStatus;
import com.bocai.common.constant.UserTokenType;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_USER_TOKEN")
public class UserToken implements Serializable {
	
	private Long id;
	private User user;
	private String token;
	private UserTokenType type;
	private UserTokenStatus status;
	private Date createAt;
	private Date closeAt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "TOKEN", unique = true, nullable = false)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	public UserTokenType getType() {
		return type;
	}
	public void setType(UserTokenType type) {
		this.type = type;
	}
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	public UserTokenStatus getStatus() {
		return status;
	}
	public void setStatus(UserTokenStatus status) {
		this.status = status;
	}
	
	@Column(name = "CREATE_AT", nullable = true)
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@Column(name = "CLOSE_AT", nullable = true)
	public Date getCloseAt() {
		return closeAt;
	}
	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}
}
