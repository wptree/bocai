<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>        
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>        
<meta http-equiv="expires" content="0"/>   
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>注册向导 波菜网 旅游美食 美食指南 美食分享 </title>
<link href="${staticContext}/css/login.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/forget-pwd.css" rel="stylesheet" type="text/css" />
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/css/contrl.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/citypicker.css" rel="stylesheet" type="text/css" />
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
</head>
<body>
<div class="main">
<s:include value="../common/header.jsp" ></s:include>
<!--forget pwd content start-->
<div class="login_contentout">
    <div class="login_content">
    <div class="forget-panel">
    	<!--忘记密码开始-->
			<ul id="forget_form" class="forget-ul pl30 pr30 pt20 pb20">
				<li id="progress" class="mb10 clean">
					<div class="l mr8 cprogress f40 fb lh50 ffar">33%</div>
					<div class="l">
						<div class="progressbarbg br3 bort2 borb2 mt10 clean" style="height:10px; width:440px;">
							<div class="l clean" style="height:10px;width:145px;">
								<div class="l complete" style="height:10px;width:131px;"></div>
								<div class="r parrow" style="height:10px;width:14px;"></div>
							</div>
							<div class="step" style="height:10px;width:145px;"></div>
							<div class="r" style="height:10px;width:146px;"></div>
						</div>
						<div class="lh20 f12">
							<span class="gray">设置我的基本信息</span>
						</div>
					</div>
				</li>
				<s:form id="wizardBasicForm" name="form1" method="post" action="wizard_save_basic.bc">
            	<li class="mb10 clean lh45" style="height:45px;">
              		<div class="l mr8 lh45" style="width:140px; text-align:right;"><span class="f18 ffyh fb lh45 ls13 ctitle">我生活的城市</span></div>
              		<div class="l mt8"><input id="cityInput" class="xlarge" type="text" size="15" name="city" 
              			value='<s:property value="city"/>' /></div>
            	</li>
            	<li class="clean lh45" style="height:45px;">
              		<div class="l mr8 lh45" style="width:140px; text-align:right;"><span class="f18 ffyh fb lh45 ls13 ctitle">我是</span></div>
              		<div class="l lh45">
              			<label class="lh45 mr10">
              				<input type="radio" value="1" name="sexy" 
              				<s:if test="%{sexy == 1}">
              					checked="checked"
              				</s:if> />
              				<span class="f14">帅哥</span>
              			</label>
              			<label class="lh45 mr10">
              				<input type="radio" value="2" name="sexy" 
              				<s:if test="%{sexy == 2}">
              					checked="checked"
              				</s:if> />
              				<span class="f14">美女</span>
              			</label>
              			<label class="lh45">
              				<input type="radio" value="0" name="sexy" 
              				<s:if test="%{sexy == 0}">
              					checked="checked"
              				</s:if> />
              				<span class="f14">保密</span>
              			</label>
					</div>
            	</li>
            	<li class="clean lh45 tr" style="height:45px;">
            		<input class="btn" type="reset" value="跳过" onclick="window.location.href='wizard_teste.bc'"></input>
            		<input class="btn success" type="submit" value="下一步" />
            	</li>
            	</s:form>
            </ul>
         <!--忘记密码结束-->
    </div>
    </div>
</div>
<!--forget pwd content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
<script type="text/javascript" src="${staticContext}/js/citypicker.js"></script>
<!-- script start -->
<script type="text/javascript">	
	$('#cityInput').focusin(function(){
		CityPicker.show('#cityInput', function(cityname){
			$('#cityInput').val(cityname);
		});
	});
	$(".footerout").css({backgroundColor:'#F0EEDF'});
</script>
<!-- script end -->
</body>
</html>