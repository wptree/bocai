<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bc" uri="bocai-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${request.place.fullName} 波菜网 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=place.css,panel_spot.css,tile.css,component_agg_spot.css,pod.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="../common/header.jsp"></s:include>
<!--place content start-->
<div class="place_contentout">
    <div class="place_content clearfix">
    	<div class="c_wel clean">
        	<span class="tit">${request.place.fullName}</span>
            <span class="addr">
				<span class="locality">${request.place.location.city}</span>,<span class="street-address">${request.place.location.street}</span></span>
        </div>
        <ul class="c_main">
          	<!--分享最多的美食开始 -->
            <li id="aggSpot_${request.bestAggSpot.id}" class="m_li bbox aggSpotPanel">
				<div id="place-feature" class="clearfix">
					<div class="spotted-by">
						<div class="clearfix">
							<div class="label">分享次数最多的菜肴:</div>
							<c:forEach items="${request.bestAggSpot.spottedBys}" var="user">
                    			<a class="avatar" href="${pageContext.request.contextPath}/user/profile.bc?id=${user.id}"><img alt="${user.name}" src="${user.avatar}" width="32" height="30" /></a>
                    		</c:forEach>
						</div>
					</div>
					<div class="ribbon">
						<p id="left_nom_num_${request.bestAggSpot.id}" class="pp1">${request.bestAggSpot.nomNum}</p>
						<p class="pp2">喜欢</p>
					</div>
					<a class="photo" href="javascript:void(0);">
						<img src="${staticContext}/spot/${request.bestAggSpot.lastSpot.id}/180.${request.bestAggSpot.lastSpot.imgType}" width="200" height="200" />
					</a>
					<h2 class="ffyh">${request.bestAggSpot.dish.name}</h2>
					<a href="javascript:void(0);">${request.bestAggSpot.lastSpot.createdBy.name}</a> <span id="timeAgo">${request.bestAggSpot.lastSpot.createdAt}</span> 发布：
					<div class="note">${request.bestAggSpot.lastSpot.description}</div>
				</div>
				<div id="aggSpotPanel_control_${request.bestAggSpot.id}" class="aggSpotPanel_control">
					<ul class="control-left">
                        <li id="sighting_btn_${request.bestAggSpot.id}" class="operator sighting sighting-off">
                        	<a href="javascript:void(0)" class="heihui" onclick="OP.switchSighting('${request.bestAggSpot.id}')">发现</a>
                        	<span class="spanhui f_brackets">(<span id="spotted_num_${request.bestAggSpot.id}">${request.bestAggSpot.spottedNum}</span>)</span>
                        </li>
                        <li class="operator wantted">
			                <a href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('${request.bestAggSpot.id}')">想吃</a>
			                <span class="spanhui f_brackets">(<span id="want_num_${request.bestAggSpot.id}">${request.bestAggSpot.wantedNum}</span>)</span>
		            	</li>
                        <li class="operator nom">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('${request.bestAggSpot.id}');">喜欢</a>
	                        <span class="spanh ui f_brackets">(<span id="nom_num_${request.bestAggSpot.id}">${request.bestAggSpot.nomNum}</span>)</span>
                        </li>
                        <li class="operator correction">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.correction('${request.bestAggSpot.id}_correction','${request.bestAggSpot.id}');">纠错</a>
	                        <span></span>
                        </li>
                    </ul>
                    <ul class="control-right">
                    	<li><a href="javascript:void(0);" class="heihui" onclick="OP.getBySameDish('${request.bestAggSpot.dish.name}')">同名菜</a></li>
                    </ul>
			    </div>
			    <ul id="aggSpot_loading_${request.bestAggSpot.id}" class="aggSpotPanel_loading" style="display:none">
					<li class="review loading">
						<img src="/bocai/images/cream-medium-load.gif"/>加载中...
					</li>
				</ul>
				<ul id="aggSpot_spot_list_${request.bestAggSpot.id}" class="spots" style="display: none"></ul>
			</li>
            <!--分享最多的美食结束 -->
            
            <!--更多美食 -->
            <li id="more_dish_out"  class="m_li tile mt20">
                <div class="mt10 clean">
                    <div class="l f16 fb ctitle ffyh">更多美食 @<span>${request.place.fullName}</span></div>
                    <div id="dish_loading" class="l ml10 none" style="display: none;"><img src="${staticContext}/images/loading.gif" /></div>
                </div>
                <ul id="more_agg_spot" class="mt20 clean">
                </ul>
                <div class="clean">
                	<div id="divpagingup" class="mt5 fl"></div>
                </div>
            </li>
            <!--更多美食结束 -->
        </ul>
        <ul class="c_side">
			 <li class="s_li top">
	            <bc:followTag targetId="${request.place.id}" targetType="place" followText="+ 关  注" unFollowText="取消关注"/>
	         </li>
            <li class="s_li">
                <s:include value="../component/pod_side_place.jsp">
					<s:param name="place"><s:property value="place" /></s:param>
				</s:include>
            </li>
            
            <s:if test="#session.sessionLoginUser!=null">
            <li class="s_li">
                <s:action name="list_following_fans_of_place" namespace="/user" executeResult="true">
                	<s:param name="id"><s:property value="place.id" /></s:param>
                </s:action>
            </li>
            </s:if>

            <li class="s_li">
                <s:action name="list_fans_of_place" namespace="/user" executeResult="true">
                	<s:param name="id"><s:property value="place.id" /></s:param>
                </s:action>
            </li>
            
            <li class="s_li">
                <s:action name="list_active_boke" namespace="/user" executeResult="true">
                	<s:param name="id"><s:property value="place.id" /></s:param>
                </s:action>
            </li>

        </ul>
    </div>
</div>
<s:include value="../component/view_big_map.jsp">
	<s:param name="place"><s:property value="place" /></s:param>
</s:include>
<s:include value="../component/pop_name_card.jsp"></s:include>
<s:include value="../component/send_private_msg.jsp"></s:include>
<s:include value="../component/sign_in.jsp"></s:include>

<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
<s:include value="../common/scripts.jsp" ></s:include>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=gmap3.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.form-defaults.js"></script>
<script type="text/javascript">
	function getAggSpotsAtPlace(pageAt){
		$.ajax({url: "${pageContext.request.contextPath}/spot/place!aggSpotPage.bc",
			  type: "POST",
			  dataType: "json",
			  data:{"pageAt":pageAt,"id":"${request.place.id}"},
			  beforeSend: function(jqXHR, settings){
				  $('#dish_loading').show(); 
			  },
			  success: function(response){
				  if(!response.isError && response.dataPage){
					  BC.Template.T({
	 					  name: 'place_more_agg_spot',
	    			   	  domId: '#more_agg_spot',
	    			   	  hintId: '#dish_loading',
	    			      data: response.dataPage,
	    			      callback: function(){
	    			    	  updatePager(response.dataPage.pageNo, response.dataPage.totalPage);
	    			      }
	 				   });
				  }
			  },
			  complete: function(){
				  $('#dish_loading').hide();
			  }
		});
	}
	function updatePager(at, total){
		BC.WG.pager.pageClkFunc = function(pageNo){
			getAggSpotsAtPlace(pageNo);
		};
		BC.WG.pager.pagination("divpagingup",at,total); 
	}
	$(document).ready(function(){
		getAggSpotsAtPlace(0);
	});
</script>
<!--place content end-->
<s:include value="../common/foot.jsp"></s:include>
</div>
</body>
</html>
