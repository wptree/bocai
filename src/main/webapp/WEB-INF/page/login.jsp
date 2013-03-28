<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>    
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>登录 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<link type="text/css" rel="stylesheet" href="http://js.wcdn.cn/t3/style/css/common/card.css" />
<link href="${staticContext}/css/login.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/validator.css" rel="stylesheet" type="text/css" />
<!-- import common css -->
<s:include value="common/base.jsp" ></s:include>
</head>
<body>
<div class="bodyContainer" style="background: none repeat scroll 0 0 #F0EEDF">
<s:include value="common/header.jsp" ></s:include>
<!--login content start-->
<div class="login_contentout">
    <div class="login_content">
        <div class="faxianmeishi">
        <img src="${staticContext}/images/blogin_3.jpg" width="392" height="369" />
        </div>
        <div class="login">
            <ul class="login_u1">
                <li id="lli1" class="llion1" onclick="jQuery.Validator.hideTipBox();tab(1);"><img src="${staticContext}/images/loginjiantou_6.gif" class="denglutu" /><span class="denglu">登录</span></li>
                <li id="lli2" class="llioff1" onclick="tab(2)"><p class="huanmeiyou">还没有波菜的账号?<a href="javascript:void(0);" onclick="tab(2);" class="mianfeizhuce">免费注册</a></p></li>
            </ul>
            <div class="clear"></div>
            <!--登录 -->
            <div id="ldiv1">
            <s:form id="form1" name="form1" method="post" action="login!login.bc">
			
            <ul  class="login_u2">
			  <li class="li0">
			  	<span class="s0"><s:fielderror/></span>
			  	<span class="s0"><s:actionerror/></span>	
			  </li>
              <li class="li-item-entry" >
                <label class="input_name">邮箱</label>
                <input name="firstEmail" type="text" id="accountLogin" autocomplete="off" class="input-text-account" onclick="this.select();"/>
                 <script type="text/javascript">
        			$(document).ready(function() {
        				$("#accountLogin").mailAutoTip();
			        });
        		</script>
                <span class="s2"></span>
              </li>
              <li class="li-item-entry">
                <label class="input_name">密码</label>
                <input name="password" id="passwordLogin" class="input-text-blur" type="password" maxlength=20 />
                <span class="s2"></span>
              </li>
              <li class="li-item-entry">
                <span class="s3">
                	<input name="rememberMe" type="checkbox" id="miandenglu_zhi" onclick="if(this.checked){this.value=1;}else{this.value=0;};" />
                </span>
                <span class="s4">两周内免登录</span>
              </li>
              <li  class="li4">
                <span class="s5">
                <input type="image" name="imageField" id="loginSubmit" src="${staticContext}/images/blogin_18.jpg" />
                </span>
                <span class="s6"><a href="${pageContext.request.contextPath}/forget_pwd.bc" class="wangjimima">忘记密码?</a></span>
              </li>
                <li class="li_xian"></li>
              <li  class="li5">
               	<s:if test="#session.sessionLoginUser==null">
        			<span id="s8"><a href="javascript:void(0);" ><img id="sina_weibo" src="${staticContext}/images/index_dengluxiao_24.jpg"/></a></span>
        			<span id="s8"><a href="javascript:void(0);" ><img id="renren_login" src="${staticContext}/images/index_dengluxiao_26.jpg"/></a></span>
        		</s:if>
              </li>
            </ul>
            
            </s:form>
            </div>
             <!--登录结束 -->
             
             <!--注册 -->
            <div id="ldiv2" style="display:none">
            <s:form id="regForm" name="regForm" method="post" action="sign_up!ajaxSignUp.bc">

            <ul  class="login_u2" >
              <li class="li-item-entry" >
              	<label class="input_name">邮箱</label>
              	<s:if test="#parameters.email==null">
              		<input name="firstEmail" type="text" id="firstEmailReg" autocomplete="off" class="input-text-account-email"/>
              	</s:if>
              	<s:else>
              		<input name="inviteFromId" type="hidden" value="<s:property value="#parameters.id"/>" />
              		<input name="firstEmail" id="firstEmailReg" autocomplete="off" value="<s:property value="#parameters.email"/>" readonly="readonly" class="cg input-text-account-email"/>
              	</s:else>
              	 <script type="text/javascript">
        			$(document).ready(function() {
        				$("#firstEmailReg").mailAutoTip();
			        });
        		</script>
              	<span id="firstEmailTip" class="tip"></span>
                <span class="s2"></span>
              </li>
              <li class="li-item-entry">
                <label class="input_name">密码</label>
                <input name="password" id="passwordReg" class="input-text-blur" type="password" maxlength=20 />
                <span id="passwordTip" class="tip"></span>
                <span class="s2"></span>
              </li>
              <li class="li-item-entry">
                <label class="input_name">确认密码</label>
                <input name="passwordRecon" id="passwordReconReg" class="input-text-blur" type="password" maxlength=20 />
                <span id="passwordReconTip" class="tip"></span>
                <span class="s2"></span>
              </li>
              <!-- li class="li-item-entry">
                <label class="input_name">验证码</label>
                <input name="authCode" id="authCodeReg" type="text" class="input-text-auth-code" maxlength=4 />
                <span class="span-auth-code"><img id="imageAuthCode" alt="验证码"/></span>
                <span class="s8"><a href="javascript:void(0);" onclick="refreshAuthCode();" class="genghaojianyi">换一换</a></span>
                <span id="authCodeRegTip" class="tip"></span>
                <span class="s2"></span>
              </li-->
              <li class="li-item-entry">
                <span class="s3">
                <input name="agreeLicense" type="checkbox" id="agreeLicense_zhi" onclick="if(this.checked){this.value=1;}else{this.value=0;};"/></span>
                <span class="s4">我已看过并同意<a target="_blank" href="${pageContext.request.contextPath}/term.bc" class="genghaojianyi">《波菜网服务使用协议》</a></span>
                <span id="agreeLicenseTip" class="tip"></span>
                <span class="s2"></span>
              </li>
              <li  class="li7">
                <span class="s5">
                <input name="struts.enableJSONValidation" type="hidden" value="true" />
                <input type="image" name="imageField" id="singUpSubmit" src="${staticContext}/images/signup.jpg" />
                </span>
                <span class="s6">已有波菜账户？<a href="javascript:void(0);" onclick="jQuery.Validator.hideTipBox();tab(1);" class="genghaojianyi">点击登录</a></span>
                <span id="signUpTip" class="tip"></span>
                <span class="s2"></span>
              </li>
              <li class="li-item-entry" id="errorMsgEntry" style="display:none">
				<span id="errorMsgTip" class="s0" ></span>
			  </li>	
            </ul>
            
            </s:form>
            </div>
             <!--注册结束 -->
        </div>
    </div>
    <div class="clear"></div>
    <div class="tips">您现在访问的是：波菜网登录页面，如果您有<a href="javascript:void(0);" onclick='$("#contactable_inner").click();' class="genghaojianyi">更好的建议</a>，欢迎反馈给我们！</div>
