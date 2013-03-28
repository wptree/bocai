package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("serial")
@Embeddable
public class UserTaskCompKey implements Serializable {
	
	private User user;
	private UserTask task;
	
	public UserTaskCompKey(){}
	
	public UserTaskCompKey(User user, UserTask task){
		this.user = user;
		this.task = task;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public UserTask getTask() {
		return task;
	}
	public void setTask(UserTask task) {
		this.task = task;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(user)
				.append(task)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof UserTaskCompKey)){
			return false;
		}
		return new EqualsBuilder()
				.append(user, ((UserTaskCompKey)obj).getUser())
				.append(task, ((UserTaskCompKey)obj).getTask())
				.isEquals();
	}
}
