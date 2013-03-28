<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="add_place" class="hm20 br5 bgc p15 clean" style="display:none; width: 662px;">
<form id="add_place_form" action="${pageContext.request.contextPath}/spot/add_place.bc" method="post"> 
	<div id="ap_hidden-data" style="display:none;">
		<input id="ap_lat_hidden_field" type="hidden" value=""/>
		<input id="ap_lng_hidden_field" type="hidden" value=""/>
	</div>
	<div class="fl bgc1 mr10" style="border: 1px solid white; width:400px;">
		<div id="place_refer_map" style="height: 300px; width:400px;
			position: relative; background-color: rgb(229, 227, 223); overflow: hidden;">
		</div>
	</div>
	<ul class="r">
		<li class="mb15 ffyh tr">
			<span class="cerror">*</span>
			<input id="place_name_input" class="upload" label="输入餐厅名称" must='true' type="text" size="20" name="name" />
		</li>
		<li class="mt15 ffyh tr">
			<input id="place_secondary_name_input" class="upload" label="输入分店名称" type="text" size="20" name="secondaryName"/>
		</li>
		<li class="mt15 ffyh tr">
			<span class="cerror">*</span>
			<input id="place_city_input" class="upload" label="所在城市" must='true' type="text" size="10" name="city" onclick="CityPicker.show('#place_city_input');"/>
		</li>
		<li class="mt15 ffyh tr">
			<input id="place_address_input" class="upload" label="餐厅地址，拖动左边图针将自动获得" type="text" size="20" name="fullAddress" />
		</li>
		<li class="mt15 ffyh tr">
			<input id="place_lat_lng_input" class="upload" label="经纬度，拖动左边图针将自动获得" readonly="readonly" type="text" size="20" name="latLng" />
		</li>
		<li class="mt15 ffyh clean">
			<p class="l ml10"><input id="place_phone_input" class="upload mr15" label="联系电话" type="text" 
				size="20" name="phone" style="width: 100px; height:18px;" /></p>
			<p class="r">
				<input class="btn success" type="submit" value="添加餐厅" /></p>
			<p id="add_place_spinner" class="r mt8 mr5 none">
				<img src="${staticContext}/images/re/loading.gif" width="16" height="16"/></p>
		</li>
	</ul>
</form>
</div>

<script type="text/javascript">
	$(function(){
		
	});
</script>