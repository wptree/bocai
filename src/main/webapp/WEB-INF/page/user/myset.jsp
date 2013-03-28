<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@page import="com.bocai.util.StringUtil"%>
<%@page import="com.bocai.dao.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>   
<title>分享健康美食 旅游美食引擎 波菜网 我的设置</title>
<link href="${staticContext}/css/citypicker.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/myset.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/common.css" rel="stylesheet" type="text/css" />

<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
</head>
<body>
<%
	User user = (User)request.getSession().getAttribute("sessionLoginUser");
	String secretFieldJson = user==null?"":user.getSecretFields();
%>
<div class="bodyContainer" style="background: none repeat scroll 0 0 #F7F6F1;">
<s:include value="../common/header.jsp"></s:include>
<script type="text/javascript" src="${staticContext}/js/myset.js"></script>
<script type="text/javascript" src="${staticContext}/js/citypicker.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.imgareaselect.js"></script>
<script type="text/javascript" src="${staticContext}/js/myset.js"></script>
<script type="text/javascript">
	function getSecretDescription(value){
		if(value=="0"){
			return "所有人可见";
		}
		if(value=="-1"){
			return "仅自己可见";
		}
		if(value=="1"){
			return "我关注的人可见";
		}
	}

	$(document).ready(function(){
		var obj = <%=secretFieldJson%>;
		if(obj!=null){
			$("input[name='isChineseNameSecret']").val(obj.chineseName);
			$("#gongkai1").html(getSecretDescription(obj.chineseName));
			
			$("input[name='isBirthdaySecret']").val(obj.birthday);
			$("#gongkai2").html(getSecretDescription(obj.birthday));
			
			$("input[name='isBlogUrlSecret']").val(obj.blogUrl);
			$("#gongkai3").html(getSecretDescription(obj.blogUrl));
			
			$("input[name='isPhoneNoSecret']").val(obj.phoneNo);
			$("#gongkai4").html(getSecretDescription(obj.phoneNo));
			
			$("input[name='isQQSecret']").val(obj.qq);
			$("#gongkai5").html(getSecretDescription(obj.qq));
			
			$("input[name='isMSNSecret']").val(obj.msn);
			$("#gongkai6").html(getSecretDescription(obj.msn));
		}
	});
