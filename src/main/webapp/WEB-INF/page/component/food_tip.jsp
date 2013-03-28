<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="foodtip" class="mt30 w300 pa none food-tip" style="z-index:100;">
	<div class="cw bors5 pl10 pr10" style="padding-top:8px;padding-bottom:8px;background:url('images/tooltip-content.png') repeat scroll 0 0 transparent">
		<div class="clean ffyh">
			<img id="ft_img" class="bors5 fl pr10" style="width:90px;height:90px;margin:-3px 0 -3px -5px;" src="">
			<div id="ft_title" class="disB mb5 fb"></div>
			<div class="disB mb5 f12 lh14 cccc">
				<span id="ft_address" class="addr"></span>
			</div>
			<div class="disB f12 lh14 cccc">
				<span id="ft_desc"></span>
				<a style="padding:3px;" href='javascript:void(0);' onclick='javascript:BC.scrollToAggSpot($("#ft_aggspot_id").val())'>详细</a>
			</div>
			
		</div>
		<div id="ft_hidden-data" style="display:none;">
			<input id="ft_aggspot_id" type="hidden" value=""/>
		</div>
	</div>
	<div class="tc w" style="height:5px;background:url('images/tooltip-pointer.png') no-repeat scroll center top transparent;"></div>
</div>