<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bc" uri="bocai-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pod p15">
	<div id="user_profile" class="mb10 clean ">
		<s:if test="#session.sessionLoginUser.id==#request.user.id">
			<a id="user_avatar" class="l br5 pr" target="_blank" href="${pageContext.request.contextPath}/user/my_set.bc">
			<img id="user_avatar_image" class="br5" src="${request.user.avatar}" width="80" height="80" />
			</a>
			<div id="edit_div" class="pa none w80 h80 op7" style="left:0px; top:0px; background-color: rgb(255, 255, 255);">
				<div class="fb tc vm c000 clink" style="display: table-cell;height:80px; padding: 0 5px; width:70px;">编辑资料</div>
			</div>
		</s:if>
		<s:else>
			<img class="l br5" id="user_avatar_image" src="${request.user.avatar}" width="80" height="80" />
		</s:else>
		<div class="ml90">
			<p class="mb10">
			<span class="fb f14 ctitle">${request.user.name}</span>
		    </p>
			<p class="mb10">
				<span class="cdesc">城市</span>
				<span class="ctitle fb">${request.user.cityName}</span></p>
			<p class="mb10">
				<span class="cdesc">头衔</span>
				<span class="ctitle fb">${bc:score2LevelName(request.user.score)}</span></p>
        	<p class="">
	        	<span class="cdesc">分享</span>
	        	<span class="clink fb"><s:if test="#request.user.totalSpotCount==null">0</s:if><s:else>${request.user.totalSpotCount}</s:else></span>
	        	<span class="cdesc">&nbsp;喜欢</span>
	        	<span class="clink fb"><s:if test="#request.user.nomCount==null">0</s:if><s:else>${request.user.nomCount}</s:else></span>
	        	<span class="cdesc">&nbsp;想吃</span>
	        	<span class="clink fb"><s:if test="#request.user.wantedCount==null">0</s:if><s:else>${request.user.wantedCount}</s:else></span></p>
		</div>
	</div>
	<dl id="user_detail" class="">
        <dd class="cgray lh150">
        	<s:if test="#session.sessionLoginUser.id!=#request.user.id">
		    <a class="hlink" href="javascript:void(0);" 
		    		onclick="OP.sendPrivateMsg(${request.user.id}, '${request.user.name}');return false;">私信TA</a>
		    </s:if>
        	<s:if test="#request.user.selfDescription==null && #session.sessionLoginUser.id==#request.user.id">您还没有个性签名哦，赶紧<a class="fb" href="${pageContext.request.contextPath}/user/my_set.bc">添加</a>上去吧！</s:if>
        	<s:else>${request.user.selfDescription}</s:else>
        	<s:if test="#request.user.blogUrl==null && #session.sessionLoginUser.id==#request.user.id">
        		<p class="mt5">您还没留博客地址哦，赶紧<a class="fb" href="${pageContext.request.contextPath}/user/my_set.bc">添加</a>上去吧！</p></s:if>
        	<s:else>${request.user.blogUrl}</s:else>
        </dd>
    </dl>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#user_avatar").mouseover(function(){
		$("#edit_div").show();
	});
	
	$("#user_avatar").mouseout(function(){
		$("#edit_div").hide("slow");
	});
	
	var user_avatar_src = $("#user_avatar_image").attr("src");
	if(user_avatar_src.length>0){
		if(user_avatar_src.indexOf("small")>0){
			var new_src = user_avatar_src.replace("small","middle");
			$("#user_avatar_image").attr("src",new_src);
		}
	}
	
});
</script>