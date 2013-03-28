<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--head start-->
<div class="headout">
    <div class="head clean">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/main.bc"><img src="${staticContext}/images/l_head1_5.png" /></a>
        </div>
        <div class="nav">
            <ul>
                <li><a title="首页" id="bka1" href="${pageContext.request.contextPath}/main.bc" class="a2" onclick="headtab(1)">首页</a></li>
                <li><a title="美食指南" id="bka2" href="${pageContext.request.contextPath}/guide.bc" class="a2" onclick="headtab(2)">美食指南</a></li>
                <li><a title="波客列表" id="bka3" href="${pageContext.request.contextPath}/boke.bc" class="a2" onclick="headtab(3)">波客</a></li>
                <li><a title="下载手机客户端" id="bka4" href="${pageContext.request.contextPath}/mobile.bc" class="a2" onclick="headtab(4)">手机版
                	<img src="${pageContext.request.contextPath}/images/re/icon_new.png" width=11 height=11 /></a></li>
            </ul>
        </div>
        <div id=welcome class="usercenter">
	        <s:if test="#session.sessionLoginUser!=null">
	        <ul id="welcome_bar">
	        	<li class="wel_li" >
	        	<div class="clean">
	        		<a title="查看我的分享、喜欢、想吃" id="loginUserHandler" class="ahelpc_re r" style="color:#EBFFCC;margin-left:54px;margin-left:57px\9" href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="#session.sessionLoginUser.id"/>">
		        		<s:if test="#session.sessionLoginUser.firstEmail==null">
		        			<s:property value="#session.sessionLoginUser.name"/>
		        		</s:if>
		        		<s:else>
		        			<s:property value="#session.sessionLoginUser.firstEmail"/>
		        		</s:else>
	        		</a>
	        	</div>
	        	</li>
	        	<li class="wel_li">
	        	<div class="clean">
	        	<a title="退出登录" class="ahelpc_re_login r" href="${pageContext.request.contextPath}/logout.bc" id="logout">退出</a>
	        	<a title="个人资料设置" class="ahelpc_re_login r mr4" href="${pageContext.request.contextPath}/user/my_set.bc">设置</a>
	        	<a title="我的主页（关注，消息）" class="ahelpc_re_login r mr4" href="${pageContext.request.contextPath}/user/mydashboard.bc">我的波菜</a>
	        		<a class="ahelpc_re r mr4" href="${pageContext.request.contextPath}/user/mydashboard.bc?cat=msg">
        					消息(<span id="headerNewNotiCount">0</span>)
        				</a>	
	        	</div>
	        	</li>
	        </ul>
	        </s:if>
	        <s:else>
	        <ul id="welcome_bar">
        		<li class="wel_li">
	        		<div class="clean">
	        			<a title="注册" class="ahelpc_re r" href="${pageContext.request.contextPath}/sign_up.bc" id="login">注册</a>
        				<span style="text-align:center" class="r mr4">|</span>
        				<a title="登录" class="ahelpc r mr4" href="${pageContext.request.contextPath}/login.bc" id="login">登录</a>
	        		</div>
	        	</li>
	        	<li class="wel_li">
	        		<div class="r"><a title="用新浪微博帐户登陆" href="javascript:void(0);" style="margin-top:20px;margin-left:2px\9"><img alt="微博帐号登陆" title="用新浪微博帐户登陆" id="sina_weibo" src="${staticContext}/images/sina_16.png"/></a></div>
	        	</li>
	        </ul>
        	</s:else>
        </div>
    </div>
</div>
<s:include value="noti_nav.jsp"></s:include>
<div style="display:none" id="const_contextPath">${pageContext.request.contextPath}</div>
<div style="display:none" id="const_loginId">${session.sessionLoginUser.id}</div>
<div style="display:none" id="const_staticContext">${staticContext}</div>
<div style="display:none" id="const_imageContext">${imageContext}</div>
<div style="display:none" id="const_userCity">${session.userCity}</div>
<!--head end-->
