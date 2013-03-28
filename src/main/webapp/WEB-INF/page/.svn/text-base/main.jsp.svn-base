<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="dns-prefetch" href="http://www.google-analytics.com/"/>
<link rel="dns-prefetch" href="http://ditu.google.cn/"/>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>波菜网 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的美食推荐服务" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/> 
<meta property="qc:admins" content="13064546676273110076375" />            
<!-- import common css -->
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=index.css,main.css,paginator.css,panel_spot.css,upload.css,citypicker.css,pod.css,component_agg_spot.css,validator.css,token-input-upload.css,upload.spot.css,uploadify.css,contrl.css" rel="stylesheet" type="text/css" />
</head>
<!-- import common scripts -->
<body>
<div class="bodyContainer">
<!-- import head-->
<s:include value="common/header.jsp"></s:include>
<!-- main body start -->
<div id="map">
	<form id="map_search" class="default" method="post" accept-charset="UTF-8" action="map_search.bc">
		<div style='display:none'>
			<input id="submit_hidden_field" type="hidden" />
			<input id="keyword_type_hidden_field" name="keywordType" type="hidden" value="DISH" />
			<input id="req_type_hidden_field" name="reqType" type="hidden" value="LATEST" />
			<input id="sw_hidden_field" name="sw" type="hidden" />
			<input id="ne_hidden_field" name="ne" type="hidden" />
			<input id="keyword_hidden_field" name="keyword" type="hidden" />
			<input id="loc_hidden_field" name="loc"  type="hidden" />
			<input id="at_hidden_field" name="at" type="hidden" />
			<input id="total_hidden_field" name="total" type="hidden" />
			<input id="center_hidden_field" name="center" type="hidden" />
			<input id="zoom_hidden_field" name="zoom" type="hidden" />
		</div>
		<fieldset id='map_fields'>
			<input class="submit bcbutton" type="submit" value="搜索" tabindex=3 />
			<div id='location-input'>
				<div class='text-box'>
					<a id="location_clear_button" class="clear" onclick="BC.clearLocation();" href="javascript:void(0);" title="清除定位">x</a>
					<label class="prompted" for="location" id="set_location_label">设置您的位置: <strong class="c000">城市，街区</strong></label>
					<input class="text" id="location" onfocus="BC.showLocations()" type="text" value="" tabindex=1 onkeypress="return BC.noEnter();"/>
					<div id='saved_locations' style='display:none'>
						<ul id='saved_location_ul'  class='ffyh'>
							<li class="current-location">
								<a title="自定定位当前位置" href="javascript:void(0);" onclick="BC.getClientLocation();"><img border="0" alt="" src="${staticContext}/images/clear.png"/>当前位置</a>
							</li>
							<li class='anywhere'>
								<a title="全国范围" href="javascript:void(0);" onclick="BC.clearLocation();"><img src="${staticContext}/images/clear.png" />全国范围</a>
							</li>
							<li class='selectcity'>
								<a title="选择城市" id="selectcitylink" onclick="OP.popUpSelectCityPanel();" href="javascript:void(0);" ><img src="${staticContext}/images/clear.png" />选择城市</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div id='genius-search-input'  class="text-box" style="z-index:1">
				<a title="清除搜索结果" id="query_clear_button" href="javascript:void(0);" class="clear" onclick="BC.clearQuery();" href="javascript:void(0);">x</a>
				<label id="genius-label" class="prompted" for="query">搜索： <strong class="c000">美食, 餐厅 or 波客</strong></label>
				<div id="genius-search" class="live-search">
	  				<div id="genius-hint" class="spinner" style="display:none"><img alt="Spinner" src="${staticContext}/images/spinner.gif" /></div>
	  				<input id="query" class="text" type="text" value="" autocomplete="off" disableautocomplete
	  				     onfocus="BC.showSearchHint()" onkeypress="return BC.noEnter();" name="query" tabindex=2 />
	  				<div class="results-container">
	    				<ul id="genius_results" class="results ffyh" style="display:none;">
	    				</ul>
	  				</div>
				</div>
			</div>
		</fieldset>
	</form>
	<div id="map-pagination" class="ffyh">
		<a id="page-right" title="向后翻页"  href="javascript:void(0);">后一页</a>
		<a id="page-left" title="向前翻页"  href="javascript:void(0);">前一页</a>
		<div id="map-sorts" class="clean ffyh">
			<a id="map-sort-best" class="first" title="人气最旺的美食" href="javascript:void(0);">人 气</a>
			<a id="map-sort-latest" class="on" title="最新上传美食"  href="javascript:void(0);">最 新</a>
			<a id="map-sort-following" class="" title="我关注的美食" href="javascript:void(0);">关 注</a>
			<a id="map-sort-wanted" class="" title="我想吃的美食" href="javascript:void(0);">想 吃</a>
			<a id="map-sort-guides" class="last" title="美食指南" href="javascript:void(0);">指 南</a>	
		</div>
	</div>
	<div id="bc_map" class="gmap" style="overflow: hidden;">
		<div id="map-loading-box" style="display: none;"><div id="map-loading-spinner"></div></div>
		<div id="map-message-box" style="display: none;"></div>
	</div>
