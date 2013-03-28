<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${staticContext}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/popup.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/citypicker.css" rel="stylesheet" type="text/css" />

<title>波菜 - Spider</title>
<!-- import common css -->
<!-- import common scripts -->
<script type="text/javascript" src="${staticContext}/js/jquery-1.5.2.js"></script>
</head>
<body>
<script type="text/javascript" src="${staticContext}/js/citypicker.js"></script>
<script type="text/javascript">

</script>
<div style="width:100%;height:100%">
    <div style="margin:50px">
    	<div style="margin:100px;width:500px;height:500px">
	       <s:form id="updatePhotoForm" name="updatePhotoForm" method ="POST" enctype ="multipart/form-data" action="tools!parseCsv.bc">
	           <div class="mpictureUpload">
	           	<input name="struts.enableJSONValidation" type="hidden" value="true" />
	            <input name="struts.enableFileUploadValidation" type="hidden" value="true" />
	            <label id="provinceCityLabel" class="inputLabel" onclick="$('#provinceCityLabel').hide();$('#provinceCity').focus();">省份，城市</label>
            	<input onfocus="$('#provinceCityLabel').hide();CityPicker.show('#provinceCity');" type="text" name="city" id="provinceCity" class="input1"/><span style="color:red; font-weight: bold;">*</span>
	          	<div><s:file name="uploadFile"></s:file></div>
	          	<div><button type="submit">上传</button></div>
	           </div>
           </s:form>
       </div>
    </div>
</div>

</body>
</html>
