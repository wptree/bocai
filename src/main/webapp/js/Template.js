BC = BC || {};

BC.Template = {
		
	cache: {},
	
	load: function(name, successHandler, errorHandler, completeHandler){
    	   $.ajax({url: contextPath + "/jtemplate.bc",
    		   type: "POST",
    		   data: {"name": name},
    		   success: function(result){
    			   if(!result.isError && result.succeed){
    				   BC.Template.cache[name] = result.model.content;
    				   if(successHandler)
    					   successHandler(result);
    			   }else{
    				   if(errorHandler)
    					   errorHandler();
    			   }
    		   },
    		   error: function(){
    			   if(errorHandler)
    				   errorHandler();
    		   },
    		   complete: function(){
    			   if(completeHandler)
    				   completeHandler();
    		   }
    	   });
	},
	
	T: function(param){
		var opt = $.extend({
			gen : 'replace'
		},param);
		if(!opt.name)return;
		if(opt.hintId){
			$(opt.hintId).show();
		}
		var targetId = opt.domId;
		if(opt.gen=='append' ||
		    opt.gen=='prepend'){
			if($('#'+$(targetId).attr('id')+'_mask').length==0){
				$('body').append($(targetId).clone()
						.attr('id', $(targetId).attr('id')+'_mask')
						.hide());
			}
			targetId = '#'+$(targetId).attr('id')+'_mask';
		}
		var success = function(obj){
			var staticContext = $("#const_staticContext").text(); 
			var imageContext = $("#const_imageContext").text(); 
		    if(!$(targetId).hasTemplate()){
		   	   $(targetId).setTemplate(BC.Template.cache[opt.name], opt.includes, opt.setting);
			   $(targetId).setParam('context', contextPath);
			   $(targetId).setParam('staticContext', staticContext);
			   $(targetId).setParam('imageContext', imageContext);
			   $(targetId).setParam('loginId', $('#const_loginId').text());
		    }
		    if(opt.params){
			   for (var props in opt.params){
				   $(targetId).setParam(props, opt.params[props]);
			   }
		    }
		    $(targetId).processTemplate(opt.data);
		    $(targetId + " span[id*=timeAgo]").each(function(){
			   $(this).html($.timeago($(this).html()));
		    });
		    if(opt.gen=='append'){
		    	var item = $(targetId).children().appendTo(opt.domId)
		    		.slideDown().animate({backgroundColor:"#FFFCDD"},200);
		    	window.setTimeout(function(){
		    		item.animate({backgroundColor:"#F7F6F1"},1000);
			    }, 3000);
		    }
		    if(opt.gen=='prepend'){
		    	var item = $(targetId).children().prependTo(opt.domId)
		    		.slideDown().animate({backgroundColor:"#FFFCDD"},200);
		    	window.setTimeout(function(){
		    		item.animate({backgroundColor:"#F7F6F1"},1000);
			    }, 3000);
		    }
		};
		var error = function(){
			
		};
		var complete = function(){
			if(opt.hintId){
				$(opt.hintId).hide();
			}
			if(opt.callback){
				opt.callback();
			}
		};
		if(!this.cache[opt.name]){
			this.load(opt.name, success, error, complete);
		}else{
			success(null);
			complete();
		}
	}
};