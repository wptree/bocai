package com.bocai.web.action.manage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.UserStatus;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.CommentManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("manage")
@Results({
	@Result(name = "data_status", location = "datastatus.jsp"),
	@Result(name = "ajax_change_result", type = "json", 
			params = {"includeProperties","result,returnMsg"}
		),
})
public class ChangeDataStatus extends BaseAction<Object> {
	private String dataType;
	private Integer status;
	private Long id;
	
	private Boolean result;
	private String returnMsg;
	
	@Autowired
	private SpotManager spotManager;
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	@Autowired
	private CommentManager commentManager;
	@Autowired
	private TagManager tagManager;
	@Autowired
	private UserManager userManager;
	
	@Action("data_status")
	public String input(){
		return "data_status";
	}
	

	public String changeStatus(){
		try {
			if(getId()!=null){
				if(getDataType().equalsIgnoreCase("aggspot")){
					AggregatedSpot aggSpot = aggSpotManager.getById(getId());
					if(aggSpot != null){
						aggSpot.setStatus(getStatus());
						aggSpotManager.update(aggSpot);
					}
					
				}else if(getDataType().equalsIgnoreCase("spot")){
					Spot spot = spotManager.getById(getId());
					if(spot != null){
						spot.setStatus(getStatus());
						spotManager.update(spot);
					}
				}else if(getDataType().equalsIgnoreCase("comment")){
					Comment comment = commentManager.getById(getId());
					if(comment != null){
						comment.setStatus(getStatus());
						commentManager.update(comment);
					}
				}else if(getDataType().equalsIgnoreCase("tag")){
					Tag tag = tagManager.getById(getId());
					if(tag != null){
						
					}
				}else if(getDataType().equalsIgnoreCase("user")){
					User user = userManager.getById(getId());
					if(user != null){
						switch(getStatus()){
							case 0: user.setStatus(UserStatus.NORMAL);break;
							case -1: user.setStatus(UserStatus.LOCKED);break;
							default: break;
						}
						userManager.update(user);
					}
				}
			}
			result = true;
			returnMsg = "更新成功";
		} catch (Exception e) {
			result = false;
			returnMsg = "更新失败！原因："+e.getMessage();
			e.printStackTrace();
		}
		return "ajax_change_result";
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}


	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}


	public String getReturnMsg() {
		return returnMsg;
	}

}
