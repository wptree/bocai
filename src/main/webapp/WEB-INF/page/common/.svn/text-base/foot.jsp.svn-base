<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- import login&signup -->
<s:include value="feedback.jsp"></s:include>
<div class="global-tip">
	  <b class="spiffy">
	  <b class="spiffy1"><b></b></b>
	  <b class="spiffy2"><b></b></b>
	  <b class="spiffy3"></b>
	  <b class="spiffy4"></b>
	  <b class="spiffy5"></b></b>

	  <div id="tipContent" class="spiffyfg">
		波币 +5
	  </div>

	  <b class="spiffy">
	  <b class="spiffy5"></b>
	  <b class="spiffy4"></b>
	  <b class="spiffy3"></b>
	  <b class="spiffy2"><b></b></b>
	  <b class="spiffy1"><b></b></b></b>
</div> 
<script type="text/javascript">
	$(document).ready(function(){		
		$("span[id*=timeAgo]").each(function(){
			$(this).html($.timeago($(this).html()));
		});
		function refreshNotifyNum(isLogin){
			if(isLogin){
				$.get(contextPath + '/user/count_msg.bc',
					function(response){
						if(!response.isError){
							var total;
							if(response.vo.total>10){
								total = '10+';
							}else{
								total = response.vo.total + '';
							}
							$("#headerNewNotiCount").html(total);
						}else{
							$("#headerNewNotiCount").html('0');
						}
					});
			}
		}
		global.checkUserLogin(refreshNotifyNum);
	});
</script>
<div class="footerout">
	<div class="footer">
		<div class="creatures"></div>
		<div class="cb"></div>
		<div class="footnav">
        	<a href="${pageContext.request.contextPath}/about.bc">关于波菜</a>
        	<span>|</span>
        	<a href="${pageContext.request.contextPath}/term.bc">使用条款</a>
        	<span>|</span>
        	<a href="${pageContext.request.contextPath}/about.bc">开发动态</a>
        	<span>|</span>
        	<a href="${pageContext.request.contextPath}/about.bc">联系我们</a>
        	<span>|</span>
        	<a href="http://weibo.com/2082026727" target="_blank" rel="nofollow">新浪微博</a>
        	<span>|</span>
        	<a href="${pageContext.request.contextPath}/aggSpotIndex.bc" target="_blank" title="各地旅遊美食索引">美食索引</a>
        </div>
        <div class="footnavCopy">
        	<span>&copy; 2011 波菜 </span><span><a href="http://www.miibeian.gov.cn/" target="_blank">浙ICP备11057672号</a></span>
        </div>
	</div>
</div>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-25707955-1']);
	_gaq.push(['_addOrganic', 'baidu', 'wd']);
	_gaq.push(['_addOrganic', 'soso', 'w']);
	_gaq.push(['_addOrganic', '3721', 'name']);
	_gaq.push(['_addOrganic', 'yodao', 'q']);
	_gaq.push(['_addOrganic', 'vnet', 'kw']);
	_gaq.push(['_addOrganic', 'sogou', 'query']);
	_gaq.push(['_setDomainName', '.bocai007.com']);
	
	_gaq.push(['_trackPageview']);
	_gaq.push(['_trackPageLoadTime']);
	
	
	$('#android_download_link').click(function() {
	    _gaq.push(['_trackEvent', 'android_download', 'clicked']);
	});
	
	(function() {
	    var ga = document.createElement('script');
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    ga.setAttribute('async', 'true');
	    document.documentElement.firstChild.appendChild(ga);
	})();
</script>