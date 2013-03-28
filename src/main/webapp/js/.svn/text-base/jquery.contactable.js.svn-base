/*
 * contactable 1.2.1 - jQuery Ajax contact form
 *
 * Copyright (c) 2009 Philip Beel (http://www.theodin.co.uk/)
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) 
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *
 * Revision: $Id: jquery.contactable.js 2010-01-18 $
 *
 */
 
//extend the plugin
(function($){

	//define the new for the plugin ans how to call it	
	$.fn.contactable = function(options) {
		//set default options  
		var defaults = {
			url: '',
			name: 'Name',
			email: 'Email',
			message : 'Message',
			subject : 'A contactable message',
			submit : 'SEND',
			recievedMsg : 'Thank you for your message',
			notRecievedMsg : 'Sorry but your message could not be sent, try again later',
			disclaimer: 'Please feel free to get in touch, we value your feedback',
			hideOnSubmit: false

		};

		//call in the default otions
		var options = $.extend(defaults, options);
		//act upon the element that is passed into the design    
		return this.each(function() {
			//construct the form
			var this_id_prefix = '#'+this.id+' ';
			$(this).html('<div id="contactable_inner"></div><form id="contactForm" method="post" action=""><div id="loading"></div><div id="callback"></div><div class="holder"><p><label for="name">'+options.name+'</label><br><input id="name" class="contact" name="name"></p><p><label for="email">'+options.email+'</label><br><input id="email" class="contact" name="email"></p><p><label for="message">'+options.message+'<span class="red"> * </span></label><br><textarea id="fmessage" name="message" class="message" rows="4" cols="30"></textarea></p><p><input class="submit" value="'+options.submit+'" type="submit"></p><p class="disclaimer">'+options.disclaimer+'</p></div></form>');
			//show / hide function
			$(this_id_prefix+' form input[type!="submit"]').each(function(){
				//$(this).click(function(){
					//$(this).select();
					//$(this).css({background:'#E2ECD4'});
				//});
				$(this).blur(function(){
					$(this).css({background:'#fff'});
				});
			});
			$(this_id_prefix+'div#contactable_inner').toggle(function() {
				var loginId = parseFloat($("#const_loginId").text());
				if(!isNaN(loginId) && loginId>0){
					var userhandler = jQuery.trim($("#loginUserHandler").html());
					if(userhandler.indexOf('@')>0){
						$(this_id_prefix+'#name').val(userhandler.substring(0,userhandler.lastIndexOf('@')));
						$(this_id_prefix+'#email').val(userhandler);
					}else{
						$(this_id_prefix+'#name').val(userhandler);
					}
		   	   	}else{
					$(this_id_prefix+'#name').val('匿名（未登录）');
				}
				
				$(this_id_prefix+'#overlay').css({display: 'block'});
				$(this).animate({"marginLeft": "-=5px"}, "fast","linear"); 
				$(this_id_prefix+'#contactForm').animate({"marginLeft": "-=0px"}, "fast","linear");
				$(this).animate({"marginLeft": "+=387px"}, "slow","linear"); 
				$(this_id_prefix+'#contactForm').animate({"marginLeft": "+=390px"}, "slow","linear"); 
			}, 
			function() {
				$(this_id_prefix+'#contactForm').animate({"marginLeft": "-=390px"}, "slow","linear");
				$(this).animate({"marginLeft": "-=387px"}, "slow","linear").animate({"marginLeft": "+=5px"}, "fast","linear"); 
				$(this_id_prefix+'#overlay').css({display: 'none'});
			});
			
			
			$(this_id_prefix+"#contactForm").ajaxForm({
				  beforeSubmit: function() {
						$(this_id_prefix+'.holder').hide();
						$(this_id_prefix+'#loading').show();
				  },
				  type: 'POST',
				  url: options.url,
				  data: {subject:options.subject, name:$(this_id_prefix+'#name').val(), email:$(this_id_prefix+'#email').val(), message:$(this_id_prefix+'#message').val()},
				  success: function(data){
						$(this_id_prefix+'#loading').css({display:'none'}); 
						if( data.saveResult) {
							$(this_id_prefix+'#callback').show().append(options.recievedMsg);
							if(options.hideOnSubmit == true) {
								//hide the tab after successful submition if requested
								$(this_id_prefix+'#contactForm').animate({dummy:1}, 2000).animate({"marginLeft": "-=390px"}, "slow");
								$(this_id_prefix+'div#contactable_inner').animate({dummy:1}, 2000).animate({"marginLeft": "-=387px"}, "slow").animate({"marginLeft": "+=5px"}, "fast"); 
								$(this_id_prefix+'#overlay').css({display: 'none'});	
							}
						} else {
							$(this_id_prefix+'#callback').show().append(options.notRecievedMsg);
							setTimeout(function(){
								$(this_id_prefix+'.holder').show();
								$(this_id_prefix+'#callback').hide().html('');
							},2000);
						}
					},
				  error:function(){
						$(this_id_prefix+'#loading').css({display:'none'}); 
						$(this_id_prefix+'#callback').show().append(options.notRecievedMsg);
	                                    }
				});	

		});
	};
 
})(jQuery);