</div>
<div id="content">
<div id="latlng" style="display: none"></div>
<div id="checkResult" style="display: block"></div>
<div id="getResult" style="display: block"></div>
<!--index content start-->
<div class="index_contentout">
    <div class="index_content">
        <div class="index_content_left">
        	<s:include value="component/upload_spot.jsp"></s:include>
        	<div id="aggSpot_list"></div>
        	<div id="divpagingup" class="mt10 fr"></div>
        </div>
        <div class="index_content_right pr">
        		<div id="info-window" class="ffyh">
    			<strong>通过手机，随时随地分享美食</strong><br/>
    			<a title="下载Android客户端" href="${staticContext}/mobile.bc?phone=android">Android</a>
    			客户端已发布,
    			<a title="下载iPhone客户端" href="${staticContext}/mobile.bc?phone=iphone">iPhone</a>
    			版即将推出
    		</div>
            <s:if test="#session.sessionLoginUser==null">
            <div class="index_content_right_b sign_in_clean">
            <a href="javascript:void(0);" class="denglu" title="登陆入口"><img id="loginButton" src="${staticContext}/images/index_btm_231.jpg" width="139" height="68" alt="登陆"/></a>
              <a href="${pageContext.request.contextPath}/sign_up.bc" class="zhuce" title="注册入口"><img id="signUpButton" src="${staticContext}/images/index_btm_251.jpg" width="139" height="68" alt="注册"/></a>
            </div>
            </s:if>
            <div class="index_content_right_c">
            <img src="${staticContext}/images/index_btm_39.jpg" width="287" height="202" alt="广告位"/>
            </div>
            <!-- div class="index_content_right_d">
                <div class="index_content_right_d1">
                </div>
                <div class="index_content_right_d2">
                <div class="toubu">
                <p class="le">热门美食指南</p>
                <p class="ri">更多…</p>
                </div>
                	<s:action name="list_spot_guide" executeResult="true">
                		<s:param name="type">1</s:param>
                	</s:action>
                </div>
                <div class="index_content_right_d3"></div>
            </div-->
            <div class="index_content_right_e">
                <s:include value="component/pod_boke_ranking.jsp"></s:include>
            </div>
            <s:if test="#session.sessionLoginUser!=null">
            <div class="index_content_right_e">
                <s:include value="component/invitation.jsp"></s:include>
            </div>
            </s:if>
        </div>
    </div>
	<div id="getResult" style="width: 200;"></div>
</div>
<a id="base_scrollToTop" class="W_gotop" href="javascript:;" style="display:none" title="返回顶部"> 
	<span> 
		<em class="sj">♦</em>
		<em class="fk">▐</em> 返回顶部 
	</span> 
