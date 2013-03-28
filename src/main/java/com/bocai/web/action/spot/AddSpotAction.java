package com.bocai.web.action.spot;

import java.io.File;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.ActivityType;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.exception.NeedLoginException;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", type = "json", 
		params = {"includeProperties","spotId"}
	)
})
public class AddSpotAction extends BaseAction<Object> {
	
	private Spot spot;
	private Long spotId;
	private Long placeId;
	private String dishName;
	private String desc;
	private Double price;
	private String postBy;
	private String orgImgName;
	private double imgRotateDegree;
	private boolean sync2Sina;
	@Autowired
	private SpotManager spotManager;
	@Autowired
	private DishManager dishManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private UserManager userManager;

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		spot = new Spot();
	}
	
	@Action("add_spot")
	public String add() {
		User user = getSessionUser();
		if(user==null){
			throw new NeedLoginException();
		}
		Dish dish = dishManager.makeSure(dishName);
		if (dish == null) {
			throw new RuntimeException("dish [" + dishName
					+ "] is persisted failed.");
		}
		Place place = placeManager.getById(placeId);
		if(place == null){
			throw new RuntimeException("place with id=" + placeId
					+ " does not exist.");
		}
		spot.setCreatedBy(user);
		spot.setSpotedBy(user);
		spot.setStatus(0);
		spot.setPostBy("web");
		spot.setCreatedAt(new Date());
		spot.setUpdatedAt(new Date());
		spot.setGrade(0);
		spot.setPrice(price != null ? price : 0);
		spot.setDescription(desc);
		spot.setDish(dish);
		spot.setPlace(place);
		spotManager.addSpot(spot, new File(SystemConfig.getUploadOrgPath() + 
				File.separator + orgImgName), imgRotateDegree, request.getContextPath());
		if(sync2Sina){
			if(user.sinaConnected()){
				userManager.weibo2Sina(spot, ActivityType.SPOT_UPLOAD);
			}
		}
		setSpotId(spot.getId());
		return AJAX;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Long getSpotId() {
		return spotId;
	}

	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getPostBy() {
		return postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public boolean isSync2Sina() {
		return sync2Sina;
	}

	public void setSync2Sina(boolean sync2Sina) {
		this.sync2Sina = sync2Sina;
	}

	public String getOrgImgName() {
		return orgImgName;
	}

	public void setOrgImgName(String orgImgName) {
		this.orgImgName = orgImgName;
	}

	public double getImgRotateDegree() {
		return imgRotateDegree;
	}

	public void setImgRotateDegree(double imgRotateDegree) {
		this.imgRotateDegree = imgRotateDegree;
	}
}
