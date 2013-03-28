package com.bocai.web.action.spot;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.Spot;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax_count_spot", type = "json", 
			params = {"includeProperties","saveResult,tip,count"}
	)
})
public class CountSpotAction extends BaseAction<Spot> {
	
	private static final String AJAX_COUNT_SPOT = "ajax_count_spot";
	private static final int GOOD_COUNT = 0;
	private static final int GREAT_COUNT = 1;
	@Autowired 
	private SpotManager spotManager;
	@Autowired
	private UserManager userManager;
	private Spot model;
	private Long id; // spot id
	private int level; // 0 for goodCount, 1 for greatCount
	private boolean saveResult;
	private String tip;
	private int count;
	
	@Action("ajax_count_spot")
	@ActionRole( ActionRoleType.USER )
	public String ajaxCount(){
		try{
			switch(level){
				case GOOD_COUNT:
					if(!userManager.isGoodCountedByUser(getSessionUser().getId(), model.getId())){
						userManager.goodCountSpot(getSessionUser(), model);
						setSaveResult(true);
						setCount(model.getGoodCount());
					}else{
						setTip("已经评过啦");
						setSaveResult(false);
					}
					break;
				case GREAT_COUNT:
					if(!userManager.isGreatCountedByUser(getSessionUser().getId(), model.getId())){
						userManager.greatCountSpot(getSessionUser(), model);
						setCount(model.getGreatCount());
						setSaveResult(true);
					}else{
						setTip("已经评过啦");
						setSaveResult(false);
					}
					break;
				default:
					setTip("参数不正确");
					setSaveResult(false);
			}
			
		}catch(Exception e){
			setTip(e.getMessage());
			setSaveResult(false);
		}
		return AJAX_COUNT_SPOT;
	}
	
	@Override
	public Spot getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null && id != 0){
			model = spotManager.getById(id);
		}
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setModel(Spot model) {
		this.model = model;
	}

	public boolean isSaveResult() {
		return saveResult;
	}

	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
