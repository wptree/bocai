package com.bocai.web.action;

import java.io.File;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.manager.PlaceManager;
import com.bocai.service.IPSeeker;
import com.bocai.spider.CsvParser;
import com.bocai.util.MapUtil;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "input", location = "test.jsp"),
	@Result(name = "csvparser", location = "parser.jsp"),
	@Result(name = "ajax_result", type = "json", 
			params = {"includeProperties","callResult,returnMsg"}
	),
	@Result(name = "ajax_upload_result", type = "json", 
			params = {
				"includeProperties", "returnMsg,tempPath,originalPath,imgOriginalW,imgOriginalH", 
				"wrapPrefix","<textarea>", 
				"wrapSuffix", "</textarea>", 
				"contentType","text/html" }
	)
})
public class ToolsAction extends BaseAction<Object>{

	private boolean callResult;
	private String returnMsg;
	private File uploadFile;
	private String city;
	private String input;
	
	@Autowired
	private IPSeeker ipService;
	
	@Autowired
	private CsvParser csvParser;
	
	@Autowired
	private PlaceManager placeManager;

	
	@Action("test")
	public String test(){
		return "input";
	}
	
	@Action("csvparser")
	public String csvParser(){
		return "csvparser";
	}
	
	public String parseCsv(){
		System.out.println(uploadFile.getName());
		
		csvParser.parse(city, uploadFile);
			
		return "ajax_upload_result";
	}
	
	@Action("testAjaxCall")
	public String ajaxCall(){
		//your test code here
		
		try {
			returnMsg = placeManager.buildIndex(1000, 0).toString();
			System.out.println(returnMsg);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		setCallResult(true);

		return "ajax_result";
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

	public void setCallResult(boolean callResult) {
		this.callResult = callResult;
	}

	public boolean isCallResult() {
		return callResult;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

}
