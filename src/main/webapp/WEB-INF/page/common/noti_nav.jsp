<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#session.sessionTaskStatus.SUW==null || #session.sessionTaskStatus.SUW==@com.bocai.common.constant.UserTaskStatus@STARTED">
<div id="suw" class="w pt10 pb15 bgc5 tc" style="border-bottom: 1px solid #E2E161;min-width: 960px">
	<div class="w960 tl clean" style="margin: 0 auto">
		<div class="tl l" >
			<div class="f20 ffyh">欢迎来到波菜</div>
			<div class="mt10 ffyh">为了更好的在这里上分享美食、寻找吃友
				<a class="hlink3 fb f16" href="${pageContext.request.contextPath}/user/wizard_basic.bc">请完成个人向导&gt;&gt;</a>
			</div>
		</div>
	</div>
	<div class="r mr15" style="margin-top: -45px">
			<a class="hlink3 fb" href="javascript:void(0);">下次再说&gt;&gt;</a>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('#suw').click(function(){
			$.get('${pageContext.request.contextPath}/user/wizard_delay.bc')
				.complete(function(){
					$('#suw').fadeOut('slow');
				});
		});
	});
</script>
</s:if>