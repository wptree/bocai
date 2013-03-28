<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pod">
	<h2>贡献该指南的人<span class="pcount f_brackets">(${page.totalCount})</span></h2>
	<ul id="creators"  class="clean">
		<c:if test="${page.totalCount == 0}">
			<li class="cchrome1"><span class="lh150">还没有人贡献该指南</span></li>
		</c:if>
		<c:forEach items="${page.list}" var="user">
			<li class="mr4 mb8 w32 h32 bcw bs1 br3 fl">
			<a class="br3" target="_blank"
				href='<c:url value="/user/profile.bc?id=${user.id}"/> '>
				<img class="br3" src="${user.avatar}" userId="${user.id}" width="32" height="32" /></a></li>
		</c:forEach>
		<c:if test="${page.totalPage > 1}">
			<li class="mr8 mb8 h32 bcchrome2 bs1 br3 fl disI pr"><a id="apl_more" class="img-more lh32 br3" target="_blank" 
				href='<c:url value="/guide.creator.bc?id=${id}"/> '>更多</a></li>
		</c:if>
	</ul>
	<script type="text/javascript">
	$(function() {
		$("#creators img").each(function(index){
			BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
		});
	});
	</script>
</div>