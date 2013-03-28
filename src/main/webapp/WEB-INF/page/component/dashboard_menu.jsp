<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pod">
	<ul>
		<li class="menu mb2">
			<a id="my_following" class="ffyh lh20 h20 disB" target="_self" 
			    href="${pageContext.request.contextPath}/user/mydashboard.bc?cat=following">我的关注</a>
		</li>
		<li class="menu mb2">
			<a id="comment_me" class="ffyh lh20 h20 disB" target="_self" 
				href="${pageContext.request.contextPath}/user/mydashboard.bc?cat=comment">评论我的</a>
		</li>
		<li class="menu mb2">
			<a id="my_msg" class="ffyh lh20 h20 disB" target="_self" 
				href="${pageContext.request.contextPath}/user/mydashboard.bc?cat=msg">我的消息</a>
		</li>
		<li class="menu">
			<a id="my_sighting" class="ffyh lh20 h20 disB" target="_self" 
				href="javascript:void(0);">我的分享</a>
		</li>
	</ul>
	<script type="text/javascript">
		$(function() {
			var selected = '<%= request.getParameter("opt") %>';
			$('.menu a').removeClass('selected');
			$('#'+selected).addClass('selected');
			$('.menu a').click(function(){
				if($(this).hasClass('selected')) return false;
			});
			$('#my_sighting').click(function(){
				var loginId = parseFloat($('#const_loginId').text());
		    	if(isNaN(loginId) || loginId<=0){
		   	   	    global.needLogin();
		   	   	}else{
		   	   		location.href = contextPath + '/user/profile.bc?id=' + loginId;
		   	   	}
			});
		});
	</script>
</div>