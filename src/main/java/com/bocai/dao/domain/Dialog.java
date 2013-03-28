package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_DIALOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dialog implements Serializable {

	private Long id;
	private User ua;
	private User ub;
	private PrivateLetter lastLetterA;
	private PrivateLetter lastLetterB;
	private Date createdAt;
	private Date updatedAt;
	private int letterCount;
	private Long unreadCount;
	private Set<PrivateLetter> privateLetters;
	
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
	@JoinColumn(name = "USER_A", nullable = false)
	public User getUa() {
		return ua;
	}
	public void setUa(User ua) {
		this.ua = ua;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_B", nullable = false)
	public User getUb() {
		return ub;
	}
	public void setUb(User ub) {
		this.ub = ub;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_PL_A_ID")
	public PrivateLetter getLastLetterA() {
		return lastLetterA;
	}
	public void setLastLetterA(PrivateLetter lastLetterA) {
		this.lastLetterA = lastLetterA;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_PL_B_ID")
	public PrivateLetter getLastLetterB() {
		return lastLetterB;
	}
	public void setLastLetterB(PrivateLetter lastLetterB) {
		this.lastLetterB = lastLetterB;
	}
	
	@Column(name = "CREATED_AT", nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(name = "LETTER_COUNT")
	public int getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}
	
	public Long getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(Long unreadCount) {
		this.unreadCount = unreadCount;
	}
	
	@OneToMany( mappedBy="dialog")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<PrivateLetter> getPrivateLetters() {
		return privateLetters;
	}
	public void setPrivateLetters(Set<PrivateLetter> privateLetters) {
		this.privateLetters = privateLetters;
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
		}else if(!(obj instanceof Dialog)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Dialog)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.toString();
	}
}
