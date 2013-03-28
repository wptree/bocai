package com.bocai.web.action.spot;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name="list_agg_spot",location="list_agg_spot.jsp")
})
public class ListAggSpotAction extends BaseAction<Pagination> {

	private static final String LIST_AGG_SPOT = "list_agg_spot";
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	private Pagination model;
	private int at; // current page NO.
	private Long id; // place id
	
	@Action("list_agg_spot")
	public String list(){
		try{
			model = aggSpotManager.getAggSpotsByPlace(id, at);
		}catch(Exception e){
			addActionError(e.getMessage());
		}
		return LIST_AGG_SPOT;
	}
	
	@Override
	public Pagination getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {}

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

	public void setModel(Pagination model) {
		this.model = model;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}
}
