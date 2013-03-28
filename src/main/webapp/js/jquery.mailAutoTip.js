;(function($) {	
    	var mailArr=new Array("@163.com", "@qq.com", "@126.com", "@hotmail.com", "@gmail.com", "@sohu.com", "@yahoo.com.cn", "@yahoo.com", "@sina.com", "@msn.com");
    	$.fn.mailAutoTip = function(options){
            var setting = {
                subOp:"li",/*options*/
                mailArr:mailArr,//数组对象
                hoverClass:"on",/*选中效果*/
                _cur:0 /*当前的index*/
            };
            var opts = $.extend({}, setting, options || {});
            
            opts.subBox = $('<div class="mailAutoTip"></div>')
            	.css("position", "absolute")
            	.appendTo(document.body).hide();
            

            	 //tipFun函数
                var tipFun = function(_v,target){
                    opts._cur=0;
                    var _that=target;
                    if(! opts.top || !opts.left){
                    	opts.top = target.offset().top+target.outerHeight();
                    	opts.left = target.offset().left;
                    }
          
                    opts.subBox.css({top:opts.top,left: opts.left,width:target.innerWidth()});
                                
                    opts.subBox.show();
                    var str="<ul>";
                    str += "<li id=\"e_type\">&nbsp;请选择邮箱类型:</li>";
                    var e=_v.indexOf("@");
                    if(e==-1){
                        $.each(opts.mailArr,function(s,m){
                            str+='<li><a href="javascript:void(0)"  >' + _v + m + "</a></li>";							
                        });
                    }else{
                        var _sh=_v.substring(0,e);
                        var _se=_v.substring(e);
                        var ind = 0;
                        $.each(opts.mailArr, function (s,m) {
                            if(m.indexOf(_se)!=-1){
                                str += '<li><a href="javascript:void(0)" >' + _sh + m + "</a></li>";
                                ind = 1;
                            }
                        });
                        if(ind==0){
                            str += '<li><a class="cur_val" href="javascript:void(0)" >' + _v + "</a></li>";
                        }
                    }
                    str+="</ul>";
                    opts.subBox.html(str); 

                    /*绑定hover事件*/
                    opts.subBox.find(opts.subOp).hover(function(){
                        var _that=$(this);
                        _that.addClass(opts.hoverClass);					   
                    },function(){
                        var _that=$(this);
                        _that.removeClass(opts.hoverClass);			
                    });
                    /*绑定click事件*/
                    opts.subBox.find(opts.subOp).each(function(){
                        $(this).click(function(e){
                            if($(e.target).attr("id")!="e_type"){
                                target.val($(e.target).text());
                                opts.subBox.hide();
                                e.stopPropagation();
                            }
                            target.focus();
                        });											  
                    });
                };
                
            	/*itemFun*/
        		var itemFun = function(){
        			var _tempArr=opts.subBox.find(opts.subOp);
        			var _size=_tempArr.size();
        			for(var i=0;i<_size;i++){
        				_tempArr.eq(i).removeClass(opts.hoverClass);
        			}
        			
        			if(_size>1){
        				if(opts._cur>_size-1){
        					opts._cur=1;	
        				}
        				if(opts._cur<1){
        					opts._cur=_size-1;	
        				}
        				_tempArr.eq(opts._cur).addClass(opts.hoverClass);
        			}else{
        				opts._cur=1;	
        			}
        		};
            	
            	$(this).keyup(function(e){
                    var _that=$(this);
                    if(_that.val()!=""){
                        if(e.keyCode!=38 && e.keyCode!=40 && e.keyCode!=13 && e.keyCode!=27){
                            var _inputVal=_that.val();
                            tipFun(_inputVal,_that);
                        }
                    }else{
                        opts.subBox.hide();
                    }
                });

        		/*点击页面其他地方关闭提示层*/
        		$(document).bind("click",function(e){
                    opts.subBox.hide();
        		});
        		
        		$(document).keydown(function(e){
                    switch(e.keyCode){
                        case 40://下键
                            opts._cur++;
                            itemFun();
                            break;
                        case 38://上键
                            opts._cur--;
                            itemFun();
                            break;
                        default:
                           break;
                    }
        		});
        	    /*文本框keydown*/
        	    $(this).keydown(function(e){
        		    var _temp = opts.subBox.find(opts.subOp);
        		    if(e.keyCode == 13){
        		        if(opts._cur!=0){
        		            $(this).val(_temp.eq(opts._cur).text());
        			        opts._cur = 0;
        		        }
        		        opts.subBox.hide();
        			    e.stopPropagation();
        			    return false;
        		    }
        		    if(e.keyCode == 9){
        		    	opts.subBox.hide();
        			    e.stopPropagation();
        		    }
        	    });
        };	  
    })(jQuery);
    	
