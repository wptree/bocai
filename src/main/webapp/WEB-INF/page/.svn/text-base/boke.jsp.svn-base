<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>波客排行 波菜网 旅游美食 美食指南 美食分享 </title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=people.css,search.css,component_boke_list.css,pod.css,contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="common/header.jsp"></s:include>
<!--people content start-->
<div class="people_contentout">
    <div class="people_content">
    	<div class="c_wel ffyh lh30 clean">
            <span id="page_title" class="tit">波客排行</span>
            <span class="opn">
            	<a title="查看最活跃的波客列表" id="bestUserLink" href="javascript:void(0);">最活跃</a>
				<span class="divider">·</span>
            	<a title="查看最新加入的波客列表" id="latestUserLink" href="javascript:void(0);">最新加入</a>
            </span>
            <div id="loading" class="l p5 mt3 none"><img src="${staticContext}/images/loading.gif"/></div>
            <span class="lk"><a title="去首页查看地图" href="${pageContext.request.contextPath}/main.bc">去地图上看看&gt;&gt;</a></span>
        </div>
        <ul class="c_main">
	        <li class="m_li">
	          	<ul id="bokes" class="list bbox">
	          		<li id="boke_error" class="error" style="display:none;"></li>
	          	</ul>
	        </li>
	        <li class="m_li clean">
	        	<div id="divpagingup" class="mt5 fr"></div>
	        </li>
	    </ul>
        <ul class="c_side">
        	<li class="s_li">
             <s:include value="component/search.jsp"></s:include>
            </li>
            <s:if test="#session.sessionLoginUser!=null">
            <li class="s_li">
	             <s:action name="list_following_boke" namespace="/user" executeResult="true">
	             	<s:param name="id"><s:property value="#session.sessionLoginUser.id"/></s:param>
	             </s:action>      
            </li>
            <li class="s_li">
	             <s:action name="list_fans_of_boke" namespace="/user" executeResult="true">
	             	<s:param name="id"><s:property value="#session.sessionLoginUser.id"/></s:param>
	             </s:action>      
            </li>
            </s:if>
            <li class="s_li">
                 <s:include value="component/pod_boke_ranking.jsp"></s:include>
            </li>
      	</ul>
    </div>
</div>
<s:include value="component/pop_name_card.jsp"></s:include>
<s:include value="component/send_private_msg.jsp"></s:include>
<s:include value="component/sign_in.jsp"></s:include>

<!--people content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<s:include value="common/scripts.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=operation.js,Dom.js,pager.js,popNameCard.js,Template.js,jquery.form-defaults.js"></script>
<script type="text/javascript">
	headtab(3);
	$(document).ready(function(){
		function getUsers(type, pageAt){
			var url, params;
			if(type == "best"){
				url = "${pageContext.request.contextPath}/boke!bestUser.bc";
				params = {"pageAt":pageAt};
				$("#bestUserLink").addClass("selected");
				$("#latestUserLink").removeClass("selected");
				$('#search_input').val('');
				$('#search_input').blur();
				$('#page_title').html('波客排行');
			}else if(type == "latest"){
				url = "${pageContext.request.contextPath}/boke!latestUser.bc";
				params = {"pageAt":pageAt};
				$("#bestUserLink").removeClass("selected");
				$("#latestUserLink").addClass("selected");
				$('#search_input').val("");
				$('#search_input').blur();
				$('#page_title').html('波客排行');
			}else{
				return;
			}
			$.ajax({url: url,
				  type: "POST",
				  dataType: "json",
				  data: params,
				  beforeSend: function(){
					  $('#loading').show();
				  },
				  success: function(response){
					  if(response.isError){
						  OP.showBokeListError("服务器出小差了，请稍后重试");
						  return;
					  }
					  BC.Template.T({
						name: 'boke_list',
	   			   	    domId: '#bokes',
	   			      	data: response.dataPage,
	   			     	params: { followship : response.followShip},
	   			      	callback: function(){
						    if(response.dataPage.list.length==0){
								OP.showBokeListError("对不起，没有找到匹配的波客");
						    }
	   			      		OP.updateBokeListPager(response.dataPage.pageNo, 
	   			      				response.dataPage.totalPage, "search");
	   			         	for(var i=0; i<response.dataPage.list.length;i++){
	 						   var user = response.dataPage.list[i];
	 						   showLastSpot(user.id);
	 					   }
	   			      	}
					 });
				  },
				  complete: function(){
					  $('#loading').hide();
				  }
			});
		}
		
		$('#bestUserLink').click(function(){
			getUsers('best', 0);
		});
		$('#latestUserLink').click(function(){
			getUsers('latest', 0);
		});
		
		function showLastSpot(userId){
			$.ajax({url: "boke!userLastSpot.bc",
				  type: "POST",
				  dataType: "json",
				  data:{'id':userId},
				  success: function(jsonObj){
					 var lastSpot = $("<div></div>");
					 if(jsonObj.hasSpot){
						 lastSpot.append("<span>最新分享了</span>");
						 lastSpot.append('<span>&nbsp;<a href="${pageContext.request.contextPath}/spot/detail_spot.bc?id='+jsonObj.lastSpotId+'" style="color: #B8B8B8!important;">'+jsonObj.lastSpotStr+'</a>:</span>');
						 lastSpot.append('<span style="line-height: 18px">'+jsonObj.lastSpotDesc+'</span>');
						 lastSpot.append('<span class="f_brackets">('+jsonObj.lastSpotTimeStr+')</span>');
					 }else{
						 lastSpot.html("还没开始分享呢");
					 }
					 $("#boke_latest_spot_"+userId).append(lastSpot);
				  }
			});
			
		}

		// attach the template
		//$("#userlist").setTemplateElement("template");
		 
		var keyword = "${request.keyword}";
		if(keyword){
			$('#search_label').hide();
			$('#search_input').val(keyword);
			$('#boke_search_form').submit();
		}else if("${request.active}"=="0"){
			getUsers('best', 0);
		}else if("${request.active}"=="1"){
			getUsers('latest', 0);
		};
	});
</script>
</body>
</html>
