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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_PRIVATE_LETTER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrivateLetter implements Serializable {

	private Long id;
	private String content;
	private Date createdAt;
	private int status;
	private int sendWay;
	private String sendSide;
	private Dialog dialog;
	
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
	
	@Column(name = "CONTENT", nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "SEND_SIDE", nullable = false)
	public String getSendSide() {
		return sendSide;
	}
	public void setSendSide(String sendSide) {
		this.sendSide = sendSide;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIALOG_ID", nullable = false)
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	
	@Column(name = "SEND_WAY")
	public int getSendWay() {
		return sendWay;
	}
	public void setSendWay(int sendWay) {
		this.sendWay = sendWay;
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
		}else if(!(obj instanceof PrivateLetter)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((PrivateLetter)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.toString();
	}
}
