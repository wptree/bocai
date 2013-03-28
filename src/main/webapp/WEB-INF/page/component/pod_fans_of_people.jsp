<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod">
	<h2><s:if test="model.id == #session.sessionLoginUser.id">我</s:if><s:else><s:property value="model.name"/></s:else>的粉丝<span class="f_brackets pcount">(<s:property value="followerPager.totalCount"/>)</span></h2>
	<ul id="followerList"  class="clean">
		<s:if test="followerPager.totalCount == 0">
			<li class="cchrome1"><span class="lh150"><s:if test="model.id == #session.sessionLoginUser.id">你还没有粉丝哦，多分享，评论美食吧。</s:if>
			<s:elseif test="model.sexy == 0">她还没有粉丝呢。</s:elseif><s:else>他还没有粉丝呢。</s:else></span></li>
		</s:if>
		<s:iterator value="followerPager.list" var="user">
			<li class="mr4 mb8 w32 h32 bcw bs1 br3 fl"><a class="br3" target="_blank" href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="id" />">
				<img class="br3" src="<s:property value="avatar" />" userId="<s:property value="id" />" width="32" height="32" /></a></li>
		</s:iterator>
		<s:if test="followerPager.totalPage > 1">
			<li class="mb8 w32 h32 bcchrome2 bs1 br3 fl disI pr"><a id="fpl_more" class="img-more lh32 br3" target="_blank" 
				href="${pageContext.request.contextPath}/user/follower.bc?id=<s:property value="id" />">更多</a></li>
		</s:if>
	</ul>
	<script type="text/javascript">
		$(function() {
			var uid = <s:property value="model.id" />
			function initPodFollowingBoke (){
				var href = $('#followerList .img-more').attr('href');
				$("#followerList img").each(function(index){
					BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
				});
			}
			initPodFollowingBoke();
		});
	</script>
</div>