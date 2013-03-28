package com.bocai.web.action.spot;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name="detail_agg_spot",location="detail_agg_spot.jsp")
})
public class DetailAggSpotAction extends BaseAction<AggregatedSpot> {

	private static final String DETAIL_AGG_SPOT = "detail_agg_spot";
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	private AggregatedSpot model;
	private Long id;
	
	@Action("detail_agg_spot")
	public String list(){
		return DETAIL_AGG_SPOT;
	}
	
	@Override
	public AggregatedSpot getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		try{
			if(id != null && id != 0){
				model = aggSpotManager.getDetailById(id);
			}
		}catch(Exception e){
			addActionError(e.getMessage());
			model = null;
		}
	}

	public AggregatedSpotManager getAggSpotManager() {
		return aggSpotManager;
	}

	public void setAggSpotManager(AggregatedSpotManager aggSpotManager) {
		this.aggSpotManager = aggSpotManager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModel(AggregatedSpot model) {
		this.model = model;
	}

}
