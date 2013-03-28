package com.bocai.web.action.user;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.image.ImageInfo;
import com.bocai.common.image.ImageScale;
import com.bocai.common.image.ImageScaleImpl;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.User;
import com.bocai.manager.UserManager;
import com.bocai.web.action.BaseAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;


@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name="errorPage",location="/WEB-INF/page/404.jsp"),
	@Result(name = "ajax_result", type = "json", 
		params = {"includeProperties","returnMsg"}
	),
	@Result(name = "ajax_upload_result", type = "json", 
			params = {
				"includeProperties", "returnMsg,tempPath,originalPath,imgOriginalW,imgOriginalH", 
				"wrapPrefix","<textarea>", 
				"wrapSuffix", "</textarea>", 
				"contentType","text/html" }
	),
	@Result(name = "ajax_saveAvatar_result", type = "json", 
			params = {"includeProperties", "middlePath,smallPath,returnMsg"})
})
public class UserPhotoAction extends BaseAction<User>{
	
	private String avatarRoot;
	
	private String originalFolder;
	private String middleFolder;
	private String smallFolder;
	
	private String tempPath;

	private String originalPath;
	private String middlePath;
	private String smallPath;
	
	private File userPhoto;
	@Autowired 
	private UserManager userManager;
	private User user;	
	
	private String userPhotoContentType;
	private String userPhotoFileName;
	
	private String returnMsg;
	
	private Integer imgOriginalW;
	private Integer imgOriginalH;
	
	private String imgAreaSelectParam;
	
	private ImageScale imageScale;
		
