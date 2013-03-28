<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var modalPanel;
	$(document).ready(function(){
		$("#emailLogin").mailAutoTip({
			subBox:"#MailAutoTip1",
			parent: "#simplemodal-container"
			});
		
		$("#emailReg").mailAutoTip({
			subBox:"#MailAutoTip2",
			parent: "#simplemodal-container"
			});
		
		$('#regForm').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				if($("#emailReg").val()==""){
					global.showOperationTip("#emailReg", "邮箱不能为空", "right");
					return false;
				}else{
					var reEmail=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
					if(!reEmail.test($("#emailReg").val())){
						global.showOperationTip("#emailReg", "邮箱格式不对，example@abc.com", "right");
						return false;
					}
				}
				if($("#passwordReg").val()==""){
					global.showOperationTip("#passwordReg", "密码不能为空", "right");
					return false;
				}
				if($("#passwordReconReg").val()==""){
					global.showOperationTip("#passwordReconReg", "请再次输入密码", "right");
					return false;
				}
				if($("#passwordReg").val() != $("#passwordReconReg").val()){
					global.showOperationTip("#passwordReconReg", "两次密码输入不一致", "right");
					return false;
				}
				$("#loadingTip").addClass("tip-pending");
				$('#loadingTip').show();
			},
			success : function (response, statusText, xhr, $form){
				if(response.signUpResult == true){
					window.location.replace("main.bc");
				}else{
					$('#loadingTip').hide();
					$("#errorTips").html(response.returnMsg);
					$("#errorTips").css({color:"red",'font-size':'12px'});
					$("#errorTips").show();
				}
			}
		});
	});
</script>
<div class="tanchudivout" id="loginAndSignUpPanel">
	<div class="tanchudiv">
		<div class="tanchudivsh">
			<div class="tanchudivsh_le"></div>
			<div class="tanchudivsh_ce">
				<div class="tanchudivsh_cezuo">
					<ul>
						<li  class="li1"><span id="denglubiaoti">登录</span><span id="loadingTip" class="tip"></span></li>
						<li class="li2on" id="dengluli1" onclick="dengluhuandong(1)">登录</li>
						<li class="li2off" id="dengluli2" onclick="dengluhuandong(2)">注册</li>
					</ul>
				</div>
				
				<div class="tanchudivsh_ceyou">
					<a href="javascript:void(0);"><img id="closeImg" onclick="modalPanel.close();" src="${staticContext}/images/shouyetanchu_6.jpg" width="32" height="33" /></a>
				</div>
			</div>
			<div class="tanchudivsh_ri"></div>
		</div>
		<div class="tanchudivzh">
			<div class="tanchudivzh_ri">
				<!--选项1 -->
				<div id="dengludiv1">
					<form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/login!login.bc">
						<ul class="denglu">
							<li class="li1">
								<label id="emailLoginLabel" class="inputLabel"  onclick="$('#emailLoginLabel').hide();$('#emailLogin').focus();">邮箱：</label>
								<input type="text" name="firstEmail" autocomplete="off" tabindex="11" id="emailLogin" class="input1"  onclick="$('#emailLoginLabel').hide();" onkeydown="if(this.value==''){$('#emailLoginLabel').hide();}" onblur="if(this.value==''){$('#emailLoginLabel').show();}" />
							</li>
							<li class="li1">
								<label id="passwordLabel" class="inputLabel"  onclick="$('#passwordLabel').hide();$('#loginPassword').focus();">密码：</label>
								<input type="password" name="password" tabindex="12" id="loginPassword" class="input1" onfocus="$('#passwordLabel').hide();" onblur="if(this.value==''){$('#passwordLabel').show();}"/></li>
							<li class="li2">
								<span class="s1"><input tabindex="13" type="checkbox" name="rememberMe" id="checkbox2" /></span> 
								<span class="s2">两周内免登录</span>
								<span class="s2"><a href="${pageContext.request.contextPath}/forget_pwd.bc">忘记密码？</a></span>
							</li>
							<li class="li3">
								<div class="anniu">
									<span class="s1"><input type="submit" tabindex="14" name="imageField4" id="imageField4" class="popbutton bcbutton" value="确定"/></span> 
									<span class="s2"><a href="javascript:void(0);" onclick="modalPanel.close();">取消</a></span>
								</div>
							</li>
						</ul>
					</form>
				</div>
				<div id="dengludiv2" style="display: none">
					<s:form id="regForm" name="regForm" method="post" action="sign_up!ajaxSignUp.bc">
						<ul class="denglu">
							<li class="li1">
								<label id="emailRegLabel" class="inputLabel"  onclick="$('#emailRegLabel').hide();$('#emailReg').focus();">邮箱：</label>
								<input type="text" name="firstEmail" autocomplete="off" onfocus="$('#emailRegLabel').hide();" onblur="if(this.value==''){$('#emailRegLabel').show();}" tabindex="5" id="emailReg" class="input1" />
								<span class="xinghao">*</span>
								<span id="firstEmailTip" class="tip"></span>
							</li>
							<li class="li1">
								<label id="passwordRegLabel" class="inputLabel"  onclick="$('#passwordRegLabel').hide();$('#passwordReg').focus();">密码：</label>
								<input type="password" name="password" tabindex="6"  id="passwordReg"  class="input1" onfocus="$('#passwordRegLabel').hide();" onblur="if(this.value==''){$('#passwordRegLabel').show();}"  />
								<span class="xinghao">*</span>
								<span id="passwordTip" class="tip"></span>
							</li>
							<li class="li1">
								<label id="passwordReconRegLabel" class="inputLabel"  onclick="$('#passwordReconRegLabel').hide();$('#passwordReconReg').focus();">再次输入密码：</label>
								<input type="password" name="passwordReconReg" tabindex="7" id="passwordReconReg" class="input1" onfocus="$('#passwordReconRegLabel').hide();this.select();" onblur="if(this.value==''){$('#passwordReconRegLabel').show();}" />
								<span class="xinghao">*</span>
								<span id="passwordReconTip" class="tip"></span>
							</li>
							<li class="li3">
								<div class="anniu">
									<span class="s1">
									 <input name="struts.enableJSONValidation" type="hidden" value="true" />
									<input type="submit" name="imageField4" tabindex="8"  id="imageField4" value="确定" class="popbutton bcbutton"/></span>
									<span class="s2"><a href="javascript:void(0);" onclick="modalPanel.close();">取消</a></span>
								</div>
							</li>
						</ul>
					</s:form>
				</div>
			</div>
		</div>
		<div class="tanchudivxia">
			<div class="tanchudivxia_le"></div>
			<div class="tanchudivxia_ce">
				<div class="qitadenglu">
					<span class="s2">
						<a href="javascript:void(0);"><img src="${staticContext}/images/index_dengluxiao_24.jpg" width="85" height="21" id="sina_weibo"/></a>
					</span>
					<!-- span class="s3">
						<a href="javascript:void(0);"><img src="${staticContext}/images/index_dengluxiao_26.jpg" width="85" height="21" id="renren_login"/></a>
					</span-->
					<span id="errorTips" style="display:none"></span>
				</div>
			</div>
			<div class="tanchudivxia_ri"></div>
		</div>
	</div>
</div>

