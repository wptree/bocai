package com.bocai.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.bocai.config.SystemConfig;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "success", type="stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "fileInputStream",
            "contentDisposition", "filename=\"${f}\"",
            "bufferSize", "1024"
    })
})
public class DownloadAction extends BaseAction<Object>  {
	
	private InputStream fileInputStream;
    private String f;
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		fileInputStream = new FileInputStream(
				new File(SystemConfig.getDownloadPath(), f));
	}

	@Action("download")
	public String execute(){
		return SUCCESS;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}
}
