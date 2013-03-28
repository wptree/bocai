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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${staticContext}/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/myset.css" rel="stylesheet" type="text/css" />
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
</head>
<body>
<%
	User user = (User)request.getSession().getAttribute("sessionLoginUser");
%>
<script type="text/javascript" src="${staticContext}/js/jquery.imgareaselect.js"></script>
        <!--mysetid2 -->
        <div id="mysetid2" class="myset_3" style="display:block; border: none;">
           <div id="uploadInfo" class="mpicture1">添加或更改你的头像<span id="avatar_saveResult" style="display:none;position: absolute;"><img src="${staticContext}/images/loading.gif"/></span></div>
           <s:form id="updatePhotoForm" name="updatePhotoForm" method ="POST" enctype ="multipart/form-data" action="user_photo!uploadTemporaryFile.bc">
           <div class="mpictureUpload">
           <input name="struts.enableJSONValidation" type="hidden" value="true" />
            <input name="struts.enableFileUploadValidation" type="hidden" value="true" />
           <s:file name="userPhoto"></s:file><button type="submit">上传照片</button>
           <span>你可以上传.jpg、.jpeg、.gif、.png或.bmp文件,请不要超过2MB。</span>
           </div>
           </s:form>
		   <s:form id="updatePhotoForm2" name="updatePhotoForm2" method ="POST" action="user_photo!saveAvatar.bc">
           <div class="mpicture2">
               <div class="mpicture2le">
               <input type="hidden" name="tempPath" id="tempPath"/>
               <input type="hidden" name="imgAreaSelectParam" id="imgAreaSelectParam"/>
                 <%
                   	String originalAvatar = StringUtil.getOriginalAvatar(user.getAvatar());
                   	if("default".equals(originalAvatar)){
                   		originalAvatar = request.getContextPath()+"/images/default-user-avatar.jpeg";
                   	}
                   %>
               <img id="userPhotoOriginal" name="userPhotoOriginal" src="<%=originalAvatar+"?"+System.currentTimeMillis()%>" />
               <script type="text/javascript">
			var imgw, imgh;
           	$("#updatePhotoForm").ajaxForm({
            	   dataType : "text/html",
            	  beforeSubmit : function (arr, form, options){
           		  	$("#avatar_saveResult").show();
           		},
                   success: function(response) {
                	 $("#avatar_saveResult").hide();
                   	 var regEx = /<[^>]*>/g;
                   	  var jsonstr = response.replace(regEx, "");
                   	  var obj = $.parseJSON(jsonstr);
                   	  
                   	if(obj && (obj.errors || obj.fieldErrors) ){
           				var errorHtml = "<ul>";
           				if(obj.errors){
           					for(var prop in obj.errors) {
           						errorHtml += "<li><span>" + obj.errors[prop] + "</span></li>";
           					}
           				}
           				if(obj.fieldErrors){
           					for(prop in obj.fieldErrors) {
           						errorHtml += "<li><span>" + obj.fieldErrors[prop] + "</span></li>";
           					}
           				}
           				errorHtml += "</ul>";
           				$("#avatar_saveResult").html(errorHtml);
           				$("#avatar_saveResult").css({color: "red"});
           				return;
                   }
                   	if(obj.returnMsg!=null){
                   		$("#avatar_saveResult").html(obj.returnMsg);
           				$("#avatar_saveResult").css({color: "red"});
           				return;
                   	}
                	 
                      $("#userPhotoOriginal").attr("src",obj.originalPath+"?"+Math.random());
                      if(obj.imgOriginalW<=300){
                    	  $("#userPhotoOriginal").attr("width",obj.imgOriginalW);
                    	  imgw = obj.imgOriginalW;
                      }
                      if(obj.imgOriginalH<=300){
                    	  $("#userPhotoOriginal").attr("height",obj.imgOriginalH);
                    	  imgh = obj.imgOriginalH;
                      }
                      $('#preimg1').attr("src",obj.originalPath+"?"+Math.random());
           	          $('#preimg2').attr("src",obj.originalPath+"?"+Math.random());
                      $("#tempPath").val(obj.tempPath);
                      $("#userPhotoOriginal").imgAreaSelect({x1:10,y1:10,x2:100,y2:100});
                   }
               });
           	
                $("#updatePhotoForm2").ajaxForm({
               	   dataType : "json",
	                      success: function(response) {
	                   	  $('#preimg1').attr("src",response.middlePath+"?"+Math.random());
	                   	  $('#preimg2').attr("src",response.smallPath+"?"+Math.random());
	                   	  $("#errorMsgTip").html(response.returnMsg);
	           			  $("#errorMsgTip").show();
	           			  var original = parent.document.getElementById("userAvatar").src;
	           			  parent.document.getElementById("userAvatar").src = original +"?"+Math.random();
	           			  parent.closeIframeModal();
                      }
                  });
	
	 $(document).ready(function () {
		 function preview(img, selection) {
         	  var scaleX1 = 100 / selection.width; 
         	  var scaleY1 = 100 / selection.height; 
         	  $('#preimg1').css({ 
         		  width: Math.round(scaleX1*200), 
         		  height: Math.round(scaleY1*200), 
         		  marginLeft: -Math.round(scaleX1 * selection.x1), 
         		  marginTop: -Math.round(scaleY1 * selection.y1) 
         	  }); 	 
         	  
         	  var scaleX2 = 50 / selection.width; 
         	  var scaleY2 = 50 / selection.height; 
         	  $('#preimg2').css({ 
         		  width: Math.round(scaleX2 * 150), 
         		  height: Math.round(scaleY2 * 150), 
         		  marginLeft: -Math.round(scaleX2 * selection.x1), 
         		  marginTop: -Math.round(scaleY2 * selection.y1)
			 	}); 
         	  }
		 
		 var offset = $("#userPhotoOriginal").offset();
		 $("#userPhotoOriginal").imgAreaSelect({
			 	x1:offset.left,
			 	y1:offset.top,
			 	x2:offset.left+$("#userPhotoOriginal").width()/2,
			 	y2:offset.top+$("#userPhotoOriginal").height()/2,
				aspectRatio: '1:1',
				handles: true,
				onSelectStart: function(){
					imgw = $('#userPhotoOriginal').width();
			        imgh = $('#userPhotoOriginal').height();
				  	$('#preimg1').attr("src", $("#userPhotoOriginal").attr("src")+"?"+Math.random());
		 	        $('#preimg2').attr("src",$("#userPhotoOriginal").attr("src")+"?"+Math.random());
				},
				onSelectChange: preview,
	 			onSelectEnd: function (img, selection) { 
	 				$("#imgAreaSelectParam").val(selection.x1+","+selection.y1+","+selection.x2+","+selection.y2);
	 			}
	 			}); 

 		$("#photoPreview").css({ 
 			position: 'relative', 
 			overflow: 'hidden', 
 			width: '100px', height: '100px' });
 		
 		$("#photoPreview2").css({ 
 			position: 'relative', 
 			overflow: 'hidden', 
 			width: '50px', height: '50px' });
               });
