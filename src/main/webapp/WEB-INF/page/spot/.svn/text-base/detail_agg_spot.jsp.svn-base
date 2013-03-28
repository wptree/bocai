<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>   
<title>波菜网 分享健康美食 旅游美食引擎 美食列表</title>
<!-- import common css -->
<s:include value="../common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=detail_agg_spot.css,panel_spot.css,component_agg_spot.css,pod.css,contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
	<!-- import header -->
	<s:include value="../common/header.jsp"></s:include>

	<!-- main body start -->
	<div class="contentout">
		<div class="content">
			<div class="c_wel ffyh lh30 clean">
				<span class="tit">
					<a href="javascript:void(0);" onclick="OP.getBySameDish('<s:property value="dish.name"/>');"><s:property value="dish.name"/></a>&nbsp;@
					<a href="${pageContext.request.contextPath}/spot/toPlace.bc?id=<s:property value="place.id"/>"><s:property value="place.fullName"/></a>
				</span>
			</div>
			<ul class="c_main">
				<li id="aggspot" class="m_li bbox">
					<div class="spot-box">
						<div id="photo-elements">
							<div id="photo-spinner" style="display:none">
								<img src="${staticContext}/images/spinner-large.gif"/>加载中...
							</div>
							<div id="photo-container">
								<img id="aggSpotImg" class="photo" src="${imageContext}/<s:property value="%{lastSpot.getSpotImgPath(600)}"/>" width=600 height=600
									alt="<s:property value="dish.name"/>" />
							</div>
						</div>
						<div class="info">
							<a href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="createdBy.id"/>"><img class="avatar" src="<s:property value="createdBy.avatar"/>" width="50" height="50" /></a>
							<div class="person-details">
								<div class="created-at">
									<a href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="createdBy.id"/>"><s:property value="createdBy.name"/></a>&nbsp;&nbsp;3&nbsp;天前发现：
								</div>
								<div class="desc">
									<s:property escape="false" value="description" />
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="aggSpotPanel_control">
						<ul class="control-left">
	                       <li id="sighting_btn_<s:property value="id"/>" class="operator sighting sighting-on">
	                       	<span class="heihui fb ml15">分享</span>
	                       	<span class="spanhui f_brackets">(<span id="spot_num_<s:property value="id"/>"><s:property value="spottedNum"/></span>)</span>
	                       </li>
	                       <li class="operator wantted">
			                <a href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('<s:property value="id"/>')"><span class="cgray">想吃</span></a>
			                <span class="spanhui f_brackets">(<span id="want_num_<s:property value="id"/>"><s:property value="wantedNum"/></span>)</span>
		            	   </li>
	                       <li class="operator nom">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('<s:property value="id"/>');"><span class="cgray">喜欢</span></a>
	                        <span class="spanhui f_brackets">(<span id="nom_num_<s:property value="id"/>"><s:property value="nomNum"/></span>)</span>
	                       </li>
	                       <li class="operator correction">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.correction('<s:property value="id"/>_correction','<s:property value="id"/>');"><span class="cgray">纠错</span></a>
	                        <span></span>
	                       </li>
	                   </ul>
	                   <ul class="control-right">
	                   	   <li><a class="cgray" href="javascript:void(0);" onclick="OP.getBySameDish('<s:property value="dish.name"/>')">同名菜</a></li>
	                   	   <li><span class="spanshenhe">|</span></li>
	                   	   <li><a class="cgray" href="${pageContext.request.contextPath}/spot/toPlace.bc?id=<s:property value="place.id"/>" >同家店</a></li>
	                   </ul>
					</div>
					<ul id="aggSpot_loading_<s:property value="id"/>" class="aggSpotPanel_loading">
						<li class="review loading"><img src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
					</ul>
					<ul id="aggSpot_spot_list_<s:property value="id"/>" class="spots" style="display:none;"></ul>
				</li>
			</ul>
			<ul class="c_side">
				<li class="s_li">
					<s:include value="../component/pod_side_place.jsp">
						<s:param name="place"><s:property value="place" /></s:param>
					</s:include>
				</li>
				<li class="s_li">
					<s:action name="list_favor_boke" namespace="/user" executeResult="true">
                		<s:param name="id"><s:property value="id" /></s:param>
                	</s:action>
				</li>
				<li class="s_li">
					<s:action name="more_at_place" executeResult="true">
                		<s:param name="id"><s:property value="place.id" /></s:param>
                	</s:action>
                </li>
                <li class="s_li">
					<s:include value="../component/share.jsp" >
					    <c:set var="content" scope="request" value='来自@波菜网：分享一道很不错的菜：${dish.name}@${place.fullName}，有机会要去尝尝。'></c:set>
						<c:set var="imgUrl" scope="request" value='${imageContext}/spot/${lastSpot.id}.${lastSpot.imgType}'></c:set>
					</s:include>
                </li>
			</ul>
		</div>
	</div>
	<div id="cmtItemTemplate" class="comment-row" style="display:none;"></div>
	
	<s:include value="../component/view_big_map.jsp">
		<s:param name="place"><s:property value="place" /></s:param>
	</s:include>
	<s:include value="../component/correction.jsp"></s:include>
	<s:include value="../component/pop_name_card.jsp"></s:include>
	<s:include value="../component/send_private_msg.jsp"></s:include>
	<s:include value="../component/sign_in.jsp"></s:include>
	<!-- import footer -->
	<s:include value="../common/foot.jsp"></s:include>
