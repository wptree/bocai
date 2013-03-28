<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod cpod">
	<h2>更多&nbsp;@&nbsp;<s:property value="#request.place.fullName" /></h2>
	 <div id="spotGaly" class="microGallery"> 
	 	<s:iterator value="#request.place.spots" var="spot">
	 		<img class="photo" src="${imageContext}/<s:property value="%{#spot.getSpotImgPath(180)}"/>" 
					alt="<s:property value="dish.name"/>" />
	 	</s:iterator>
		
 	</div>
 	<div class="clear"></div>
 	<script type="text/javascript">
		$(function() {
			$("#spotGaly").microgallery({
	            menu:true,
	            size:'large',
	            mode:'thumbs'
	        });
		});
	</script>
</div>