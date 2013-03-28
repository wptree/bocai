<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>美食指南 波菜网 旅游美食 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="common/base.jsp" ></s:include>
</head>
<body>
<div class="bodyContainer page-bg">
<s:include value="common/header.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>
<div class="out">
    <div class="wrapper">
    	<div id="guide-wf">
    		<c:if test="${not empty pins}">
			<c:forEach var="pin" items="${pins}">
				<div class="p10 pin wft">
					<div class="pr thumb-box">
						<a class="img" href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">
							<img width=192 height=192 src="${pin.thumb.imageUrl}" /></a>
						<div class="thumb-banner pa b-0 lh25 blackbg w ffyh tr op5 none">
							<span class="cfff mr5">${pin.thumb.fullName}</span>
						</div>
					</div>
					<div class="caption ffyh">
						<h3><a class="hlink5" href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">
							${pin.tagName}</a></h3>
					</div>
					<div class="convo clean">
						<c:forEach var="tail" items="${pin.tails}" varStatus="status" end="5">
							<p class="clean <c:if test="${!status.last}">bb</c:if> ">
								<a href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${tail.aggSpotId}" class="img l bg2 p2 mr10 mb5">
									<img src="${tail.imageUrl}" width=32 height=32 /></a>
								<a href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=${tail.aggSpotId}" class="hlink"><span>${tail.fullName}&nbsp;</span></a><br/>
								<span>
									喜欢:&nbsp;<span class="">${tail.nommedNum}</span>&nbsp;
									想吃:&nbsp;<span class="">${tail.tastedNum}</span>&nbsp;
									点击:&nbsp;<span class="">${tail.viewedNum}</span>&nbsp;
								</span>
							</p>
						</c:forEach>
						<c:if test="${pin.tails.size()>6}">
							<p class="bt tr"><a class="hlink"
							 	href="${pageContext.request.contextPath}/guide.bc?id=${pin.tagId}">还有&nbsp;<span class="ctitle">${pin.tails.size()-6}</span>&nbsp;个美食发现</a></p>
						</c:if>
					</div>
				</div>
			</c:forEach>
			</c:if>
    	</div>
    </div>
    <div id="page-nav">
		<a href="<c:url value="/spot/pins.bc" />"></a>
	</div>
</div>
<!--people content end-->
<s:include value="common/foot.jsp"></s:include>
<script type="text/javascript" src="${staticContext}/js/operation.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.masonry.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.infinitescroll.js"></script>
<script type="text/javascript" src="${staticContext}/js/jquery.imagesloaded.js"></script>
<script type="text/javascript">
	headtab(2);
	$(function(){
		var $wf = $('#guide-wf');
		
		$wf.masonry({
			itemSelector : '.pin',
		    columnWidth : 222,
		    gutterWidth: 20,
		    isAnimated: false,
		    animationOptions: {
		    	queue: false
		    },
		    isFitWidth: true
		});
		
		$wf.infinitescroll(
			{
				navSelector : '#page-nav', // selector for the paged navigation
				nextSelector : '#page-nav a', // selector for the NEXT link (to page 2)
				itemSelector : '.pin', // selector for all items you'll retrieve
				debug        : false,
				animate	 : false,
				animationOptions: {
				    duration: 750,
				    easing: 'linear',
				    queue: false
				},
				loading: {
					selector: '.wrapper',
					finishedMsg: '没有更多了',
					msgText: '发现美食指南中...',
					img: '${staticContext}/images/spinner-large.gif',
					speed: 0
				},
				state : {
					currPage: 1
				},
				pathParse: function() {
			        return ['<c:url value="/spot/pins.bc?no=" />',''];
			    }
			},
			// trigger Masonry as a callback
			function( newElements ) {
				// hide new items while they are loading
				var $newElems = $( newElements ).hide();
				// ensure that images load before adding to masonry layout
				$newElems.imagesLoaded(function(){
					// show elems now they're ready
					$wf.append( $newElems ).masonry( 'appended', 
							$newElems, false, function(){
						$newElems.fadeIn('slow');
					});
				});
				bindEvent4Pins($newElems);
			}
		);
	});
	
	bindEvent4Pins($('.pin'));
	
	function bindEvent4Pins($pins){
		$pins.bind("mouseleave mouseenter",
			function(event){
				var $this = $(this); 
				if(event.type == "mouseenter"){
					$this.addClass('pin-focus');
				}else{
					$this.removeClass('pin-focus');
				}
		});
		$pins.find('.thumb-box').bind("mouseleave mouseenter",
			function(event){
				var $this = $(this); 
				if(event.type == "mouseenter"){
					$this.children(".thumb-banner").fadeIn();
				}else{
					$this.children(".thumb-banner").fadeOut();
				}
		});
	}
	
</script>
</div>
</body>
</html>
