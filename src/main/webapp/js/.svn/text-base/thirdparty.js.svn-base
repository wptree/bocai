$("img[id*=renren_login]").each(function(){
	$(this).hide();
	//$(this).click(function(){
	//	$.ajax({url: "login!thirdPartyRequest.bc",
	//		  type: "GET",
	//		  data: {"source":"renren","backUrl":"http:\/\/wptree.gnway.net/bocai/login!renrenUserLogin.bc"},
	//		  success: function(jsonObj){
	//			window.location.href=jsonObj.backUrl;
	//		  }
	//		});
	//});
});
$("img[id*=sina_weibo]").each(function(){
	$(this).click(function(){
		var contextPath = $("#const_contextPath").text();
		$.ajax({url: contextPath+"/login!thirdPartyRequest.bc",
			  type: "GET",
			  data: {"needClean":false,"source":"sina","backUrl":"http:\/\/www.bocai007.com/bocai/login!sinaUserLogin.bc"},
			  success: function(jsonObj){
				window.location.href=jsonObj.backUrl;
			  }
			});
	});
});