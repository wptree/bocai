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
<title>手机客户端下载 波菜网 旅游美食 美食指南 美食分享 </title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/css/elements.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/page.css" rel="stylesheet" type="text/css" />
<link href="${staticContext}/css/mobile.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bc_body" style="background-color:#F0EEDF">
<s:include value="common/header.jsp"></s:include>
<!--people content start-->
<div class="bc_contentout" style="background-color:#F0EEDF">
    <div class="bc_content">
    	<div class="bc_post">
	    	<div class="bc_nav ffyh">
	    		<h2>手机版<em>波菜</em></h2>
	    		<div class="phones">
		    		<ul>
						<li class="btlr5">
							<a class="curr android" href="javascript:void(0);" alt="andriod_post">Android</a></li>
						<li class="btrr5">
							<a class="iphone" href="javascript:void(0);" alt="iphone_post">iPhone</a></li>
					</ul>
				</div>
	    	</div>
	    	<ul>
		    	<li id="andriod_post" class="post_phone andriod ffyh">
		    		<h3><em>Android</em>客户端</h3>
		    		<div class="down_rec clean">
		    			<div class="downNow ffyh">
		    				<h4>直接下载</h4>
		    				<p><a id="android_download_link" class="downStart" href="${staticContext}/download/android_latest.apk">免费下载本地APK安装包</a></p>
		    			</div>
		    			<div class="use_phone_down ffyh">
							<h4>用手机下载</h4>
							<ul>
								<li>1. 在<a href="https://market.android.com/details?id=com.bocai">Android电子市场</a>
									中搜索"波菜"并安装</li>
								<li>2. 在手机浏览器输入以下地址开始下载<a href="http://t.cn/zO7ZtL4">http://t.cn/zO7ZtL4</a></li>
								<li>3. 使用二维码下载（二维码是条形码的一种，可以将网址编译成图案，手机用户可以通过解码软件扫描后下载）</li>
								<li><img width="104" height="104" alt="手机条形码" src="${staticContext}/images/re/barcode_android.png"/></li>
							</ul>
						</div>
		    		</div>
		    		<div class="phone_slide">
		    			<img src="${staticContext}/images/re/android_slide_01.jpg" />
		    		</div>
		    	</li>
		    	<li id="iphone_post" class="post_phone iphone ffyh none">
		    		<h3><em>iPhone</em>客户端</h3>
		    		<div class="down_rec clean">
		    			<div class="downNow ffyh">
		    				<h4>即将推出，敬请期待...</h4>
		    				<!-- <p><a class="downStart" href="${staticContext}/download.bc?f=iphone_latest.apk">免费下载本地APK安装包</a></p>-->
		    			</div>
		    			<!-- 
		    			<div class="use_phone_down ffyh">
							<h4>用手机下载</h4>
							<ul>
								<li>1. 在<a href="https://market.android.com/details?id=com.bocai">App Store</a>
									中搜索"波菜"并安装</li>
								<li>2. 在手机浏览器输入以下地址开始下载<a href="http://t.cn/SI7PZA">http://t.cn/SI7PZA</a></li>
								<li>3. 使用二维码下载（二维码是条形码的一种，可以将网址编译成图案，手机用户可以通过解码软件扫描后下载）</li>
								<li><img width="104" height="104" alt="手机条形码" src="http://cdn.gaopeng.com/v2/images/barcode_android.png"/></li>
							</ul>
						</div>
						-->
		    		</div>
		    		<div class="phone_slide">
		    			<img src="${staticContext}/images/re/iphone_slide_01.jpg" />
		    		</div>
		    	</li>
	    	</ul>
    	</div>
    </div>
</div>

<!--people content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<s:include value="common/scripts.jsp" ></s:include>
<script type="text/javascript">
	headtab(4);
	$(".footerout").css({backgroundColor:'#F0EEDF'});
	$(function(){
		
		var phone = '<s:property value="phone"/>';
		if(phone){
			$('.post_phone').hide();
			$('.phones a').removeClass('curr');
			$('.phones a.' + phone).addClass('curr');
			var f = $('.phones a.' + phone).attr('alt');
			$('#' + f).show();
		}
		
		$('.phones a').click(function(){
			var t = $(this).attr('alt');
			$('.post_phone').hide();
			$('.phones a').removeClass('curr');
			$(this).addClass('curr');
			$('#' + t).show();
		});
	});
</script>
</body>
</html>