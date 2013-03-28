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
<title>密码重置 波菜网 旅游美食 美食指南 美食分享 </title>
<link href="${staticContext}/css/login.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/forget-pwd.css" rel="stylesheet" type="text/css" />
<!-- import common css -->
<s:include value="common/base.jsp" ></s:include>
<!-- import common scripts -->
<s:include value="common/scripts.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/js/login.js"></script>
</head>
<body>
<div class="main">
<s:include value="common/header.jsp" ></s:include>
<!--forget pwd content start-->
<div class="login_contentout">
    <div class="login_content">
    <div class="forget-panel">
    	<!--忘记密码开始-->
			<ul id="forget_form" class="forget-ul ht280">
				<s:form id="retrievePwdForm" method="post" action="commit_pwd.bc">
		  		<li class="mb10">
					<img class="denglutu" src="${staticContext}/images/loginjiantou_6.gif" />
					<span class="denglu">密码重置</span>
					<span id="errorMsgTip" class="error hidden" ></span>
				</li>
			  	<li class="mt15">
              		<label class="input_name">注册邮箱</label>
              		<input name="firstEmail" type="hidden" value="<s:property value="model.firstEmail" />"/>
              		<p class="item_label shadow" title="<s:property value="model.firstEmail" />"><span><s:property value="model.firstEmail" /></span></p>
            	</li>
            	<li class="mt15">
              		<label class="input_name">新密码</label>
              		<input name="password" id="passwordReg" class="input-text-blur" type="password" maxlength=20 value='输入您的新密码' />
               		<span id="passwordTip" class="tip"></span>
            	</li>
            	<li class="mt15">
                	<label class="input_name">确认密码</label>
                	<input id="passwordReconReg" class="input-text-blur" type="password" maxlength=20 value='重复您的新密码' />
                	<span id="passwordReconTip" class="tip"></span>
              	</li>
            	<li class="mt15">
              		<label class="input_name">验证码</label>
             		<input id="authCodeReg" class="input-text-auth-code" value="输入右侧验证码" />
              		<span class="span-auth-code"><img id="imageAuthCode" alt="验证码"/></span>
              		<span class="s8"><a href="javascript:void(0);" onclick="refreshAuthCode();" class="genghaojianyi">换一换</a></span>
              		<span id="authCodeRegTip" class="tip"></span>
            	</li>
            	<li class="ml30 mr30 mt20 clearfix">
              		<div class="right">
              			<input type="image" name="imageField" id="loginSubmit" src="${staticContext}/images/blogin_18.jpg" />
              		</div>
              		<div class="left"><span id="signUpTip" class="tip"></span></div>
            	</li>
            	</s:form>
            </ul>
            <ul id="forget_result" class="forget-ul ht150 hidden">
            	<li class="mb10">
					<img class="denglutu" src="${staticContext}/images/loginjiantou_6.gif" />
					<span class="denglu">密码重置</span>
				</li>
				<li class="ml30 mr30 mt20 clearfix gray ll1p3 inline-center">
            		<p id="resend_box"><span id="resend_hint" class="fs18"></span>（<span id="secleft"></span>秒后自动跳转至首页）</p>
            	</li>
            </ul>
         <!--忘记密码结束-->
    </div>
    </div>
    <div class="tips">您现在访问的是：波菜网“密码重置”页面，如果您有<a href="javascript:void(0);" class="genghaojianyi">更好的建议</a>，欢迎反馈给我们！</div>
</div>
<!--forget pwd content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<!-- script start -->
<script type="text/javascript">
	$(document).ready(function(){
		$('#retrievePwdForm').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				var validator = jQuery.Validator;
				var hasError = validator.validateBeforeSubmit.call(validator);
				if(!hasError){
					$("#errorMsgEntry").hide();
					$("#signUpTip").addClass("tip-pending");
					$('#signUpTip').show();
				}else{
					return false;
				}
			},
			success : function (response, statusText, xhr, $form){
				if(!response.isError ){
					if(!response.succeed){
						$("#errorMsgTip").html(response.frontMsg);
						$("#errorMsgTip").show();
					}else{
						$('#resend_hint').html("密码修改成功！");
						showResult();
						startCountBack();
					}
				}else {
					$("#errorMsgTip").html("服务器出小差了，请稍后再试");
					$("#errorMsgTip").show();
				}
			},
			error: function(){
				$("#errorMsgTip").html("服务器出小差了，请稍后再试");
				$("#errorMsgTip").show();
			},
			complete: function(xhr){
				if($("#signUpTip").hasClass("tip-pending")){
					$("#signUpTip").removeClass("tip-pending");
				}
				$("#signUpTip").hide();
  			}
		});
		var validator = jQuery.Validator;
		validator.setup({
			"form"   : "retrievePwdForm"
		}).add({
			"target" : "passwordReg",
			"tipCnt" : "passwordTip",
			"rule"   : "password",
			"plus"   : true
		}).add({
			"target" : "passwordReconReg",
			"rule"   : "password",
			"tipCnt" : "passwordReconTip",
			"to"     : "passwordReg"
		}).add({
			"target" : "authCodeReg",
			"rule"   : "authcode",
			"tipCnt" : "authCodeRegTip",
			"action" : "check_auth_code.bc",
			"param"  : "authCode"
		});
		$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
		$("#passwordReg").focus(passwordReg_onFocus);
		$("#passwordReconReg").focus(passwordReconReg_onFocus);
		$("#authCodeReg").focus(authCodeReg_onFocus);
	});
	function refreshAuthCode(){
		setTimeout(function () {
			$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
			$("#imageAuthCode").css("display", "");
		}, 10);
	};
	function startCountBack(){
		if($('#resend_box').length!=0){
			$('#resend_box').show();
		}
		$('#secleft').html(5);
		var interval = setInterval(function(){
			var sec = parseFloat($('#secleft').html());
			if(sec <= 0){
				clearInterval(interval);
				window.location.href = "default.jsp";
			}else{
				$('#secleft').html(sec - 1);
			}
		}, 1000);
	};
	function showResult(){
		$('#forget_form').hide();
		$('#forget_result').show();
	};
	function passwordReg_onFocus(){
		var value = $('#passwordReg').val();
		if(value=='输入您的新密码'){
			value='';
			$('#passwordReg').val(value);
		}
		$('#passwordReg').css('color', '#333333');
	};
	function passwordReconReg_onFocus(){
		var value = $('#passwordReconReg').val();
		if(value=='重复您的新密码'){
			value='';
			$('#passwordReconReg').val(value);
		}
		$('#passwordReconReg').css('color', '#333333');
	};
	function authCodeReg_onFocus(){
		var value = $('#authCodeReg').val();
		if(value=='输入右侧验证码'){
			value='';
			$('#authCodeReg').val(value);
		}
		$('#authCodeReg').css('color', '#333333');
	}
</script>
<!-- script end -->
</body>
</html>