<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>波菜网 分享健康美食 旅游美食引擎 关于我们</title>
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/css/about.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="common/header.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>

<div class="about_contentout">
    <div class="about_content">
        <div class="about_content_left">
            <div class="about_content_left_a">
                <span class="s1">邮箱验证</span>
            </div>
          <div class="about_content_left_b">
          <div class="pbocai1"></div>
          <div class="pbocai2">
          		<s:set name="vType" value="type" />  
          		<s:if test="%{#vType == 'success'}">
          			<div class="title">
          			恭喜您，邮箱<strong><s:property value="email"/></strong>验证成功！ 
	          		</div>
	          		<div class="aboutus">
	          			<p>请点<a href="default.jsp"><strong>这里</strong></a>跳转至首页（或<span id="secleft"></span>秒后自动跳转）</p>
	          		</div>
          		</s:if>
          		<s:elseif test="%{#vType == 'revalidation'}">
          			<div class="title">
	          		邮箱<strong><s:property value="email"/></strong>已经验证过了，请不要重复验证！
	          		</div>
	          		<div class="aboutus">
	          			<p>请点<a href="login.jsp"><strong>这里</strong></a>直接登录（或<span id="secleft"></span>秒后自动跳转至首页）</p>
	          		</div>
          		</s:elseif>
          		<s:else>
          		</s:else>
          </div>
          <div class="pbocai3"></div>  
        </div>
        </div>
           <div class="about_content_right">
             <div class="about_content_right_d">
                <div class="about_content_right_d1">
                </div>
                <div class="about_content_right_d2">
                <ul id="uu1">
	                <li>
	                <div class="wenzhangyou">
	                  	<a href="${pageContext.request.contextPath}/bocai/main.bc"> 返回首页</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	<a href="${pageContext.request.contextPath}/bocai/about.bc">关于我们</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	 <a href="javascript:void(0);">加入我们</a>
	                </div>
	                </li>
	                <li style="border-bottom: 0">
	                <div class="wenzhangyou">
	                  	<a href="javascript:void(0);"> 联系我们</a>
	                </div>
	                </li>
                </ul>
                </div>
                <div class="about_content_right_d3"></div>
            </div>
            </div>
    </div>
</div>
<!--people content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#secleft').html(5);
		var interval = setInterval(function(){
			var sec = parseFloat($('#secleft').html());
			if(sec <= 0){
				clearInterval(interval);
				window.location.href = "default.jsp";
			}else{
				$('#secleft').html(sec - 1);
			}
		}, 1000);
	});
</script>
</body>
</html>