</div>
<!--login content end-->
<s:include value="common/foot.jsp"></s:include>
<!-- import common scripts -->
<s:include value="common/scripts.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=login.js"></script>
<script type="text/javascript" src="http://js.wcdn.cn/t3/platform/js/api/wb.js" charset="utf-8"></script>
<!-- script start -->
<script type="text/javascript">
	$(document).ready(function(){
		$("#authCodeReg").val("");
		$('#regForm').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				if($("#firstEmailReg").val()==""){
					global.showOperationTip("#firstEmailReg", "邮箱不能为空", "right");
					return false;
				}else{
					var reEmail=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
					if(!reEmail.test($("#firstEmailReg").val())){
						global.showOperationTip("#firstEmailReg", "邮箱格式不对，example@abc.com", "right");
						return false;
					}
				}
				
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
				if($("#signUpTip").hasClass("tip-pending")){
					$("#signUpTip").removeClass("tip-pending");
				}
				$("#signUpTip").hide();
				$("#errorMsgEntry").hide();
				if(response.signUpResult == true){
					//redirect to last visit url
					window.location.replace("user/my_set.bc");
				}else{
					$("#errorMsgEntry").html(response.returnMsg);
					$("#errorMsgEntry").css({color:'red',marginLeft:'40px',lineHeight: "15px"});
					$("#errorMsgEntry").show();
				}
			}
		});
		var validator = jQuery.Validator;
		validator.setup({
			"form"   : "regForm"
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
			"target" : "agreeLicense_zhi",
			"rule"   : "agreelicense",
			"tipCnt" : "agreeLicenseTip",
			"boxCnt" : "agreeLicense"
		});

		//.add({
		//	"target" : "authCodeReg",
		//	"rule"   : "authcode",
		//	"tipCnt" : "authCodeRegTip",
		//	"action" : "check_auth_code.bc",
		//	"param"  : "authCode"
		//})
		
		$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
	});
	function refreshAuthCode(){
		setTimeout(function () {
			$("#imageAuthCode").attr("src", "authCode.jsp?r=" + ((new Date()).getTime()));
			$("#imageAuthCode").css("display", "");
			$("#authCodeReg").val("");
		}, 10);
	};
	
	$(".footerout").css({backgroundColor:'#F0EEDF'});
	
	tab(<s:property value="type"/>);

</script>
<!-- script end -->
</div>
</body>
</html>
