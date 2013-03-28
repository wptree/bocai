package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Place;
import com.bocai.manager.PlaceManager;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.PlaceVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings({"serial","unchecked"})
@ParentPackage("spot")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_side_place_more.jsp"),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","modelVo.*,aggSpotPager.*"}
	)
})
public class MoreAtPlaceAction extends BaseAction<Place> {

	private static final int PAGE_SIZE = 6;
	private long id;
	private int at;
	@Autowired
	private PlaceManager placeManager;
	private Place model;
	private PlaceVo modelVo;
	private Pagination aggSpotPager;
	
	@Action("more_at_place")
	public String execute(){
		if(id != 0){
			model = placeManager.getById(id);
			aggSpotPager = placeManager.getAggSpotsByPlace(model.getId(), PAGE_SIZE, at);
		}
		return SUCCESS;
	}
	
	@Action("ajax_more_at_place")
	public String ajaxExecute(){
		if(id != 0){
			model = placeManager.getById(id);
			modelVo = PlaceVo.toBasic(model);
			Pagination p = placeManager.getAggSpotsByPlace(model.getId(), PAGE_SIZE, at);
			aggSpotPager = (Pagination)p.clone();
		}
		//replace domain object list with value object list
		if(aggSpotPager.getList()!=null &&
				!aggSpotPager.getList().isEmpty()){
			List<AggregatedSpot> aggSpots = (List<AggregatedSpot>)aggSpotPager.getList();
			List<AggregatedSpotVo> aggSpotVos = new ArrayList<AggregatedSpotVo>();
			for (AggregatedSpot aggSpot : aggSpots){
				aggSpotVos.add(AggregatedSpotVo.fromAggSpotBase(aggSpot));
			}
			aggSpotPager.setList(aggSpotVos);
		}
		return AJAX;
	}
	
	@Override
	public Place getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public PlaceVo getModelVo() {
		return modelVo;
	}

	public void setModelVo(PlaceVo modelVo) {
		this.modelVo = modelVo;
	}

	public Pagination getAggSpotPager() {
		return aggSpotPager;
	}

	public void setAggSpotPager(Pagination aggSpotPager) {
		this.aggSpotPager = aggSpotPager;
	}

	public void setModel(Place model) {
		this.model = model;
	}
}