	private static int IMAGE_MAX_SIZE = 300;
	
	
	@InputConfig(resultName = "ajax_upload_result")
	public String uploadTemporaryFile(){
		if(userPhoto==null){
			setReturnMsg("请指定您要上传的图片。");
		}else{
			ImageInfo info = new ImageInfo();
			File tempFile = null;
			int acceptW=0, acceptH=0; 
			try {
				info.setInput(new FileInputStream(userPhoto));
				info.check();
				tempFile = new File(originalFolder+File.separator+getModel().getId()+"temp."+info.getFormatName());
				acceptW=info.getWidth();acceptH=info.getHeight();
				if(info.getWidth()>IMAGE_MAX_SIZE){
					acceptW = IMAGE_MAX_SIZE;
					//维持原图的高宽比
					float hwrate = (float)info.getHeight()/(float)info.getWidth();
					acceptH =(int)(acceptW*hwrate);
				}else if(info.getHeight()>IMAGE_MAX_SIZE){
					acceptH = IMAGE_MAX_SIZE;
					float whrate = (float)info.getWidth()/(float)info.getHeight();
					acceptW =(int)(acceptH*whrate);
				}
				
				imageScale.resizeFix(userPhoto, tempFile, acceptW, acceptH,0);
				String mark = "@bocai/"+getModel().getName();
				//Calculate the offsetX for mark, the value for Chinese is bigger than the one for English 
				int offsetX = mark.getBytes().length!=mark.length()?100:30;
				offsetX = (mark.getBytes().length*10-offsetX)*-1;
				imageScale.imageMark(tempFile, tempFile, acceptW, acceptH, 4, offsetX, -20, mark, Color.WHITE, 14, 50);
				
				setImgOriginalW(acceptW);
				setImgOriginalH(acceptH);
				setTempPath(ServletActionContext.getServletContext().getContextPath()+"/avatar/original/"+tempFile.getName());
				setOriginalPath(ServletActionContext.getServletContext().getContextPath()+"/avatar/original/"+tempFile.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "ajax_upload_result";
	}

	
	public String saveAvatar(){
		ImageInfo info = new ImageInfo();
		File originalFileTemp = new File(SystemConfig.getStaticRoot()+tempPath);
		try {
			info.setInput(new FileInputStream(originalFileTemp));
			info.check();
			File orignal = new File(originalFolder+File.separator+getModel().getId()+"."+info.getFormatName()); 
			FileUtils.copyFile(originalFileTemp, orignal);
			if(imgAreaSelectParam!=null&&!"".equals(imgAreaSelectParam)){
				if(orignal!=null&&orignal.exists()){
					File middleFile = new File(getMiddleFolder()+File.separator+orignal.getName());
					File smallFile = new File(getSmallFolder()+File.separator+orignal.getName());
					String[] params = imgAreaSelectParam.split(",");
					//resize
					int x1 = Integer.parseInt(params[0]),y1=Integer.parseInt(params[1]),x2=Integer.parseInt(params[2]),y2=Integer.parseInt(params[3]);
					imageScale.resizeFix(orignal, middleFile, 100, 100, y1, x1, x2-x1, y2-y1,0);
					imageScale.resizeFix(orignal, smallFile, 50, 50, y1, x1, x2-x1, y2-y1,0);
				
					//setMiddlePath(ServletActionContext.getServletContext().getContextPath()+"/avatar/middle/"+middleFile.getName());
					//setSmallPath(ServletActionContext.getServletContext().getContextPath()+"/avatar/small/"+smallFile.getName());
					
					getModel().setAvatar(SystemConfig.imageContext()+"/avatar/small/"+smallFile.getName());
					userManager.update(getModel());
					setReturnMsg("图像更新成功！");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setReturnMsg("图像更新失败！");
		}finally{
			originalFileTemp.delete();
		}
		return "ajax_saveAvatar_result";
	}
	
	public String getAvatar(){
		return null;
	}
		
	public File getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserPhotoContentType() {
		return userPhotoContentType;
	}

	public void setUserPhotoContentType(String userImageContentType) {
		this.userPhotoContentType = userImageContentType;
	}

	public String getUserPhotoFileName() {
		return userPhotoFileName;
	}

	public void setUserPhotoFileName(String userImageFileName) {
		this.userPhotoFileName = userImageFileName;
	}
	
	@Override
	public User getModel() {
		return user;
	}

	@Override
	protected void prepareModel() throws Exception {
		user = getSessionUser();
		imageScale = new ImageScaleImpl();
		StringBuffer root = new StringBuffer(SystemConfig.getStaticRoot());
		originalFolder = root.append(ServletActionContext.getServletContext().getContextPath()).append(File.separator).append("avatar").append(File.separator).append("original").toString();
		root = null;
		middleFolder = originalFolder.replaceFirst("original", "middle");
		smallFolder = originalFolder.replaceFirst("original", "small");
		
		File dirO = new File(originalFolder);
		File dirM = new File(middleFolder);
		File dirS = new File(smallFolder);
		if(!dirO.exists()){
			dirO.mkdirs();
		}
		if(!dirM.exists()){
			dirM.mkdirs();
		}
		if(!dirS.exists()){
			dirS.mkdirs();
		}
		dirO = null;dirM = null;dirS = null;
		
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getOriginalFolder() {
		return originalFolder;
	}

	public void setOriginalFolder(String originalFolder) {
		this.originalFolder = originalFolder;
	}

	public String getMiddleFolder() {
		return middleFolder;
	}

	public void setMiddleFolder(String middleFolder) {
		this.middleFolder = middleFolder;
	}

	public String getSmallFolder() {
		return smallFolder;
	}

	public void setSmallFolder(String smallFolder) {
		this.smallFolder = smallFolder;
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}

	public String getMiddlePath() {
		return middlePath;
	}

	public void setMiddlePath(String middlePath) {
		this.middlePath = middlePath;
	}

	public String getSmallPath() {
		return smallPath;
	}

	public void setSmallPath(String smallPath) {
		this.smallPath = smallPath;
	}
	
	public String getImgAreaSelectParam() {
		return imgAreaSelectParam;
	}

	public void setImgAreaSelectParam(String imgAreaSelectParam) {
		this.imgAreaSelectParam = imgAreaSelectParam;
	}
	
	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}


	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}


	public String getReturnMsg() {
		return returnMsg;
	}


	public void setImgOriginalW(Integer imgOriginalW) {
		this.imgOriginalW = imgOriginalW;
	}


	public Integer getImgOriginalW() {
		return imgOriginalW;
	}


	public void setImgOriginalH(Integer imgOriginalH) {
		this.imgOriginalH = imgOriginalH;
	}


	public Integer getImgOriginalH() {
		return imgOriginalH;
	}


	public void setAvatarRoot(String avatarRoot) {
		this.avatarRoot = avatarRoot;
	}


	public String getAvatarRoot() {
		return avatarRoot;
	}


}
