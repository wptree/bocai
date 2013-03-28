<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bc" uri="/WEB-INF/bocai.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${staticContext}/images/favicon.ico" />
<title>关于我们 旅游美食 美食指南 美食分享</title>
<meta name="description" content="通过手机随时随地上传美食，搜寻美食。基于地理位置的美食导航，个性化的旅游美食服务" />
<s:include value="common/base.jsp" ></s:include>
<link href="${staticContext}/min/b=bocai/css&amp;f=about.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="bodyContainer">
<s:include value="common/header.jsp"></s:include>
<s:include value="common/scripts.jsp" ></s:include>
<div class="about_contentout">
    <div class="about_content">
        <div class="about_content_left">
            <div class="about_content_left_a">
                <span id="bigtitle" class="s1">关于波菜</span>
            </div>
          <div class="about_content_left_b">
          <div class="pbocai1"></div>
          <div id="container" class="pbocai2">
          		<div id="title" class="aboutus" style="margin-top:10px">
          			<p>波菜网  - 旅游美食社区！</p>
          		</div>
          		<div id="goal" class="aboutus">
          			<p>我们是一个基于地理位置服务(LBS)的旅游美食分享社区。我们的宗旨是让你的旅途“美味”无穷！</p>
          		</div>
          		<div id="cando" class="aboutus" style="height: auto;padding-top:5px">
          			<h2>吃货们，在这里你可以</h2>
          			<div class="cando">
          				<ul>
          					<li> - 通过地图搜寻目标城市的美食</li>
							<li> - 通过手机随时随地分享你吃过的美食，并写下就餐的感觉</li>
							<li> - 通过网站，点评、推荐你喜欢的菜，让后来人吃的放心</li>
							<li> - 和口味相似的朋友们交流美食心情</li>
							<li> - 最重要的，在城市的某个角落，通过手机寻找与“美味”的艳遇</li>
							<li> - 摄影爱好者，请不要吝啬你拍好的美食照片，这里的吃货们需要你从视觉上触动他们的味蕾！</li>
          				</ul>
          			</div>
          		</div>
          		<div id="links" class="aboutus" style="height: auto">
	          		<div class="cando">
	          			<ul>
	          			<li>新浪微博：<a target="_blank" href="http://weibo.com/2082026727">@波菜网</a></li>
	          			<li>腾讯QQ：2308132891 215326589(波客交流群)</li>
	          			<li>MSN：bocai.app@gmail.com</li>
	          			<li>邮箱：<a target="_blank" href="mailto:bocai.app@gmail.com">bocai.app@gmail.com</a></li>
	          			</ul>
	          		</div>
          		</div>
          </div>
          <div class="pbocai3"></div>  
        </div>
        </div>
           <div class="about_content_right">
             <div class="about_content_right_d">
                <div class="about_content_right_d1">
                </div>
                <div class="about_content_right_d2">
                <ul id="uu1">
	                <li>
	                <div class="wenzhangyou">
	                  	<a href="${pageContext.request.contextPath}/main.bc"> 返回首页</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	<a href="javascript:void(0);" onclick="aboutus();">关于波菜</a>
	                </div>
	                </li>
	                <li>
	                <div class="wenzhangyou">
	                  	 <a href="javascript:void(0);" onclick="ourstatus();">开发动态</a>
	                </div>
	                </li>
	                <li style="border-bottom: 0">
	                <div class="wenzhangyou">
	                  	<a href="javascript:void(0);" onclick="linkus();">联系我们</a>
	                </div>
	                </li>
                </ul>
                </div>
                <div class="about_content_right_d3"></div>
            </div>
            </div>
    </div>
</div>
<!--people content end-->
<s:include value="common/foot.jsp"></s:include>
</div>
<script type="text/javascript">
	var title = $('<div id="title" class="aboutus" style="margin-top:10px"><p>波菜网  - 旅游美食社区！</p></div>');
	var goal = $('<div id="goal" class="aboutus"><p>我们是一个基于地理位置服务(LBS)的旅游美食分享社区。我们的宗旨是让你的旅途“美味”无穷！</p></div>');
	var cando = $('<div id="cando" class="aboutus" style="height: auto;padding-top:5px"><h2>吃货们，在这里你可以</h2><div class="cando"><ul><li> - 通过地图搜寻目标城市的美食</li><li> - 通过手机随时随地分享你吃过的美食，并写下就餐的感觉</li><li> - 通过网站，点评、推荐你喜欢的菜，让后来人吃的放心</li><li> - 和口味相似的朋友们交流美食心情</li><li> - 最重要的，在城市的某个角落，通过手机寻找与“美味”的艳遇</li><li> - 摄影爱好者，请不要吝啬你拍好的美食照片，这里的吃货们需要你从视觉上触动他们的味蕾！</li></ul></div></div>	');
	var links = $('<div id="links" class="aboutus" style="height: auto"><div class="cando"><ul><li>新浪微博：<a target="_blank" href="http://weibo.com/2082026727/profile">@波菜网</a></li><li>腾讯QQ：2308132891</li><li>MSN：bocai.app@gmail.com</li><li>邮箱：<a target="_blank" href="mailto:bocai.app@gmail.com">bocai.app@gmail.com</a></li></ul></div></div>');
	var wordsLink = $('<div id="title" class="aboutus" style="margin-top:10px"><p>关于波菜，如果你有任何想法，或者遇到任何问题，可以通过下面任何一种方式联系我们。</p></div>');
	var status = $('<div id="title" class="aboutus" style="margin-top:10px"><p>我们每天都在成长，明天会更好！</p></div>');
	
	function aboutus(){
		$("#container").empty();
		$("#container").append(title).append(goal).append(cando).append(links);
		$("#bigtitle").html("关于波菜");
	}
	function linkus(){
		$("#container").empty();
		$("#container").append(wordsLink).append(links);
		$("#bigtitle").html("联系我们");
	}
	function ourstatus(){
		$("#container").empty();
		$("#container").append(status).append(links);
		$("#bigtitle").html("开发动态");
	}
</script>
</body>
</html>
