package com.bocai.web.action.spot;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.bocai.dao.domain.Spot;
import com.bocai.manager.SpotManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name="detail_spot",location="detail_spot.jsp")
})
public class DetailSpotAction extends BaseAction<Spot> {

	private static final String DETAIL_SPOT = "detail_spot";
	private SpotManager spotManager;
	private Spot model;
	private Long id; // spot id
	
	@Action("detail_spot")
	public String list(){
		model.setViewTimes(model.getViewTimes()+1);
		spotManager.update(model);
		
		return DETAIL_SPOT; 
	}
	
	@Override
	public Spot getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		try{
			if(id != null && id != 0){
				model = spotManager.getById(id);
			}
		}catch(Exception e){
			addActionError(e.getMessage());
			model = null;
		}
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModel(Spot model) {
		this.model = model;
	}
	

}