</a>
<!--index content end-->
</div>
<div id="cmtItemTemplate" class="comment-row" style="display:none;"></div>
<s:include value="component/sign_in.jsp"></s:include>
<s:include value="component/food_tip.jsp"></s:include>
<s:include value="component/correction.jsp"></s:include>
<s:include value="component/add_place.jsp"></s:include>
<s:include value="component/pop_name_card.jsp"></s:include>
<s:include value="component/send_private_msg.jsp"></s:include>
<!-- main body end -->
</div>
<!-- import foot-->
<s:include value="common/foot.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>
<script type="text/javascript">
	$(window).scroll(function(){
		$("#base_scrollToTop").fadeIn("slow");
		if($(window).scrollTop()==0){
			$("#base_scrollToTop").fadeOut("slow");
		}
	});
	$(document).ready(function(){
		$("#base_scrollToTop").click(function(){
			$(document).scrollTop(0);
		});
		
		$("#loginButton").click(function(){
			OP.popupSignIn();
		});
	});
	

	$(function() {
		headtab(1);
		var sortType = "LATEST";
		$('#query').keyup(function(event){
			BC.runGenius(false, event);
		});
		$('#query').keydown(BC.onQueryKeyDown);
		$('#query').val("");
		$('#location').keydown(BC.recordLocation);
		$('#location').val("");
		$('#map-sorts a').click(BC.sortByType);
		$('#page-right').click(BC.nextPage);
		$('#page-left').click(BC.previousPage);
		var overlay, nextZIdx, markerMap = {};
		$('#map_search').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				BC.updateAddress();
				if(!(typeof BC.WG.CustomOverlaysManage === 'undefined')){
					$("#"+BC.WG.CustomOverlaysManage.infoPanel.id).hide();
				}
				$("#map-loading-box").show();
				return true;
			},
			success : function (response, statusText, xhr, $form){
				$("#map-loading-box").hide();
				if(!response.isError){
					BC.Template.T({
 	    			   name: 'agg_spot_list',
 	    			   setting: {
 	    				   filter_data: false
 	    			   },
 	    			   domId: '#aggSpot_list',
 	    			   data: response.model,
 	    			   callback: function(){
 	    				  BC.updateAggSpots(response);
	  					  var query = $('#keyword_hidden_field').val();
	  					  $('.aggSpot_name > a').each(function(){
			      		       var text = $(this).text();
			      			   text = text.replace(new RegExp(query, 'g'),'<span class="cerror">'+query+'</span>');
			      			   $(this).html(text);
			      		  });
 	    			   }
 	    		   });
				}else{
				   BC.sendMsg("服务器出小差了，请稍后重试");
				}
			},
			error: function(){
				$("#map-loading-box").hide();
				BC.sendMsg("服务器出小差了，请稍后重试");
			}
		});
		BC.initPage('<s:property value="#session.sessionMapQstr" escape="false"/>');
		BC.checkCityResultHandler(null);
	});
	
	function getInitCenter(){
		return BC.toLatlng($('#center_hidden_field').val());
	};
	
	function getInitZoom(){
		return parseFloat($('#zoom_hidden_field').val());
	};
	
	function getInitCity(){
		if(!userCity){
			return '全国';
		}else{
			return userCity;
		}
	};
	
	function map_init(){
		Sid.js(['${staticContext}/js/gmap3.js',
		        '${staticContext}/js/CustomOverlayG.js',
		        '${staticContext}/js/CustomOverlaysManage.js'], function(){
			var args = BC.initMapArgs();
			$("#bc_map").gmap3(args[0]);
		});
	}
</script>
<script type="text/javascript" src="${staticContext}/min/b=bocai/js&amp;f=sidjs-0.1.js,jquery.paginate.js,bocai.core-0.1.0.js,index.js,operation.js,Dom.js,citypicker.js,Template.js,Event.js,Lang.js,pager.js,MapNav.js,popNameCard.js,upload.js,jquery.uploadify.v2.1.4.js,swfobject.js,jquery.form-defaults.js,jQueryRotate.2.1.js"></script>
</body>
</html>