</script>
<!--myset content start-->
<div class="myset_contentout">
    <div class="myset clean">
      <div class="myset_1">
      	 我的设置
      	 <span id="errorMsgTip" style="display:none;color: red;font-weight: normal;font-size:12px; margin:10px;" ></span>
        </div>
        <div class="myset_2out">
            <ul class="myset_2">
               <li id="liid1" class="lion" onclick="settab(1)">详细资料</li>
               <li id="liid2" class="lioff" onclick="settab(2)">修改密码</li>
               <li id="liid3" class="lioff" onclick="settab(3)">同步分享</li>
            </ul>
        </div>
        <!--mysetid1 -->
        <div id="mysetid1" class="myset_3" style="display:block">
          <div id="mysetWelcome" class="welcome">
            欢迎，<s:property value="#session.sessionLoginUser.name"/>
            <s:if test="#session.sessionLoginUser.source=='bocai'">
            	您登录的邮箱是<s:property value="#session.sessionLoginUser.firstEmail"/>
            </s:if>
            <s:else>
            	您来自于<s:property value="#session.sessionLoginUser.source"/>&nbsp;网，请进一步完善您的个人信息。
            </s:else>
            </div>
            <form id="myDetailForm" name="myDetailForm" method="post" action="my_set!ajaxSave.bc">
				<ul class="ul1">
				<li class="li1 firstli">
	                    <span class="s1">
	                      个人图像:
	                    </span>
	                    <span class="s2">
	                       <a style="margin-left:10px;font-weight:bold" href="javascript:void(0);" onclick="updateAvatar();">
	                       <img id="userAvatar" width="32px" height="32px" style="margin-left:19px" src="<s:property value="#session.sessionLoginUser.avatar"/>"/></a>
	                       <a style="margin-left:10px;vertical-align: top" href="javascript:void(0);" onclick="updateAvatar();">点击更改</a>
	                    </span>
	                </li>
				<s:if test="#session.sessionLoginUser.source!='bocai'">
					<li class="li1">
	                    <span class="s1">
	                        <span class="he">*</span>
	                       邮箱：
	                    </span>
	                    <span class="s2">
	                        <input type="text" name="firstEmail" id="firstEmail"  value="${session.sessionLoginUser.firstEmail}" class="input1" />
	                    </span>
	                    <span id="firstEmailTip" class="tip"></span>
	                </li>
	                <s:set name="isReadonly" value="readonly=\"readonly\""/>
				</s:if>
				<s:else>
					<s:set name="isReadonly" value=""/>
				</s:else>
				<li class="li1">
                    <span class="s1">
                        <span class="he">*</span>
                        昵称：
                    </span>
                   
                    <span class="s2">
                        <input type="text" name="name" id="nickName" <s:property value="#isReadonly"/> value="${session.sessionLoginUser.name}" class="input1" />
                    </span>
                </li>
                <li class="li1">
                    <span class="s1">
                        
                        真实姓名：
                    </span>
                    
                    <span class="s2">
                        <input type="text" name="chineseName" value="${session.sessionLoginUser.chineseName}" id="textfield" class="input1" />
                    </span>
                    <div class="simi">
                    <!--selfselect1-->	
                     <div class="selfselectout" onmouseout="selectno('listdata1')"> 	  
				<div class="selfselect">
                  <input name="isChineseNameSecret" id="hgongkai1" type="hidden" value="0" />
                  <p id="gongkai1" class="selectzhi"  onclick="changedisplay('listdata1')" >所有人可以见</p>
                  <p style="float:left;margin:0" onclick="changedisplay('listdata1')">
				  <img src="${staticContext}/images/szxxzl1_14.jpg" width="7" height="4" border="0" style="margin-top:7px; margin-left:3px;" />
				  </p>
                </div>
                <div id="listdata1" class="listdata" style="display:none" onmouseover="selectyes('listdata1')" onmouseout="selectno('listdata1')"></div>
				</div>
				<!--selfselect1 end-->	
                   </div>
                    <span class="s3_hidden">
                    </span>
                </li>
                <li class="li1">
                    <span class="s1">
                        <span class="he">*</span>
                        性别：
                    </span>
                    
                     <span class="s2">
                        <span class="spanjiange"></span>
                        <input type="radio" name="sexy" id="sexy" value="1" class="reado1" <s:if test="#session.sessionLoginUser.sexy==\"1\"">checked="checked"</s:if>/>
                        <span class="sex_value" >男</span>
                        <input type="radio" name="sexy" id="sexy" value="2" class="reado1" <s:if test="#session.sessionLoginUser.sexy==\"2\"">checked="checked"</s:if>/>
                        <span class="sex_value">女</span>
                        <input type="radio" name="sexy" id="sexy" value="0" class="reado1" <s:if test="#session.sessionLoginUser.sexy==\"0\"">checked="checked"</s:if>/>
                        <span class="sex_value">保密</span>
                    </span>
                </li>
                <li class="li2">
                <%
                    String[] temp = new String[3];
                	if(user.getBirthday()!=null){
                		temp = user.getBirthday().split("-");
                		
                	}
                %>
                <script type="text/javascript">
	                $(window).load(function(){ 
	                	$("#selectYear").val(<%=temp[0]%>);
	                	$("#selectMonth").val(<%=temp[1]%>);
	                	$("#selectDate").val(<%=temp[2]%>);
	                });
                </script>
                <span class="s1"><span class="he">*</span>
                生日：</span>
                <span class="s2">
                <span class="spanjiange"></span>
                	<select name="year" id="selectYear" class="select1" style="font-size:12px">
                		<option value="">--</option>
                	</select>
				<span class="wenzi">年</span>
					<select name="month" id="selectMonth" class="select1" style="font-size:12px">
						<option value="">--</option>
					</select>
                <span class="wenzi">月</span>
               		<select name="date" id="selectDate" class="select1" style="font-size:12px">
               			<option value="">--</option>
               		</select>
                </span>
               <div class="simi">
                    <!--selfselect2-->	
                     <div class="selfselectout" onmouseout="selectno('listdata2')"> 	
					<div  class="selfselect" >
	                  <input name="isBirthdaySecret" id="hgongkai2" type="hidden" value="1" />
	                  <p id="gongkai2" class="selectzhi"  onclick="changedisplay('listdata2')" >我关注的人可见</p>
	                </div>
	                <div id="listdata2" class="listdata" style="display:none" onmouseover="selectyes('listdata2')" onmouseout="selectno('listdata2')"></div>
				</div>
				<!--selfselect2 end-->	
                   </div>
                </li>
                <li class="li3">
                <span class="s1"><span class="he">*</span>
               	 所在地：</span>
                <span class="s2">
                <span class="spanjiange"></span>
                <input type="text" name="cityName" id="cityName" value="${session.sessionLoginUser.cityName}" class="input1" onclick="CityPicker.show('#cityName');"/>
                </span>
                </li>
                <li class="li4">
                <div class="div1"></div>
                </li>
                <li class="li1">
                    <span class="s1">
                        个人主页：
                    </span>
                    <span class="s2">
                        <input type="text" name="blogUrl" id="blogUrl" value="${session.sessionLoginUser.blogUrl}" class="input1" />
                    </span>
                    <div class="simi">
                    <!--selfselect3-->	
                     <div class="selfselectout" onmouseout="selectno('listdata3')">  	  
						<div  class="selfselect" >
		                  <input name="isBlogUrlSecret" id="hgongkai3" type="hidden" value="1" />
		                  <p id="gongkai3" class="selectzhi"  onclick="changedisplay('listdata3')" >我关注的人可见</p>
		                </div>
		                <div id="listdata3" class="listdata" style="display:none" onmouseover="selectyes('listdata3')" onmouseout="selectno('listdata3')" ></div>
		               </div>
				<!--selfselect3 end-->	
                   </div>
                    <span class="s3_hidden">
                    </span>
                    <span class="s4"></span>
                </li>
                <li class="li1">
                    <span class="s1">
                        绑定手机：
                    </span>
                    <span class="s2">
                        <input type="text" name=phoneNo id="phoneNo" value="${session.sessionLoginUser.phoneNo}" class="input1" />
                    </span>
                    <div class="simi">
                    <!--selfselect4-->		
                     <div class="selfselectout" onmouseout="selectno('listdata4')">    
				<div  class="selfselect" onmouseout="selectno('listdata4')">
                  <input name="isPhoneNoSecret" id="hgongkai4" type="hidden" value="-1" />
                  <p id="gongkai4" class="selectzhi"  onclick="changedisplay('listdata4')" >仅自己可见</p>
                 
                </div>
                <div id="listdata4" class="listdata" style="display:none" onmouseover="selectyes('listdata4')" onmouseout="selectno('listdata4')"></div>
                </div>
				<!--selfselect4 end-->	
                   </div>
                    <span class="s3_hidden"></span>
                    <span class="s4"></span>
                </li>
                <li class="li1">
                    <span class="s1">
                        QQ：
                    </span>
                    <span class="s2">
                        <input type="text" name="qq" id="qq" value="${session.sessionLoginUser.qq}" class="input1" />
                    </span>
                    <div class="simi">
                    <!--selfselect5-->	
                     <div class="selfselectout" onmouseout="selectno('listdata5')">  	  
				<div  class="selfselect" >
                  <input name="isQQSecret" id="hgongkai5" type="hidden" value="1" />
                  <p id="gongkai5" class="selectzhi"  onclick="changedisplay('listdata5')" >我关注的人可见</p>
                 
                </div>
                <div id="listdata5" class="listdata" style="display:none" onmouseover="selectyes('listdata5')" onmouseout="selectno('listdata5')" ></div>
                </div>
				<!--selfselect5 end-->	
                   </div>
                    <span class="s3_hidden">
                    </span>
                    <span class="s4"></span>
                </li>
                <li class="li1">
                    <span class="s1">
                        MSN：
                    </span>
                    <span class="s2">
                        <input type="text" name="msn" id="msn" value="${session.sessionLoginUser.msn}" class="input1" />
                    </span>
                    <div class="simi">
                    <!--selfselect6-->	
                     <div class="selfselectout" onmouseout="selectno('listdata6')">  	  
				<div  class="selfselect" >
                  <input name="isMSNSecret" id="hgongkai6" type="hidden" value="1" />
                  <p id="gongkai6" class="selectzhi"  onclick="changedisplay('listdata6')" >我关注的人可见</p>
                 
                </div>
                <div id="listdata6" class="listdata" style="display:none" onmouseover="selectyes('listdata6')" onmouseout="selectno('listdata6')"></div>
                </div>
				<!--selfselect6 end-->	
                   </div>
                    <span class="s3_hidden">
                    </span>
                    <span class="s4"></span>
                </li>
                <li class="li1">
                    <span class="s1">
                        
                        备用邮箱：
                    </span>
                    
                    <span class="s2">
                        <input type="text" name="secondrayEmail" id="secondrayEmail" value="${session.sessionLoginUser.secondrayEmail}" class="input1" />
                    </span>
                    <span class="s3_hidden">
                    </span>
                    <span class="s4"></span>
                </li>
                <li class="li5">
                <span class="s1">
                        自我介绍：
                    </span>
                    <span class="s2">
                    <span class="spanjiange"></span>
                    <script type="text/javascript">
                    	$(document).ready(function(){
                    		$("#selfDescription").css("font-size:12px");
							$("#selfDescription").html('${session.sessionLoginUser.selfDescription}');
							if($("#selfDescription").html()==''){
								$("#selfDescription").html('这个家伙很懒什么也没留下');
							}
						});
                    </script>
                    <textarea name="selfDescription" id="selfDescription" cols="45" rows="5" class="text1" onclick="this.select();"></textarea>
                    </span>
                    <div class="div1" style="display: none">提示：还可以输入163个字</div>
                </li>
                <li class="li6">
                <div class="div1"></div>
                </li>
                <li class="li7">
                <span class="s1">
                  <input type="image" name="imageField" id="imageField" src="${staticContext}/images/szxxzl1_19.jpg" />
                  </span>
                  <span id="profile_saveResult" style="display:none;margin:12px;position: absolute;"><img src="${staticContext}/images/loading.gif"/></span>
                </li>
            </ul>
            </form>
        </div>
        <!--mysetid1 end-->
        <!--mysetid2 -->
        <div id="mysetid2" class="myset_3">
         <div id="resetpswd" class="mpicture1"></div>
        <form id="resetPasswdForm" name="resetPasswdForm" method="post" action="my_set!ajaxResetPassword.bc">
       	<input name="struts.enableJSONValidation" type="hidden" value="true" />
        <ul class="mpassword">
            <li class="li1">
                    <span class="s1">
                        <span class="he">*</span>
                        您的旧密码：
                    </span>
                    
                    <span class="s2">
                        <input type="password" name="oldPassword" id="passwordOld" class="input1" />
                    </span>
                    <span id="passwordOldTip" class="tip"></span>
                    <span class="s4"></span>
                </li>
                <li class="li1">
                    <span class="s1">
                        <span class="he">*</span>
                        输入新密码：
                    </span>
                    
                    <span class="s2">
                        <input type="password" name="newPassword" id="textfield" class="input1" />
                    </span>
                    <span id="passwordTip" class="tip"></span>
                    <span class="s4">限用6～16位字母、数字、特殊字符，字母有大小写之分。</span>
                </li>
                <li class="li1">
                    <span class="s1">
                        <span class="he">*</span>
                        新密码确认：
                    </span>
                    
                    <span class="s2">
                        <input type="password" name="newPasswordRetry" id=passwordNew class="input1" />
                    </span>
                     <span id="passwordNewTip" class="tip"></span>
                    <span class="s4"></span>
                </li>
                 <li class="li7">
                <span class="s1">
                  <input type="image" name="imageField" id="imageField" src="${staticContext}/images/szxxzl1_19.jpg" />
                   <span id="password_saveResult" style="display:none;margin:12px;position: absolute;"><img src="${staticContext}/images/loading.gif"/></span>
                  </span>
                </li>
        </ul>
         </form>
      </div>
         <!--mysetid3 end-->
        <!--mysetid4 -->
        <div id="mysetid3" class="myset_3">
        <ul class="u2">
           <li>
           <s:if test="#request.sinaConnected==false">
               <div id="tongbu1" class="divno">
                   <span class="s1">同步到</span>
                   <span class="s2">
                   <a href="javascript:void(0)" onclick="changeBundle(1)">
                   <img src="${staticContext}/images/sync_sina.png" />
                   </a>
                   </span>
                   <span class="s4">
                   <img src="${staticContext}/images/sztbfx1_34.jpg" width="17" height="11" />
                   </span>
                   <span class="s3">
                   <a href="javascript:void(0)" onclick="changeBundle(1)">
                   <img src="${staticContext}/images/sztbfx1_8.jpg"  width="10" height="10"/>
                   </a>
                   </span>
               </div>
               </s:if>
               <s:else>
               <div id="yibangding1" class="divyes">
                   <span class="s1"><img src="${staticContext}/images/sztbfx1_31.jpg" width="17" height="13" style="margin-right:10px"/>已绑定</span>
                   <span class="s2"> <img src="${staticContext}/images/sync_sina.png" /></span>
                   <span class="s3">微博Id: ${request.sinaId}</span>
               </div>
               </s:else>
           </li>
       </ul>
        </div>
        <!--mysetid3 end-->
    </div>
   
