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
					<div class="l mr8 cprogress f40 fb lh50 ffar">100%</div>
					<div class="l">
						<div class="progressbarbg br3 bort2 borb2 mt10 clean" style="height:10px; width:430px;">
							<div class="l" style="height:10px;width:145px;">
								<div class="complete" style="height:10px;width:145px;"></div>
							</div>
							<div class="l" style="height:10px;width:145px;">
								<div class="complete" style="height:10px;width:145px;"></div>
							</div>
							<div class="r" style="height:10px;width:140px;">
								<div class="complete" style="height:10px;width:139px;"></div>
							</div>
						</div>
						<div class="lh20 f12">
							<span class="gray">关注我感兴趣的波客、餐厅和美食</span>
						</div>
					</div>
				</li>
				<s:form id="wizardForm" name="form1" method="post" action="wizard_submit.bc">
            	<li class="clean" style="width:100%">
	            	<div class="clean l">
	  					<div class="mb20 ctitle clean">
	            			<h4 class="lh20 f14 ffyh">臭味相投的波客
	            				<span class="f14">(<a class="hlink boke" href="javascript:void(0);">+关注</a>)</span></h4>
		            		<s:iterator value="suggestedUsers" id="entry">
	            			<div class="m5 l ofh" style="width:60px;">
	            				<dl class="tc">
	            				<dt class="mb3"><img class="br3" width="50" height="50" userid="<s:property value="#entry.key.id" />" src="<s:property value="#entry.key.avatar" />" /></dt>
	            				<dt class="mb5"><span class="wsnw"><s:property value="#entry.key.name" /></span></dt>
	            				<dt class="settings-checkbox usetting">
	            					<label class="boke ml10" for='boke_<s:property value="#entry.key.id" />'>
	            						<input id='boke_<s:property value="#entry.key.id" />' type="checkbox" value='<s:property value="#entry.key.id" />' name="selectedUsers"/>
	            						<span>+ 关注</span>
	            					</label>
	            				</dt>
	            				</dl>
	            			</div>
	            			</s:iterator>
	            		</div>
	            		<div id="place" class="mb20 ctitle clean">
	            			<h4 class="lh20 f14 ffyh">感兴趣的餐厅
	            				<span class="f14">(<a class="hlink" href="javascript:void(0);">+关注</a>)</span></h4>
	            			<s:iterator value="suggestedPlaces" id="entry">
	            			<div class="settings-checkbox">
		            			<label class="place" for='place_<s:property value="#entry.key.id" />'>
									<input id='place_<s:property value="#entry.key.id" />' type="checkbox" value='<s:property value="#entry.key.id" />' name="selectedPlaces"/>
									<span><s:property value="#entry.key.fullName" /></span>
								</label>
	            			</div>
	            			</s:iterator>
	            		</div>
	            		<div id="dish" class="mb20 ctitle clean">
	            			<h4 class="lh20 f14 ffyh">对味的美食
	            				<span class="f14">(<a class="hlink" href="javascript:void(0);">+关注</a>)</span></h4>
	            			<s:iterator value="suggestedDishs" id="entry">
	            			<div class="settings-checkbox">
		            			<label class="dish" for='dish_<s:property value="#entry.key.id" />'>
									<input id='dish_<s:property value="#entry.key.id" />' type="checkbox" value='<s:property value="#entry.key.id" />' name="selectedDishs"/>
									<span><s:property value="#entry.key.name" /></span>
								</label>
	            			</div>
	            			</s:iterator>
	            		</div>
	            	</div>
            	</li>
            	<li class="clean lh45 tr" style="height:45px;">
            		<input class="btn" type="reset" value="上一步" onclick="window.location.href='wizard_teste.bc'"></input>
            		<input class="btn success" type="submit" value="完成，开始波菜之旅" />
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
	$('#wizardForm input[type="checkbox"]').click(
		function(event){
			var $this = $(event.currentTarget);
			var $parent = $this.parent();
			var txt = $this.siblings().text();
			var $dom = $(event.currentTarget).parent().parent();
			var hasChecked = $this.attr('checked');
			if($dom.hasClass('usetting')){
				if(txt == '+ 关注'){
					$this.siblings().text('取消关注');
					$parent.css('margin-left', '6px');
				}else{
					$this.siblings().text('+ 关注');
					$parent.css('margin-left', '10px');
				}
			}
			if(hasChecked){
				$dom.addClass('checked');
			}else{
				$dom.removeClass('checked');
			}
		});
	$('#wizardForm .settings-checkbox').click(
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
	$('#wizardForm h4 a').click(
			function(event){
				var txt = $(event.currentTarget).text();
				var $this = $(event.currentTarget);
				var $dom = $(event.currentTarget).parent().parent().parent();
				if(txt == '+关注'){
					$dom.find('.settings-checkbox').addClass('checked');
					$dom.find('input[type="checkbox"]').attr('checked', 'checked');
					if($this.hasClass('boke')){
						$dom.find('.settings-checkbox span').text('取消关注');
						$dom.find('.settings-checkbox label').css('margin-left', '6px');
					}
					$(event.currentTarget).text('取消关注');
				}else{
					$dom.find('.settings-checkbox').removeClass('checked');
					$dom.find('input[type="checkbox"]').attr('checked', '');
					if($this.hasClass('boke')){
						$dom.find('.settings-checkbox span').text('+ 关注');
						$dom.find('.settings-checkbox label').css('margin-left', '10px');
					}
					$(event.currentTarget).text('+关注');
				}
				
			});
	$(".footerout").css({backgroundColor:'#F0EEDF'});
	
</script>
<!-- script end -->
</body>
</html>