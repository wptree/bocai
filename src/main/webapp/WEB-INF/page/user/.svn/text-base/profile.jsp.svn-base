<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>     
<title>${request.user.name} 波客主页   分享健康美食  旅游美食引擎  波菜网  </title>
<title>${request.user.name}个人主页 波菜网 旅游美食 美食指南 美食分享 </title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=person.css,userpanel.css,component_agg_spot.css,comment.css,pod.css,detail_agg_spot.css,contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="../common/header.jsp" ></s:include>
<!--person content start-->
<div class="person_contentout">
    <div class="person_content clean">
    	<div class="c_wel ffyh lh30 clean">
           	<span id="page_title" class="tit">
           		<s:if test="#request.user.id == #session.sessionLoginUser.id">我<span id="title_cat">发现</span></s:if>
           		<s:else>${request.user.name}<span id="title_cat">发现</span></s:else>
           	</span>
           	<span class="opn">
            	<a id="spotLink" class="selected" href="javascript:void(0);" onclick="getSpotPage(0);">发现</a>
				<span class="divider">·</span>
            	<a id="nomLink" href="javascript:void(0);" onclick="getNomedPage(0);">喜欢</a>
            	<span class="divider">·</span>
            	<a id="wantLink" href="javascript:void(0);" onclick="getWantedPage(0);">想吃</a>
            	<span class="divider">·</span>
            	<a id="commentLink" href="javascript:void(0);" onclick="getCommentPage(0);">评论</a>
            </span>
            <div id="loading" class="l p5 mt3 none"><img src="${staticContext}/images/loading.gif"/></div>
          	<span class="lk"><a href="${pageContext.request.contextPath}/main.bc">去地图上看看&gt;&gt;</a></span>
        </div>
    	
        <ul class="c_main">
        	<li class="m_li">
        	 	<ul id="spotlist" class="wr-wb"></ul>
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
	            	<s:param name="id"><s:property value="#request.user.id"/></s:param>
	        	</s:action>
	        </li>
	        <li class="s_li">
	             <s:action name="list_following_boke" namespace="/user" executeResult="true">
	             	<s:param name="id"><s:property value="#request.user.id"/></s:param>
	             </s:action>      
            </li>
            <li class="s_li">
	             <s:action name="list_fans_of_boke" namespace="/user" executeResult="true">
	             	<s:param name="id"><s:property value="#request.user.id"/></s:param>
	             </s:action>      
            </li>
        </ul>
    </div>
</div>
<ul id="cmtItemTemplate" class="none"><li></li></ul>
<s:include value="../component/sign_in.jsp"></s:include>
<!-- import common scripts -->
<s:include value="../common/scripts.jsp" ></s:include>
<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=gmap3.js,bocai.core-0.1.0.js,operation.js,Dom.js,Template.js,jquery.form-defaults.js,pager.js,popNameCard.js,jquery.ellipsis.js"></script>