</div>
<s:include value="../common/scripts.jsp" ></s:include>
<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=gmap3.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js,jquery.form-defaults.js"></script>

	<script type="text/javascript">
		$(function() {
			$('form.new-comment').ajaxForm({
				dataType : "json",
				beforeSubmit : function (arr, form, options){
					var spotId = $('input[name="spotId"]').val();
					var content = $('textarea[name="content"]').val();
					if(!spotId || !content || $.trim(content).length == 0){
						return false;
					}
				},
				success : function (response, statusText, xhr, $form){
					if(response && response.vo){
						$('div.comments').append(getCommentHtml(response.vo));
					}
				}
			});
			
			$('a[id^=a_great_count_]').click(function(){
				var id = $(this).attr('id');
				var pos = id.lastIndexOf('_'); 
				var spotId = id.substring(pos+1, id.length);
				var indicator = "#great_count_" + spotId;
				OP.countGreat('${session.sessionLoginUser.id}', spotId, 1, indicator);
			});
			$('a[id^=a_good_count_]').click(function(){
				var id = $(this).attr('id');
				var pos = id.lastIndexOf('_'); 
				var spotId = id.substring(pos+1, id.length);
				var indicator = "#good_count_" + spotId;
				OP.countGreat('${session.sessionLoginUser.id}', spotId, 0, indicator);
			});
			
			function getCommentHtml(vo){
				var html = "<div id='comment_" + vo.id + "' class='comment-container'>\n";
				html += "<div class='user'>";
				html += "<a href='#'>"+ vo.name + "</a>\n";
				html += "<div class='timestamp'>20天前</div>\n";
				html += "</div>";
				html += "<div class='avatar'><img src='" + vo.avatar +"' /></div>\n";
				html += "<div class='comment'>" + vo.content + "</div>\n";
				html += "<div class='clear'></div>\n";
				html += "</div>";
				return html;
			};
			
			function getSpotsOnAgg(at){
				var pageAt = at || 0;
				$.get(contextPath + '/spot/ajax_list_spot_on_agg.bc?pageAt='+pageAt+'&id=<s:property value="id"/>',function(response){
					if(!response.isError){
						BC.Template.T({
							name: 'panel_spot',
		   			   	    domId: '#aggSpot_spot_list_'+<s:property value="id"/>,
		   			      	data: response,
			   			    setting: {
	  	    				   filter_data: false
	  	    			    },
		   			      	params: {
		   			      		'type': 'detail_agg_spot',
		   			      		'currentUser': response.currentUser
		   			      	},
		   			      	callback: function(){
		   			    	  	$('#aggSpot_loading_' + <s:property value="id"/>).hide();
		   			    	    $('#aggSpot_spot_list_' + <s:property value="id"/>).show();
		   			    	    $('.spot .getmore').hide();
		   			    	 	updatePager(response.pageAt, response.total);
		   			    	 	registerForm(<s:property value="id"/>);
		   			      	}
						});
					}else{
						$('#aggSpot_loading_' + <s:property value="id"/> + " > li").html("服务器开小差了，请稍后重试！");
					}
				});
			};
			
			function updatePager(at, total){
				BC.WG.pager.pageClkFunc = function(pageNo){
					getSpotsOnAgg(pageNo);
				};
				BC.WG.pager.pagination("divpagingup",at,total, '#aggSpot_spot_list_<s:property value="id"/>'); 
			}
			
			function registerForm(aggSpotId){
				$("#aggSpot_spot_list_" + aggSpotId + " form").each(function(){
				   var thisform = $(this);
				   var id = thisform.attr('id');
				   var idx = id.lastIndexOf('_');
				   var spotId = thisform.attr('id').substring(idx+1, id.length);
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
		     				   BC.Template.T({
		     					  name: 'comment_item',
		   	    			   	  domId: '#comments_review_' + spotId,
		   	    			   	  gen: 'prepend',
		   	    			      data: response.vo,
		   	    			      setting: {
		   	    			    	  filter_data: false
		   	    			      },
		   	    			      params: {
		   	    			    	  spotId: spotId
		   	    			      },
		   	    			      callback: function(){
		   	    			    	  $('#comments_review_' + spotId + " .empty").replaceWith('');
		   	    			    	  thisform.find('textarea').val('');
		   	    			    	  thisform.find('textarea').blur();
		   	    			      }
		     				   });
		   				   }
		   			   }
		   		   });
				});
			};
			
			getSpotsOnAgg(0);
			
		});
	</script>
</body>
</html>