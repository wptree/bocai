<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty pins}">
<c:forEach var="pin" items="${pins}">
	<div class="p10 pin wft">
		<div class="pr thumb-box">
			<a class="img" href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">
							<img width=192 height=192 src="${pin.thumb.imageUrl}" /></a>
			<div class="thumb-banner pa b-0 lh25 blackbg w ffyh tr op5 none">
				<span class="cfff mr5">${pin.thumb.fullName}</span>
			</div>
		</div>
		<div class="caption ffyh">
			<h3><a class="hlink5" href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">
				${pin.tagName}</a></h3>
		</div>
		<div class="convo clean">
			<c:forEach var="tail" items="${pin.tails}" varStatus="status" end="5">
				<p class="clean <c:if test="${!status.last}">bb</c:if> ">
					<a href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${tail.aggSpotId}" class="img l bg2 p2 mr10 mb5">
						<img src="${tail.imageUrl}" width=32 height=32 /></a>
					<a href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${tail.aggSpotId}" class="hlink"><span>${tail.fullName}&nbsp;</span></a><br/>
					<span>
						喜欢:&nbsp;<span class="">${tail.nommedNum}</span>&nbsp;
						想吃:&nbsp;<span class="">${tail.tastedNum}</span>&nbsp;
						点击:&nbsp;<span class="">${tail.viewedNum}</span>&nbsp;
					</span>
				</p>
			</c:forEach>
			<c:if test="${pin.tails.size()>6}">
				<p class="bt tr"><a class="hlink" 
					href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">还有&nbsp;<span class="ctitle">${pin.tails.size()-6}</span>&nbsp;个美食发现</a></p>
			</c:if>
		</div>
	</div>
</c:forEach>
</c:if>