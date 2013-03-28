<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="max-age=86400"/>   
<title>${tag.tagName} 波菜网 分享健康美食 旅游美食引擎 美食列表</title>
<!-- import common css -->
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/css/panel_spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/detail_agg_spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/component_agg_spot.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/pod.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/contrl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="common/header.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>

	<!-- main body start -->
	<div class="contentout">
		<div class="content p20" style="margin: auto;">
			<div class="ffyh lh25 clean mb10">
				<span class="f20 fb">
					${tag.tagName}
				</span>
				<p class="c666">创建时间：<f:formatDate value="${tag.createdAt}" pattern="yyyy-MM-dd"/> | 
					最后修改时间：<f:formatDate value="${tag.updatedAt}" pattern="yyyy-MM-dd"/></p>
			</div>
			<ul class="c_main">
				<li id="agg-spot-list" class="m_li">
					<c:forEach var="aggSpot" items="${page.list}" varStatus="status">
					<c:set var="spotId" scope="request" value="${aggSpot.lastSpot.id}"/>
					<c:set var="imgType" scope="request" value="${aggSpot.lastSpot.imgType}"/>
					<div id="aggSpot_${aggSpot.id}" class="aggSpotPanel bbox" 
						<c:if test ="${status.first}">
							style="margin-top: 2px;"
						</c:if> >
						<div class="aggSpotPanel_body">
							<div class="aggSpotPanel_body_left pr clean">
								<ul class="pb5 h32 mt10 ml-5 mb10 hpbg clean">
									<li class="l f13 pl14 cfff hrbbg h32 lh32 w90 mr5">他们也分享了</li>
									<c:forEach var="spottedBy" items="${aggSpot.spottedBys}" begin="0" end="7">
					                <li class="l mr5"><a href="${pageContext.request.contextPath}/user/profile.bc?id=${spottedBy.id}" title="进入${spottedBy.name}主页"><img alt="${spottedBy.name}" title="${spottedBy.name}" src="${spottedBy.avatar}" width="32" height="32" /></a></li>
					                </c:forEach>
					                <c:if test="${aggSpot.spottedBys.size()>8}">
					                <li class="l w30 h32 pr">
										<a title="查看所有的分享" id="peer_more_${aggSpot.id}" class="pa lh16" style="left:0; bottom:0;" href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${aggSpot.id}" >更多</a>
									</li>
					                </c:if>
								</ul>
					            <div class="aggSpot_title">
					                <span class="aggSpot_name ffyh">
					                	<a title="查看所有的分享" href="javascript:void(0);" onclick="OP.switchSighting('${aggSpot.id}')" class="spotName">
					                		${aggSpot.dish.name}</a>@<a class="spotName" title="查看${aggSpot.place.fullName}店内所有分享" 
					                		href='<c:url value="/spot/toPlace.bc?id=${aggSpot.place.id}" />'>${aggSpot.place.fullName}</a>
					                </span>
					                <c:if test="${aggSpot.averagePrice > 0}">
					                	<span class="spanhe2 spotPrice">参考价￥${aggSpot.averagePrice}</span>
					                </c:if>
					            </div>
					            <div class="aggSpot_way">
					            	<span class="aggSpot_post_way">
					            		<span>最后由&nbsp;<a class="shenhei" title="查看${aggSpot.lastSpot.spotedBy.name}所有的分享" 
					            		href='<c:url value="/user/profile.bc?id=${aggSpot.lastSpot.spotedBy.id}"/> '>${aggSpot.lastSpot.spotedBy.name}</a>&nbsp;</span>
					            		<span class="time-ago">${aggSpot.lastSpot.createdAt}</span>&nbsp;通过 &nbsp;${aggSpot.lastSpot.postBy}&nbsp;发布
					            	</span>
					            </div>
					            <div class="aggSpot_splitter_silk"></div>
					            <div class="aggSpot_desc f14">${aggSpot.lastSpot.description}</div>
					            <ul class="aggSpot_tag">
					            	
					            </ul>
							</div>
							<div class="aggSpotPanel_body_right">
								<div class="dish_photo">
									<div class="dish_photo_tip">
										<p class="pp1" id="left_nom_num_${aggSpot.id}">${aggSpot.nomNum}</p>
										<p class="pp2">喜欢</p>
					                </div>
					                <a href='<c:url value="/spot/detail_agg_spot.bc?id=${aggSpot.id}"/>'>
					                	<img alt="${aggSpot.dish.name}@${aggSpot.place.fullName}" title="查看${aggSpot.dish.name}@${aggSpot.place.fullName}" 
					                		src="${staticContext}/spot/${aggSpot.lastSpot.id}/180.${aggSpot.lastSpot.imgType}" width="170px" height="170px" /></a>
								</div>
					        </div>
					        <div class="clear"></div>
						</div>
						<div id="aggSpotPanel_control_${aggSpot.id}" class="aggSpotPanel_control bbr5">
							<ul class="control-left">
					            <li id="sighting_btn_${aggSpot.id}" class="operator sighting sighting-off">
					            	<a title="查看所有分享" href="javascript:void(0)" class="heihui" onclick="OP.switchSighting('${aggSpot.id}')"><span class="cgray">发现</span></a>
					            	<span class="spanhui f_brackets">(<span id="comment_num_${aggSpot.id}">${aggSpot.spottedNum}</span>)</span>
					            </li>
					            <li class="operator wantted">
					         		<a title="我想吃这道菜" href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('${aggSpot.id}');"><span class="cgray">想吃</span></a>
					         		<span class="spanhui f_brackets">(<span id="want_num_${aggSpot.id}">${aggSpot.wantedNum}</span>)</span>
					    		</li>
					            <li class="operator nom">
					                <a title="我喜欢这道菜" href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('${aggSpot.id}');"><span class="cgray">喜欢</span></a>
					                <span class="spanhui f_brackets">(<span id="nom_num_${aggSpot.id}">${aggSpot.nomNum}</span>)</span>
					            </li>
					            <li class="operator correction">
					                <a title="我来纠错" href="javascript:void(0);" class="heihui" onclick="OP.correction('#'+'${aggSpot.id}_correction','${aggSpot.id}');"><span class="cgray">纠错</span></a>
					                <span></span>
					            </li>
					        </ul>
					        <ul class="control-right shenhe">
					          	<li><a title="查看所有同名菜" href="javascript:void(0);" onclick="OP.getBySameDish('${aggSpot.dish.name}');location.reload();">同名菜</a></li>
					          	<li><span class="spanshenhe">|</span></li>
					   			<li><a title="查看该店所有菜" href='<c:url value="/spot/toPlace.bc?id=${aggSpot.place.id}" />'>同家店</a></li>
					        </ul>
					    </div>
					    <ul id="aggSpot_loading_${aggSpot.id}" class="aggSpotPanel_loading" style="display:none">
							<li class="review loading cchrome1">
								<img src="${staticContext}/images/cream-medium-load.gif"/>加载中...
							</li>
						</ul>
						<ul id="aggSpot_spot_list_${aggSpot.id}" class="spots" style="display:none"></ul>
					</div>
					</c:forEach>
					<div id="divpagingup" class="mt10 fr" data-no="${page.pageNo}" 
						data-total="${page.totalPage}" data-href="<c:url value="/guide.bc?id=${tag.id}&no=" />"></div>
				</li>
			</ul>
			<ul class="c_side">
				<li class="s_li top">
        			<bc:followTag targetType="guide" targetId="${tag.id}" followText="+关注" unFollowText="取消关注"/>
        		</li>
				<li class="s_li">
					<s:include value="component/about_tag.jsp" >
						<s:param name="tag"><s:property value="tag" /></s:param>
					</s:include>
				</li>
				<li class="s_li">
					<s:action name="list_fans_of_guide" namespace="/user" executeResult="true">
                		<s:param name="id"><s:property value="tag.id" /></s:param>
                	</s:action>
				</li>
				<li class="s_li">
					<s:action name="list_creator_of_guide" namespace="/user" executeResult="true">
                		<s:param name="id"><s:property value="tag.id" /></s:param>
                	</s:action>
                </li>
                <li class="s_li">
					<s:include value="component/share.jsp" >
					    <c:set var="content" scope="request" value="来自@波菜网：分享一个很不错的美食指南  #${tag.tagName}#"></c:set>
						<c:set var="imgUrl" scope="request" value="${staticContext}/spot/${spotId}/480.${imgType}"></c:set>
					</s:include>
                </li>
			</ul>
		</div>
	</div>
	<div id="cmtItemTemplate" class="comment-row" style="display:none;"></div>

	<s:include value="component/sign_in.jsp"></s:include>
	<s:include value="component/pop_name_card.jsp"></s:include>
	
	<!-- import footer -->
	<s:include value="common/foot.jsp"></s:include>
