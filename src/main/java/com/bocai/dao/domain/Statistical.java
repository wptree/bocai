package com.bocai.dao.domain;

import java.io.Serializable;

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
@Table(name="BC_STATISTICAL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Statistical implements Serializable{
	private static final long serialVersionUID = -6801000532605858579L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	private Long id;
	
	@Column(name="USER_ID")
	private Long userId;
	@Column(name="NEW_FOLLOWER_NUM_WEEK")
	private Integer newFollowerNumWeek = 0;
	@Column(name="TOTAL_FOLLOWER_NUM")
	private Integer totalFollowerNum = 0;
	@Column(name="NEW_SPOT_NUM_WEEK")
	private Integer newSpotNumWeek = 0;
	@Column(name="TOTAL_SPOT_NUM")
	private Integer totalSpotNum = 0;
	@Column(name="NEW_SPOT_WANT_NUM_WEEK")
	private Integer newSpotWantedNumWeek = 0;
	@Column(name="TOTAL_SPOT_WANT_NUM")
	private Integer totalSpotWantedNum = 0;
	@Column(name="NEW_SPOT_NOM_NUM_WEEK")
	private Integer newSpotNomNumWeek = 0;
	@Column(name="TOTAL_SPOT_NOM_NUM")
	private Integer totalSpotNomNum = 0;
	@Column(name="NEW_COMMENT_GOOD_NUM_WEEK")
	private Integer newCommentGoodNumWeek = 0;
	@Column(name="TOTAL_COMMENT_GOOD_NUM")
	private Integer totalCommentGoodNum = 0;
	@Column(name="NEW_COMMENT_GREAT_NUM_WEEK")
	private Integer newCommentGreatNumWeek = 0;
	@Column(name="TOTAL_COMMENT_GREAT_NUM")
	private Integer totalCommentGreatNum = 0;
	
	
	public Integer getNewFollowerNumWeek() {
		return newFollowerNumWeek;
	}
	public void setNewFollowerNumWeek(Integer newFollowerNumWeek) {
		this.newFollowerNumWeek = newFollowerNumWeek;
	}
	public Integer getTotalFollowerNum() {
		return totalFollowerNum;
	}
	public void setTotalFollowerNum(Integer totalFollowerNum) {
		this.totalFollowerNum = totalFollowerNum;
	}
	public Integer getNewSpotNumWeek() {
		return newSpotNumWeek;
	}
	public void setNewSpotNumWeek(Integer newSpotNumWeek) {
		this.newSpotNumWeek = newSpotNumWeek;
	}
	public Integer getTotalSpotNum() {
		return totalSpotNum;
	}
	public void setTotalSpotNum(Integer totalSpotNum) {
		this.totalSpotNum = totalSpotNum;
	}
	public Integer getNewSpotWantedNumWeek() {
		return newSpotWantedNumWeek;
	}
	public void setNewSpotWantedNumWeek(Integer newSpotWantedNumWeek) {
		this.newSpotWantedNumWeek = newSpotWantedNumWeek;
	}
	public Integer getTotalSpotWantedNum() {
		return totalSpotWantedNum;
	}
	public void setTotalSpotWantedNum(Integer totalSpotWantedNum) {
		this.totalSpotWantedNum = totalSpotWantedNum;
	}
	public Integer getNewSpotNomNumWeek() {
		return newSpotNomNumWeek;
	}
	public void setNewSpotNomNumWeek(Integer newSpotNomNumWeek) {
		this.newSpotNomNumWeek = newSpotNomNumWeek;
	}
	public Integer getTotalSpotNomNum() {
		return totalSpotNomNum;
	}
	public void setTotalSpotNomNum(Integer totalSpotNomNum) {
		this.totalSpotNomNum = totalSpotNomNum;
	}
	public Integer getNewCommentGoodNumWeek() {
		return newCommentGoodNumWeek;
	}
	public void setNewCommentGoodNumWeek(Integer newCommentGoodNumWeek) {
		this.newCommentGoodNumWeek = newCommentGoodNumWeek;
	}
	public Integer getTotalCommentGoodNum() {
		return totalCommentGoodNum;
	}
	public void setTotalCommentGoodNum(Integer totalCommentGoodNum) {
		this.totalCommentGoodNum = totalCommentGoodNum;
	}
	public Integer getNewCommentGreatNumWeek() {
		return newCommentGreatNumWeek;
	}
	public void setNewCommentGreatNumWeek(Integer newCommentGreatNumWeek) {
		this.newCommentGreatNumWeek = newCommentGreatNumWeek;
	}
	public Integer getTotalCommentGreatNum() {
		return totalCommentGreatNum;
	}
	public void setTotalCommentGreatNum(Integer totalCommentGreatNum) {
		this.totalCommentGreatNum = totalCommentGreatNum;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return userId;
	}

	

}
