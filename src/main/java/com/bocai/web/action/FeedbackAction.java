package com.bocai.web.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.dao.domain.Feedback;
import com.bocai.dao.domain.User;
import com.bocai.manager.FeedbackManager;


@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "boke_input", location = "boke.jsp"),
	@Result(name = "ajax_save_result", type = "json", 
			params = {"includeProperties","saveResult,returnMsg"}
		),
	@Result(name = "ajax_get_data_page", type = "json", 
				params = {"includeProperties","userLogin,dataPage.*,followShip.*","excludeProperties",".*hibernateLazyInitializer"}
			)
})
public class FeedbackAction extends BaseAction<Feedback> {

	private Feedback model;
	private boolean saveResult;
	
	private String name;
	private String email;
	private String message;
	
	
	@Autowired
	private FeedbackManager feedbackManager;
	
	
	public String save(){
		try {
			User user = getSessionUser();
			if(user!=null){
				model.setCreateBy(user.getId());
				model.setCreateByName(user.getName());
				model.setReplayEmail(user.getFirstEmail());
				
			}else{
				model.setCreateByName(getName());
				model.setReplayEmail(getEmail());
			}
			
			model.setCreateByName(getName());
			model.setContent(getMessage());
			model.setStatus(0);
			model.setCreateAt(new Date());
			
			feedbackManager.save(model);
			saveResult = true;
			
		} catch (Exception e) {
			saveResult = false;
			e.printStackTrace();
		}
		
		return "ajax_save_result";
	}
	
	
	@Override
	public Feedback getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(model == null){
			model = new Feedback();
		}
		
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}


	public boolean isSaveResult() {
		return saveResult;
	}

}
