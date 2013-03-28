<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>        
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>        
<meta http-equiv="expires" content="0"/>   
<title>分享健康美食 旅游美食引擎 波菜网  波客</title>
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=token-input-bocai.css,upload.css,citypicker.css,pod.css,mydashboard.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/token-input-upload.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/upload.spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bc_body">
	<!-- header -->
	<s:include value="../common/header.jsp" ></s:include>
	<!--content start-->
	<div class="bc_contentout">
	    <div class="bc_content clean">
	    	<div class="c_wel ffyh lh30 clean">
	           	<span id="page_title" class="tit">我的波菜/我的消息</span>
	          	<span class="lk"><a href="${pageContext.request.contextPath}/main.bc">去地图上看看&gt;&gt;</a></span>
	        </div>
	    	
	        <ul class="c_main">
	        	<li class="m_li">
	        	 	<s:include value="../component/upload_spot.jsp"></s:include>
	       		</li>
	       		<li class="m_li pr">
	       			<ul id="db_msg_tabs" class="db-tabs w301 bbox-tabs clean">
	        	 		<li id="reminder_tab" class="l w150 bgc1 btlr5 borb1 borr1 clean on">
	        	 			<a class="l disB p15 lh150" href="javascript:void(0);"><span class="ffyh f14 fb">提醒</span>
	        	 			<br/><span class="f_brackets">未读(<span id="reminderUnreadNum"><s:property value="msgCountVo.reminder"/></span>)</span></a>
	        	 		</li>
	        	 		<li id="private_tab" class="l w150 bgc1 btrr5 borb1 clean">
	        	 			<a class="l disB p15 lh150" href="javascript:void(0);"><span class="ffyh f14 fb">私信</span>
	        	 			<br/><span class="f_brackets">未读(<span id="privateUnreadNum"><s:property value="msgCountVo.privateLetter"/></span>)</span></a>
	        	 		</li>
	        	 	</ul>
	        	 	<div id="db_msg_reminder_out" class="db-list bbox none">
	        	 		<div id="db_msg_reminder_op" class="p15 borb1" style="height: 22px; line-height: 22px;">
	        	 			<a class="unread tglbtn mr2 p5 br3 selected" href="javascript:void(0);" 
						 		onclick="return false;">未读提醒</a>
						 	<a class="full tglbtn mr2 p5 br3" href="javascript:void(0);" 
						 		onclick="return false;">所有提醒</a>
						</div>
	        	 		<ul id="db_msg_reminder_loading" class="p15 none bort1 borb1" >
							<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
						</ul>
						<ul id="db_msg_reminder_tip" class="p15 none pt30 pb30 bort1 borb1" >
							<li class="ctitle">没发现新提醒哦！快来
								<a href="#" class="hlink fb">分享美食</a>
								吧，系统会提醒其他波客的互动信息给你！</li>
						</ul>
	        	 		<ul id="db_msg_reminder" class="wr-wb"></ul>
	        	 		<ul class="p15 bort1 clean">
       						<li id="divpagingup_reminder" class="mt5 fr"></li>
       					</ul>
	        	 	</div>
	        	 	<div id="db_msg_private_out" class="db-list bbox none">
	        	 		<div id="db_msg_private_op" class="p15 borb1" style="height: 22px; line-height: 22px;">
	        	 			<a class="unread tglbtn p5 br3 mr2 selected" href="javascript:void(0);" 
						 		onclick="return false;">未读私信对话</a>
							<a class="full tglbtn p5 br3 mr2" href="javascript:void(0);" 
						 		onclick="return false;">所有私信对话</a>
						 	<!--<div class="r">
	 								<a class="tool msg" href="javascript:void(0);" onclick="OP.sendPrivateMsg();return false;">发私信</a>
							</div>-->
						</div>
	        	 		<ul id="db_msg_private_loading" class="p15 none bort1 borb1" >
							<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
						</ul>
						<ul id="db_msg_private_tip" class="p15 none pt30 pb30 bort1 borb1" >
							<li class="ctitle">没发现新私信哦！快来
								<a href="#" class="hlink fb">关注好友</a>
								吧，您就可以直接和TA进行私信哦！</li>
						</ul>
	        	 		<ul id="db_msg_private" class="wr-wb"></ul>
	        	 		<ul class="p15 bort1 clean">
       						<li id="divpagingup_private" class="mt5 fr"></li>
       					</ul>
	        	 	</div>
	        	 	<div id="db_msg_private_detail_out" class="db-list bbox none">
	        	 		<ul id="dialog_detail_loading" class="p15 none borb1" >
							<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
						</ul>
	        	 		<ul id="db_msg_private_detail"></ul>
	        	 		<ul class="p15 bort1 clean">
       						<li id="divpagingup_private_detail" class="mt5 fr"></li>
       					</ul>
	        	 	</div>
	       		</li>
	        </ul>
	        
	        <ul class="c_side">
	        	<li class="s_li">
	        		<s:action name="user_panel" namespace="/user" executeResult="true">
		            	<s:param name="id"><s:property value="user.id"/></s:param>
		        	</s:action>
		        </li>
		        <li class="s_li">
                	<s:include value="../component/dashboard_menu.jsp">
						<s:param name="opt">my_msg</s:param>
					</s:include>
            	</li>
		        <li class="s_li">
		             <s:action name="list_following_boke" namespace="/user" executeResult="true">
		             	<s:param name="id"><s:property value="user.id"/></s:param>
		             </s:action>      
	            </li>
	            <li class="s_li">
		             <s:action name="list_fans_of_boke" namespace="/user" executeResult="true">
		             	<s:param name="id"><s:property value="user.id"/></s:param>
		             </s:action>      
	            </li>
	        </ul>
	    </div>
	</div>
	<!--content end-->
	
	<!-- template -->
	<ul id="cmtItemTemplate" class="none"><li></li></ul>
	
	<!-- hiden data -->
	<div class="none">
		<input id="db_msg_type_hidden_field" type="hidden" value=""/>
		<input id="db_msg_reminder_full_hidden_field" type="hidden" value="0"/>
		<input id="db_msg_private_full_hidden_field" type="hidden" value="0"/>
		<input id="db_reminder_noti_hidden_field" type="hidden" value="1"/>
		<input id="db_private_noti_hidden_field" type="hidden" value="1"/>
	</div>
	
	<!-- popup -->
	<s:include value="../component/add_place.jsp"></s:include>
	<s:include value="../component/send_private_msg.jsp"></s:include>
	<s:include value="../component/pop_name_card.jsp"></s:include>
	
	<!-- footer -->
	<s:include value="../common/foot.jsp"></s:include>
