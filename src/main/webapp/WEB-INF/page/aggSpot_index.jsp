<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>美食索引 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="common/base.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/js/jquery.masonry.js"></script>
<link href="${staticContext}/css/about.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.place_ul li {line-height:25px; text-align: left; border-top: 1px dotted #B2B3AE;}
	.place-container{background-color:#eeeeee;margin-top:15px; border-radius:3px; box-shadow: 1px 1px 3px #B4AAA0;width:250px;}
	.p-wrapper{padding:10px 8px;}
</style>
</head>
<body>
<div class="bodyContainer">
<s:include value="common/header.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>
<div class="about_contentout">
    <div id="index-container" class="" style="margin: 0 auto; position: relative;">
   <c:forEach items="${indexList}" var="placeIndex">
   		<div class="place-container">
		        <div class="p-wrapper ">
		        	   <div id="city_name" class="w tc mb8"><h2>${placeIndex.cityName}旅遊美食</h2></div>
		               <ul class="place_ul">
		               		<c:forEach items="${placeIndex.aggSpotList}" var="aggSpot">
		               			<li><a title="${aggSpot.name}" href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${aggSpot.id}">${aggSpot.name}</a></li>
		               		</c:forEach>
		               </ul> 
		        </div>
	     </div>
   </c:forEach>
   </div>
</div>
<s:include value="common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
	$('#index-container').masonry({
	  itemSelector: '.place-container',
	  gutterWidth: 15,
	  isFitWidth: true
	});
</script>
</body>
</html>
