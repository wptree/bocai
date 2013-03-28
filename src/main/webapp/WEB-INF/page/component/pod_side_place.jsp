<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod">
	<h2><s:property value="#request.place.fullName" /></h2>
	<div class="c555">
		<dl class="mb10 clean">
			<dt class="l mr10">电话:</dt>
			<dd class=""><s:property value="#request.place.phone" /></dd>
		</dl>
		<dl class="mb10 clean">
			<dt class="l lh20 mr10">地址:</dt>
			<dd class="lh20">
				<a href="javascript:void(0);" onclick="OP.viewBigMap();" >
					<s:if test="#request.place.location.province != null">
						<span class="province"><s:property value="#request.place.location.province" /></span>
					</s:if>
					<s:if test="#request.place.location.city != null">
						<span class="city"><s:property value="#request.place.location.city" /></span>
					</s:if>
					<s:if test="#request.place.location.district != null">
						<span class="district"><s:property value="#request.place.location.district" /></span>
					</s:if>
					<s:if test="#request.place.location.street != null">
						<span class="street"><s:property value="#request.place.location.street" /></span>
					</s:if>
				</a>
			</dd>
		</dl>
		<div id="podPlaceMapContainer">
			<div id="podPlaceMap">
			</div>
		</div>
		<dl class="clean">
			<dt class="r"><a href="javascript:void(0);" onclick="OP.viewBigMap();" >查看大图</a></dt>
		</dl>
	</div>
	<script type="text/javascript">
		$(function() {
			
			$("#podPlaceMap").gmap3(
					{ action: 'addMarker',
						latLng: new google.maps.LatLng(<s:property value="#request.place.location.lat" />,
														 <s:property value="#request.place.location.lng" />),
			            map:{
			            	center: true,
					     	zoom: 14,
					     	disableDoubleClickZoom: true,
					     	scrollwheel: false,
					     	navigationControl: true,
					     	mapTypeControl: false,
					     	navigationControl: true,
					     	navigationControlOptions: {
					     		position : google.maps.ControlPosition.RIGHT,
					     		style : google.maps.NavigationControlStyle.SMALL
					     	},
					     	noClear: true,
					     	scaleControl: false,
							streetViewControl: false
			            }
			        }
			);
			
		});
	</script>
</div>
