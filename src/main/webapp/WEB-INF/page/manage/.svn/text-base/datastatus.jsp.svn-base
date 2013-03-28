<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>关于我们 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/css/about.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
$(document).ready(function() {
	$("#data-management").ajaxForm({
	 	   dataType : "json",
	        success: function(response) {
        		$("#update-result").html(response.returnMsg);
        		if(response.result){
        			$("#update-result").css({color: "green"});
        		}else{
        			$("#update-result").css({color: "red"});
        		}
	        }
	    });
});

</script>
<div class="bodyContainer">
<s:include value="../common/header.jsp"></s:include>
<s:include value="../common/scripts.jsp" ></s:include>
<div class="about_contentout">
    <div class="about_content">
        <div class="about_content_left">
            <div class="about_content_left_a">
                <span id="bigtitle" class="s1">数据管理</span>
            </div>
          <div class="about_content_left_b">
          <div class="pbocai1"></div>
          <div id="container" class="pbocai2" style="text-align: left">
          <div class="fl f14 p20">
          		<s:form id="data-management" action="change_data_status!changeStatus.bc" method="post" >
          			<div class="p10 h20 lh20">
	          			<span class="fl w100">数据类型:</span>
	          			<span class="fl m5 w150"><select name="dataType">
	          					<option value="aggspot">AggSpot</option>
	          					<option value="spot">Spot</option>
	          					<option value="comment">Comment</option>
	          					<option value="tag">Tag</option>
	          					<option value="user">User</option>
	          				 </select>
	          			</span>
          			</div>
          			<div class="p10 h20 lh20">
          				<span class="fl w100">ID:</span>
          				<span class="fl m5 w150"><input name="id"/></span>
          			</div>
          			<div class="p10 h20 lh20">
          				<span class="fl w100">状态:</span>
          				<span class="fl m5 w150"><select name="status">
	          					<option value="-1">-1</option>
	          					<option value="0">0</option>
	          					<option value="1">1</option>
	          				 </select>
	          			</span>
          			</div>
          			<div class="p10"><input type="submit" value="提交"/></div>
          		</s:form>
          	</div>
          	<div class="p20"><span id="update-result"></span></div>
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
	                  	<a href="${pageContext.request.contextPath}/main.bc"> 返回首页</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	<a href="javascript:void(0);" onclick="aboutus();">关于波菜</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	 <a href="javascript:void(0);" onclick="ourstatus();">开发动态</a>
	                </div>
	                </li>
	                <li style="border-bottom: 0">
	                <div class="wenzhangyou">
	                  	<a href="javascript:void(0);" onclick="linkus();">联系我们</a>
	                </div>
	                </li>
                </ul>
                </div>
                <div class="about_content_right_d3"></div>
            </div>
            </div>
    </div>
</div>
<s:include value="../common/foot.jsp"></s:include>
</div>
</body>
</html>
