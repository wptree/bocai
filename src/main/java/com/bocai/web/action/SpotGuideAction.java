package com.bocai.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Tag;
import com.bocai.manager.TagManager;
import com.bocai.vo.PinVo;


@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "input", location = "guide.jsp"),
	@Result(name = "detail", location = "guide_detail.jsp")
})
public class SpotGuideAction extends BaseAction<Object>{
	
	private static final int DETAIL_AGG_SPOT_SIZE = 8;
	@Autowired
	private TagManager tagManager;
	private List<PinVo> pins;
	private Tag tag;
	private Pagination page;
	private long id;
	private int no;
	
	@Action("guide")
	public String spotGuide(){
		if(id > 0){
			return detail();
		}else{
			pins = tagManager.getLatestTagPins(1, 20);
			return "input";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String detail(){
		tag = tagManager.getById(id);
		page = tagManager.getAggSpotsForTag(id, no, DETAIL_AGG_SPOT_SIZE);
		if(page.getList()!=null){
			List<Object[]> objs = (List<Object[]>) page.getList(); 
			List<AggregatedSpot> aggs = new ArrayList<AggregatedSpot>();
			for(Object[] obj: objs){
				if(obj.length>2){
					aggs.add((AggregatedSpot) obj[2]);
				}
			}
			page.setList(aggs);
		}
		return "detail";
	}

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}

	public List<PinVo> getPins() {
		return pins;
	}
	public void setPins(List<PinVo> pins) {
		this.pins = pins;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
