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
	    		<span id="page_title" class="tit">我的波菜/评论我的</span>
	          	<span class="lk"><a href="${pageContext.request.contextPath}/main.bc">去地图上看看&gt;&gt;</a></span>
	        </div>
	    	
	        <ul class="c_main">
	        	<li class="m_li">
	        	 	<s:include value="../component/upload_spot.jsp"></s:include>
	       		</li>
	       		<li class="m_li pr">
	       			<ul id="cmt_loading" class="p15 none bbox mb15" >
						<li class="cdesc tc"><img class="mr10" src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
					</ul>
					<ul id="cmt_tip" class="p15 none pt30 pb30 bbox" >
						<li class="ctitle">还没有人评论你的美食！多
							<a href="#" class="hlink fb">分享美食</a>，就会有更多的机会让别人对你的分享作评论！</li>
					</ul>
	        	 	<ul id="db_cmt_me" class="bbox wr-wb"></ul>
	       		</li>
	       		<li class="m_li clean">
       				<div id="divpagingup" class="mt5 fr"></div>
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
						<s:param name="opt">comment_me</s:param>
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
	</div>
	
	<!-- popup -->
	<s:include value="../component/add_place.jsp"></s:include>
	<s:include value="../component/pop_name_card.jsp"></s:include>
	<s:include value="../component/send_private_msg.jsp"></s:include>
		
	<s:include value="../common/scripts.jsp" ></s:include>
	<!-- footer -->
	<s:include value="../common/foot.jsp"></s:include>
</div>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=bocai.core-0.1.0.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js"></script>
<script type="text/javascript" src="${staticContext}/js/upload.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${staticContext}/js/swfobject.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.form-defaults.js"></script>
<script type="text/javascript" src="${staticContext}/js/jQueryRotate.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		function getCmtMe(at){
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return;
	   	   	}
			var url = contextPath + '/spot/ajax_list_cmt_on_people.bc?uid='+ loginId + '&at=' + at; 
			$('#cmt_loading').show();
			$('#cmt_tip').hide();
			$.get(url, function(response){
				if(!response.isError){
					BC.Template.T({
						name: 'dashboard_cmt_list',
	   			   	    domId: '#db_cmt_me',
	   			   	    hintId: '#cmt_loading',
	   			      	data: response.pager,
	   			        setting: {
   			    	    	filter_data: false
   			      		},
	   			      	callback: function(){
	   			      		updatePager(response.pager.pageNo, response.pager.totalPage);
		   			      	if(!response.pager.list ||
									response.pager.list.length==0){
								$('#cmt_tip').show();
							}
	   			      	}
					});
				}else{
					//$('#db_following')
				}
		    }).complete(function(){
		    	$('#cmt_loading').hide();
		    });
		}
		
		function updatePager(at, total){
			BC.WG.pager.pageClkFunc = function(pageNo){
				getCmtMe(pageNo);
			};
			BC.WG.pager.pagination("divpagingup",at,total); 
		}
		
		getCmtMe(0);
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