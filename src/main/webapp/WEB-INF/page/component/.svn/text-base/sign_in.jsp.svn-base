<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="sign_in" class="bbox p30 bgc11 br5 none ffyh" style="width: 315px;">
	<form id="sign_in_form" action="${pageContext.request.contextPath}/login!ajaxLogin.bc" 
	 	method="post">
	 	<ul>
	 		<li class="ui-bg-sign-in mb20 ffyh clean">
	 			<div class="l ffyh">
	 			<span class="c-theme fw-b fs-16">波客登录</span></div>
	 			<div class="r ffyh"><a href="${pageContext.request.contextPath}/sign_up.bc" class="theme">没有账号？立即注册</a></div>
	 		</li>
	 		<li class="mb20 ffyh">
				<input id="firstEmail_input" name="firstEmail" class="upload" label='输入登录邮箱' type="text" size="20"
				       style="width: 300px;" autocomplete="off"/>
				<input name="struts.enableJSONValidation" type="hidden" value="true"/>
			</li>
			<li class="mb20 ffyh">
				<input id="password_input" name="password" class="upload" label='输入密码' mask="password_input_mask" type="password" size="20" 
					   style="width: 300px;" autocomplete="off"/>
				<input id="password_input_mask" name="" class="upload none" type="text" size="20" 
					   style="width: 300px;" autocomplete="off"/>
			</li>
			<li class="mb5 clean lh16">
				<input type="checkbox" name="rememberMe" value="true" />
                <span class="c-label mr10" style="vertical-align: 2px;">两周内免登陆</span>
                <a href="${pageContext.request.contextPath}/forget_pwd.bc" class="theme" style="vertical-align: 2px;">忘记密码？</a>
			</li>
			<li class="ffyh tr clean">
				<div class="l mt5"><a title="用新浪微博帐户登陆" href="javascript:void(0);" style="margin-top:20px;margin-left:2px\9">
					<img alt="微博帐号登陆" title="用新浪微博帐户登陆" id="sina_weibo" src="${staticContext}/images/sina_16.png"/></a>
				</div>
				<div class="r"><img class="loading none" src="${staticContext}/images/re/loading.gif" width="16" height="16"/>
				<input class="btn success" type="submit" value="登录" />
				</div>
			</li>
	 	</ul>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#firstEmail_input").mailAutoTip();
	});
</script>