</div>
<script type="text/javascript" src="${staticContext}/js/jquery.contactable.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery-jtemplates.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.easing.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.scrollTo.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.timeAgo.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.mailAutoTip.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.form-defaults.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.simplemodal-1.4.2.js"></script>
<script type="text/javascript" src="${staticContext}/js/global.js"></script>
<script type="text/javascript" src="${staticContext}/js/head.js"></script>
<script type="text/javascript" src="${staticContext}/js/bocai.core-0.1.0.js"></script>
<script type="text/javascript" src="${staticContext}/js/operation.js"></script>
<script type="text/javascript" src="${staticContext}/js/Dom.js"></script>
<script type="text/javascript" src="${staticContext}/js/Template.js"></script>
<script type="text/javascript" src="${staticContext}/js/pager.js"></script>
<script type="text/javascript" src="${staticContext}/js/popNameCard.js"></script>
<script type="text/javascript">
	headtab(2);
	$(".time-ago").each(function(){
		$(this).html($.timeago($(this).html()));
	});
	var pageNo = parseFloat($('#divpagingup').attr('data-no'));
	var pageTotal = parseFloat($('#divpagingup').attr('data-total'));
	var nextHref = $('#divpagingup').attr('data-href');
	BC.WG.pager.pageClkFunc = function(pageNo){
		window.location.href= nextHref + pageNo;
	};
	BC.WG.pager.pagination("divpagingup", pageNo, pageTotal);
</script>
</body>
</html>