</div>
<script type="text/javascript">
	settab('${request.active}');
	$('#myDetailForm').ajaxForm({
		dataType : "json",
		beforeSubmit : function (arr, form, options){
	 		  	$("#profile_saveResult").show();
			},
		success : function (response, statusText, xhr, $form) {
			$("#profile_saveResult").text(response.returnMsg);
			$("#profile_saveResult").show();
			if(response.saveResult){
    			$("#profile_saveResult").css({color: "green"});
    			window.setTimeout(function(){
    				$("#profile_saveResult").hide();
    	        }, 3000);
    		}else{
    			$("#profile_saveResult").css({color: "red"});
    		}
		}
	});
	
     	
     	$('#resetPasswdForm').ajaxForm({
			dataType : "json",
			success : function(response, statusText, xhr, $form) {
				if(response && (response.errors || response.fieldErrors) ){
					var errorHtml = "<ul>";
					if(response.errors){
						for(var prop in response.errors) {
							errorHtml += "<li><span>" + response.errors[prop] + "</span></li>";
						}
					}
					if(response.fieldErrors){
						for(prop in response.fieldErrors) {
							errorHtml += "<li><span>" + response.fieldErrors[prop] + "</span></li>";
						}
					}
					errorHtml += "</ul>";
					$("#resetpswd").html(errorHtml);
					$("#resetpswd").css({color: "red"});
					return;
				}
				if(response.returnMsg!=null){
	        		$("#resetpswd").html(response.returnMsg);
	        		if(response.saveResult){
	        			$("#resetpswd").css({color: "green"});
	        		}else{
	        			$("#resetpswd").css({color: "red"});
	        		}
	        	}
			}
		});
     	
</script>
<!--myset content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
$(".footerout").css({backgroundColor:'#F7F6F1'});
</script>
</body>

</html>
