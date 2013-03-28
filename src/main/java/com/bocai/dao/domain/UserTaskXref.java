package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.common.constant.UserTaskStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="BC_XREF_USER_TASK")
@AssociationOverrides({
	@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "USER_ID")),
	@AssociationOverride(name = "id.task", joinColumns = @JoinColumn(name = "USER_TASK_ID"))
 })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTaskXref implements Serializable {
	
	private UserTaskCompKey id;
	private UserTaskStatus status;
	private Date startedAt;
	private Date endedAt;
	
	@Id
	public UserTaskCompKey getId() {
		return id;
	}
	public void setId(UserTaskCompKey id) {
		this.id = id;
	}
	
	@Column(name = "STATUS", nullable=false)
	@Enumerated(EnumType.STRING)
	public UserTaskStatus getStatus() {
		return status;
	}
	public void setStatus(UserTaskStatus status) {
		this.status = status;
	}
	
	@Column(name = "STARTED_AT")
	public Date getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}
	
	@Column(name = "ENDED_AT")
	public Date getEndedAt() {
		return endedAt;
	}
	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}
	
	@Transient
	public User getUser(){
		return getId().getUser();
	}
	public void setUser(User user){
		getId().setUser(user);
	}
	
	@Transient
	public UserTask getTask(){
		return getId().getTask();
	}
	public void setTask(UserTask task){
		getId().setTask(task);
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
		}else if(!(obj instanceof UserTaskXref)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((UserTaskXref)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.toString();
	}
}
