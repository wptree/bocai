
package com.bocai.dao.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bocai.web.util.ImageHelper;


@Entity
@Table(name="BC_SPOT")
@PrimaryKeyJoinColumn(name="ID")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Spot extends Post {

	private static final long serialVersionUID = 2632684569556579490L;
	private double price = 0.00;
	private double grade;
	private String description;
	private String links;
	private String photoPath;
	private String imgType;
	private Dish dish;
	private Place place;
	private AggregatedSpot aggSpot;
	private Set<User> goodCountUsers;
	private Integer goodCount = 0;
	private Set<User> greatCountUsers;
	private Integer greatCount = 0;
	private User spotedBy;
	
	@Column(name = "PHOTO_PATH")
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	@Column(name = "PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "GRADE")
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	@Column(name = "GREAT_COUNT")
	public Integer getGreatCount() {
		return greatCount;
	}
	public void setGreatCount(Integer greatCount) {
		this.greatCount = greatCount;
	}
	
	@Column(name = "GOOD_COUNT")
	public Integer getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	
	
	@Column(name = "LINKS")
	public String getLinks() {
		return links;
	}
	public void setLinks(String links) {
		this.links = links;
	}
	
	@Column(name = "IMG_TYPE")
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getImgType() {
		return imgType;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "DISH_ID", nullable = false)
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLACE_ID", nullable = false)
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGG_SPOT_ID")
	public AggregatedSpot getAggSpot() {
		return aggSpot;
	}
	public void setAggSpot(AggregatedSpot aggSpot) {
		this.aggSpot = aggSpot;
	}
	
	@JSON(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPOTED_BY", nullable = false, updatable = false)
	public User getSpotedBy() {
		return spotedBy;
	}
	public void setSpotedBy(User spotedBy) {
		this.spotedBy = spotedBy;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class, fetch=FetchType.LAZY, mappedBy="goodCountSpots")
    @JoinTable(name="BC_XREF_SPOT_USER_GOOD_COUNT",
    		joinColumns=
                @JoinColumn(name="SPOT_ID"),
            inverseJoinColumns=
                @JoinColumn(name="USER_ID")
               )
	public Set<User> getGoodCountUsers() {
		return goodCountUsers;
	}
	public void setGoodCountUsers(Set<User> goodCountUsers) {
		this.goodCountUsers = goodCountUsers;
	}
	
	@JSON(serialize=false)
	@ManyToMany(targetEntity=User.class, fetch=FetchType.LAZY, mappedBy="greatCountSpots")
    @JoinTable(name="BC_XREF_SPOT_USER_GREAT_COUNT",
    		joinColumns=
                @JoinColumn(name="SPOT_ID"),
            inverseJoinColumns=
                @JoinColumn(name="USER_ID")
               )
	public Set<User> getGreatCountUsers() {
		return greatCountUsers;
	}
	public void setGreatCountUsers(Set<User> greatCountUsers) {
		this.greatCountUsers = greatCountUsers;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Spot)){
			return false;
		}
		return new EqualsBuilder()
				.append(super.getId(), ((Spot)obj).getId())
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.getId())
			    .append(" ")
				.append(dish != null ? dish.getName() : "")
				.append(" @ ")
				.append(place != null ? place.getName() : "")
				.toString();
	}
	
	public String getSpotImgPath(int size){
		return ImageHelper.getSpotImagePath(super.getId(), size, imgType);
	}	
	
}
