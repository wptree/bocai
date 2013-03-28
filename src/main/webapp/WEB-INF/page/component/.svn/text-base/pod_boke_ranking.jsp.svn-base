<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="ofh">
	<ul id="bokeListTabBar" class="tabbar">
    	<li class="tab selected" ><a id="bestBokeLink" href="javascript:void(0);" >最佳波客</a></li>
    	<li class="tab" ><a id="latestBokeLink" href="javascript:void(0);" >最新加入</a></li>
    </ul>
    <s:action name="list_user" executeResult="true">
    	<s:param name="type">1</s:param>
    </s:action>
    <s:action name="list_user" executeResult="true">
    	<s:param name="type">2</s:param>
    </s:action>
    <script type="text/javascript">
		$(function() {
			$(".ulist .avatar img").each(function(index){
				BC.WG.popNameCard.cardFunc(this,this.getAttribute("userId"));
			});
			function switchBokeList(type){
				$('#uuu' + type).show();
				$('#uuu' + (3-type)).hide();
				if(type==1){
					$('#uuu' + type).css('border-top-left-radius', 0);
					$($('#bokeListTabBar')[0].children[1]).removeClass("selected");
					$($('#bokeListTabBar')[0].children[0]).addClass("selected");
				}else{
					$('#uuu' + type).css('border-top-left-radius', 5);
					$($('#bokeListTabBar')[0].children[0]).removeClass("selected");
					$($('#bokeListTabBar')[0].children[1]).addClass("selected");
				}
			}
			$('#bestBokeLink').click(function(){
				switchBokeList(1);
			});
			$('#latestBokeLink').click(function(){
				switchBokeList(2);
			});
			switchBokeList(1);
		});
	</script>
</div>