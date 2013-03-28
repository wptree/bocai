package com.bocai.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bocai.common.constant.AppConstant;
import com.bocai.config.SystemConfig;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "ajax", type = "json", 
			params = {"includeProperties","url,fileName"}
	)
})
public class UploadAction extends BaseAction<Object> {
	
	private static final Logger logger = 
		LoggerFactory.getLogger(UploadAction.class);  

	private File fileInput;   
	private String fileInputFileName;
	private String url;
	private String fileName; //新文件名
	
	@Action("upload_spot")
	@InputConfig(resultName = "ajax")
	public String uploadSpot(){
		try {
			uploadFile();
			return AJAX;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private void uploadFile() throws Exception {

		String savePath = SystemConfig.getUploadOrgPath();
		String extName = "";// 扩展名
		fileInputFileName = new String(fileInputFileName.getBytes("GBK"),
				"utf-8");
		logger.info("upload file with file name ：" + fileInputFileName);
		// 获取扩展名
		if (fileInputFileName.lastIndexOf(".") > -1) {
			extName = fileInputFileName.substring(fileInputFileName
					.lastIndexOf("."));
		}

		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());// 当前时间
		fileName = nowTime + extName;
		url = SystemConfig.getUploadOrgURI() + AppConstant.URL_SEPARATOR
				+ fileName;
		logger.info("upload file to be saved as ：" + savePath + fileName);

		fileInput.renameTo(new File(savePath + AppConstant.URL_SEPARATOR
				+ fileName));

		response.setCharacterEncoding("utf-8");
		logger.info("upload file successfully : " + fileInputFileName);
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