<s:include value="../component/pop_name_card.jsp"></s:include>
<s:include value="../component/send_private_msg.jsp"></s:include>
<!--person content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
	headtab(3);
	function getSpotPage(pageAt){
		$("#spotLink").addClass("selected");
		$("#nomLink").removeClass("selected");
		$("#wantLink").removeClass("selected");
		$("#commentLink").removeClass("selected");
		$("#title_cat").html("发现");
		
		getDataPage("spot", pageAt);
	}
	function getWantedPage(pageAt){
		$("#spotLink").removeClass("selected");
		$("#nomLink").removeClass("selected");
		$("#wantLink").addClass("selected");
		$("#commentLink").removeClass("selected");
		$("#title_cat").html("想吃");
		
		getDataPage("want", pageAt);
	}
	function getNomedPage(pageAt){
		$("#spotLink").removeClass("selected");
		$("#nomLink").addClass("selected");
		$("#wantLink").removeClass("selected");
		$("#commentLink").removeClass("selected");
		$("#title_cat").html("喜欢");
		
		getDataPage("nom", pageAt);
	}
	
	function getCommentPage(pageAt){
		$("#spotLink").removeClass("selected");
		$("#nomLink").removeClass("selected");
		$("#wantLink").removeClass("selected");
		$("#commentLink").addClass("selected");
		$("#title_cat").html("评论");
		
		getDataPage("comment", pageAt);
	}
	
	function updatePager(at, total, type){
		BC.WG.pager.pageClkFunc = function(pageNo){
			getDataPage(type, pageNo);
		};
		BC.WG.pager.pagination("divpagingup",at,total); 
	}
	
	function getDataPage(pageType, pageAt, functionName){
		$.ajax({url: contextPath+"/user/profile!spotPage.bc",
			  type: "POST",
			  dataType: "json",
			  data:{"id":"${request.user.id}","pageAt":pageAt, "pageType":pageType},
			  beforeSend: function(){
				  $('#loading').show();
			  },
			  success: function(response){
				   var domId = '#spotlist';
				   var templateName = "profile_spot_list";
				   if($(domId).hasTemplate()){
					   $(domId).removeTemplate();
				   }
				   if(pageType == "comment"){
					   templateName = "profile_comment_list";
					   $(domId).addClass("bbox");
				   }else{
					   $(domId).removeClass("bbox");
				   }
				   var successHandler = function(obj){
 					   if(!$(domId).hasTemplate()){
 						   $(domId).setTemplate(BC.Template.cache[templateName], null, {filter_data: false});
 						   $(domId).setParam('context', contextPath);
						   $(domId).setParam('imageContext', imageContext);
						   $(domId).setParam('loginId', $('#const_loginId').text());
						   $(domId).setParam('sessionUserId', response.sessionUserId);
						   $(domId).setParam('type', 'normal');
 					   }
  					   $(domId).processTemplate(response.dataPage);
  					   updatePager(response.dataPage.pageNo, response.dataPage.totalPage, pageType);
  					   $(domId + " span[id*=timeAgo]").each(function(){
  						  $(this).html($.timeago($(this).html()));
  					   });
  					   $('.spot-view').mouseenter(function(){
  						   var spotId = $(this).attr('spotId');
  					       OP.expandPRMap('normal', spotId);
  					   });
  					   $('.spot-view').mouseleave(function(){
						   var spotId = $(this).attr('spotId');
					       OP.collapPRMap('normal', spotId);
					   });
 				   };
 				  
 				   if(!BC.Template.cache[templateName]){
 					  BC.Template.load(templateName, successHandler, null);
				   }else{
					  successHandler(null);
				   }
			  },
			  complete: function(){
				  $('#loading').hide();
			  }
		});
	}
	
	function registerForm(spotId){
	   $("#comment_form_review_" + spotId).each(function(){
		   var thisform = $(this);
		   $(this).ajaxForm({
    		   dataType : "json",
   			   beforeSubmit : function (arr, form, options){
   				   var content = thisform.find('textarea').val();
   				   if(!spotId || !content || $.trim(content).length == 0){
   					   return false;
   				   }
   			   },
   			   success : function (response, statusText, xhr, $form){
   				   if(!response.isError && response.vo){
   					   //var id = $form.attr('id');
     				   //var pos = id.lastIndexOf('_'); 
     				   //var spotId = id.substring(pos+1, id.length);
     				   BC.Template.T({
     					  name: 'profile_comment_item',
   	    			   	  domId: '#cmtItemTemplate',
   	    			      data: response.vo,
   	    			      setting: {
	    			    	  filter_data: false
	    			      },
	    			      params: {
   	    			    	  spotId: spotId
   	    			      },
   	    			      callback: function(){
   	    			    	  $('#comments_' + spotId).prepend($('#cmtItemTemplate').html());
   	    			    	  $('#comments_' + spotId + " > li:first-child").slideDown();
   	    			    	  var cmtNum = parseFloat($('#comment_num_' + spotId).html());
   	    			    	  if(!isNaN(cmtNum)){
   	    			    		 cmtNum += 1;
   	    			    		 $('#comment_num_' + spotId).html(cmtNum);
   	    			    	  }
   	    			    	  $('#comment_item_empty_'+spotId).replaceWith('');
   	    			    	  thisform.find('textarea').val('');
   	    			    	  thisform.find('textarea').blur();
   	    			      }
     				   });
   				   }
   			   }
   		   });
		   
	   });
    }
	
	$(document).ready(function(){
		//$("#spotlist").setTemplateElement("spot_template");
		//$("#paginator").setTemplateElement("paginator_template");
		getSpotPage(0);
				
		$(".ellipsis").each(function(){
			$(this).ellipsis(false,false);
		});
	});
</script>
</body>
</html>