</script>
               </div>
               <div class="mpicture2ri">
                   <div class="mpicture2ri_a">
                   <div class="mpicture2ri_b">大小：100×100</div>
                   <div id="photoPreview">
                   <%
	                   	String middleAvatar = StringUtil.getMiddleAvatar(user.getAvatar());
	                   	if("default".equals(middleAvatar)){
	                   		middleAvatar = request.getContextPath()+"/images/default-user-avatar100.jpeg";
	                   	}
                   %>
                   <img src="<%=middleAvatar+"?"+System.currentTimeMillis()%>" id="preimg1"  style="position: relative;" />
                   </div>
           		   <div class="mpicture2ri_b2">大小：50×50</div>
                   <div id="photoPreview2">
                   <s:if test="#session.sessionLoginUser.avatar==null||#session.sessionLoginUser.avatar==''">
                   		<img src="<%=request.getContextPath()%>/images/default-user-avatar30.jpeg"  id="preimg2" style="position: relative;" />	               </s:if>
	               <s:else>
	               		<img src="<%=user.getAvatar()+"?"+System.currentTimeMillis()%>"  id="preimg2"  style="position: relative;" />	             
	               </s:else>
                   </div>
                   </div>
                   <div class="mpicture2ri_d">
                   请在左图中选择要保存作为小头像的区域， <br />
确定需要裁剪的部分后点击'保存'按钮进行裁剪。
                   </div>
                   <div class="mpicture2ri_e">
                   	<input type="image" src="${staticContext}/images/szxgtx1_13.jpg" />
                   </div>
                   <div class="mpicture2ri_f">
                   </div>
               </div>
           </div></s:form>
        </div>
         <!--mysetid2 end-->
   
</body>
</html>
