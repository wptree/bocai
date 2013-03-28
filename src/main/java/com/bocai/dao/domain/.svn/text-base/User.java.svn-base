/*
 * Licensed to the bocai007.com under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The bocai.com licenses this file to You under the bocai.com License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.bocai007.com/licenses/LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.bocai.common.constant.UserStatus;
import com.bocai.config.UserConfig;
/**
 * <p>
 * Domain Class of User.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 * Revision History
 * DATE            | REVISER      | REASON
 * ---------------------------------------
 * 2011.03.20      | Shi,Tao      | Creation.
 * 
 */
@Entity
@Table(name="BC_USER")
@Proxy(lazy=false) 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	//this "name" is for screen display name or nick name. The default value is the prefix of email address 
	private String name;
	private String chineseName;
	//user photo path
	private String avatar;
	private String firstEmail;
	private String secondrayEmail;
	private String password;
	private String source;  //for sina user, this value="sina"; the default value is "bocai"
	private String outSourceUserId;
	private String signature;
	private Integer score = 0;//波币
	private String cityName;
	private String birthday;//"1983-10-19"
	private String msn;
	private String blogUrl;
	private Integer sexy;
	private Integer age;
	private Integer weight;
	private Integer height;
	private Integer followedByNumber;
	private Integer followToNumber;
	private String address;
	private String phoneNo;
	private UserStatus status;
	private String qq;
	private String selfDescription;
	private String actionRole;
	private Integer loginTimes;
	private Date lastLoginTime;
	private String secretFields;
	private Integer totalSpotCount;
	private Integer nomCount;
	private Integer wantedCount;
	private Date createdAt;
	private Date updatedAt;
	private Long feedFetchTimeline;
	private Long notificatonFetchTimeline;
	private Long reminderTimeline;
	private Long privateLetterTimeline;
	private DishAlbum dishAlbum;
	private Set<User> followedBys; 
	private Set<User> followTos;
	private Set<UserThirdparty> thirdpartyAccounts;
	private Set<AggregatedSpot> nomAggSpots;
	private Set<AggregatedSpot> wantedAggSpots;
	private Set<AggregatedSpot> testedAggSpots;
	private Set<AggregatedSpot> aggSpotted;
	private Set<AggregatedSpot> sharedAggSpots;
	private Set<Post> myPosts;
	private Set<Spot> mySpots;
	private Set<Spot> goodCountSpots;
	private Set<Spot> greatCountSpots;
	private Set<Comment> myComments;
	private Set<UserTitle> titles;
	private Set<Place> followingPlaces;
	private Set<Place> nomedPlaces;
	private Set<Dish> followingDishs;
	private Set<Tag> followingTags;
	private Set<Tag> createdTags;
	private Set<Tag> myTags;
	private Set<UserTaskXref> myTasks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 * Updated: set nullable = true. if user comes from thirdparty, its firstEmail can be null when initialized
	 * @return
	 */
	@Column(name = "FIRST_EMAIL", unique = true, nullable = true, updatable = false)
	public String getFirstEmail() {
		return firstEmail;
	}
	public void setFirstEmail(String firstEmail) {
		this.firstEmail =firstEmail;
	}
	
	@Column(name = "SECONDARY_EMAIL")
	public String getSecondrayEmail() {
		return secondrayEmail;
	}
	public void setSecondrayEmail(String secondrayEmail) {
		this.secondrayEmail =secondrayEmail;
	}
	
	@Column(name = "CREATED_AT")
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
	
	public void setDishAlbum(DishAlbum dishAlbum) {
		this.dishAlbum = dishAlbum;
	}
	
	@JSON(serialize=false)
	@OneToOne
	public DishAlbum getDishAlbum() {
		return dishAlbum;
	}
	
	@Column(name = "NAME",unique = false, nullable = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "CHINESE_NAME")
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "PASSWORD", nullable = false)
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}
	@Column(name = "OUT_SOURCE_USER_ID")
	public String getOutSourceUserId() {
		return outSourceUserId;
	}

	public void setOutSourceUserId(String outSourceUserId) {
		this.outSourceUserId = outSourceUserId;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Column(name = "SCORE")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Column(name = "CITY_NAME")
	public String getCityName() {
		if(cityName==null){
			return "地理位置未知";
		}
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Column(name = "BIRTHDAY")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "MSN")
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	@Column(name = "QQ")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Column(name = "BLOG_URL")
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	
	@Column(name = "SEXY")
	public Integer getSexy() {
		return sexy;
	}
	public void setSexy(Integer sexy) {
		this.sexy = sexy;
	}
	
	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	@Column(name = "SELF_DESCRIPTION")
	public String getSelfDescription() {
		if(selfDescription==null || "".equals(selfDescription.trim())){
			return "这个家伙很懒什么也没留下";
		}
		return selfDescription;
	}

	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}
	
	@Column(name = "FOLLOWED_BY_NUM")
	public Integer getFollowedByNumber() {
		if(followedByNumber==null){
			return 0;
		}
		return followedByNumber;
	}
	public void setFollowedByNumber(Integer followedByNumber) {
		this.followedByNumber = followedByNumber;
	}
	
	@Column(name = "FOLLOW_TO_NUM")
	public Integer getFollowToNumber() {
		if(followToNumber==null){
			return 0;
		}
		return followToNumber;
	}
	public void setFollowToNumber(Integer followToNumber) {
		this.followToNumber = followToNumber;
	}
	
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "PHONE_NO")
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	
	@Column(name = "ACTION_ROLE", nullable = false)
	public String getActionRole() {
		return actionRole;
	}
	public void setActionRole(String actionRole) {
		this.actionRole = actionRole;
	}
	
	@Column(name = "AVATAR")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name = "SECRET_FIELDS")
	public void setSecretFields(String secretFields) {
		this.secretFields = secretFields;
	}

	public String getSecretFields() {
		return secretFields;
	}

	@Column(name = "REMINDER_TL")
	public Long getReminderTimeline() {
		return reminderTimeline;
	}
	public void setReminderTimeline(Long reminderTimeline) {
		this.reminderTimeline = reminderTimeline;
	}
	
	@Column(name = "PRIVATE_LETTER_TL")
	public Long getPrivateLetterTimeline() {
		return privateLetterTimeline;
	}
	public void setPrivateLetterTimeline(Long privateLetterTimeline) {
		this.privateLetterTimeline = privateLetterTimeline;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class)
    @JoinTable(name="BC_USER_FOLLOWSHIP",
    		joinColumns=
                @JoinColumn(name="TARGET_USER"),
            inverseJoinColumns=
                @JoinColumn(name="FOLLOWED_BY")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getFollowedBys(){
		if(followedBys==null){
			followedBys = new HashSet<User>();
    	}
		return followedBys;
	}
	public void setFollowedBys(Set<User> followedBys){
		this.followedBys = followedBys;
	}
	
	@JSON(serialize=false)
    @ManyToMany(targetEntity=User.class,mappedBy="followedBys")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getFollowTos(){
    	if(followTos==null){
    		followTos = new HashSet<User>();
    	}
		return followTos;
	}	
	public void setFollowTos(Set<User> followTos){
		this.followTos = followTos;
	}
	
	@JSON(serialize=false)
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	@OrderBy("id asc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<UserThirdparty> getThirdpartyAccounts() {
		return thirdpartyAccounts;
	}
	public void setThirdpartyAccounts(Set<UserThirdparty> thirdpartyAccounts) {
		this.thirdpartyAccounts = thirdpartyAccounts;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=AggregatedSpot.class)
    @JoinTable(name="BC_XREF_USER_AGGSPOT_NOM",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="AGG_SPOT_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<AggregatedSpot> getNomAggSpots() {
		return nomAggSpots;
	}
	public void setNomAggSpots(Set<AggregatedSpot> nomAggSpots) {
		this.nomAggSpots = nomAggSpots;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=AggregatedSpot.class)
    @JoinTable(name="BC_XREF_USER_AGGSPOT_WANTED",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="AGG_SPOT_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<AggregatedSpot> getWantedAggSpots() {
		if (wantedAggSpots == null) {
			wantedAggSpots = new HashSet<AggregatedSpot>();
		}
		return wantedAggSpots;
	}
	public void setWantedAggSpots(Set<AggregatedSpot> wantedAggSpots) {
		this.wantedAggSpots = wantedAggSpots;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=AggregatedSpot.class)
    @JoinTable(name="BC_XREF_USER_AGGSPOT_TESTED",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="AGG_SPOT_ID")
               )
	public Set<AggregatedSpot> getTestedAggSpots() {
		return testedAggSpots;
	}
	public void setTestedAggSpots(Set<AggregatedSpot> testedAggSpots) {
		this.testedAggSpots = testedAggSpots;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=AggregatedSpot.class, fetch = FetchType.LAZY)
    @JoinTable(name="BC_XREF_USER_AGGSPOTTED",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="AGG_SPOT_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<AggregatedSpot> getAggSpotted() {
		if (aggSpotted == null) {
			aggSpotted = new HashSet<AggregatedSpot>();
		}
		return aggSpotted;
	}
	public void setAggSpotted(Set<AggregatedSpot> aggSpotted) {
		this.aggSpotted = aggSpotted;
	}
	
	@JSON(serialize=false)
	@OneToMany( mappedBy="user")
	@OrderBy("realSpotNum desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<UserTitle> getTitles() {
		return titles;
	}
	public void setTitles(Set<UserTitle> titles) {
		this.titles = titles;
	}

	@JSON(serialize=false)
	@OneToMany( mappedBy="createdBy")
	public Set<Post> getMyPosts() {
		return myPosts;
	}
	public void setMyPosts(Set<Post> myPosts) {
		this.myPosts = myPosts;
	}
	
	@JSON(serialize=false)
	@OneToMany( mappedBy="spotedBy")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Spot> getMySpots() {
		return mySpots;
	}
	public void setMySpots(Set<Spot> mySpots) {
		this.mySpots = mySpots;
	}
	
	@JSON(serialize=false)
	@OneToMany( mappedBy="commentBy")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Comment> getMyComments() {
		return myComments;
	}
	public void setMyComments(Set<Comment> myComments) {
		this.myComments = myComments;
	}

	@JSON(serialize=false)
	@ManyToMany(targetEntity=AggregatedSpot.class)
    @JoinTable(name="BC_XREF_USER_AGGSPOT_SHARED",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="AGG_SPOT_ID")
               )
	public Set<AggregatedSpot> getSharedAggSpots() {
		return sharedAggSpots;
	}
	public void setSharedAggSpots(Set<AggregatedSpot> sharedAggSpots) {
		this.sharedAggSpots = sharedAggSpots;
	}
	
	public UserThirdparty sinaAccount(){
		Set<UserThirdparty> set = getThirdpartyAccounts();
		if(set!=null){
			for(UserThirdparty thrdp:set){
				if(UserConfig.USER_SOURCE_SINA.equals(thrdp.getSource())){
					return thrdp;
				}
			}
		}
		return null;
	}
	
	public boolean sinaConnected(){
		if(UserConfig.USER_SOURCE_SINA.equals(getSource()) && sinaAccount() != null){
			return true;
		}else{
			return false;
		}
	}
	
	public UserThirdparty renrenAccount(){
		Set<UserThirdparty> set = getThirdpartyAccounts();
		if(set!=null){
			for(UserThirdparty thrdp:set){
				if(UserConfig.USER_SOURCE_RENREN.equals(thrdp.getSource())){
					return thrdp;
				}
			}
		}
		return null;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Place.class)
    @JoinTable(name="BC_XREF_USER_FOLLOWING_PLACE",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="PLACE_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Place> getFollowingPlaces() {
		return followingPlaces;
	}
	public void setFollowingPlaces(Set<Place> followingPlaces) {
		this.followingPlaces = followingPlaces;
	}

	@JSON(serialize=false)
	@ManyToMany(targetEntity=Tag.class)
    @JoinTable(name="BC_XREF_USER_FOLLOWING_TAG",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="TAG_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Tag> getFollowingTags() {
		return followingTags;
	}
	public void setFollowingTags(Set<Tag> followingTags) {
		this.followingTags = followingTags;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Tag.class)
    @JoinTable(name="BC_XREF_USER_CREATED_TAG",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="TAG_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Tag> getCreatedTags() {
		return createdTags;
	}
	public void setCreatedTags(Set<Tag> createdTags) {
		this.createdTags = createdTags;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Dish.class)
    @JoinTable(name="BC_XREF_USER_FOLLOWING_DISH",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="DISH_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Dish> getFollowingDishs() {
		return followingDishs;
	}
	public void setFollowingDishs(Set<Dish> followingDishs) {
		this.followingDishs = followingDishs;
	}
	
	@Column(name = "SPOT_COUNT")
	public Integer getTotalSpotCount() {
		if(totalSpotCount==null){
			return 0;
		}
		return totalSpotCount;
	}
	public void setTotalSpotCount(Integer totalSpotCount) {
		this.totalSpotCount = totalSpotCount;
	}
	
	@Column(name = "NOM_COUNT")
	public Integer getNomCount() {
		return nomCount;
	}
	public void setNomCount(Integer nomCount) {
		this.nomCount = nomCount;
	}
	
	@Column(name = "WANTED_COUNT")
	public Integer getWantedCount() {
		return wantedCount;
	}
	public void setWantedCount(Integer wantedCount) {
		this.wantedCount = wantedCount;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Spot.class)
    @JoinTable(name="BC_XREF_SPOT_USER_GOOD_COUNT",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="SPOT_ID")
               )
	public Set<Spot> getGoodCountSpots() {
		return goodCountSpots;
	}
	public void setGoodCountSpots(Set<Spot> goodCountSpots) {
		this.goodCountSpots = goodCountSpots;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Spot.class)
    @JoinTable(name="BC_XREF_SPOT_USER_GREAT_COUNT",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="SPOT_ID")
               )
	public Set<Spot> getGreatCountSpots() {
		return greatCountSpots;
	}
	public void setGreatCountSpots(Set<Spot> greatCountSpots) {
		this.greatCountSpots = greatCountSpots;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Place.class)
    @JoinTable(name="BC_XREF_USER_NOM_PLACE",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="PLACE_ID")
               )
	public Set<Place> getNomedPlaces() {
		return nomedPlaces;
	}
	public void setNomedPlaces(Set<Place> nomedPlaces) {
		this.nomedPlaces = nomedPlaces;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=Tag.class)
    @JoinTable(name="BC_XREF_USER_TAG",
    		joinColumns=
                @JoinColumn(name="USER_ID"),
            inverseJoinColumns=
                @JoinColumn(name="TAG_ID")
               )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Tag> getMyTags() {
		return myTags;
	}
	public void setMyTags(Set<Tag> myTags) {
		this.myTags = myTags;
	}
	
	@JSON(serialize=false)
	@OneToMany(mappedBy = "id.user")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<UserTaskXref> getMyTasks() {
		return myTasks;
	}
	public void setMyTasks(Set<UserTaskXref> myTasks) {
		this.myTasks = myTasks;
	}
	
	@Column(name = "FEED_TIMELINE")
	public Long getFeedFetchTimeline() {
		return feedFetchTimeline;
	}
	public void setFeedFetchTimeline(Long feedFetchTimeline) {
		this.feedFetchTimeline = feedFetchTimeline;
	}
	
	@Column(name = "NOTIFICATION_TIMELINE")
	public Long getNotificatonFetchTimeline() {
		return notificatonFetchTimeline;
	}
	public void setNotificatonFetchTimeline(Long notificatonFetchTimeline) {
		this.notificatonFetchTimeline = notificatonFetchTimeline;
	}
	
	@Column(name = "LOGIN_TIMES")
	public Integer getLoginTimes() {
		if(loginTimes == null){
			return 0;
		}
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	
	@Column(name = "LAST_LOGIN_TIME")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
		}else if(!(obj instanceof User)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((User)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(name)
				.toString();
	}
}
