package com.bocai.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.SpotGuide;
import com.bocai.manager.SpotGuideManager;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "list_spot_guide", location = "component/spot_guide_list.jsp")
})
public class ListSpotGuideAction extends BaseAction<Object> {

	private static final String LIST_SPOT_GUIDE = "list_spot_guide";
	private static final int HOTEST = 1;
	private List<SpotGuide> spotGuides;
	@Autowired 
	private SpotGuideManager spotGuideManager;
	private int type;
	
	@Action("list_spot_guide")
	public String list(){
		try{
			switch(type){
				case HOTEST:
					spotGuides = spotGuideManager.getHotestSpotGuides();
					break;
				default:
					break;
			}
		}catch(Exception e){
			addActionError(e.getMessage());
		}
		return LIST_SPOT_GUIDE;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	public List<SpotGuide> getSpotGuides() {
		return spotGuides;
	}

	public void setSpotGuides(List<SpotGuide> spotGuides) {
		this.spotGuides = spotGuides;
	}

	public SpotGuideManager getSpotGuideManager() {
		return spotGuideManager;
	}

	public void setSpotGuideManager(SpotGuideManager spotGuideManager) {
		this.spotGuideManager = spotGuideManager;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
