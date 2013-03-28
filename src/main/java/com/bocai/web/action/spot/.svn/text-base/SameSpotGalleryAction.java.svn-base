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
import com.bocai.dao.domain.Spot;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.SpotManager;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.SpotVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings({"serial","unchecked"})
@ParentPackage("spot")
@Results({
	@Result(name="success",location="/WEB-INF/page/component/pod_side_same_spot.jsp"),
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","spotPager.*"}
	)
})
public class SameSpotGalleryAction extends BaseAction<AggregatedSpot> {

	private static final int PAGE_SIZE = 6;
	private long id;
	private int at;
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	@Autowired
	private SpotManager spotManager;
	private AggregatedSpot model;
	private Pagination spotPager;
	
	@Action("same_spot_gallery")
	public String execute(){
		if(id != 0){
			model = aggSpotManager.getById(id);
			spotPager = spotManager.getSpotsOnAggSpot(id, PAGE_SIZE, at);
		}
		return SUCCESS;
	}
	
	@Action("ajax_same_spot_gallery")
	public String ajax(){
		Pagination p = spotManager.getSpotsOnAggSpot(id, PAGE_SIZE, at);
		spotPager = (Pagination)p.clone();
		//replace domain object list with value object list
		if(spotPager.getList()!=null &&
				!spotPager.getList().isEmpty()){
			List<Spot> spots = (List<Spot>)spotPager.getList();
			List<SpotVo> spotVos = new ArrayList<SpotVo>();
			for (Spot spot : spots){
				spotVos.add(SpotVo.fromSpotBase(spot));
			}
			spotPager.setList(spotVos);
		}
		return AJAX;
	}
	
	@Override
	public AggregatedSpot getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {}

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

	public Pagination getSpotPager() {
		return spotPager;
	}

	public void setSpotPager(Pagination spotPager) {
		this.spotPager = spotPager;
	}

	public void setModel(AggregatedSpot model) {
		this.model = model;
	}
}
