<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod">
	<h2>该店活跃波客<span class="pcount f_brackets">(<s:property value="bokePager.totalCount"/>)</span></h2>
	<ul id="activePeopleList"  class="clean">
		<s:if test="bokePager.totalCount == 0">
			<li class="cchrome1"><span class="lh150">还没有人在该店发现过美食</span></li>
		</s:if>
		<s:iterator value="bokePager.list" var="user">
			<li class="mr4 mb8 w32 h32 bcw bs1 br3 fl"><a class="br3" target="_blank" href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="id" />">
				<img class="br3" src="<s:property value="avatar" />" userId="<s:property value="id" />" width="32" height="32" /></a></li>
		</s:iterator>
		<s:if test="bokePager.totalPage > 1">
			<li class="mb8 h32 bcchrome2 bs1 br3 fl disI pr"><a id="apl_more" class="img-more lh32 br3" target="_blank" 
				href="${pageContext.request.contextPath}/place/more_active_boke.bc">更多</a></li>
		</s:if>
	</ul>
	<script type="text/javascript">
		$(function() {
			var uid = <s:property value="id" />
			function initActivePeopleList (){
				var href = $('#activePeopleList .img-more').attr('href');
				$('#activePeopleList .img-more').attr('href', href+"?id=" + uid);
				$("#activePeopleList img").each(function(index){
					BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
				});
			}
			initActivePeopleList();
		});
	</script>
</div>