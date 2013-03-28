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
<title>忘记密码 波菜网 旅游美食 美食指南 美食分享 </title>
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
			<ul id="forget_form" class="forget-ul">
				<s:form id="forgetpwdForm" name="form1" method="post" action="mail_pwd.bc">
		  		<li class="mb10">
					<img class="denglutu" src="${staticContext}/images/loginjiantou_6.gif" />
					<span class="denglu">忘记密码？</span>
					<span id="errorMsgTip" class="error hidden" ></span>
				</li>
            	<li class="mt15">
              		<label class="input_name">邮箱</label>
              		<input name="firstEmail" id="emailInput" autocomplete="off" class="input-text-account" value="输入您的注册邮箱"/>
               		<span id="emailTip" class="tip"></span>
               		<script type="text/javascript">
      					$(document).ready(function() {
      						$("#emailInput").mailAutoTip();
	        			});
      				</script>
            	</li>
            	<li class="mt15">
              		<label class="input_name">验证码</label>
             		<input id="authCodeReg" class="input-text-auth-code" value="输入右侧验证码" />
              		<span class="span-auth-code"><img id="imageAuthCode" alt="验证码"/></span>
              		<span class="s8"><a href="javascript:void(0);" onclick="refreshAuthCode();" class="genghaojianyi">换一换</a></span>
              		<span id="authCodeRegTip" class="tip"></span>
              		<span class="s2"></span>
            	</li>
            	<li class="ml30 mr30 mt20 clearfix">
            		<div class="left gray"><span class="va-m">请输入您的注册邮箱，密码重置链接将发送至此邮箱!</span></div>
              		<div class="left"><span id="signUpTip" class="tip"></span></div>
              		<div class="right">
              			<input type="submit" name="imageField" id="loginSubmit" class="bcbutton" value="确定"/>
              		</div>
            	</li>
            	</s:form>
            </ul>
            <ul id="forget_result" class="forget-ul ht150 hidden">
            	<li class="mb10">
					<img class="denglutu" src="${staticContext}/images/loginjiantou_6.gif" />
					<span class="denglu">忘记密码？</span>
				</li>
				<li class="ml30 mr30 mt20 clearfix gray ll2">
            		密码重置链接已发送至邮箱<span id="result-mail" class="bold fs14"></span>, 请及时访问邮箱修改密码！<br/>
            		<p id="op_box"><span class="fs12">尚未收到邮件？点击<span class="fs14 bold">
          				<a id="resend_a" href="javascript:void(0);" >
          				这里</a></span>
          				重发密码重置邮件！</span>
          				<span id="signUpTip" class="tip"></span></p>
          			<br/><span id='resend_hint' style="display:none;"></span>
            	</li>
            </ul>
         <!--忘记密码结束-->
    </div>
    </div>
    <div class="tips">您现在访问的是：波菜网“忘记密码”页面，如果您有<a href="javascript:void(0);" class="genghaojianyi">更好的建议</a>，欢迎反馈给我们！</div>
</div>
<!--forget pwd content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<!-- script start -->
<script type="text/javascript">
		$('#forgetpwdForm').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				var validator = jQuery.Validator;
				var hasError = validator.validateBeforeSubmit.call(validator);
				if(!hasError){
					$("#errorMsgTip").hide();
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
						showResult(response.email);
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
			"form"   : "forgetpwdForm"
		}).add({
			"target" : "emailInput",
			"rule"   : "email",
			"tipCnt" : "emailTip",
			"action" : "verify_user_mail.bc",
			"param"  : "firstEmail",
			"error"  : "邮箱尚未注册！"
		}).add({
			"target" : "authCodeReg",
			"rule"   : "authcode",
			"tipCnt" : "authCodeRegTip",
			"action" : "check_auth_code.bc",
			"param"  : "authCode"
		});
		$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
		$("#emailInput").focus(emailInput_onFocus);
		$("#authCodeReg").focus(authCodeReg_onFocus);
		$('#resend_a').click(function(){
			reSendMail($(this).attr('title'));
		});
	function reSendMail(email){
		$.ajax({
  			url: "${pageContext.request.contextPath}/mail_pwd.bc",
  			data: {"firstEmail": email},
  			type  : "POST",
  			dataType: "json",
  			beforeSend: function( xhr ) {
  				$("#signUpTip").addClass("tip-pending");
				$('#signUpTip').show();
  			},
  			success:function(result){
  				if(result.succeed){
  					$('#resend_hint').html("验证邮件重发成功！");
  				}else if(result.frontMsg){
  					$('#resend_hint').html(result.frontMsg);
  				}else{
  					$('#resend_hint').html("服务器出小差了，请稍后重试发送验证邮件！");
  				}
  			},
  			error: function() {
  				$('#op_box').replaceWith(getResendHint());
  				$('#resend_hint').html("服务器出小差了，请稍后重试发送验证邮件！");
  			},
  			complete: function(xhr){
  				if($("#signUpTip").hasClass("tip-pending")){
					$("#signUpTip").removeClass("tip-pending");
				}
				$("#signUpTip").hide();
				$("#resend_hint").show();
  			}
  		});
	};

	function refreshAuthCode(){
		setTimeout(function () {
			$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
			$("#imageAuthCode").css("display", "");
		}, 10);
	};
	function showResult(email){
		$('#forget_form').hide();
		$('#resend_a').attr('title', email);
		$('#result-mail').html(email);
		$('#forget_result').show();
	};
	function emailInput_onFocus(){
		var value = $('#emailInput').val();
		if(value=='输入您的注册邮箱'){
			value='';
			$('#emailInput').val(value);
		}
		$('#emailInput').css('color', '#333333');
	}
	function authCodeReg_onFocus(){
		var value = $('#authCodeReg').val();
		if(value=='输入右侧验证码'){
			value='';
			$('#authCodeReg').val(value);
		}
		$('#authCodeReg').css('color', '#333333');
	}
	
	$(".footerout").css({backgroundColor:'#F0EEDF'});
</script>
<!-- script end -->
</body>
</html>