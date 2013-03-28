package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * Domain Class of UserThirdparty.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 * Revision History
 * DATE            | REVISER      | REASON
 * ---------------------------------------
 * 2011.04.03      | Shi,Tao      | Creation.
 * 
 */
@Entity
@Table(name="BC_USER_THIRDPARTY")
public class UserThirdparty implements Serializable {

	private static final long serialVersionUID = 4967166197234171936L;
	
	private Long id;
	//This field is from weibo API, it's a user handler
	private String outSourceUserId;
	private String name;
	private String password;
	private String url;
	private User user;
	private String source;
	private String token;
	private String tokenSecret;
	
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
	
	@Column(name = "OUT_SOURCE_USER_ID", unique = true,nullable = true)
	public String getOutSourceUserId() {
		return outSourceUserId;
	}
	public void setOutSourceUserId(String userId) {
		this.outSourceUserId = userId;
	}
	
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", unique = false, nullable = false, 
			insertable = true, updatable = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name = "TOKEN_SECRET")
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
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
		return new EqualsBuilder()
				.append(id, ((UserThirdparty)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(" ")
				.append(name)
				.append(" from ")
				.append(source)
				.toString();
	}

	
}
