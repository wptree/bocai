package com.bocai.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;


@SuppressWarnings("serial")
public class AggregatedSpotVo implements Serializable{
	
	private Long id;
	private double averagePrice;
	private Integer spottedNum = 0;
	private Integer nomNum = 0;
	private Integer wantedNum = 0;
	private Integer tastedNum = 0;
	private Long lastSpotId;
	private String lastSpotImgType;
	private String lastSpotPhotoPath;
	private Long lastSpotCreatedById;
	private String lastSpotCreatedByName;
	private Date lastSpotCreatedAt;
	private String lastSpotPostBy;
	private String lastSpotDescription;
	private Long dishId;
	private String dishName;
	private String dishStyle;
	private String dishType;
	private Long placeId;
	private String placeName;
	private String place2ndName;
	private String placeFullName;
	private String createdByName;
	private Long createdById;
	private String description;
	private List<UserVo> spottedBys;
	private boolean spottedByHasMore;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSpottedNum() {
		if(null==spottedNum){
			return 0;
		}
		return spottedNum;
	}
	public void setSpottedNum(Integer spottedNum) {
		this.spottedNum = spottedNum;
	}
	public Integer getNomNum() {
		if(null==nomNum){
			return 0;
		}
		return nomNum;
	}
	public void setNomNum(Integer nomNum) {
		this.nomNum = nomNum;
	}
	public Long getLastSpotId() {
		return lastSpotId;
	}
	public void setLastSpotId(Long lastSpotId) {
		this.lastSpotId = lastSpotId;
	}
	public String getLastSpotImgType() {
		if(null==lastSpotImgType){
			return "jpg";
		}
		return lastSpotImgType;
	}
	public void setLastSpotImgType(String lastSpotImgType) {
		this.lastSpotImgType = lastSpotImgType;
	}
	public String getDishName() {
		if(null==dishName){
			return "未知菜品";
		}
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Integer getWantedNum() {
		if(null==wantedNum){
			return 0;
		}
		return wantedNum;
	}
	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
	}
	public Integer getTastedNum() {
		if(null==tastedNum){
			return 0;
		}
		return tastedNum;
	}
	public void setTastedNum(Integer tastedNum) {
		this.tastedNum = tastedNum;
	}
	public String getLastSpotPhotoPath() {
		if(null==lastSpotPhotoPath){
			return "";
		}
		return lastSpotPhotoPath;
	}
	public void setLastSpotPhotoPath(String lastSpotPhotoPath) {
		this.lastSpotPhotoPath = lastSpotPhotoPath;
	}
	public Long getDishId() {
		return dishId;
	}
	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}
	public Long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		if(null==placeName){
			return "无名餐厅";
		}
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	public String getDescription() {
		if(null==description){
			return "这家伙很懒，神马也没有说";
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserVo> getSpottedBys() {
		return spottedBys;
	}
	public void setSpottedBys(List<UserVo> spottedBys) {
		this.spottedBys = spottedBys;
	}
	public String getPlace2ndName() {
		if(null==place2ndName){
			return "无名分店";
		}
		return place2ndName;
	}
	public void setPlace2ndName(String place2ndName) {
		this.place2ndName = place2ndName;
	}
	public String getDishStyle() {
		if(null==dishStyle){
			return "";
		}
		return dishStyle;
	}
	public void setDishStyle(String dishStyle) {
		this.dishStyle = dishStyle;
	}
	public String getDishType() {
		if(null==dishType){
			return "";
		}
		return dishType;
	}
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}
	public Long getLastSpotCreatedById() {
		return lastSpotCreatedById;
	}
	public void setLastSpotCreatedById(Long lastSpotCreatedById) {
		this.lastSpotCreatedById = lastSpotCreatedById;
	}
	public String getLastSpotCreatedByName() {
		return lastSpotCreatedByName;
	}
	public void setLastSpotCreatedByName(String lastSpotCreatedByName) {
		this.lastSpotCreatedByName = lastSpotCreatedByName;
	}
	public Date getLastSpotCreatedAt() {
		return lastSpotCreatedAt;
	}
	public void setLastSpotCreatedAt(Date lastSpotCreatedAt) {
		this.lastSpotCreatedAt = lastSpotCreatedAt;
	}
	public String getLastSpotPostBy() {
		return lastSpotPostBy;
	}
	public void setLastSpotPostBy(String lastSpotPostBy) {
		this.lastSpotPostBy = lastSpotPostBy;
	}
	public String getLastSpotDescription() {
		if(null==lastSpotDescription){
			return "这家伙很懒，神马也没有说";
		}
		return lastSpotDescription;
	}
	public void setLastSpotDescription(String lastSpotDescription) {
		this.lastSpotDescription = lastSpotDescription;
	}
	public boolean isSpottedByHasMore() {
		return spottedByHasMore;
	}
	public void setSpottedByHasMore(boolean spottedByHasMore) {
		this.spottedByHasMore = spottedByHasMore;
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
		else if(!(obj instanceof AggregatedSpotVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((AggregatedSpotVo)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(id)
				.append(" ")
				.append(getDishName())
				.append(" @ ")
				.append(getPlaceName())
				.toString();
	}
	
	public static AggregatedSpotVo fromAggSpotMini(AggregatedSpot aggSpot){
		if(aggSpot == null) return null;
		AggregatedSpotVo vo = new AggregatedSpotVo();
		vo.setNomNum(aggSpot.getNomNum());
		vo.setSpottedNum(aggSpot.getSpottedNum());
		vo.setWantedNum(aggSpot.getWantedNum());
		vo.setTastedNum(aggSpot.getTastedNum());
		vo.setLastSpotId(aggSpot.getLastSpot()!=null?aggSpot.getLastSpot().getId():null);
		vo.setLastSpotImgType(aggSpot.getLastSpot()!=null?aggSpot.getLastSpot().getImgType():null);
		vo.setDishName(aggSpot.getDish()!=null?aggSpot.getDish().getName():null);
		return vo;
	}
	
	public static AggregatedSpotVo fromAggSpotBase(AggregatedSpot aggSpot){
		if(aggSpot == null) return null;
		AggregatedSpotVo vo = new AggregatedSpotVo();
		vo.setId(aggSpot.getId());
		vo.setAveragePrice(aggSpot.getAveragePrice());
		vo.setDishId(aggSpot.getDish()!=null?aggSpot.getDish().getId():null);
		vo.setDishName(aggSpot.getDish()!=null?aggSpot.getDish().getName():null);
		vo.setDishStyle(aggSpot.getDish()!=null?aggSpot.getDish().getStyle():null);
		vo.setDishType(aggSpot.getDish()!=null?aggSpot.getDish().getType():null);
		vo.setPlaceId(aggSpot.getPlace()!=null?aggSpot.getPlace().getId():null);
		vo.setPlaceName(aggSpot.getPlace()!=null?aggSpot.getPlace().getName():null);
		vo.setPlace2ndName(aggSpot.getPlace()!=null?aggSpot.getPlace().getSecondaryName():null);
		vo.setPlaceFullName(aggSpot.getPlace()!=null?aggSpot.getPlace().getFullName():null);
		vo.setNomNum(aggSpot.getNomNum());
		vo.setSpottedNum(aggSpot.getSpottedNum());
		vo.setWantedNum(aggSpot.getWantedNum());
		vo.setTastedNum(aggSpot.getTastedNum());
		vo.setLastSpotId(aggSpot.getLastSpot()!=null?aggSpot.getLastSpot().getId():null);
		vo.setLastSpotImgType(aggSpot.getLastSpot()!=null?aggSpot.getLastSpot().getImgType():null);
		vo.setLastSpotPhotoPath(aggSpot.getLastSpot()!=null?aggSpot.getLastSpot().getPhotoPath():null);
		vo.setLastSpotCreatedById(aggSpot.getLastSpot()!=null && aggSpot.getLastSpot().getCreatedBy()!=null ? 
				aggSpot.getLastSpot().getCreatedBy().getId():null);
		vo.setLastSpotCreatedByName(aggSpot.getLastSpot()!=null && aggSpot.getLastSpot().getCreatedBy()!=null ? 
				aggSpot.getLastSpot().getCreatedBy().getName():null);
		vo.setLastSpotCreatedAt(aggSpot.getLastSpot()!=null? aggSpot.getLastSpot().getCreatedAt():null);
		vo.setLastSpotDescription(aggSpot.getLastSpot()!=null? aggSpot.getLastSpot().getDescription():null);
		vo.setLastSpotPostBy(aggSpot.getLastSpot()!=null? aggSpot.getLastSpot().getPostBy():null);
		vo.setCreatedById(aggSpot.getCreatedBy()!=null?aggSpot.getCreatedBy().getId():null);
		vo.setCreatedByName(aggSpot.getCreatedBy()!=null?aggSpot.getCreatedBy().getName():null);
		vo.setDescription(aggSpot.getDescription());
		if(aggSpot.getSpottedBys()!=null){
			List<UserVo> spottedBys = new ArrayList<UserVo>();
			User[] users = aggSpot.getSpottedBys().toArray(new User[0]);
			int len = 8;
			if(users.length <8){
				len = users.length;
			}else{
				vo.setSpottedByHasMore(true);
			}
			for (int i=0; i <len ; i++ ){
				User user = users[i];
				if(user!=null){
					spottedBys.add(UserVo.fromUserMin(user));
				}
			}
			vo.setSpottedBys(spottedBys);
		}
		return  vo;
	}
	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}
	public String getPlaceFullName() {
		return placeFullName;
	}
}
