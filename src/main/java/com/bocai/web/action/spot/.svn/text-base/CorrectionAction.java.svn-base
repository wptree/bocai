package com.bocai.web.action.spot;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Correction;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.CorrectionManager;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax_add_correction", type = "json", 
			params = {"includeProperties","isLogin,succeed"}
	)
})
public class CorrectionAction extends BaseAction<Correction>{
	
	private Correction model;
	private boolean isLogin;
	private boolean succeed;
	private User loginUser;
	
	private Long aggSpotId;
	private String title;
	private String content;
	
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private CorrectionManager correctionManager;
	
	public String save(){
		try {
			if(isLogin){
				model.setCreateBy(loginUser.getId());
				model.setCreateByName(loginUser.getName());
				
				loginUser.setScore(loginUser.getScore() + 5);
				userManager.update(loginUser);
				isLogin = false;
			}else{
				model.setCreateByName("匿名");
			}
			model.setCreateAt(System.currentTimeMillis());
			model.setStatus(0);
			
			if(aggSpotId != null){
				AggregatedSpot aggspot = aggSpotManager.getById(aggSpotId);
				Dish dish = aggspot.getDish();
				Place place = aggspot.getPlace();
				model.setDishId(dish.getId());
				model.setDishName(dish.getName());
				model.setPlaceId(place.getId());
				model.setPlaceName(place.getName()+" - "+place.getSecondaryName());
			}
			correctionManager.save(model);
			succeed = true;
			isLogin = true;
		} catch (Exception e) {
			succeed = false;
		}
		
		return "ajax_add_correction";
	}
	
	
	@Override
	public Correction getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(model == null){
			model = new Correction();
		}
		loginUser = getSessionUser();
		isLogin = loginUser==null ? false:true;
	}


	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}


	public boolean isLogin() {
		return isLogin;
	}


	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}


	public boolean isSucceed() {
		return succeed;
	}


	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}


	public User getLoginUser() {
		return loginUser;
	}


	public void setAggSpotId(Long aggSpotId) {
		this.aggSpotId = aggSpotId;
	}


	public Long getAggSpotId() {
		return aggSpotId;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTitle() {
		return title;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getContent() {
		return content;
	}

}
