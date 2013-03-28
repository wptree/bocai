package com.bocai.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.web.util.ImageHelper;
/**
 * <p>
 * Domain Class of AggregatedSpot.
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
@Table(name="BC_AGG_SPOT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AggregatedSpot implements Serializable {

	private static final long serialVersionUID = 2553658659339208500L;
	
	private Long id;
	private Dish dish;
	private Place place;
	private String description;
	private double averagePrice;
	private Integer status;
	private Spot lastSpot;
	private Set<Spot> spots;
	private Set<User> wantedBys;
	private Set<User> testedBys;
	private Set<User> spottedBys;
	private Set<User> sharedBys;
	private Set<User> nomBys;
	private Integer spottedNum = 0;
	private Integer nomNum = 0;
	private Integer wantedNum = 0;
	private Integer tastedNum = 0;
	private Integer viewedNum = 0;
	private Integer sharedNum = 0;
	private User createdBy;
	private Date createdAt;
	private User updatedBy;
	private Date updatedAt;
	
	public AggregatedSpot(){};
	
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
	
	@JSON(serialize=false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "DISH_ID", nullable = false)
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	@JSON(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLACE_ID", nullable = false)
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "STATUS", nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JSON(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Last_SPOT_ID", nullable = false)
	public Spot getLastSpot() {
		return lastSpot;
	}
	public void setLastSpot(Spot bestSpot) {
		this.lastSpot = bestSpot;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class, mappedBy="wantedAggSpots")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getWantedBys() {
		if (wantedBys == null) {
			wantedBys = new HashSet<User>();
		}
		return wantedBys;
	}
	public void setWantedBys(Set<User> wantedBys) {
		this.wantedBys = wantedBys;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class,mappedBy="testedAggSpots")
	public Set<User> getTestedBys() {
		return testedBys;
	}
	public void setTestedBys(Set<User> testedBys) {
		this.testedBys = testedBys;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class, mappedBy="aggSpotted")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getSpottedBys() {
		if(spottedBys==null){
			spottedBys = new HashSet<User>();
		}
		return spottedBys;
	}
	public void setSpottedBys(Set<User> spottedBys) {
		this.spottedBys = spottedBys;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class,mappedBy="sharedAggSpots")
	public Set<User> getSharedBys() {
		return sharedBys;
	}
	public void setSharedBys(Set<User> sharedBys) {
		this.sharedBys = sharedBys;
	}
	
	@JSON(serialize=false)
	@OneToMany( mappedBy="aggSpot")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Spot> getSpots() {
		if(spots==null){
			spots = new HashSet<Spot>();
		}
		return spots;
	}
	public void setSpots(Set<Spot> spots) {
		this.spots = spots;
	}
	
	@Column(name = "NOM_NUM")
	public Integer getNomNum() {
		return nomNum;
	}
	public void setNomNum(Integer nomNum) {
		this.nomNum = nomNum;
	}
	
	@Column(name = "SPOTTED_NUM")
	public Integer getSpottedNum() {
		return spottedNum;
	}
	public void setSpottedNum(Integer spottedNum) {
		this.spottedNum = spottedNum;
	}
	
	@Column(name = "VIEWED_NUM")
	public Integer getViewedNum() {
		return viewedNum;
	}
	public void setViewedNum(Integer viewedNum) {
		this.viewedNum = viewedNum;
	}
	
	@Column(name = "SHARED_NUM")
	public Integer getSharedNum() {
		return sharedNum;
	}
	public void setSharedNum(Integer sharedNum) {
		this.sharedNum = sharedNum;
	}
	
	@Column(name = "TASTED_NUM")
	public Integer getTastedNum() {
		return tastedNum;
	}
	public void setTastedNum(Integer tastedNum) {
		this.tastedNum = tastedNum;
	}
	
	@Column(name = "WANTED_NUM")
	public Integer getWantedNum() {
		return wantedNum;
	}
	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
	}
	@JSON(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "CREATED_AT", nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class,mappedBy="nomAggSpots")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<User> getNomBys() {
		if(nomBys==null){
			nomBys = new HashSet<User>();
		}
		return nomBys;
	}
	public void setNomBys(Set<User> nomBys) {
		this.nomBys = nomBys;
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
		else if(!(obj instanceof AggregatedSpot)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((AggregatedSpot)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(" ")
				.append(dish!= null ? dish.getName() : "")
				.append(" @ ")
				.append(place != null ? place.getName() : "")
				.toString();
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}
	@Column(name = "AVERAGE_PRICE")
	public double getAveragePrice() {
		return averagePrice;
	}
	
	public String getSpotImgPath(int size){
		if(getLastSpot()==null){
			return ImageHelper.getMissingImagePath(size);
		}else{
			return ImageHelper.getSpotImagePath(getLastSpot().getId(), size, getLastSpot().getImgType());
		}
	}
}
