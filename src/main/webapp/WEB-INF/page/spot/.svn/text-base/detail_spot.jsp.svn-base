<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
							<div id="share" style="display:none;position: absolute; cursor: pointer;z-index: 200px">
								<script type="text/javascript" charset="utf-8">
								(function(){
								  var _w = 24 , _h = 24;
								  var param = {
								    url:location.href,
								    type:'2',
								    appkey:'100748713', 
								    title:'分享一道很不错的菜：<s:property escape="false" value="dish.name"/>@<s:property escape="false" value="place.fullName"/>，有机会要去尝尝。',
								    pic:'${imageContext}/<s:property value="%{model.getSpotImgPath(480)}"/>',
								    ralateUid:'2082026727', 
									language:'zh_cn',
								    rnd:new Date().valueOf()
								  }
								  var temp = [];
								  for( var p in param ){
								    temp.push(p + '=' + encodeURIComponent( param[p] || '' ) )
								  }
								  document.write('<iframe allowTransparency="true" frameborder="0" scrolling="no" src="http://hits.sinajs.cn/A1/weiboshare.html?' + temp.join('&') + '" width="'+ _w+'" height="'+_h+'"></iframe>')
								})()
								</script>
							</div>
							<div id="photo-container">
								<img class="photo" src="${imageContext}/<s:property value="%{model.getSpotImgPath(600)}"/>" width=600 height=600
									alt="<s:property value="dish.name"/>" />
							</div>
							<script type="text/javascript">
								$("#photo-container").mousemove(function(e){
									$("#share").fadeIn("slow");
									$("#share").offset({top: e.pageY,left:e.pageX});
								});
								$("#photo-container").mouseout(function(){
									$("#share").fadeOut("slow");
								});
							</script>
						</div>
						<div class="info">
							<a href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="spotedBy.id"/>"><img class="avatar" src="<s:property value="spotedBy.avatar"/>" width="50" height="50" /></a>
							<div class="person-details">
								<div class="created-at">
									<a href="${pageContext.request.contextPath}/user/profile.bc?id=<s:property value="spotedBy.id"/>"><s:property value="spotedBy.name"/></a>&nbsp;&nbsp;3&nbsp;天前发现：
								</div>
								<div class="desc">
									<s:property escape="false" value="description"/>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="aggSpotPanel_control">
						<ul class="control-left">
	                       <li id="sighting_btn_<s:property value="id"/>" class="operator sighting sighting-on">
	                       	<span class="heihui fb ml15">评论</span>
	                       	<span class="spanhui f_brackets">(<span id="comment_num_<s:property value="id"/>"><s:property value="commentNum"/></span>)</span>
	                       </li>
	                       <li class="operator wantted">
			                <a href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('<s:property value="aggSpot.id"/>')"><span class="cgray">想吃</span></a>
			                <span class="spanhui f_brackets">(<span id="want_num_<s:property value="aggSpot.id"/>"><s:property value="aggSpot.wantedNum"/></span>)</span>
		            	   </li>
	                       <li class="operator nom">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('<s:property value="aggSpot.id"/>');"><span class="cgray">喜欢</span></a>
	                        <span class="spanhui f_brackets">(<span id="nom_num_<s:property value="aggSpot.id"/>"><s:property value="aggSpot.nomNum"/></span>)</span>
	                       </li>
	                       <li class="operator correction">
	                        <a href="javascript:void(0);" class="heihui" onclick="OP.correction('<s:property value="aggSpot.id"/>_correction','<s:property value="aggSpot.id"/>');"><span class="cgray">纠错</span></a>
	                        <span></span>
	                       </li>
	                   </ul>
	                   <ul class="control-right">
	                   	   <li><a href="javascript:void(0);" class="shenhe" onclick="OP.getBySameDish('<s:property value="dish.name"/>')">同名菜</a></li>
	                   	   <li><span class="spanshenhe">|</span></li>
	                   	   <li><a href="${pageContext.request.contextPath}/spot/toPlace.bc?id=<s:property value="place.id"/>" class="shenhe" >同家店</a></li>
	                   </ul>
					</div>
					<ul id="spot_loading_<s:property value="id"/>" class="aggSpotPanel_loading">
						<li class="review loading"><img src="${staticContext}/images/cream-medium-load.gif"/>加载中...</li>
					</ul>
					<ul id="spot_comment_<s:property value="id"/>" class="reviews none">
					</ul>
					<div id="review_more" class="bort1 p10 tc bbr5" style="display:none; background-color:#F0EEE4">
						<a href="javascript:void(0);" at="0">More</a></div>
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
                		<s:param name="id"><s:property value="aggSpot.id" /></s:param>
                	</s:action>
				</li>
				<li class="s_li">
					<s:action name="same_spot_gallery" executeResult="true">
                		<s:param name="id"><s:property value="aggSpot.id" /></s:param>
                	</s:action>
                </li>
			</ul>
			<ul id="cmtItemTemplate" class="none">
		   		<li></li>
		   	</ul>
		</div>
	</div>
	<s:include value="../component/view_big_map.jsp">
		<s:param name="place"><s:property value="place" /></s:param>
	</s:include>
	<s:include value="../component/correction.jsp"></s:include>
	<s:include value="../component/pop_name_card.jsp"></s:include>
	<s:include value="../component/send_private_msg.jsp"></s:include>
	<s:include value="../component/sign_in.jsp"></s:include>
	<script src="http://ditu.google.cn/maps/api/js?v=3&sensor=false" type="text/javascript"></script>
	<s:include value="../common/scripts.jsp" ></s:include>
	<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=gmap3.js,operation.js,Dom.js,Template.js,pager.js,popNameCard.js,jquery.form-defaults.js"></script>
	<!-- import footer -->
	<s:include value="../common/foot.jsp"></s:include>
	
	<script type="text/javascript">
	function prepareShare(){
		 var _w = 72 , _h = 16;
		  var param = {
		    url:location.href,
		    type:'3',
		    count:'1', /**是否显示分享数，1显示(可选)*/
		    appkey:'', /**您申请的应用appkey,显示分享来源(可选)*/
		    title:'', /**分享的文字内容(可选，默认为所在页面的title)*/
		    pic:'', /**分享图片的路径(可选)*/
		    ralateUid:'', /**关联用户的UID，分享微博会@该用户(可选)*/
			language:'zh_cn', /**设置语言，zh_cn|zh_tw(可选)*/
		    rnd:new Date().valueOf()
		  }
		  var temp = [];
		  for( var p in param ){
		    temp.push(p + '=' + encodeURIComponent( param[p] || '' ) )
		  }
		  document.write('<iframe allowTransparency="true" frameborder="0" scrolling="no" src="http://hits.sinajs.cn/A1/weiboshare.html?' + temp.join('&') + '" width="'+ _w+'" height="'+_h+'"></iframe>');
	}
	
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
		$('#review_more a').click(function(){
			var at = parseFloat($(this).attr('at'));
			loadCmt(<s:property value="id"/>, ++at);
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
		
		function loadCmt(spotId, pageAt){
    	   $.ajax({url: contextPath + "/spot/ajax_page_comment_in_spot.bc",
    		   type: "POST",
    		   data: {"spotId": spotId,
    			      "pageAt": pageAt},
    		   success: function(result){
    			   if(!result.isError){
    				   BC.Template.T({
    	    			   name: 'profile_review_list',
    	    			   domId: '#spot_comment_' + spotId,
    	    			   data: result.vo,
    	    			   setting: {
    	    				   filter_data: false
    	    			   },
    	    			   params: result.more,
    	    			   callback: function(){
    	    				   $('#spot_comment_' + spotId).show();
    	    				   $('#spot_loading_'+spotId).hide();
    	    				   registerCmtForm(spotId);
    	    				   if(result.more){
    	    					   $('#review_more').show();
    	    				   }else{
    	    					   $('#review_more').hide();
    	    				   }
    	    				   $('#review_more a').attr('at', result.pageAt);
    	    			   }
    	    		   });
    			   }
    		   },
    		   error: function(){
    		   }
    	   });
	   };
	   
	   function registerCmtForm(spotId){
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
	     				   BC.Template.T({
	     					  name: 'profile_comment_item',
	   	    			   	  domId: '#comments_' + spotId,
	   	    			      data: response.vo,
	   	    			   	  setting: {
	   	    			    	  filter_data: false
	   	    			      },
	   	    			      params: {
	   	    			    	  spotId: spotId
	   	    			      },
	   	    			      gen: 'prepend',
	   	    			      callback: function(){
	   	    			    	  $('#comment_item_empty_'+spotId).replaceWith('');
	   	    			    	  var cmtNum = parseFloat($('#comment_num_' + spotId).html());
	   	    			    	  if(!isNaN(cmtNum)){
	   	    			    		 cmtNum += 1;
	   	    			    		 $('#comment_num_' + spotId).html(cmtNum);
	   	    			    	  }
	   	    			    	  thisform.find('textarea').val('');
	   	    			    	  thisform.find('textarea').blur();
	   	    			      }
	     				   });
	   				   }
	   			   }
	   		   });
			   
		   });
	    };
	   
	    //execute
	    loadCmt(<s:property value="id"/>, 0);
	});
	</script>
	</div>
</body>
</html>