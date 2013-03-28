<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>波菜网 分享健康美食 旅游美食引擎 关注的人/店/粉丝</title>
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/css/people.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/userpanel.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/search.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:include value="../common/header.jsp"></s:include>
<s:include value="../common/scripts.jsp" ></s:include>
<!--people content start-->
<script type="text/javascript">
	function getFollowToUser(pageAt){
		$("#followToUser").css("font-weight","bold");
		$("#followByUser").css("font-weight","normal");
		$("#pageTitle").html("关注的人");
		$.ajax({url: "user_profile!followToUserPage.bc",
			  type: "POST",
			  dataType: "json",
			  data:{"pageAt":pageAt,"id":"${request.id}"},
			  success: function(jsonObj){
					// attach the template
					$("#userlist").setParam('followship',jsonObj.followShip);
					$("#userlist").setParam('userLogin',jsonObj.userLogin);
					// process the template
					$("#userlist").processTemplate(jsonObj);
					
					$("#paginator").setParam('functionName',"getFollowToUser");
					$("#paginator").processTemplate(jsonObj);
					
			  }
		});
	}
	function getFollowByUser(pageAt){
		$("#followToUser").css("font-weight","normal");
		$("#followByUser").css("font-weight","bold");
		$("#pageTitle").html("的粉丝");
		$.ajax({url: "user_profile!fanPage.bc",
			  type: "POST",
			  dataType: "json",
			  data:{"pageAt":pageAt,"id":"${request.id}"},
			  success: function(jsonObj){
					$("#userlist").setParam('followship',jsonObj.followShip);
					$("#userlist").setParam('userLogin',jsonObj.userLogin);
					$("#userlist").setParam('functionName',"getLatestUser");
					// process the template
					$("#userlist").processTemplate(jsonObj);
					
					$("#paginator").setParam('functionName',"getFollowByUser");
					$("#paginator").processTemplate(jsonObj);
			  }
		});
	}
	$(document).ready(function(){
		// attach the template
		$("#userlist").setTemplateElement("template");
		$("#paginator").setTemplateElement("paginator_template");
		if("${request.active}"=="0"){
			getFollowToUser(0);
		}else if("${request.active}"=="1"){
			getFollowByUser(0);
		}; 
		
	});
</script>
<div class="people_contentout">
    <div class="people_content">
        <div class="people_content_left">
            <div class="people_content_left_a">
                <span class="s1">
                  <s:if test="#session.sessionLoginUser.id==#request.user.id">我</s:if><s:else>${request.user.name}</s:else><span id="pageTitle">关注的人</span>
                </span>
                <span class="s2"><a href="javascript:void(0);" onclick="getFollowToUser(0);" id="followToUser">关注</a> | <a id="followByUser" href="javascript:void(0);" onclick="getFollowByUser(0);" class="qlvse">粉丝</a></span>
                <span class="s3"><a href="${pageContext.request.contextPath}/main.bc" class="shenhe2">去地图上看看&gt;&gt;</a></span>
            </div>
          <div class="people_content_left_b">
          <div class="pbocai1"></div>
          <textarea id="template" style="display:none">
          	<ul >
             {#foreach $T.dataPage.list as buser}
              <li>
                <div class="pbocai2le"><a href="${pageContext.request.contextPath}/user/profile.bc?id={$T.buser.id}"><img src="{$T.buser.avatar}" width="42" height="42" /></a></div>
                <div class="pbocai2ri">
                  <div class="pbocai2ri1"><span class="s1"><a style="color:#000" href="${pageContext.request.contextPath}/user/profile.bc?id={$T.buser.id}">{$T.buser.name}</a></span> <span class="s2"> </span> <span class="s3">{$T.buser.totalSpotCount}次分享，{$T.buser.followedByNumber}个粉丝 </span> 
                 	 <s:set name="score"></s:set>
                 	 <span class="s4"></span> 
                  	 <span class="s5"><a id="{$T.buser.id}_user_followTag" onclick="{#if $P.userLogin}global.changeFollow('{$T.buser.id}','user');{#else}global.needLogin();{#/if}" href="javascript:void(0);">{$P.followship[$T.buser.id]}</a></span> 
                  </div>
                  <div class="pbocai2ri2">{$T.buser.cityName}</div>
                  <div class="pbocai2ri3">{$T.buser.selfDescription}</div>
                  <div class="pbocai2ri6"></div>
                </div>
              </li>
              {#/for}
            </ul>
          </textarea>
          <div class="pbocai2" id="userlist">
          	<span style="margin:20px"><img src="${staticContext}/images/loading.gif"/></span>
          </div>
          <div class="pbocai3"></div>  
        </div>
        <s:include value="../component/paginator.jsp"></s:include>
        </div>
        <div class="people_content_right">
             <s:include value="../component/search.jsp"></s:include>
             <s:include value="userpanel.jsp"></s:include>
            </div>
    </div>
</div>
<!--people content end-->
<s:include value="../common/foot.jsp"></s:include>
<script type="text/javascript">
	headtab(3);
</script>
</body>
</html>
