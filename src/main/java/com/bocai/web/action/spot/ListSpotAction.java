package com.bocai.web.action.spot;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.manager.SpotManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name="list_spot",location="list_spot.jsp")
})
public class ListSpotAction extends BaseAction<Pagination> {
	
	private static final String LIST_SPOT = "list_spot";
	private static final int LIST_BY_AGG_SPOT = 1;
	private static final int LIST_BY_PLACE = 2;
	private static final int LIST_BY_DISH = 3;
	@Autowired
	private SpotManager spotManager;
	private Pagination model;
	/**
	 * 1 for LIST_BY_AGG_SPOT;
	 * 2 for LIST_BY_PLACE;
	 * 3 for LIST_BY_DISH
	 */
	private int type;
	/**
	 * current page NO.
	 */
	private int at;
	/**
	 * when type = 1, id should indicate AggSpot id;
	 * when type = 2, id should indicate Place id;
	 * when type = 3, id should indicate Dish id;
	 */
	private Long id;
	
	@Action("list_spot")
	public String list(){
		try{
			switch(type){
			case LIST_BY_AGG_SPOT:
				model = spotManager.getSpotsOnAggSpot(id, at, 20);
				break;
			case LIST_BY_PLACE:
				model = spotManager.getSpotsByPlace(id, at, 20);
				break;
			case LIST_BY_DISH:
				model = spotManager.getSpotsByDish(id, at, 20);
				break;
			default:
				break;
			}
		}catch(Exception e){
			addActionError(e.getMessage());
		}
		return LIST_SPOT;
	}
		
	@Override
	public Pagination getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
