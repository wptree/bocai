package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.util.StringUtil;
/**
 * <p>
 * Domain Class of Dish.
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
@Table(name="BC_DISH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dish implements Serializable {
	
	private static final long serialVersionUID = -2567629768814532396L;
	private Long id;
	private String name;
	/**
	 * 类别："炒菜","自助餐","西餐","烧烤"...
	 * @DishTypeMeta
	 */
	private String type;
	/**
	 *菜系： "杭帮菜","川菜","粤菜","清真"...
	 * @DishStyleMeta
	 */
	private String style;
	/**
	 * 口味："酸","辣","甜","清淡","麻"...
	 * @DishTasteMeta
	 */
	private String taste;
	private String pinyin;
	private String pinyinHeader;
	private Long spottedCount;
	private Long followerCount;
	private Set<Spot> spots;
	private Set<AggregatedSpot> aggSpots;
	private Set<User> followers;
	
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
	
	@Column(name = "NAME",unique = true, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		setPinyin(StringUtil.getPingYin(name));
		setPinyinHeader(StringUtil.getPinYinHeadChar(name));
	}
	
	@Column(name = "TYPE")
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	@Column(name="STYLE")
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyle() {
		return style;
	}
	
	@Column(name = "TASTE")
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	
	@OneToMany( mappedBy="dish")
	public Set<Spot> getSpots() {
		return spots;
	}
	public void setSpots(Set<Spot> spots) {
		this.spots = spots;
	}
	
	@OneToMany( mappedBy="dish")
	@OrderBy("spottedNum desc")
	public Set<AggregatedSpot> getAggSpots() {
		if(aggSpots==null){
			aggSpots = new HashSet<AggregatedSpot>();
		}
		return aggSpots;
	}
	public void setAggSpots(Set<AggregatedSpot> aggSpots) {
		this.aggSpots = aggSpots;
	}
	
	@Column(name = "PINYIN")
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	@Column(name = "PINYIN_HEADER")
	public String getPinyinHeader() {
		return pinyinHeader;
	}
	public void setPinyinHeader(String pinyinHeader) {
		this.pinyinHeader = pinyinHeader;
	}
	
	@Column(name = "SPOTTED_COUNT")
	public Long getSpottedCount() {
		return spottedCount;
	}
	public void setSpottedCount(Long spottedCount) {
		this.spottedCount = spottedCount;
	}
	
	@Column(name = "FOLLOWER_COUNT")
	public Long getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}
	
	@ManyToMany(targetEntity=User.class, mappedBy="followingDishs")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
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
		}else if(!(obj instanceof Dish)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Dish)obj).getId())
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
