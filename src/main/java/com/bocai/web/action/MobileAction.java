package com.bocai.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "list", location = "mobile.jsp")
})
public class MobileAction extends BaseAction<Object> {
	
	private String phone;

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {}
	
	@Action("mobile")
	public String list(){
		return LIST;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
