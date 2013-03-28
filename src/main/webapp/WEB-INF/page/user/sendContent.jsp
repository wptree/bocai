<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>        
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>        
<meta http-equiv="expires" content="0"/>   
<title>分享健康美食 旅游美食引擎 波菜网</title>
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
</head>

<body>
<!--head start-->
<s:include value="../common/header.jsp"></s:include>
<!--head end-->
<script type="text/javascript">
$(document).ready(function() {
	$("#sendConentForm").ajaxForm({
	 	   dataType : "json",
	        success: function(response) {
        		$("#uploadInfo").html(response.returnMsg);
        		if(response.sendResult){
        			$("#uploadInfo").css({color: "green"});
        			$("#contenttxt").val("");
        		}else{
        			$("#uploadInfo").css({color: "red"});
        		}
	        }
	    });
});

</script>
<div class="myset_contentout">
    <div class="myset">
        <s:form id="sendConentForm" name="sendConentForm" method ="POST" action="thirdparty!sendStatus.bc">
        <ul class="mpassword">
             <li class="li1">
                    <span class="s1">
                		 我说：
                    </span>
                    <span class="s2">
                       <textarea id="contenttxt" name="content" cols="45" rows="5" class="text1"  style="margin-bottom: 20px;margin-left: 10px"></textarea>
                    </span>
                    <span class="s4"></span>
                </li>
                 <li class="li1">
                 <span class="s1">
                		 发送到：
                    </span>
                 	<s:if test="#session.sessionLoginUser.source=='sina'">
                		<span class="s2"><input type="checkbox" checked="checked" name="sendToSina"/><img src="${staticContext}/images/index_fx1_34.jpg"/></span>
                	</s:if>
                	<s:elseif test="#session.sessionLoginUser.source=='renren'">
                		<span class="s2"><input type="checkbox" checked="checked" name="sendToRenren"/><img src="${staticContext}/images/index_fx1_32.jpg"/></span>
                	</s:elseif>
                	<s:else>
                		<span>此页面仅供测试新浪/人人发布消息用。</span>
                	</s:else>
                </li>
                 <li class="li1">
                 <span class="s1"> </span>
                	<span class="s2">
                  		<input style="margin-left: 10px" type="image" name="imageField" id="imageField" src="${staticContext}/images/szxxzl1_19.jpg" />
                 	</span>
                </li>   
                 <li class="li1">
                 <span class="s1"> </span>
                 <span id="uploadInfo" class="s2" style="font-size:15px;margin-left: 10px;margin-top: 5px;"></span>
                </li>
        </ul>
         </s:form>
    </div>
   
</div>

<!--foot start-->
<s:include value="../common/foot.jsp"></s:include>
<!--foot end-->

</body>
</html>