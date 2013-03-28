<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>        
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>        
<meta http-equiv="expires" content="0"/>   
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>分享健康美食 旅游美食引擎 波菜网  Oops...</title>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=/jquery-1.5.2.js,jquery.form.js,jquery.contactable.js"></script>
<link href="${staticContext}/min/b=bocai/css&amp;f=/base.css,contactable.css" rel="stylesheet" type="text/css" />
</head>

<!-- import common scripts -->
<body>
<div class="bodyContainer">
	<div class="headout">
	    <div class="head clean">
	        <div class="logo">
	            <a href="${pageContext.request.contextPath}/main.bc"><img src="${staticContext}/images/l_head1_5.png" width="174" height="40" /></a>
	        </div>
	        <div class="nav">
	            <ul>
	                <li><a id="bka1" href="${pageContext.request.contextPath}/main.bc" class="a2" onclick="headtab(1)">首页</a></li>
	                <li><a id="bka2" href="${pageContext.request.contextPath}/guide.bc" class="a2" onclick="headtab(2)">美食指南</a></li>
	                <li><a id="bka3" href="${pageContext.request.contextPath}/boke.bc" class="a2" onclick="headtab(3)">波客</a></li>
	                <li><a id="bka4" href="javascript:void(0);" class="a2" onclick="headtab(4)">下载</a></li>
	            </ul>
	        </div>
	        <div id=welcome class="usercenter"></div>
	    </div>
	</div>
	
	
	<!--404 content start-->
	<div class="contentout_404">
		 <div class="content_404 clean">
		 	<div class="img404_sad"></div>
		 	<div class="word_404">
		 		<p>Oops..., 服务器出错了...，去&nbsp;<strong><a title="首页" href="${pageContext.request.contextPath}/main.bc" >其他</a></strong>&nbsp;地方看看？</p>
		 		<p>或者在左下角给我们反馈一下？</p>
		 		<p><a title="返回" href="javascript:void(0);" onclick="window.history.back(-1);">直接返回</a></p>
		 	</div>
		 </div>
	</div>
	
	<div class="footerout">
		<div class="footer">
			<div class="creatures"></div>
			<div class="cb"></div>
			<div class="footnav">
	        	<a href="${pageContext.request.contextPath}/bocai/about.bc">关于波菜</a><span>|</span><a href="${pageContext.request.contextPath}/bocai/about.bc">使用条款</a><span>|</span><a href="${pageContext.request.contextPath}/bocai/about.bc">开发动态</a><span>|</span><a href="${pageContext.request.contextPath}/bocai/about.bc">联系我们</a>
	        </div>
		</div>
	</div>

</div>
<div id="contactable"></div>
<script type="text/javascript">
$(function(){
	 $('#contactable').contactable({
		 url:'${pageContext.request.contextPath}/feedback!save.bc',
		 name: '昵称',
		 submit: '提交',
		 email: 'Email',
		 message : '我说',
		 subject : '我的问题/反馈',
		 recievedMsg : '谢谢你的反馈!  ^_^',
		 notRecievedMsg : '对不起，您的反馈暂时还不能提交，请稍后再试...',
		 disclaimer: '感谢您帮助我们成长，我们会及时通过email回复您！',
		 hideOnSubmit: true
	 });
	});
</script>
</body>
</html>