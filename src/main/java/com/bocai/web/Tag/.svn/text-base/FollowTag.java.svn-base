package com.bocai.web.Tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.constant.FollowingType;
import com.bocai.dao.domain.User;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;

@Service
@SuppressWarnings("serial")
public class FollowTag extends RequestContextAwareTag{
	
	private String targetId;
	private String targetType;
	private String followText;
	private String unFollowText;
	
	private UserManager userManager;
	

	private String body;
	
	private Object prepareTarget(){
		 Object target = null;
		 if(FollowingType.USER.toString().equals(getTargetType().toUpperCase())){
			 target = userManager.getById(Long.parseLong(getTargetId()));
		 }else if(FollowingType.DISH.toString().equals(getTargetType().toUpperCase())){
			 DishManager dishManager = (DishManager)this.getRequestContext().getWebApplicationContext().getBean("dishManager");
			 target = dishManager.getById(Long.parseLong(getTargetId()));
		 }else if(FollowingType.PLACE.toString().equals(getTargetType().toUpperCase())){
			 PlaceManager placeManager = (PlaceManager)this.getRequestContext().getWebApplicationContext().getBean("placeManager");
			 target = placeManager.getById(Long.parseLong(getTargetId()));
		 }else if(FollowingType.GUIDE.toString().equals(getTargetType().toUpperCase())){
			 TagManager tagManager = (TagManager)this.getRequestContext().getWebApplicationContext().getBean("tagManager");
			 target = tagManager.getById(Long.parseLong(getTargetId()));
		 }else{
			 
		 }
		 
		 return target;
	}
	
	
	//<a id="6_user_followTag" class="follow-button" href="javascript:void(0);" onclick="global.needLogin();"> +关注 </a>
	@Override
	 public int doStartTagInternal() throws JspException {
		 StringBuffer content = new StringBuffer("<a href=\"javascript:void(0);\" onclick=\"");
		 //changeFollow(isFollow,targetId,targetType,newText);
		 //changeFollow(true,'1','user','取 消 关 注');
		 //changeFollow(false,'1','user','关 注 TA');
		 Object obj = pageContext.getSession().getAttribute(AppConstant.SESSION_LOGIN_USER);
		 if(obj!=null){
			 User currentUser = (User)obj;
			 userManager = (UserManager)this.getRequestContext().getWebApplicationContext().getBean("userManager");  
			 Object target = prepareTarget();
			 if(userManager.isFollowed(currentUser.getId(), target)){
				 content.append("global.changeFollow('");
				 content.append(getTargetId());
				 content.append("','");
				 content.append(getTargetType());
				 content.append("');\" ");
				 content.append("title=\"取消关注\" ");
				 content.append("class=\"unfollow-button\" ");
				 content.append("id=\"");
				 content.append(getTargetId());
				 content.append("_");
				 content.append(getTargetType());
				 content.append("_followTag\">");
				 content.append(getUnFollowText());
			 }else{
				 content.append("global.changeFollow('");
				 content.append(getTargetId());
				 content.append("','");
				 content.append(getTargetType());
				 content.append("');\" ");
				 content.append("title=\"加关注\" ");
				 content.append("class=\"follow-button\" ");
				 content.append("id=\"");
				 content.append(getTargetId());
				 content.append("_");
				 content.append(getTargetType());
				 content.append("_followTag\">");
				 content.append(getFollowText());
			 }
		 }else{
			 content.append("global.changeFollow('");
			 content.append(getTargetId());
			 content.append("','");
			 content.append(getTargetType());
			 content.append("');\" ");
			 content.append("title=\"加关注\" ");
			 content.append("class=\"follow-button\" ");
			 content.append("id=\"");
			 content.append(getTargetId());
			 content.append("_");
			 content.append(getTargetType());
			 content.append("_followTag\">");
			 content.append(getFollowText());
		 }
		 content.append("</a>");
		 
		 body = content.toString();
		 content = null;
		 return EVAL_PAGE;
	 }
	 
	 public int doEndTag() throws JspException{
        try {
        	JspWriter out = pageContext.getOut();
			out.println(this.body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return EVAL_PAGE;
	 }
	

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetType() {
		return targetType;
	}
	
	public static void main(String[] args){
		System.out.print(FollowingType.USER.equals(FollowingType.USER));
	}

	public void setFollowText(String followText) {
		this.followText = followText;
	}

	public String getFollowText() {
		if(followText==null){
			return "关 注 TA";
		}
		return followText;
	}

	public void setUnFollowText(String unFollowText) {
		this.unFollowText = unFollowText;
	}

	public String getUnFollowText() {
		if(unFollowText==null){
			return "取 消 关 注";
		}
		return unFollowText;
	}

}
