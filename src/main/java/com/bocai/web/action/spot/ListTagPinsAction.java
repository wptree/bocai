package com.bocai.web.action.spot;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.manager.TagManager;
import com.bocai.vo.PinVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "ajax", location="pins.jsp")
})
public class ListTagPinsAction extends BaseAction<Object> {

	private final int PAGE_SIZE = 20;
	
	@Autowired
	private TagManager tagManager;
	private int no;
	private List<PinVo> pins;
	
	@Override
	public Object getModel() {
		return this;
	}
	@Override
	protected void prepareModel() throws Exception {}
	
	@Action("pins")
	public String list(){
		if(no < 1) no = 1;
		pins = tagManager.getLatestTagPins(no, PAGE_SIZE);
		return AJAX;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public List<PinVo> getPins() {
		return pins;
	}
	public void setPins(List<PinVo> pins) {
		this.pins = pins;
	}
}
