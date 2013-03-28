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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="BC_INVITATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Invitation implements Serializable{
	private static final long serialVersionUID = -3676428230834848402L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	private Long id;
	
	@Column(name = "FROM_UID")
	private Long fromUid;
	
	@Column(name = "TARGET_UID")
	private Long targetId;

	@Column(name = "TARGET_NAME")
	private String targetName;
	
	@Column(name = "TARGET_EMAIL")
	private String targetEmail;
	
	@Column(name = "INVITE_TIME")
	private Date inviteTime;

	/**
	 * 0: inactive
	 * 1: succeed
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromUid() {
		return fromUid;
	}

	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}

	public String getTargetEmail() {
		return targetEmail;
	}

	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}

	public Date getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Long getTargetId() {
		return targetId;
	}

}
