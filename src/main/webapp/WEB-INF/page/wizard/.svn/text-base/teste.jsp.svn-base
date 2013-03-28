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
<title>注册向导 波菜网 旅游美食 美食指南 美食分享 </title>
<link href="${staticContext}/css/login.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/forget-pwd.css" rel="stylesheet" type="text/css" />
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/css/contrl.css" rel="stylesheet" type="text/css" />
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
</head>
<body>
<div class="main">
<s:include value="../common/header.jsp" ></s:include>
<!--forget pwd content start-->
<div class="login_contentout">
    <div class="login_content">
    <div class="forget-panel">
    	<!--忘记密码开始-->
			<ul id="forget_form" class="forget-ul pl30 pr30 pt20 pb20">
				<li id="progress" class="mb10 clean">
					<div class="l mr8 cprogress f40 fb lh50 ffar">66%</div>
					<div class="l">
						<div class="progressbarbg br3 bort2 borb2 mt10 clean" style="height:10px; width:440px;">
							<div class="l" style="height:10px;width:145px;">
								<div class="l complete" style="height:10px;width:145px;"></div>
							</div>
							<div class="l clean" style="height:10px;width:145px;">
								<div class="l complete" style="height:10px;width:131px;"></div>
								<div class="r parrow" style="height:10px;width:14px;"></div>
							</div>
							<div class="r" style="height:10px;width:146px;"></div>
						</div>
						<div class="lh20 f12">
							<span class="gray">选择我喜欢的口味、菜系和菜品</span>
						</div>
					</div>
				</li>
				<s:form id="wizardTesteForm" method="post" action="wizard_save_teste.bc">
            	<div class="clean" style="width:100%">
            	<div class="clean l">
            	<s:iterator value="suggestedTags" id="entry">
  					<fieldset class="mb20 ctitle">
            		<h4 class="lh20 f16 ffyh"><s:property value="#entry.key" />
            			<span class="f14">(<a class="hlink" href="javascript:void(0);">全选</a>)</span></h4>
            		<s:iterator value="#entry.value" id="tagEntry">
            			<div class='settings-checkbox <s:if test="#tagEntry.value">checked</s:if>'>
            			<label class="tag" for='tag_<s:property value="#tagEntry.key.id" />'>
							<input id='tag_<s:property value="#tagEntry.key.id" />' type="checkbox" 
								value='<s:property value="#tagEntry.key.id" />' name="selectedTags"
								<s:if test="#tagEntry.value">checked</s:if>
								/>
							<span><s:property value="#tagEntry.key.tagName" /></span>
						</label>
            			</div>
            		</s:iterator>
            		</fieldset>
  				</s:iterator>
            	</div>
            	</div>
            	<li class="clean lh45 tr" style="height:45px;">
            		<input class="btn" type="reset" value="上一步" onclick="window.location.href='wizard_basic.bc'"></input>
            		<input class="btn" type="reset" value="跳过" onclick="window.location.href='wizard_following.bc'"></input>
            		<input class="btn success" type="submit" value="下一步" />
            	</li>
            	</s:form>
            </ul>
         <!--忘记密码结束-->
    </div>
    </div>
</div>
<!--forget pwd content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
<!-- script start -->
<script type="text/javascript" src="${staticContext}/js/operation.js"></script>
<script type="text/javascript">
	$('#wizardTesteForm input[type="checkbox"]').click(
		function(event){
			var $this = $(event.currentTarget);
			var $dom = $(event.currentTarget).parent().parent();
			var hasChecked = $this.attr('checked');
			if(hasChecked){
				$dom.addClass('checked');
			}else{
				$dom.removeClass('checked');
			}
		});
	$('#wizardTesteForm .settings-checkbox').click(
			function(event){
				var $this = $(event.currentTarget);
				var $ctrl = $this.find('input[type="checkbox"]');
				var $span = $this.find('label span');
			    var hasChecked = $this.hasClass('checked');
			    var fromSpan = (event.target == event.currentTarget)? false:true;
			    if(fromSpan) return;
			    if(hasChecked){
			    	$ctrl.attr('checked','');
			    	$this.removeClass('checked');
			    }else{
			    	$ctrl.attr('checked','checked');
			    	$this.addClass('checked');
			    }
			});
	$('#wizardTesteForm fieldset h4 a').click(
			function(event){
				var txt = $(event.currentTarget).text();
				var $dom = $(event.currentTarget).parent().parent().parent();
				if(txt == '全选'){
					$dom.find('.settings-checkbox').addClass('checked');
					$dom.find('input[type="checkbox"]').attr('checked', 'checked');
					$(event.currentTarget).text('取消');
				}else{
					$dom.find('.settings-checkbox').removeClass('checked');
					$dom.find('input[type="checkbox"]').attr('checked', '');
					$(event.currentTarget).text('全选');
				}
				
			});
	$(".footerout").css({backgroundColor:'#F0EEDF'});
	
</script>
<!-- script end -->
</body>
</html>