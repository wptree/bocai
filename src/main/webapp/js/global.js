/**
 * Depends on 
 * jquery-1.5.2.js, 
 * jquery.bt.js, 
 * jquery.simplemodal.js
 */

var global={
		/**
		 * Show an operation tip arround the @selector 
		 * @param selector  jquery selector, support '#id', '.clazz' etc.
		 * @param text  The message you want to show.
		 * @param position  'top' | 'left' | 'right','topleft'
		 */
		showOperationTip: function(selector, text, position, time){
			var cpx=.5, sr=.5;
			if(position == 'topleft'){
				cpx =.2;
				sr = .2;
			}
			$(selector).btOff();
			$(selector).bt(text,{
				  trigger: 'none',
				  fill: "#393837",
				  cssStyles:{color:"#fff"},
				  spikeLength: 5,
				  spikeGirth: 10, 
				  spikeRatio: sr,
				  centerPointX: cpx,
				  cornerRadius: 3,
				  shrinkToFit: true,
				  positions:position
				});
			$(selector).btOn();
			var period = 2000;
			if(time!=null){
				period = time;
			}
			window.setTimeout(function(){
				$(selector).btOff();
	        }, period);
		},
		
		needLogin: function(){
			/*modalPanel = $("#loginAndSignUpPanel").modal({
				focus:false,
				overlayClose: true,
				containerCss:{height:$('#loginAndSignUpPanel').css('height'),width:$('#loginAndSignUpPanel').css('width'),backgroundColor:"#333",boxShadow: "0 0 90px 5px #000000"}
			});
			dengluhuandong(1);
			$("#closeImg").focus();*/
			OP.popupSignIn();
		},
		/**
		 * Change the followship between the current session user with @targetId 
		 * @param targetId
		 * @param targetType 'user' | 'dish' | 'place' | 'spotGuide'
		 */
		changeFollow: function(targetId,targetType){
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   		global.needLogin();
	   	   		return;
	   	   	}
			var contextPath = $("#const_contextPath").text();
			$.ajax({url: contextPath+"/user/changeFollow.bc",
				  type: "POST",
				  dataType: "json",
				  data: {"targetId":targetId,"targetType":targetType},
				  success: function(jsonObj){
					  var id = targetId+"_"+targetType+"_followTag";
					  if(jsonObj.saveResult){
						 $("a[id*="+id+"]").each(function(){
								$(this).html(jsonObj.returnMsg);
								if(jsonObj.returnMsg=="取消关注"){
									$(this).removeClass("follow-button").addClass("unfollow-button");
								}else{
									$(this).removeClass("unfollow-button").addClass("follow-button");
								}
							});
					  }else{
						  $("a[id*="+id+"]").each(function(){
							  global.showOperationTip($(this),jsonObj.returnMsg,"top");
							});
						  
					  }
				  }
			});
		},
		
		/**
		 * Show global tip which will display in the absolutely center of window.
		 * @param msg
		 */
		showGlobalTip:function(msg){
			$(".spiffyfg").html(msg);
			$(".global-tip").fadeIn();
			window.setTimeout(function(){
	        	$(".global-tip").fadeOut('slow');
	        	$(".global-tip").css({display:"none"});
	        }, 2000);
		},
		
		checkUserLogin: function(callback){
			var contextPath = $("#const_contextPath").text();
			$.ajax({url: contextPath+"/checkUserLogin.bc",
				  type: "POST",
				  dataType: "json",
				  success: function(jsonObj){
					  if($.isFunction(callback)){
						  callback(jsonObj.checkResult);
					  } 
				  }
			});
		}
};