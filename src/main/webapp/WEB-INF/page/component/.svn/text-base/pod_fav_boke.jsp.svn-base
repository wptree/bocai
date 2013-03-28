<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod">
	<h2>关于这次分享</h2>
	<dl class="dlist" style="margin-bottom:0">
		<dt class="dtitle">喜欢这道菜的<span class="pcount f_brackets">(<s:property value="nomedByPager.totalCount"/>)</span></dt>
		<dd>
			<ul id="nomlist" class="clean">
				<s:if test="nomedByPager.totalCount == 0">
					<li class="cchrome1"><span class="lh150">暂时没有人喜欢这次分享</span></li>
				</s:if>
				<s:iterator value="nomedByPager.list">
					<li class="mr4 mb8 w32 h32 bcw bs1 br3 fl"><a class="br3" target="_blank" href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="id" />">
						<img class="br3" src="<s:property value="avatar" />" userId="<s:property value="id" />" width="32" height="32" /></a></li>
				</s:iterator>
				<s:if test="nomedByPager.totalPage > 1">
					<li class="mb8 h32 bcchrome2 bs1 br3 fl disI pr"><a id="nl_more" class="img-more lh32 br3" target="_blank" 
						href="${pageContext.request.contextPath}/place/more_nomed.bc">更多</a></li>
				</s:if>
			</ul>
		</dd>
		<dt class="dtitle">想吃这道菜的<span class="pcount f_brackets">(<s:property value="nomedByPager.totalCount"/>)</span></dt>
		<dd>
			<ul id="wantedlist" class="clean">
				<s:if test="wanttedByPager.totalCount == 0">
					<li class="cchrome1"><span class="lh150">暂时没有人想吃这次分享</span></li>
				</s:if>
				<s:iterator value="wanttedByPager.list">
					<li class="mr4 mb8 w32 h32 bcw bs1 br3 fl"><a class="br3" target="_blank" href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="id" />">
						<img class="br3" src="<s:property value="avatar" />" userId="<s:property value="id" />" width="32" height="32" /></a></li>
				</s:iterator>
				<s:if test="wanttedByPager.totalPage > 1">
					<li class="mr8 mb8 w32 h32 bcchrome2 bs1 br3 fl disI pr"><a id="wl_more" class="img-more lh32 br3" target="_blank" 
						href="${pageContext.request.contextPath}/place/more_wantted.bc">更多</a></li>
				</s:if>
			</ul>
		</dd>
		<dt class="clear"></dt>
	</dl>
	<script type="text/javascript">
		$(function() {
			var pid = <s:property value="id" />
			function initPodFollowingBoke (){
				var href = $('#nomlist .img-more').attr('href');
				$('#nomlist .img-more').attr('href', href+"?id=" + pid);
				var href = $('#wantedlist .img-more').attr('href');
				$('#wantedlist .img-more').attr('href', href+"?id=" + pid);
				$("#nomlist img").each(function(index){
					BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
				});
				$("#wantedlist img").each(function(index){
					BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
				});
			}
			initPodFollowingBoke();
		});
	</script>
</div>