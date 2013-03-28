<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pod">
	<div class="hauto">
		<h2 style="margin-bottom: 10px!important;">邀请好友加入波菜</h2>
		<div class="h20 lh20 cchrome1"><a id="invite_email_link" href="javascript:void(0);" style="color:#333">通过邮箱</a></div>
		<div class="h20 lh20 cchrome1 none"><a id="invite_sina_link" href="javascript:void(0);" style="color:#333">通过新浪微博</a></div>
		<div class="h20 cg mt10">每成功邀请一位好友将获赠100波币</div>
	</div>
</div>
<div id="invite_email_panel" class="none w500 hauto bgcwhite br5">
	<div class="p15"><h2>邀请好友加入波菜网&nbsp;&nbsp;<span id="invitationLoadingTip" class="none"><img src="${staticContext}/images/loading.gif"/></span></h2></div>
	<div class="p5 pl15" id="inviteForm">
		<form id="sendInvite" action="${pageContext.request.contextPath}/user/invitation!send.bc">
			<label id="emailLabel" class="inputLabel" onclick="$('#emailLabel').hide();$('#emailList').focus();">输入邮箱，多个邮箱之间用“;”隔开</label>
			<input type="text" id="emailList" name="emailList" class="bs1 bcchrome1 w360 h30 lh30 pl5"  onclick="$('#emailLabel').hide();" onkeydown="if(this.value==''){$('#emailLabel').hide();}" onblur="if(this.value==''){$('#emailLabel').show();}"/>
			<input type="submit" class="wauto h30 lh30 ml10 bcbutton" value="发送邀请">
		</form>
	</div>
	<div class="p15">
		<div>已邀请的好友<span class="f_brackets">(<span id="invitedNum">0</span>)</span></div>
		<ul id="invitedList" class="mt10 hauto clean"></ul>
	</div>
	<div class="p15 none">或者 <span class="h20 lh20"><a id="invite_sina_link" href="javascript:void(0);">通过<img alt="新浪微博" src="${staticContext}/images/sina_24x24.png"/>新浪微博</a></span> 来邀请</div>
</div>
<div id="invite_sina_panel" class="none"></div>
<script type="text/javascript">
	 var inviteModal;
	 $("#invite_email_link").click(function(){
		 inviteModal = $('#invite_email_panel').modal({
    		    closeHTML: "<div class='h36 w36 modalCloseBg cp' style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
    		    containerCss:{
    		    	boxShadow: "0 0 90px 5px #000000"
    			},
    			focus:false,
    			onShow:showInvited,
    		    overlayClose:true
    		 	});
	});
	 
	 function showInvited(){
		 var contextPath = $("#const_contextPath").text();
			$.ajax({url: contextPath+"/user/invitation!fetchInvited.bc",
				  type: "POST",
				  dataType: "json",
				  data:{id:'${session.sessionLoginUser.id}'},
				  success: function(jsonObj){
					 var dataPage = jsonObj.dataPage;
					 $("#invitedNum").text(dataPage.totalCount);
					 var list = dataPage.list;
					 for(var i = 0; i<list.length; i++){
						 $.ajax({url: contextPath+"/get_user.bc",
							  type: "POST",
							  dataType: "json",
							  data:{userId:list[i].targetId},
							  success : function(jsonObj) {
										var userInfo = jsonObj.userInfo;
										$("#invitedList").append(
														$('<li class="l mr5 mt10 hauto w200"><a class="avatar br5 mr10 fl" href="${pageContext.request.contextPath}/user/profile.bc?id='
																+ userInfo.id
																+ '"><img class="br5" width="32" height="32" title="ss" src="'+userInfo.avatar+'"></a><div class="fl lh16"><div style="display:block;"><a class="uname" href="${pageContext.request.contextPath}/user/profile.bc?id='
																+ userInfo.id
																+ '">'
																+ userInfo.firstEmail
																+ '</a></div><span class="finfo cg">'
																+ userInfo.signature
																+ '</span></div></li>'));
									}
								});
						}
					}
				});
	}

	$("#sendInvite")
			.ajaxForm(
					{
						dataType : "json",
						beforeSubmit : function(arr, form, options) {
							if ($("#emailList").val() == "") {
								global.showOperationTip("#emailList", "邮箱不能为空",
										"right");
								return false;
							} else {
								var reEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
								//Replace all chinese "；" to ";"
								var emailList = $("#emailList").val().replace(
										/；/g, ";");
								var re = true;
								if (emailList.indexOf(';') > 0) {
									var list = emailList.split(';');
									for ( var i = 0; i < list.length; i++) {
										if (!reEmail.test(list[i])) {
											global.showOperationTip(
													"#emailList", "\""
															+ list[i]
															+ "\" 邮箱格式不正确",
													"right");
											re = false;
											break;
										}
									}
									return re;
								} else {
									if (!reEmail.test(emailList)) {
										global
												.showOperationTip("#emailList",
														"\"" + emailList
																+ "\" 邮箱格式不正确",
														"right");
										return false;
									}
								}
							}
							$('#invitationLoadingTip').show();
						},
						success : function(response, statusText, xhr, $form) {
							$(
									"<div style='color:#5C7E27; margin:11px;font-size:14px; color:#5C7E27;float:right'>"
											+ response.returnMsg + "</div>")
									.appendTo($("#inviteForm"));
							$('#invitationLoadingTip').fadeOut("slow");
							if (response.sendResult) {
								setTimeout(function() {
									inviteModal.close();
								}, 2000);
							} else {

							}
						}
					});
</script>