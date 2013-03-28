package com.bocai.web.action.spot;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.JSONObject;
import com.bocai.common.image.ImageInfo;
import com.bocai.common.image.ImageScale;
import com.bocai.common.image.ImageScaleImpl;
import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.DishTypeMeta;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.DishManager;
import com.bocai.manager.DishTypeMetaManager;
import com.bocai.manager.LocationManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.SpotUploadRequest;
import com.bocai.web.action.BaseAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@SuppressWarnings("serial")
@ParentPackage("spot")
@Results({
	@Result(name = "spot_input", location = "upload.jsp"),
	@Result(name="errorPage",location="/WEB-INF/page/404.jsp"),
	@Result(name = "ajax_result", type = "json", 
		params = {"includeProperties","saveResult,returnMsg"}
	),
	@Result(name = "ajax_getLastSpot", type = "json", 
			params = {"includeProperties","saveResult,spot"}
		),
	@Result(name = "ajax_upload_result", type = "json", 
			params = {
				"includeProperties", "saveResult,returnMsg", 
				"wrapPrefix","<textarea>", 
				"wrapSuffix", "</textarea>", 
				"contentType","text/html" }
	)
	
})

public class SpotAction extends BaseAction<SpotUploadRequest> {

	private String dishImageRoot;
	private File spotImg;	
	private String spotDishName;
	private String spotPlaceId;
	private String spotDishTypeName;
	private String spotDishTagName;
	private String spotPrice;
	private String description;
	private String newSpotDishName;
	private String newSpotDishTypeName;
	private String newSpotDishTagName;
	private String newSpotPlaceId;
	private boolean send2Sina;

	@Autowired
	private SpotManager spotManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private AggregatedSpotManager aggSpotManager;

	private User user;	
	private Long userId;
	private Long spotId;
	private Long aggSpotId;

	private Spot spot;
	private String returnMsg;
	private boolean saveResult;
	
	private int[] imageSizeArray;
		
	private SpotUploadRequest uploadRequest;

	@Action("spot_input")
	public String input(){
		return "spot_input";
	}
	
	@ActionRole( ActionRoleType.USER )
	@InputConfig(resultName = "ajax_upload_result")
	public String saveSpot(){
		if(uploadRequest.getSpotImg()==null){
			setSaveResult(false);
			setReturnMsg("请指定您要上传的图片。");
		}else{
			imageSizeArray = SystemConfig.getSpotImgSizeDefineArray();
			uploadRequest.setDishImageRoot(dishImageRoot);
			uploadRequest.setImgSizeArray(imageSizeArray);
			setSaveResult(spotManager.persistSpot(uploadRequest));
			setReturnMsg(isSaveResult()?"您上传的菜已经保存成功！":"没有上传成功，请仔细检查各项输入！");
		}
		return "ajax_upload_result";
	}
	
	public String getSpotComments(){
		return null;
	}
	
	public String getLastSpot(){
		User user = userManager.getById(getUserId());
		Spot spot = spotManager.getLastSpotByUser(user.getId());
		if(spot==null){
			setSaveResult(false);
		}else{
			setSaveResult(true);
			setSpot(spot);
		}
		
		return "ajax_getLastSpot";
	}
	
	public String wantSpot(){
		User user = getSessionUser();
		if(user!=null && aggSpotId !=null){
			if(userManager.isWantedByUser(user.getId(), aggSpotId)){
				setSaveResult(false);
				setReturnMsg("您以前已经“想吃”过了");
			}else{
				setSaveResult(true);
				userManager.addWantedSpot(user.getId(), aggSpotId);
			}
		}else{
			setSaveResult(false);
			setReturnMsg("服务器出小差了，请稍后再试");
		}
		return "ajax_result";
	}
	
