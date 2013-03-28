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
<link href="${staticContext}/min/b=bocai/css&amp;f=token-input-upload.css,upload.css,citypicker.css,pod.css,mydashboard.css,upload.spot.css,uploadify.css,contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bc_body">
	<!-- header -->
	<s:include value="../common/header.jsp" ></s:include>
	<!--content start-->
	<div class="bc_contentout">
	    <div class="bc_content clean">
	    	<div class="c_wel ffyh lh30 clean">
	    		<span id="page_title" class="tit">我的波菜/我的关注</span>
	          	<span class="lk"><a href="${pageContext.request.contextPath}/main.bc">去地图上看看&gt;&gt;</a></span>
	        </div>
	    	
	        <ul class="c_main">
	        	<li class="m_li">
	        	 	<s:include value="../component/upload_spot.jsp"></s:include>
	       		</li>
	       		<li class="m_li pr">
	       			<ul id="db_following_tabs" class="db-tabs w301 bbox-tabs clean">
	        	 		<li id="people_tab" class="l w150 bgc1 btlr5 borb1 borr1 clean on">
	        	 			<a class="l disB p15 lh150" href="javascript:void(0);"><span class="ffyh f14 fb">好友</span><br/><span class="f_brackets">关注 (<s:property value="followPeopleCount" />)</span></a>
	        	 		</li>
	        	 		<li id="place_tab" class="l w150 bgc1 btrr5 borb1 clean">
	        	 			<a class="l disB p15 lh150" href="javascript:void(0);"><span class="ffyh f14 fb">餐厅</span><br/><span class="f_brackets">关注 (<s:property value="followPlaceCount" />)</span></a>
	        	 		</li>
	        	 	</ul>
	        	 	<div id="db_following_people_out" class="db-list bbox none">
	        	 		<ul id="db_following_people_loading" class="p15 none borb1" >
							<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
						</ul>
						<ul id="db_following_people_tip" class="p15 none pt30 pb30 borb1" >
							<li class="ctitle">没有发现美食！快来
								<a href="#" class="hlink fb">关注好友</a>
								吧，系统会自己推送好友发布的美食信息给你！</li>
						</ul>
	        	 		<ul id="db_following_people"></ul>
	        	 		<ul class="p15 bort1 clean">
       						<li id="divpagingup_people" class="mt5 fr"></li>
       					</ul>
	        	 	</div>
	        	 	<div id="db_following_place_out" class="db-list bbox none">
	        	 		<ul id="db_following_place_loading" class="p15 none borb1" >
							<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
						</ul>
						<ul id="db_following_place_tip" class="p15 none pt30 pb30 borb1" >
							<li class="ctitle">没有发现美食！快来
								<a href="#" class="hlink fb">关注餐厅</a>
								吧，系统会自己推送和该餐厅有关的美食信息给你！</li>
						</ul>
	        	 		<ul id="db_following_place"></ul>
	        	 		<ul class="p15 bort1 clean">
       						<li id="divpagingup_place" class="mt5 fr"></li>
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
						<s:param name="opt">my_following</s:param>
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
	             <li class="s_li">
                	<s:include value="../component/invitation.jsp">
						<s:param name="id"><s:property value="user.id"/></s:param>
					</s:include>
            	</li>
	        </ul>
	    </div>
	</div>
	<!--content end-->
	
	<!-- template -->
	<ul id="cmtItemTemplate" class="none"><li></li></ul>
	
	<!-- hiden data -->
	<div class="none">
		<input id="db_following_type_hidden_field" type="hidden" value="0"/>
		<input id="db_people_noti_hidden_field" type="hidden" value="1"/>
		<input id="db_place_noti_hidden_field" type="hidden" value="1"/>
	</div>
	
	<!-- popup -->
	<s:include value="../component/add_place.jsp"></s:include>
	<s:include value="../component/pop_name_card.jsp"></s:include>
	<s:include value="../component/send_private_msg.jsp"></s:include>
	<!-- footer -->
	<s:include value="../common/foot.jsp"></s:include>
</div>

	<s:include value="../common/scripts.jsp" ></s:include>
	<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=gmap3.js,bocai.core-0.1.0.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js,upload.js,jquery.uploadify.v2.1.4.js,swfobject.js,jquery.form-defaults.js,jQueryRotate.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		function getSpotsByFollowing(at){
			var url;
			var type = $('#db_following_type_hidden_field').val();
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return;
	   	   	}
			if(type=='people'){
				url = contextPath + '/spot/ajax_list_spot_on_following_people.bc?uid='+ loginId + '&at=' + at; 
			}else if(type=="place"){
				url = contextPath + '/spot/ajax_list_spot_on_following_place.bc?uid='+ loginId + '&at=' + at; 
			}else{
				return;
			}
			
			$('#db_following_people_out').hide();
			$('#db_following_place_out').hide();
			$('#db_following_' + type +"_out").show();
			var noti = 1;
			if(noti==1){
				$('#db_following_' + type + '_loading').show();
				$('#db_following_'+ type + '_tip').hide();
				$.get(url, function(response){
					if(!response.isError){
						BC.Template.T({
							name: 'dashboard_feed_list',
		   			   	    domId: '#db_following_' + type,
		   			   	    hintId: '#db_following_' + type + '_loading',
		   			      	data: response.pager,
		   			     	setting: {
		   			     		filter_data: false
		   			     	},
		   			      	params: {
		   			      		type: type
		   			      	},
		   			      	callback: function(){
		   			      		$('#db_' + type + '_noti_hidden_field').val(0);
			   			      	if(!response.pager.list ||
										response.pager.list.length==0){
									$('#db_following_'+ type + '_tip').show();
								}
			   			     	updatePager(response.pager.pageNo, response.pager.totalPage, type);
			   			     	$('.spot-view').mouseenter(function(){
		  						    var spotId = $(this).attr('spotId');
		  					        OP.expandDBMap(type, spotId);
		  					    });
		  					    $('.spot-view').mouseleave(function(){
								    var spotId = $(this).attr('spotId');
							        OP.collapDBMap(type, spotId);
							    });
		   			      	}
						});
					}else{
						//$('#db_following')
					}
			    }).complete(function(){
			    	$('#db_following_' + type + '_loading').hide();
			    });
			}
		}
		
		function setFollow(at, type){
			$('#db_following_type_hidden_field').val(type);
			$('#db_following_tabs > li').removeClass('on');
			$('#' + type + '_tab').addClass('on');
			getSpotsByFollowing(at);
		}
		
		function bindingPage(){
			$('#people_tab a').click(function(){
				if($(this).parent().hasClass('on')) return false;
				setFollow(0, 'people');
			});
			$('#place_tab a').click(function(){
				if($(this).parent().hasClass('on')) return false;
				setFollow(0, 'place');
			});
		}
		
		function updatePager(at, total, type){
			BC.WG.pager.pageClkFunc = function(pageNo){
				getSpotsByFollowing(pageNo);
			};
			BC.WG.pager.pagination("divpagingup_" + type, at, total); 
		}
		
		bindingPage();
		setFollow(0, 'people');
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