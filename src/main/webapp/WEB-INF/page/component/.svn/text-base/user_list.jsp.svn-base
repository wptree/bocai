<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bc" uri="bocai-tags" %>
<ul id="uuu<s:property value="type" />" class="pod ulist">
	<s:iterator value="users" var="user">
		<li class="uli clean pr">
	       	<a class="avatar br5 mr10" href="${pageContext.request.contextPath}/user/profile.bc?id=${user.id}"
	       		target="_blank" >
	       		<img class="br5" src="${user.avatar}" userId="${user.id}" title="${user.name}" width="32" height="32" /></a>
	       	<div class="fl lh16">
	       		<div style="display:block;">
	       			<a class="uname" href="${pageContext.request.contextPath}/user/profile.bc?id=${user.id}"
	       				target="_blank"><s:property value="name" /></a>
				</div>
				<span class="finfo">共分享了${user.totalSpotCount}个美食</span>
	       	</div>
	      	<div class="fr ps f_btn">
	      		<bc:followTag targetId="${user.id}" targetType="user" followText="+关注" unFollowText="取消关注"/>
	      	</div>
	  	</li>
  	</s:iterator>
  	<s:if test="type==\"1\"">
  		<li class="limore clean"><a class="fr lh14" href="${pageContext.request.contextPath}/boke.bc?active=0">更多…</a></li>
  	</s:if>
  	<s:else>
  		<li class="limore clean"><a class="fr lh14" href="${pageContext.request.contextPath}/boke.bc?active=1">更多…</a></li>
  	</s:else>
</ul>