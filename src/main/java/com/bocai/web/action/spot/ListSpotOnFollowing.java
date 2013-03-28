package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Spot;
import com.bocai.manager.UserManager;
import com.bocai.vo.SpotVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings({"serial","unchecked"})
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","pager.*"}
	)
})
public class ListSpotOnFollowing extends BaseAction<Object> {

	private final int PAGE_SIZE = 10;
	private Long uid;
	private int at;
	private Pagination pager;
	@Autowired
	private UserManager userManager;
	
	@Action("ajax_list_spot_on_following_place")
	public String onPlace(){
		if(uid==null || uid == 0 ){
			throw new RuntimeException("Invalid parameter uid");
		}
		Pagination p = userManager.getSpotsOnFollowingPlaceByUserId(uid, at, PAGE_SIZE);
		pager = (Pagination) p.clone();
		List<Spot> spots = (List<Spot>)pager.getList();
		if(spots!=null && !spots.isEmpty()){
			List<SpotVo> spotVos = new ArrayList<SpotVo>();
			for (Spot spot : spots){
				SpotVo spotVo = SpotVo.fromSpotBase(spot);
				if(spotVo!=null){
					spotVos.add(spotVo);
				}
			}
			pager.setList(spotVos);
		}
		return AJAX;
	}
	
	@Action("ajax_list_spot_on_following_people")
	public String onPeople(){
		if(uid==null || uid == 0 ){
			throw new RuntimeException("Invalid parameter uid");
		}
		Pagination p = userManager.getSpotsOnFollowingPeopleByUserId(uid, at, PAGE_SIZE);
		pager = (Pagination) p.clone();
		List<Spot> spots = (List<Spot>)pager.getList();
		if(spots!=null && !spots.isEmpty()){
			List<SpotVo> spotVos = new ArrayList<SpotVo>();
			for (Spot spot : spots){
				SpotVo spotVo = SpotVo.fromSpotBase(spot);
				if(spotVo!=null){
					spotVos.add(spotVo);
				}
			}
			pager.setList(spotVos);
		}
		return AJAX;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Pagination getPager() {
		return pager;
	}

	public void setPager(Pagination pager) {
		this.pager = pager;
	}
}