</div>
	<s:include value="../common/scripts.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=bocai.core-0.1.0.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js"></script>
<script type="text/javascript" src="${staticContext}/js/upload.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${staticContext}/js/swfobject.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.form-defaults.js"></script>
<script type="text/javascript" src="${staticContext}/js/jQueryRotate.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		function getMyMsg(at){
			var url, tname;
			var type = $('#db_msg_type_hidden_field').val();
			var full = $('#db_msg_' + type + '_full_hidden_field').val();
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return;
	   	   	}
			if(isNaN(full) && full != 1){
				full = 0;
			}
			if(type=='reminder'){
				url = contextPath + '/user/ajax_list_reminder.bc?at=' + at + '&full=' + full; 
				tname = 'dashboard_msg_reminder_list';
			}else if(type=="private"){
				url = contextPath + '/user/ajax_list_private_msg.bc?at=' + at + '&full=' + full; 
				tname = 'dashboard_msg_private_letter_list';
			}else{
				return;
			}
			
			$('#db_msg_reminder_out').hide();
			$('#db_msg_private_out').hide();
			$('#db_msg_private_detail_out').hide();
			$('#db_msg_' + type +"_out").show();
			//var noti = $('#db_' + type + '_noti_hidden_field').val();
			var noti = 1;
			if(noti==1){
				$('#db_msg_' + type + '_loading').show();
				$('#db_msg_'+ type + '_tip').hide();
				$('#db_msg_'+ type).html('');
				$.get(url, function(response){
					if(!response.isError){
						$('#'+ type + 'UnreadNum').html(response.unreadCount);
						BC.Template.T({
							name: tname,
		   			   	    domId: '#db_msg_' + type,
		   			   	    hintId: '#db_msg_' + type + '_loading',
		   			      	data: response.pager,
		   			      	setting: {
		   			      		filter_data: false
		   			      	},
		   			      	params: {
		   			      		type: type
		   			      	},
		   			      	callback: function(){
			   			      	if(!response.pager.list ||
										response.pager.list.length==0){
									$('#db_msg_'+ type + '_tip').show();
								}
			   			     	updatePager(response.pager.pageNo, response.pager.totalPage, type);
				   			    if(type=='reminder'){
				   			    	$('#db_msg_reminder > li.new').animate({
						   				backgroundColor: '#F7F6F1'
						   			}, 3000);
				   			    }
		   			      	}
						});
					}else{
						//$('#db_following')
					}
			    }).complete(function(){
			    	$('#' + type + '_loading').hide();
			    });
			}
		}
		
		function setMsg(at, type){
			$('#db_msg_type_hidden_field').val(type);
			$('#db_msg_tabs > li').removeClass('on');
			$('#' + type + '_tab').addClass('on');
			getMyMsg(at);
		}
		
		function bindingPage(){
			$('#reminder_tab a').click(function(){
				if($(this).parent().hasClass('on')) return false;
				setMsg(0, 'reminder');
			});
			$('#private_tab a').click(function(){
				if($(this).parent().hasClass('on')) return false;
				setMsg(0, 'private');
			});
			$('#db_msg_reminder_op a.full').click(function(){
				$('#db_msg_reminder_op a.unread').removeClass('selected');
				$('#db_msg_reminder_op a.full').addClass('selected');
				$('#db_msg_reminder_full_hidden_field').val('1');
				getMyMsg(0);
			});
			$('#db_msg_reminder_op a.unread').click(function(){
				$('#db_msg_reminder_op a.unread').addClass('selected');
				$('#db_msg_reminder_op a.full').removeClass('selected');
				$('#db_msg_reminder_full_hidden_field').val('0');
				getMyMsg(0);
			});
			$('#db_msg_private_op a.full').click(function(){
				$('#db_msg_private_op a.full').addClass('selected');
				$('#db_msg_private_op a.unread').removeClass('selected');
				$('#db_msg_private_full_hidden_field').val('1');
				getMyMsg(0);
			});
			$('#db_msg_private_op a.unread').click(function(){
				$('#db_msg_private_op a.unread').addClass('selected');
				$('#db_msg_private_op a.full').removeClass('selected');
				$('#db_msg_private_full_hidden_field').val('0');
				getMyMsg(0);
			});
		}
		
		function updatePager(at, total, type){
			BC.WG.pager.pageClkFunc = function(pageNo){
				getMyMsg(pageNo);
			};
			BC.WG.pager.pagination("divpagingup_" + type,at,total); 
		}
		
		bindingPage();
		setMsg(0, 'reminder');
	});
	function getInitCenter(){
		return  new google.maps.LatLng(33.5044087794152, 
				118.270087125);
	};
	
	function getInitZoom(){
		return 4;
	};
	
	function getInitCity(){
		if(!userCity){
			return '全国';
		}else{
			return userCity;
		}
	};
</script>
</body>
</html>