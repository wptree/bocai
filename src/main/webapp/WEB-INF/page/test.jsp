<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>波菜 - Test</title>
<!-- import common css -->
<!-- import common scripts -->
<script type="text/javascript" src="${staticContext}/js/jquery-1.5.2.js"></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sendRequest").click(function(){
			$.ajax({url: "testAjaxCall.bc",
				  type: "GET",
				  dataType: "json",
				  success: function(jsonObj){
					  $("#returnMsg").html(jsonObj.returnMsg);
					  if(!jsonObj.callResult){
						  $("#returnMsg").html("Error happend...");
					  }
				  }
			});
			
		});
	});
</script>
<div style="width:100%;height:100%">
    <div style="margin:50px">
    	<div style="margin:100px;width:500px;height:500px">
	      <div><button id="sendRequest">Send Ajax Request</button></div>
	       <div id="callResult" style="height:20px;width:auto;"></div>
	       <div id="returnMsg" style="height:20px;width:auto;"></div>
       </div>
    </div>
</div>

</body>
</html>