	public String nomSpot(){
		User user = getSessionUser();
		if(user!=null && aggSpotId !=null){
			AggregatedSpot spot = aggSpotManager.getById(aggSpotId);
			if(userManager.isNommedByUser(user.getId(), aggSpotId)){
				setSaveResult(false);
				setReturnMsg("您以前已经“喜欢”过TA了");
			}else{
				setSaveResult(true);
				userManager.addNomedSpot(user.getId(), aggSpotId);
			}
		}else{
			setSaveResult(false);
			setReturnMsg("服务器出小差了，请稍后再试");
		}
		return "ajax_result";
	}

	@Override
	public SpotUploadRequest getModel() {
		return uploadRequest;
	}

	@Override
	protected void prepareModel() throws Exception {
		StringBuffer buf = new StringBuffer(SystemConfig.getStaticRoot());
		dishImageRoot = buf.append(ServletActionContext.getServletContext().getContextPath()).append(File.separator).append("spot").toString();
		buf = null;
		setUser(getSessionUser());
		uploadRequest = new SpotUploadRequest();
		uploadRequest.setCurrentUser(getSessionUser());
	}


	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}


	public String getReturnMsg() {
		return returnMsg;
	}

	public void setDishImageRoot(String dishImageRoot) {
		this.dishImageRoot = dishImageRoot;
	}


	public String getDishImageRoot() {
		return dishImageRoot;
	}
	
	public static void main(String[] args){
		System.out.println(new Date());
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}

	public boolean isSaveResult() {
		return saveResult;
	}

	public void setSpotImg(File spotImg) {
		this.spotImg = spotImg;
	}

	public File getSpotImg() {
		return spotImg;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setSpotDishTagName(String spotDishTagName) {
		this.spotDishTagName = spotDishTagName;
	}

	public String getSpotDishTagName() {
		return spotDishTagName;
	}

	public void setSpotDishTypeName(String spotDishTypeName) {
		this.spotDishTypeName = spotDishTypeName;
	}

	public String getSpotDishTypeName() {
		return spotDishTypeName;
	}

	public void setSpotDishName(String spotDishName) {
		this.spotDishName = spotDishName;
	}

	public String getSpotDishName() {
		return spotDishName;
	}

	public void setSpotPrice(String spotPrice) {
		this.spotPrice = spotPrice;
	}

	public String getSpotPrice() {
		return spotPrice;
	}

	public void setNewSpotDishName(String newSpotDishName) {
		this.newSpotDishName = newSpotDishName;
	}

	public String getNewSpotDishName() {
		return newSpotDishName;
	}
	
	public void setNewSpotDishTypeName(String newSpotDishTypeName) {
		this.newSpotDishTypeName = newSpotDishTypeName;
	}

	public String getNewSpotDishTypeName() {
		return newSpotDishTypeName;
	}

	public void setNewSpotDishTagName(String newSpotDishTagName) {
		this.newSpotDishTagName = newSpotDishTagName;
	}

	public String getNewSpotDishTagName() {
		return newSpotDishTagName;
	}

	public void setSpotPlaceId(String spotPlaceId) {
		this.spotPlaceId = spotPlaceId;
	}

	public String getSpotPlaceId() {
		return spotPlaceId;
	}

	public void setNewSpotPlaceId(String newSpotPlaceId) {
		this.newSpotPlaceId = newSpotPlaceId;
	}

	public String getNewSpotPlaceId() {
		return newSpotPlaceId;
	}	

	public SpotUploadRequest getUploadRequest() {
		return uploadRequest;
	}

	public void setUploadRequest(SpotUploadRequest uploadRequest) {
		this.uploadRequest = uploadRequest;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}

	public Long getSpotId() {
		return spotId;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}
	public Long getAggSpotId() {
		return aggSpotId;
	}

	public void setAggSpotId(Long aggSpotId) {
		this.aggSpotId = aggSpotId;
	}

	public void setSend2Sina(boolean send2Sina) {
		this.send2Sina = send2Sina;
	}

	public boolean isSend2Sina() {
		return send2Sina;
	}
	

}
