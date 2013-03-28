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
<title>${request.user.name} 波客主页   分享健康美食  旅游美食引擎  波菜网  </title>
<title>${request.user.name}个人主页 波菜网 旅游美食 美食指南 美食分享 </title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/css/person.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/userpanel.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/detail_agg_spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/component_agg_spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/comment.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/pod.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/component_boke_list.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="../common/header.jsp" ></s:include>
<!--person content start-->
<div class="person_contentout">
    <div class="person_content clean">
    	<div class="c_wel ffyh lh30 clean">
           	<span id="page_title" class="tit">
           		<s:if test="#request.user.id == #session.sessionLoginUser.id">我<span id="title_cat">的关注</span></s:if>
           		<s:else>${request.user.name}<span id="title_cat">的关注</span></s:else>
           	</span>
           	<span class="opn">
            	<a id="followingLink" class="selected" href="javascript:void(0);" onclick="getFollowingBokePage(0);">关注</a>
				<span class="divider">·</span>
            	<a id="followerLink" href="javascript:void(0);" onclick="getFollowerPage(0);">粉丝</a>
            </span>
            <div id="loading" class="l p5 mt3 none"><img src="${staticContext}/images/loading.gif"/></div>
          	<span class="lk"><a href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="user.id"/>">去
          		<s:if test="#request.user.id == #session.sessionLoginUser.id">我</s:if>
          		<s:else>${request.user.name}</s:else>
          		的主页看看&gt;&gt;</a></span>
        </div>
    	
        <ul class="c_main">
        	<li class="m_li">
        	 	<ul id="main_list" class="bbox"></ul>
       		</li>
       		<li class="m_li clean">
       			<div id="divpagingup" class="mt5 fr"></div>
       		</li>
        </ul>
        
        <ul class="c_side">
        	<li class="s_li top">
        		<bc:followTag targetType="user" targetId="${request.user.id}" followText="+关注" unFollowText="取消关注"/>
        	</li>
        	<li class="s_li clean">
        		<div class="bgc8 br5 l tc w105 pl15 pr15 pt10 pb10">
                    <p class="ccount f30 fb tsd1">${request.user.totalSpotCount}</p>
                    <p class="mt3 c555">分享</p>
                </div>
                <div class="bgc8 br5 r tc w105 pl15 pr15 pt10 pb10">
                    <p class="ccount f30 fb tsd1">${request.user.score}</p>
                    <p class="mt3 c555">波币</p>
                </div>
        	</li>
        	<li class="s_li">
        		<s:action name="user_panel" namespace="/user" executeResult="true">
	            	<s:param name="id"><s:property value="user.id"/></s:param>
	        	</s:action>
	        </li>
        </ul>
    </div>
</div>
<ul id="cmtItemTemplate" class="none"><li></li></ul>
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
<script type="text/javascript" src="${staticContext}/js/gmap3.js"></script>
<script type="text/javascript" src="${staticContext}/js/operation.js"></script>
<script type="text/javascript" src="${staticContext}/js/bocai.core-0.1.0.js"></script>
<script type="text/javascript" src="${staticContext}/js/Template.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.ellipsis.js"></script>
<script type="text/javascript" src="${staticContext}/js/Dom.js"></script>
<script type="text/javascript" src="${staticContext}/js/pager.js"></script>
<script type="text/javascript" src="${staticContext}/js/popNameCard.js"></script>
<s:include value="../component/pop_name_card.jsp"></s:include>
<!--person content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
	headtab(3);
	var uid = <s:property value="user.id"/>;
	var ptype = '<s:property value="type"/>';
	function getFollowerPage(pageAt){
		$("#followerLink").addClass("selected");
		$("#followingLink").removeClass("selected");
		$("#title_cat").html("的粉丝");
		
		getDataPage("follower", pageAt);
	}
	function getFollowingBokePage(pageAt){
		$("#followingLink").addClass("selected");
		$("#followerLink").removeClass("selected");
		$("#title_cat").html("的关注");
		
		getDataPage("following", pageAt);
	}
	
	function updatePager(at, total, type){
		BC.WG.pager.pageClkFunc = function(pageNo){
			getDataPage(type, pageNo);
		};
		BC.WG.pager.pagination("divpagingup",at,total); 
	}
	
	function getDataPage(type, at){
		var url
			,tname='boke_list';
		if(type=="following"){
			url = contextPath+'/user/ajax_list_following_boke.bc?id=' + uid + '&at=' + at;
		}else{
			url = contextPath+'/user/ajax_list_fans_of_boke.bc?id=' + uid + '&at=' + at;
		}
		$.ajax({
			url: url,
			type: "GET",
			dataType: "json",
			beforeSend: function(){
				$('#loading').show();
			},
			success: function(response){
				if(!response.isError){
					var pager;
					if(type == 'following'){
						pager = response.followingPager;
					}else{
						pager = response.followerPager;
					}
					BC.Template.T({
						name: tname,
	   			   	    domId: '#main_list',
	   			   	    hintId: '#loading',
	   			      	data: pager,
	   			      	params: {
	   			      		followship: response.followShip
	   			      	},
	   			      	callback: function(){
		   			      	if(!pager.list ||
		   			      		pager.list.length==0){
								//$('#db_msg_'+ type + '_tip').show();
							}
		   			     	updatePager(pager.pageNo, pager.totalPage, type);
	   			      	}
					});
				}
		  },
	      complete: function(){
			  $('#loading').hide();
		  }
		});
	}
	
	$(document).ready(function(){
		if(ptype=='follower'){
			getFollowerPage(0);
		}else{
			getFollowingBokePage(0);
		}
		$(".ellipsis").each(function(){
			$(this).ellipsis(false,false);
		});
	});
</script>
</body>
</html>