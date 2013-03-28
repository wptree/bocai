<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>        
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>        
<meta http-equiv="expires" content="0"/>   
<title><s:property value="title"/> 波菜网</title>
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
           <ul id="forget_result" class="forget-ul ht150">
           	<li class="mb10">
				<img class="denglutu" src="${staticContext}/images/loginjiantou_6.gif" />
				<span class="denglu"><s:property value="title"/></span>
			</li>
			<li id="msgBox" class="ml30 mr30 mt20 clearfix gray ll2 inline-center">
           		<p class="fs18"><s:property value="frontMsg"/></p>
           		<s:set name="vType" value="type" />  
          		<s:if test="%{#vType == 'invalid'}">
          			<p id="op_box"><span class="fs12">点击<span class="fs14 bold">
          				<a id="resend_a" href="javascript:void(0);" title="<s:property value="model.firstEmail"/>">
          				这里</a></span>
          				重新发送密码重置邮件</span>
          				<span id="signUpTip" class="tip"></span></p>
          		</s:if>
          		<s:else>
          			<p id="resend_box"><span id="resend_hint"></span>（<span id="secleft"></span>秒后自动跳转至首页）</p>
          		</s:else>
           	</li>
           </ul>
    </div>
    </div>
    <div class="tips">您现在访问的是：波菜网“<s:property value="title"/>”页面，如果您有<a href="javascript:void(0);" class="genghaojianyi">更好的建议</a>，欢迎反馈给我们！</div>
</div>
<!--forget pwd content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<!-- script start -->
<script type="text/javascript">
	$(document).ready(function(){
		startCountBack();
		$('#resend_a').click(function(){
			reSendMail($(this).attr('title'));
		});
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
  				$('#op_box').replaceWith(getResendHint());
  				if(result.succeed){
  					$('#resend_hint').html("验证邮件发送成功！");
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
				startCountBack();
  			}
  		});
	};
	
	function getResendHint(){
		return "<p id='resend_box'><span id='resend_hint'></span>（<span id='secleft'></span>秒后自动跳转至首页）</p>";
	}
	
	function startCountBack(){
		if($('#resend_box').length!=0){
			$('#resend_box').show();
		}else{
			return;
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
</script>
<!-- script end -->
</body>
</html>