package com.bocai.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.manager.SpotManager;
import com.bocai.manager.UserManager;
import com.bocai.util.DateUtil;
import com.bocai.vo.UserVo;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "boke_input", location = "boke.jsp"),
	@Result(name = "ajax_save_result", type = "json", 
			params = {"includeProperties","saveResult,returnMsg"}
		),
	@Result(name = "ajax_last_spot", type = "json", 
			params = {"includeProperties","hasSpot,lastSpotId,lastSpotStr,lastSpotTimeStr,lastSpotDesc"}
		),
	@Result(name = "ajax_get_data_page", type = "json", 
				params = {"includeProperties","succeed,dataPage.*,followShip.*","excludeProperties",".*hibernateLazyInitializer"}
			)
})
public class BokeAction extends BaseAction<User>{
	
	private static final int PAGE_SIZE = 20;
	private static final Logger log = LoggerFactory
			.getLogger(BokeAction.class);
	
	private Long id;
	private User model;
	private Pagination dataPage;
	private Map<String, String> followShip;
	private boolean userLogin;
	private Integer pageAt = 0;
	private Integer active;
	private Boolean succeed;
	private boolean hasSpot;
	private Long lastSpotId;
	private String lastSpotStr;
	private String lastSpotTimeStr;
	private String lastSpotDesc;
	private String keyword;
	
	@Autowired 
	private UserManager userManager;
	@Autowired 
	private SpotManager spotManager;
	
	
	
	@Action("boke")
	public String boke(){
		if(getSessionUser()!=null){
			User user = getSessionUser();
			Pagination userFollowPage = userManager.getFollowed(user.getId(), FollowingType.USER, 10, 0);
			request.setAttribute("userFollowPage", userFollowPage);
		}
		try {
			if(keyword!=null){
				keyword=new String(keyword.getBytes("iso-8859-1"),"UTF-8");
			}
			request.setAttribute("keyword", getKeyword());
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage());
		}
		request.setAttribute("active", getActive()==null?0:getActive());
		return "boke_input";
	}
	
	public String userLastSpot(){
		if(id==null){
			hasSpot = false;
		}else{
			try {
				User user = userManager.getById(id);
				if(user.getTotalSpotCount()!=null&&user.getTotalSpotCount()>0){
					hasSpot = true;
					Spot lastSpot = spotManager.getLastSpotByUser(id);
					if(lastSpot!=null){
						lastSpotId = lastSpot.getId();
						Place place = lastSpot.getPlace();
						StringBuffer bf = new StringBuffer();
						bf.append(lastSpot.getDish().getName()).append("@").append(place.getName()).append("-").append(place.getSecondaryName());
						lastSpotStr = bf.toString();
						bf = null;
						if(DateUtil.compareDay(lastSpot.getCreatedAt(),new Date())<365){
							lastSpotTimeStr = DateUtil.getDateStr(lastSpot.getCreatedAt(), DateUtil.SHORT_FORMAT);
						}else{
							lastSpotTimeStr = DateUtil.getDateStr(lastSpot.getCreatedAt(), DateUtil.LABEL_CN_FORMAT);
						}
						bf = new StringBuffer(lastSpotTimeStr);
						bf.append("通过").append(lastSpot.getPostBy());
						lastSpotTimeStr = bf.toString();
						bf = null;
						if(lastSpot.getDescription()!=null){
							lastSpotDesc = "“"+lastSpot.getDescription()+"”";
						}else{
							lastSpotDesc = "";
						}
						
					}else{
						hasSpot = false;
					}
				}else{
					hasSpot = false;
				}
			} catch (Exception e) {
				hasSpot = false;
				e.printStackTrace();
			}
		}
		
		return "ajax_last_spot";
	}
	
	public String bestUser(){
		Pagination p = userManager.getBestUsers(PAGE_SIZE,getPageAt());
		dataPage = (Pagination) p.clone();
		List userlist = dataPage.getList();
		followShip = new HashMap<String,String>();
		if(getSessionUser()!=null){
			setUserLogin(true);
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				String temp = userManager.isFollowed(getSessionUser().getId(), user)? "取消关注":"+关注";
				followShip.put(user.getId().toString(), temp);
			}
		}else{
			setUserLogin(false);
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				followShip.put(user.getId().toString(), "+关注");
			}
		}
		
		List<UserVo> voList = new ArrayList<UserVo>();
		for(int i = 0; i<userlist.size();i++){
			User user = (User) userlist.get(i);
			voList.add(UserVo.fromUserBasic(user));
		}
		dataPage.setList(voList);
		setSucceed(true);
		return "ajax_get_data_page";
	}
	
	public String latestUser(){
		Pagination p = userManager.getLatestUsers(PAGE_SIZE,getPageAt());
		dataPage = (Pagination) p.clone();
		List userlist = dataPage.getList();
		followShip = new HashMap<String,String>();
		if(getSessionUser()!=null){
			setUserLogin(true);
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				String temp = userManager.isFollowed(getSessionUser().getId(), user)? "取消关注":"+关注";
				followShip.put(user.getId().toString(), temp);
			}
		}else{
			setUserLogin(false);
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				followShip.put(user.getId().toString(), "+关注");
			}
		}
		List<UserVo> voList = new ArrayList<UserVo>();
		for(int i = 0; i<userlist.size();i++){
			User user = (User) userlist.get(i);
			voList.add(UserVo.fromUserBasic(user));
		}
		dataPage.setList(voList);
		setSucceed(true);
		return "ajax_get_data_page";
	}
	
	@Override
	public User getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id!=null){
			model = userManager.getById(id);
		}else{
			model = getSessionUser();
		}
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setModel(User model) {
		this.model = model;
	}

	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}

	public Pagination getDataPage() {
		return dataPage;
	}

	public void setFollowShip(Map<String, String> followShip) {
		this.followShip = followShip;
	}

	public Map<String, String> getFollowShip() {
		return followShip;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

	public boolean getUserLogin() {
		return userLogin;
	}

	public void setPageAt(Integer pageAt) {
		this.pageAt = pageAt;
	}

	public Integer getPageAt() {
		return pageAt;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getActive() {
		return active;
	}

	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public void setHasSpot(boolean hasSpot) {
		this.hasSpot = hasSpot;
	}

	public boolean isHasSpot() {
		return hasSpot;
	}

	public void setLastSpotId(Long lastSpotId) {
		this.lastSpotId = lastSpotId;
	}

	public Long getLastSpotId() {
		return lastSpotId;
	}

	public void setLastSpotStr(String lastSpotStr) {
		this.lastSpotStr = lastSpotStr;
	}

	public String getLastSpotStr() {
		return lastSpotStr;
	}

	public void setLastSpotTimeStr(String lastSpotTimeStr) {
		this.lastSpotTimeStr = lastSpotTimeStr;
	}

	public String getLastSpotTimeStr() {
		return lastSpotTimeStr;
	}

	public void setLastSpotDesc(String lastSpotDesc) {
		this.lastSpotDesc = lastSpotDesc;
	}

	public String getLastSpotDesc() {
		return lastSpotDesc;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
