package com.bocai.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.vo.JTemplateVO;
import com.bocai.web.util.JTemplateHelper;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "ajax_get_template", type = "json", 
		params = {"includeProperties","succeed,model.*"}
	)
})
public class JTemplateAction extends BaseAction<JTemplateVO> {
	
	private static final String AJAX_GET_TEMPLATE = "ajax_get_template";
	private JTemplateVO model;
	private String name;
	private Boolean succeed;
	@Autowired
	private JTemplateHelper jtemplateHelper;
	
	@Action("jtemplate")
	public String list(){
		try{
			model.setContent(jtemplateHelper.getTemplate(name));
			setSucceed(true);
		}catch(Exception e){
			setSucceed(false);
		}
		return AJAX_GET_TEMPLATE;
	}
	
	@Override
	public JTemplateVO getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(	name != null && !StringUtils.isEmpty(name)){
			model = new JTemplateVO();
			model.setName(name);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public void setModel(JTemplateVO model) {
		this.model = model;
	}
}
