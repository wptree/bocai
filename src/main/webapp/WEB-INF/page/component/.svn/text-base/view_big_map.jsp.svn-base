<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="view_big_map" class="hm20 br5 bgc" style="display:none; width: 700px;">
	<div class="pr pt20 pb10 pl10 pr10 clean" style="border-bottom: 1px solid #E1DCC8;">
		<div class="l tl">
			<span id="placeName" class="f14 ffyh"><s:property value="#request.place.fullName" /></span>
			<span class="addr">
				<span class="locality"><s:property value="#request.place.location.city" /></span>,
				<span class="street-address"><s:property value="#request.place.location.street" /></span>
			</span>
		</div>
	</div>
	<div id="vbm_hidden-data" style="display:none;">
		<input id="vbm_lat_hidden_field" type="hidden" value="<s:property value="#request.place.location.lat" />"/>
		<input id="vbm_lng_hidden_field" type="hidden" value="<s:property value="#request.place.location.lng" />"/>
	</div>
	<div class="p10 bgc1" style="border-top: 1px solid white;border-bottom: 1px solid #E1DCC8;">
		<div id="big_map" class="w" style="height: 300px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;">
		</div>
	</div>
	<div class="tl p15 clean" style="border-top: 1px solid white;">
		<span class="fl c888 f12">提醒: 地图标注位置仅供参考，具体情况以实际道路标识信息为准</span>
	</div>
</div>