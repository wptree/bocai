var contextPath = $("#const_contextPath").text();
var loginId = $("#const_loginId").text();
var imageContext  = $("#const_imageContext").text();
var userCity  = $("#const_userCity").text();
//$.jTemplatesDebugMode(true);
var OP = {
		correctionModal: new Object(),
		correction: function(selector,aggSpotId){
			$("#aggSpotId").val(aggSpotId);
			OP.correctionModal = $('#correctionDiv').modal({
				overlayClose: true,
				containerCss:{"height":$('#correctionDiv').css('height'),"width":$('#correctionDiv').css('width'),"background-color":"#333" }
			});
		},
		
		toLatlng: function(latLngStr){
			var str = latLngStr, 
				idx, lat, lng, result;
			if(str){
				idx = str.indexOf('_');
				if(idx!=-1){
					lat = parseFloat(str.substring(0, idx));
					lng = parseFloat(str.substring(idx+1, str.length));
					if(!isNaN(lat) && !isNaN(lng))
						result = new google.maps.LatLng(lat, lng);
				}
			}
			return result;	
		},
		
		wantSpot: function(spotid){
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return false;
	   	   	}
			$.ajax({url: contextPath+"/spot/spot!wantSpot.bc",
				  type: "POST",
				  data: {"aggSpotId":spotid},
				  success: function(jsonObj){
					if(jsonObj.saveResult){
						if($("#want_num_"+spotid).length>0){
							$("#want_num_"+spotid).html(eval($("#want_num_"+spotid).html())+1);
						}
						global.showGlobalTip("波币 +15   ^_^");
					}else{
						global.showOperationTip("#want_num_"+spotid,jsonObj.returnMsg,"top");
					}
				  }
				});
       },
       nomSpot: function(spotid){
    	    var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return false;
	   	   	}
			$.ajax({url: contextPath+"/spot/spot!nomSpot.bc",
				  type: "POST",
				  data: {"aggSpotId":spotid},
				  success: function(jsonObj){
					if(jsonObj.saveResult){
						if($("#nom_num_"+spotid).length>0){
							$("#nom_num_"+spotid).html(eval($("#nom_num_"+spotid).html())+1);
						}
						if($("#left_nom_num_"+spotid).length>0){
							$("#left_nom_num_"+spotid).html(eval($("#left_nom_num_"+spotid).html())+1);
						}
						global.showGlobalTip("波币 +15   ^_^ ");
					}else{
						global.showOperationTip("#nom_num_"+spotid,jsonObj.returnMsg,"top");
					}
				  }
				});
       },
       countGreat: function(userid, spotid, level, indicator){
    	   if(!userid || userid==''){
   	   			global.needLogin();
   	   			return false;
   	       }
    	   $.ajax({url: contextPath+"/spot/ajax_count_spot.bc",
				  type: "POST",
				  data: {"id":spotid, "level":level},
				  success: function(jsonObj){
					if(jsonObj.saveResult){
						if($(indicator).length>0){
							$(indicator).html(jsonObj.count);
						}
						global.showGlobalTip("波币 +10   ^_^ ");
					}else if(jsonObj.tip){
						global.showOperationTip(indicator, jsonObj.tip, "top");	
					}else{
						global.showOperationTip(indicator, "服务器出小差了，请稍后再试","top");
					}
				  }
			});
       },
       
       templates: new Object(),
       
       loadSpotsOnAgg: function(aggSpotId){
    	   $.ajax({url: contextPath + "/spot/ajax_list_spot_on_agg.bc",
    		   type: "POST",
    		   data: {"id": aggSpotId},
    		   beforeSend: function(){
    			   $('#aggSpot_loading_' + aggSpotId).show();  
    		   },
    		   success: function(result){
    			   if(!result.isError){
    				   BC.Template.T({
    	    			   name: 'panel_spot',
    	    			   setting: {
    	    				   filter_data: false
    	    			   },
    	    			   domId: '#aggSpot_spot_list_' + aggSpotId,
    	    			   hintId: '#aggSpot_loading_' + aggSpotId,
    	    			   data: result,
    	    			   params: {type: 'main'},
    	    			   callback: function(){
    	   					   OP.registerCommentForm(aggSpotId);
    	   					   OP.genSpotCmtPager(aggSpotId, 1);
    	   					   $('#aggSpot_spot_list_'+aggSpotId + '>li:last-child').css("border-bottom", "none");
    	   					   $('#aggSpot_spot_list_' + aggSpotId).show(); 
    	   					   BC.scrollToAggSpot(aggSpotId);
    	    			   }
    	    		   });
    				   /*
    				   $('#aggSpot_loading_'+aggSpotId).hide();
    				   $('#aggSpot_spot_list_'+aggSpotId).setParam('context',contextPath);
    				   $('#aggSpot_spot_list_'+aggSpotId).setParam('type','main');
    				   $('#aggSpot_spot_list_'+aggSpotId).setParam('currentUser',result.currentUser);
   					   $('#aggSpot_spot_list_'+aggSpotId).processTemplate(result);
   					   $('#aggSpot_spot_list_'+aggSpotId+' span[id*=timeAgo]').each(function(){
   						   $(this).html($.timeago($(this).html()));
   					   });
   					   */
    			   }else{    				   
    				   $('#aggSpot_loading_' + aggSpotId + '>li').html("服务器出小差了，请稍后再试");
    			   }
    		   },
    		   error: function(){
    			   $('#aggSpot_loading_' + aggSpotId + '>li').html("服务器出小差了，请稍后再试");
    		   },
    		   complete: function(){
    			   if(BC.Template.cache['panel_spot']){
    				   $('#aggSpot_loading_' + aggSpotId).hide();
    			   }
    		   }
    	   });
       },
       loadCmtOnProfile: function(spotId, pageAt){
    	   $.ajax({url: contextPath + "/spot/ajax_page_comment_in_spot.bc",
    		   type: "POST",
    		   data: {"spotId": spotId,
    			      "pageAt": pageAt},
    		   success: function(result){
    			   if(!result.isError){
    				   BC.Template.T({
    	    			   name: 'profile_review_list',
    	    			   domId: '#spot_comment_' + spotId,
    	    			   data: result.vo,
    	    			   setting: {
    	    				   filter_data: false
    	    			   },
    	    			   callback: function(){
    	    				   registerForm(spotId);
    	    			   }
    	    		   });
    			   }
    		   },
    		   error: function(){
    		   }
    	   });  
       },
       switchSighting: function(aggSpotId){
    	   if($('#sighting_btn_'+aggSpotId).hasClass('sighting-off')){
    		   $('#sighting_btn_'+aggSpotId).removeClass('sighting-off');
    		   $('#sighting_btn_'+aggSpotId).addClass('sighting-on');
    		   $('#aggSpotPanel_control_'+aggSpotId).addClass('control-on');
    		   $('#aggSpotPanel_control_'+aggSpotId).removeClass('bbr5');
    		   var html = $('#aggSpot_spot_list_'+aggSpotId).html();
    		   if(html){
    			   $('#aggSpot_spot_list_'+aggSpotId).show();
    		   }else{
    			   OP.loadSpotsOnAgg(aggSpotId);
    		   }
    	   }else{
    		   $('#sighting_btn_'+aggSpotId).removeClass('sighting-on');
    		   $('#sighting_btn_'+aggSpotId).addClass('sighting-off');
    		   $('#aggSpotPanel_control_'+aggSpotId).removeClass('control-on');
    		   $('#aggSpotPanel_control_'+aggSpotId).addClass('bbr5');
    		   $('#aggSpot_spot_list_'+aggSpotId).hide();
    	   }
       },
       
       switchComments: function(spotId){
    	   if($('#sighting_btn_'+spotId).hasClass('sighting-off')){
    		   $('#sighting_btn_'+spotId).removeClass('sighting-off');
    		   $('#sighting_btn_'+spotId).addClass('sighting-on');
    		   $('#spot_control_'+spotId).addClass('control-on');
    		   $('#spot_comment_'+spotId).show();
    		   OP.loadCmtOnProfile(spotId, 0);
    	   }else{
    		   $('#sighting_btn_'+spotId).removeClass('sighting-on');
    		   $('#sighting_btn_'+spotId).addClass('sighting-off');
    		   $('#spot_control_'+spotId).removeClass('control-on');
    		   $('#spot_comment_'+spotId).hide();
    	   }
       },
       focusCommentBox: function(spotId){
    	   var loginId = parseFloat($('#const_loginId').text());
    	   if(isNaN(loginId) || loginId<=0){
   	   	     global.needLogin();
   	   		 return false;
   	   	   }
    	   OP.countInputStrByte('#comment_text_review_' + spotId, 
    			   '#input_num_count_' + spotId, 140);
    	   $('#comment_text_review_out_' + spotId).addClass('cmt-focused');
    	   $('#input_num_count_out_' + spotId).show();
   		   $('#comment_text_review_' + spotId).addClass('focused');
       },
       blurCommentBox: function(spotId){
    	   $('#comment_text_review_out_' + spotId).removeClass('cmt-focused');
    	   if($('#comment_text_review_' + spotId).val() == ""){
       		   $('#comment_text_review_' + spotId).removeClass('focused');
       		   $('#input_num_count_out_' + spotId).hide();
    	   }
       },
       
       countInputStrByte: function(taId, counterId, max){
    	   var byteCount=0; 
    	   var len=$(taId)[0].value.length;
    	   $(counterId).html(len);
    	   if(len <= max){
    		   $(counterId).removeClass('cerror');
    	   }else{
    		   $(counterId).addClass('cerror');
    	   }
       },
       
       registerCommentForm: function(aggSpotId){
    	   if($("#aggSpot_" + aggSpotId + " form").length==0)
    		   return;
    	   $("#aggSpot_" + aggSpotId + " form").each(function(){
    		   var thisform = $(this);
    		   $(this).ajaxForm({
        		   dataType : "json",
       			   beforeSubmit : function (arr, form, options){
       				   var id = $(form).attr('id');
       				   var pos = id.lastIndexOf('_'); 
       				   var spotId = id.substring(pos+1, id.length);
       				   var content = thisform.find('textarea').val();
       				   if(!spotId || !content || $.trim(content).length == 0){
       					   return false;
       				   }
       			   },
       			   success : function (response, statusText, xhr, $form){
       				   if(!response.isError){
       					   var id = $form.attr('id');
         				   var pos = id.lastIndexOf('_'); 
         				   var spotId = id.substring(pos+1, id.length);
         				   if($('#comments_review_' + spotId).hasClass('empty')){
         					  $('#comments_review_' + spotId).removeClass('empty');
         					  $('#comments_review_' + spotId).empty();
         				   }
         				   BC.Template.T({
	       	    			   name: 'comment_item',
	       	    			   domId: '#cmtItemTemplate',
	       	    			   data: response.vo,
	       	    			   setting: {
	       	    				   filter_data: false
	       	    			   },
	       	    			   params: {
	       	    				   type: 'main',
	       	    				   spotId: spotId 
	       	    			   },
	       	    			   callback: function(){
	       	    				   $('#comments_review_' + spotId + ".empty").replaceWith('');
	       	    				   $('#comments_review_' + spotId).prepend($('#cmtItemTemplate').html());
	       	    				   $('#review_comment_' + response.vo.id).slideDown();
	       	    				   $('#review_comment_' + response.vo.id).animate({backgroundColor:"#FFFCDD"},200);
	         					   window.setTimeout(function(){
	         						  $('#review_comment_' + response.vo.id).animate({backgroundColor:"#F7F6F1"},1000);
							        }, 3000);
	         					  $('#comment_text_review_' + spotId).val('');
	            				  $('#comment_text_review_' + spotId).blur();
	       	    			   }
         				   });
       				   }
       			   }
       		   });
    		   
    	   });
       },
       
       loadJTemplate: function(name, successHandler, errorHandler){
    	   $.ajax({url: contextPath + "/jtemplate.bc",
    		   type: "POST",
    		   data: {"name": name},
    		   success: function(result){
    			   if(result.succeed){
    				   OP.templates["jtemplate_"+name] = result.model.content;
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
    		   }
    	   });
       },
       genCmtPager: function(domId, spotId, at, vNo, tNo){
    	   //clean the pager
    	   $(domId).html('');
    	   //var tNo = 20, at = 9, vNo = 6;
    	   var nos = [at], i, ls, rs;
    	   ls = Math.max(at - Math.round(vNo/2), 0);
    	   rs = Math.min(at + Math.round(vNo/2), tNo);
    	   rs = Math.max(rs, Math.min(at+(vNo-1-ls), tNo));
    	   for (i=at+1; i <= rs; i++){
    		   nos.push(i);
    	   }
    	   //ls = Math.min(ls, at-Math.max(vNo-1-(rs-at), 0));
    	   for(i=at-1; i>ls ; i--){
    		   nos.push(i);
    	   }
    	   nos.sort(function(x,y){
    		   return y-x;
    	   });
    	   if(rs!=tNo){
    		   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+',' + tNo + ')" class="pager-el">></a>');
    	   }
    	   for (i= 0; i<nos.length; i++){
    		   if(rs !=tNo && i==0){
    			   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+',' + nos[i] + ')" class="pager-el">' + nos[i] + '...</a>');
    			   continue;
    		   }
    		   if(ls != 0 && i == nos.length-1){
    			   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+',' + nos[i] + ')" class="pager-el">...' + nos[i] + '</a>');
    			   continue;
    		   }
    		   if(nos[i] != at ){
    			   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+',' + nos[i] + ')" class="pager-el">' + nos[i] + '</a>');
    		   }else{
    			   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+',' + nos[i] + ')" class="pager-el on">' + nos[i] + '</a>');
    		   } 
    	   }
    	   if(ls!=0){
    		   $(domId).append('<a href="javascript:void(0);" onclick="OP.pageCmt('+spotId+', 1)" class="pager-el"><</a>');
    	   }
    	   $(domId).append('<div class="clear">' + nos[i] + '</div>');
       },
       genSpotCmtPager: function(aggSpotId, at){
    	   if($('#aggSpot_' + aggSpotId + ' div.comment-pager').length == 0){
    		   return;
    	   }
    	   $('#aggSpot_' + aggSpotId + ' div.comment-pager').each(function(idx){
    		   var id = $(this).attr('id');
			   var pos = id.lastIndexOf('_'); 
			   var spotId = id.substring(pos+1, id.length);
			   var tNo = $('#comment_tNo_hidden_' + spotId).val();
			   if($('#spot-' + spotId + ' div.comment-row').length != 0){
				   OP.genCmtPager($(this)[0], spotId, at, 6, tNo);
			   }
    	   });
       },
       pageCmt:function(spotId, at){
    	   $.ajax({url: contextPath + "/spot/ajax_page_comment.bc",
    		   type: "POST",
    		   data: {"spotId" : spotId,
    			   	  "pageAt" : at},
    		   success: function(result){
    			   if(result.succeed){
    				   var successHandler = function(obj){
    					   var domId = '#comments_review_'+spotId;
    					   var pagerDomId = '#comments_pager_' + spotId;
    					   if(!$(domId).hasTemplate()){
    						   $(domId).setTemplate(OP.templates["jtemplate_comment_list"]);
    					   }
     					   $(domId).processTemplate(result.pager);
     					   $(domId).find("span[id*=timeAgo]").each(function(){
     						  $(this).html($.timeago($(this).html()));
     					   });
    					   
     					  
     					   OP.genCmtPager(pagerDomId, spotId, result.pageAt, 6, result.pageNo);
     				   };
    				   if(!OP.templates["jtemplate_comment_list"]){
	     				   OP.loadJTemplate('comment_list', successHandler, null);
     				   }else{
     					  successHandler(null);
     				   }
    			   }
    		   }
    	   });
       },
       getBySameDish: function(dish){
    	   var url = contextPath + "/main.bc#";
    	   var params = {
				keyword : dish,
				keywordType: 'DISH'
    	   };
    	   url += "/"+$.param(params);
    	   location.href = encodeURI(url);
       },
       viewBigMap: function(){
    	   $('#view_big_map').modal({
    		    closeHTML: "<div class='h36 br5 w36 modalCloseBg cp' style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
    		    containerCss:{
    		    	boxShadow: "0 0 90px 5px #000000"
    			},
    		    overlayClose:true,
    		    onShow: function(){
    		    	var lat = $('#vbm_lat_hidden_field').val();
    				var lng = $('#vbm_lng_hidden_field').val();
    				$("#big_map").gmap3(
    						{ action: 'addMarker',
    						  latLng: new google.maps.LatLng(lat,lng),
    						  map: {
    							center: [lat, lng],
    				     		zoom: 14,
    				     		disableDoubleClickZoom: false,
    				     		scrollwheel: false,
    				     		navigationControl: true,
    				     		mapTypeControl: false,
    				     		navigationControl: true,
    				     		navigationControlOptions: {
    				     			position : google.maps.ControlPosition.RIGHT,
    				     			style : google.maps.NavigationControlStyle.SMALL
    				     		},
    				     		noClear: true,
    				     		scaleControl: false,
    							streetViewControl: false
    						  }
    				        }
    				);
    		    }
    		});
       },
       tipFormError: function($form, position){
    	   $form.find('input, textarea')
    	   	  .each(function(idx, ele){
    	   		  var f = $(ele).data('failed');
    	   		  if(f){
    	   			  BC.Dom.errShow($(ele).attr('id'));
    	   			  global.showOperationTip(ele, f, position);
    	   		  }
    	   	  });
       },
       isAddingPlace: false,
       isAddingSuccess: false,
       addPlace:function(setting){
    	   $('#add_place').modal({
   		    closeHTML: "<div class='h36 w36 br5 modalCloseBg cp' style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
   		    containerCss:{
   		    	borderRadius: "5px",
   		    	boxShadow: "0 0 90px 5px #000000"
   			},
   		    onShow: function(){
   		    	OP.isAddingSuccess = false;
   		    	$('#simplemodal-container #place_name_input').defaultValue();
   		  		$('#simplemodal-container #place_secondary_name_input').defaultValue();
   		  		$('#simplemodal-container #place_city_input').defaultValue();
   		  		$('#simplemodal-container #place_address_input').defaultValue();
   		  		$('#simplemodal-container #place_lat_lng_input').defaultValue();
   		  		$('#simplemodal-container #place_phone_input').defaultValue();
   		  		
   		  		function validatePlace(formData, jqForm, options){
	   		  		$('#simplemodal-container #add_place_form input[label]')
	   		  			.each(function(idx, ele){
							var val = $(ele).val();
							if(!val){
								$(ele).val($(ele).attr('label'));
								$(ele).addClass('def-val');
							}
					});
	   		  		var failed = false;
	   		  	    $('#simplemodal-container #add_place_form input[must]')
	   		  	    	.each(function(idx, ele){
							var val = $(ele).val();
							var label = $(ele).attr('label');
							$(ele).data('failed', '');
							if(!val || val == label){
								$(ele).data('failed', '必填项，不能为空');
						    	failed = true;
							}
					});
	   		  	    if(failed){
	   		  	    	OP.tipFormError($('#simplemodal-container #add_place_form'), 'left');
	   		  	    }
	   		  	    return !failed;
   		  		}
   		  		
   				$('#simplemodal-container #add_place_form').ajaxForm({
   					dataType : "json",
   					beforeSubmit : function (arr, form, options){
   						var valid = validatePlace(arr, form, options);
   						if(valid){
	   						var acs = $('#simplemodal-container #place_address_input').data('acs');
	   						if(acs){
	   							for (var i =0 ; i< acs.length; i++){
	   								arr.push({name: 'acs', value : JSON.stringify(acs[i])});
	   							}
	   						}
	   						OP.isAddingPlace = true;
	   						$('#simplemodal-container #add_place_spinner').show();
	   						return true;
   						}else{
   							return false;
   						}
   					},
   					success : function (response, statusText, xhr, $form){
   						setting.callback.call(this, response);
   						if(!response.isError){
   							OP.isAddingSuccess = true;
   							OP.isAddingPlace = false;
   							$.modal.close();
   						}
   					},
   					error: function(){
   						
   					},
   					complete: function(){
   						OP.isAddingPlace = false;
   						$('#simplemodal-container #add_place_spinner').hide();
   					}
   				});
   		    	
   				OP.addMarker({
   					selector: '#simplemodal-container #place_refer_map',
   					center : setting.center,
   					zoom: setting.zoom
   				});
   				$('#simplemodal-container #place_address_input').change(function(){
   		  	    	var address = $(this).val();
   		  	    	var label = $(this).attr('label');
	   		  	    $('#simplemodal-container #place_lat_lng_input')
	   		  	    	.val(function(){
	   		  	    		return $(this).attr('label');
	   		  	    	});
	   		  	    $('#simplemodal-container #place_lat_lng_input')
	   		  	        .addClass('def-val');
	   		  	    if(!address || address == label) return;
	   		  	    var address = $('#simplemodal-container #place_city_input').val() +
	   		  	    	address;
   		  	    	$('#simplemodal-container #place_refer_map').gmap3({
   		  	    		action: "getLatlng",
   		  	    		address: address,
   		  	    		callback: function(results){
   		  	    			var acs = results[0].address_components;
   		  	    			var latlng = results[0].geometry.location;
   		  	    			$('#simplemodal-container #place_address_input').data('acs', acs);
   		  	    			$(this).gmap3({
				 				action: 'getZoom',
				 				callback: function(zoom){
				 					var zom = Math.max(zoom, 12);
				 					$(this).gmap3({
		   		  	    				action: 'clear',
		   		  	    				name: 'marker'
		   		  	    			},{
				 			  			action: "panTo",
				 						args: [latlng]
				 					},{
				 					    action: "setZoom",
				 					    args: [zom]
				 					});
				 					$('#simplemodal-container #place_lat_lng_input')
				 						.val(latlng.lat()+"_"+
		   									latlng.lng());
		   							$('#simplemodal-container #place_lat_lng_input')
		   								.removeClass('def-val');
				 					OP.addMarker({
			   		   					selector: this,
			   		   					center : latlng,
			   		   					zoom: zom
			   		   				});
				 				}
				 			});
   		  	    		}
   		  	    	});
   		  	    });
   				/*
   				$("#simplemodal-container #place_refer_map").gmap3({
   					action: 'addMarker',
					latLng: setting.center,
					map:{
						center: true,
						zoom: setting.zoom,
						disableDoubleClickZoom: false,
        	     		scrollwheel: false,
        	     		mapTypeControl: false,
        	     		navigationControl: true,
        	     		noClear: true,
        	     		scaleControl: false,
        				streetViewControl: false
						},
					marker: {
						options:{
							draggable:true
						},
						events:{
							drag: function(marker){
								var latlng = marker.getPosition();
								$('#simplemodal-container #place_lat_lng_input').val(latlng.lat()+"_"+
										latlng.lng());
								$('#simplemodal-container #place_lat_lng_input').removeClass('def-val');
							},
							dragend: function(marker){
								$(this).gmap3({
									action:'getAddress',
									latLng:marker.getPosition(),
									callback:function(results){
										if(results && results[0]){
				   							var acs = results[0].address_components;
				   							var i = Math.max(acs.length - 3, 0)  ;
				   							$('#simplemodal-container #place_city_input').val(OP.parseCity(acs));
				   							$('#simplemodal-container #place_city_input').removeClass('def-val');
				   							$('#simplemodal-container #place_address_input').val(results[0].formatted_address);
				   							$('#simplemodal-container #place_address_input').removeClass('def-val');
				   							$('#simplemodal-container #place_address_input').data('acs', acs);
				   						}
									}
								});
							}
						}
					}
   				},{
   					action:'getAddress',
   					latLng: setting.center,
   					callback: function(results){
   						if(results && results[0]){
   							var acs = results[0].address_components;
   							var i = Math.max(acs.length - 3, 0);
   							$('#simplemodal-container #place_city_input').val(OP.parseCity(acs));
   							$('#simplemodal-container #place_city_input').removeClass('def-val');
   						}
   					}
   				});
   				*/
   				$('#simplemodal-container #place_name_input').val(setting.name);
   				$('#simplemodal-container #place_name_input').removeClass('def-val');
   		    },
   		    onClose: function(){
   		    	if(!OP.isAddingPlace){
   		    		$.modal.close();
   		    	}
   		    	if(!OP.isAddingSuccess){
   		    		setting.drop.call(this);
   		    	}
   		    }
   		});
       },
       addMarker: function(arg){
    	   // init map
    	   var map = $(arg.selector).gmap3('get');
    	   if(!map){
	    	   var param0 = {
	    	       action: "init",
	    	       options: {
	    	    	   center: arg.center,
		      		   zoom: arg.zoom,
		      		   disableDoubleClickZoom: false,
		      	   	   scrollwheel: false,
		      	   	   mapTypeControl: false,
		      	   	   navigationControl: true,
		      	   	   noClear: true,
		      	   	   scaleControl: false,
		      	   	   streetViewControl: false
	    	       },
	   			   events: {
	   					zoom_changed: function(zoom){
	   						var markers = $(this).gmap3({
	   							action: 'get',
	   							name: 'marker',
	   							all: true
	   						});
	   						if(markers && markers[0]){
	   							var pos = markers[0].getPosition();
	   							if(pos){
	   								$(this).gmap3({
	   			 			  			action: "panTo",
	   			 						args: [pos]
	   			 				    });
	   							}
	   						}
	   					}
	   				}
	    	   };
    	   }
    	   
    	   //add marker
    	   var param1 = {
    		   action: 'addMarker',
   			   latLng: arg.center,
   			   marker: {
   					options:{
   						draggable:true
   					},
   					events:{
   						drag: function(marker){
   							var latlng = marker.getPosition();
   							$('#simplemodal-container #place_lat_lng_input').val(latlng.lat()+"_"+
   									latlng.lng());
   							$('#simplemodal-container #place_lat_lng_input').removeClass('def-val');
   						},
   						dragend: function(marker){
   							$(this).gmap3({
   								action:'getAddress',
   								latLng:marker.getPosition(),
   								callback:function(results){
   									if(results && results[0]){
   			   							var acs = results[0].address_components;
   			   							var i = Math.max(acs.length - 3, 0)  ;
   			   							$('#simplemodal-container #place_city_input').val(OP.parseCity(acs));
   			   							$('#simplemodal-container #place_city_input').removeClass('def-val');
   			   							$('#simplemodal-container #place_address_input').val(results[0].formatted_address);
   			   							$('#simplemodal-container #place_address_input').removeClass('def-val');
   			   							$('#simplemodal-container #place_address_input').data('acs', acs);
   			   						}
   								}
   							});
   						}
   					}
   				}
    	   };
    	  
    	   // get address
    	   var param2 = {
    		 action:'getAddress',
			 latLng: arg.center,
			 callback: function(results){
				 if(results && results[0]){
					var acs = results[0].address_components;
				 	var i = Math.max(acs.length - 3, 0);
				 	$('#simplemodal-container #place_city_input').val(OP.parseCity(acs));
				 	$('#simplemodal-container #place_city_input').removeClass('def-val');
				}
			 } 
    	   };
    	   
    	   if(map){
    		   $(arg.selector).gmap3(param1, param2);
    	   }else{
    		   $(arg.selector).gmap3(param0, param1, param2);
    	   }
    	   
       },
       parseCity: function(acs){
    	   for(var i=0; i < acs.length; i++){
    		   var data = acs[i];
    		   if(data.types && data.types[0] == "locality"){
    			   return data.short_name;
    		   }
    	   }
       },
       sendPrivateMsg: function(targetId, targetName){
    	   var loginId = parseFloat($('#const_loginId').text());
    	   if(isNaN(loginId) || loginId<=0){
   	   	     global.needLogin();
   	   		 return false;
   	   	   }
    	   if(targetId && targetName){
    		   $('#spm_tn_input').val(targetName);
    		   $('#spm_tn_input').attr('disabled', 'disabled');
    		   $('#spm_tn_hidden').val(targetName);
    		   $('#spm_uid_hidden').val(targetId);
    		   $('#spm_tn_input_mock').hide();
    	   }else{
    		   $('#spm_tn_input').attr('disabled', '');
    		   $('#spm_tn_input_mock').show();
    		   $('#spm_tn_input').val('');
    		   $('#spm_tn_hidden').val('');
    	   }
    	   $('#send_private_msg').modal({
    		    focus: false,
   		    	closeHTML: "<div class='h36 w36 modalCloseBg cp' style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
   		    	containerCss:{
   		    		boxShadow: "0 0 90px 5px #000000"
   		    	},
   		    	overlayClose:true,
   		    	onShow: function(){
   		    		$('#spm_error_span').hide();
   		    		$('#spm_num_count').html('0');
   		    		$('#spm_content_ta').focus();
   		    	}
    	   });
       },
       addDBMap:function(type, spotId, lat, lng){
    	   $('#review_map_' + type + '_' + spotId + " .map").gmap3(
					{ action: 'addMarker',
						  latLng: new google.maps.LatLng(lat,lng),
						  map: {
							center: [lat, lng],
				     		zoom: 14,
				     		disableDoubleClickZoom: false,
				     		scrollwheel: false,
				     		navigationControl: true,
				     		mapTypeControl: false,
				     		navigationControl: true,
				     		navigationControlOptions: {
				     			position : google.maps.ControlPosition.RIGHT,
				     			style : google.maps.NavigationControlStyle.SMALL
				     		},
				     		noClear: true,
				     		scaleControl: false,
							streetViewControl: false
						  }
				   }
				);
       },
       expandDBMap: function(type, spotId){
    	   $('#review_' + type + '_' + spotId).animate({
			   width: '545px'
		   }, 300);
    	   var map = $('#review_map_' + type + '_' + spotId + " .map").gmap3('get');
    	   if(!map){
    		   var lat = $('#review_map_' + type + '_' + spotId + " .map").data('lat');
        	   var lng = $('#review_map_' + type + '_' + spotId + " .map").data('lng');
    		   OP.addDBMap(type, spotId, lat, lng);
    	   }
       },
       collapDBMap: function(type, spotId){
    	   $('#review_' + type + '_' + spotId).animate({
			   width: '280px'
		   }, 300);
       },
       toggleDBMap: function(type, spotId){
    	   var width = $('#review_'  + type + '_' +  spotId).css('width');
    	   if(width == '280px'){
    		   OP.expandDBMap(type, spotId);
    	   }else{
    		   OP.collapDBMap(type, spotId);
    	   }
       },
       
       expandPRMap: function(type, spotId){
    	   $('#review_' + type + '_' + spotId).animate({
			   width: '600px'
		   }, 300);
    	   var map = $('#review_map_' + type + '_' + spotId + " .map").gmap3('get');
    	   if(!map){
    		   var lat = $('#review_map_' + type + '_' + spotId + " .map").data('lat');
        	   var lng = $('#review_map_' + type + '_' + spotId + " .map").data('lng');
    		   OP.addDBMap(type, spotId, lat, lng);
    	   }
       },
       collapPRMap: function(type, spotId){
    	   $('#review_' + type + '_' + spotId).animate({
			   width: '320px'
		   }, 300);
       },
       togglePRMap: function(type, spotId){
    	   var width = $('#review_'  + type + '_' +  spotId).css('width');
    	   if(width == '320px'){
    		   OP.expandPRMap(type, spotId);
    	   }else{
    		   OP.collapPRMap(type, spotId);
    	   }
       },
       
       toggleDBCmt: function(type, spotId){
    	   $('#comments_out_' + type + "_" + spotId).toggle(300, null, function(){
    		   var isVisible= $('#comments_out_' + type + "_" + spotId).is(':visible');
        	   if(isVisible){
        		  if($.trim($('#comments_' + type + "_" + spotId).html())==""){
        			  OP.loadCmtOnDB(type, spotId, 0);
        		  }
        	   }
    	   });
       },
       loadCmtOnDB: function(type, spotId, pageAt){
    	   $('#cmt_loading_'  + type + "_" + spotId).show();
    	   $.ajax({url: contextPath + "/spot/ajax_page_comment_in_spot.bc",
    		   type: "POST",
    		   data: {"spotId": spotId,
    			      "pageAt": pageAt},
    		   success: function(result){
    			   if(!result.isError){
    				   BC.Template.T({
    	    			   name: 'dashboard_spot_cmt_list',
    	    			   domId: '#comments_' + type + "_" + spotId,
    	    			   data: result.vo,
    	    			   setting: {
    	    				   filter_data: false
    	    			   },
    	    			   params: {
    	    				   type: type
		   			       },
    	    			   callback: function(){
    	    				   OP.registerDBForm(type, spotId);
    	    			   }
    	    		   });
    			   }
    		   },
    		   error: function(){
    		   },
    		   complete:function(){
    			   $('#cmt_loading_'  + type + "_" + spotId).hide();
    		   }
    	   });  
       },
       focusDBCmt: function(type, spotId){
    	   var loginId = parseFloat($('#const_loginId').text());
    	   if(isNaN(loginId) || loginId<=0){
   	   	     global.needLogin();
   	   		 return false;
   	   	   }
    	   OP.countInputStrByte('#spot_cmt_ta_' + type + '_' + spotId, 
    			   '#input_num_count_' + type + '_' + spotId, 140);
    	   $('#input_num_count_out_' + type + '_' + spotId).show();
    	   $('#spot_cmt_ta_out_' + type + '_' + spotId).addClass('cmt-focused');
    	   $('#spot_cmt_ta_' + type + '_' + spotId).addClass('focused');
       },
       blurDBCmt: function(type, spotId){
    	   $('#spot_cmt_ta_out_' + type + '_' + spotId).removeClass('cmt-focused');
    	   if($('#spot_cmt_ta_' + type + '_' + spotId).val() == ""){
       		   $('#spot_cmt_ta_' + type + '_' + spotId).removeClass('focused');
       		   $('#input_num_count_out_' + type + '_' + spotId).hide();
    	   }
       },
       registerDBForm: function(type, spotId){
    	   $('#spot_cmt_form_' + type + '_' + spotId).each(function(){
    		   var thisform = $(this);
    		   $(this).ajaxForm({
        		   dataType : "json",
       			   beforeSubmit : function (arr, form, options){
       				   var content = thisform.find('textarea').val();
       				   if(!spotId || !content || $.trim(content).length == 0){
       					   return false;
       				   }
       			   },
       			   success : function (response, statusText, xhr, $form){
       				   if(!response.isError && response.vo){
         				   BC.Template.T({
         					  name: 'dashboard_spot_cmt_item',
       	    			   	  domId: '#cmtItemTemplate',
       	    			      data: response.vo,
       	    			      setting: {
       	    			    	filter_data: false  
       	    			      },
       	    			      params: {
       	    			    	  spotId: spotId,
       	    			    	  type: type
       	    			      },
       	    			      callback: function(){
       	    			    	  $('#spot_cmt_list_' + type + '_' +  spotId).prepend($('#cmtItemTemplate').html());
       	    			    	  $('#spot_cmt_list_' + type + '_' +  spotId + " > li:first-child").slideDown();
       	    			    	  var cmtNum = parseFloat($('#cmt_num_'+ type + '_' + spotId).html());
       	    			    	  if(!isNaN(cmtNum)){
       	    			    		 cmtNum += 1;
       	    			    		 $('#cmt_num_'+ type + '_' + spotId).html(cmtNum);
       	    			    	  }
       	    			    	  $('#cmt_item_empty_'+ type + '_' + spotId).replaceWith('');
       	    			    	  thisform.find('textarea').val('');
       	    			    	  thisform.find('textarea').blur();
       	    			      }
         				   });
       				   }
       			   }
       		   });
    		   
    	   });
        },
        getDialogDetail: function(id, at){
        	$('#db_msg_reminder_out').hide();
			$('#db_msg_private_out').hide();
			$('#db_msg_private_detail').html('');
			$('#db_msg_private_detail_out').show();
        	var url = contextPath + '/user/ajax_list_private_msg_detail.bc?targetId=' + id + '&at=' + at;
        	$('#dialog_detail_loading').show();
			$.get(url, function(response){
				if(!response.isError){
					BC.Template.T({
						name: 'dashboard_msg_private_letter_detail_list',
	   			   	    domId: '#db_msg_private_detail',
	   			   	    hintId: '#dialog_detail_loading',
	   			      	data: response.pager,
	   			      	params: {
	   			      		cp: response.dialogVo.counterParty
	   			      	},
	   			      	callback: function(){
	   			      		OP.updateDialogDetailPager(id,
	   			      				response.pager.pageNo, 
	   			      				response.pager.totalPage);
		   			      	$('#db_msg_private_detail > li.new').animate({
				   				backgroundColor: '#F7F6F1'
				   			}, 3000);
	   			      	}
					});
				}else{
					//$('#db_following')
				}
		    }).complete(function(){
		    	$('#dialog_detail_loading').hide();
		    });
        },
        updateDialogDetailPager: function(id, at, total){
			BC.WG.pager.pageClkFunc = function(pageNo){
				OP.getDialogDetail(id, pageNo);
			};
			BC.WG.pager.pagination("divpagingup_private_detail",at,total); 
		},
        returnDialogList: function(){
        	$('#db_msg_reminder_out').hide();
			$('#db_msg_private_detail_out').hide();
			$('#db_msg_private_out').show();
        },
        validateSignIn: function(formData, jqForm, options){
		    var failed = false;
		    $('#firstEmail_input, #password_input')
	  	    	.each(function(idx, ele){
	  	    		$(ele).data('failed', '');
	  	    		var val = $(ele).val();
					if($.trim(val)==''){
						var name = $(ele).attr('name');
						if(name.indexOf('firstEmail')!=-1){
							$(ele).data('failed', 
									'登录邮箱不能为空');
						}else if(name.indexOf('password')!=-1){
							var id=$(ele).attr('id');
							$('#' + id + '_mask').data('failed', 
									'密码不能为空');
						}
				    	failed = true;
					}
			});
		    if(failed){
		    	$('#sign_in input[label],'+
					'#sign_in textarea[label]').each(function(idx, ele){
					var val = $(ele).val();
					if(!val){
						$(ele).val($(ele).attr('label'));
						$(ele).addClass('def-val');
					}
				});
		  	    OP.tipFormError($('#sign_in'), 'topleft');
		  	}else{
		  		$('#sign_in_form .loading').show();
		  	}
		    return !failed;
		},
        popupSignIn: function(){
    	    $('#sign_in').modal({
    	    	focus: false,
    	    	overlayClose:true,
			    closeHTML: "<div class='h36 w36 br5 modalCloseBg cp' " +
			    		"style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
			    containerCss:{
			    	borderRadius: "5px",
			    	boxShadow: "0 0 90px 5px #000000"
				},
				onShow: function(){
			    	$('#firstEmail_input,'+
			    			'#password_input').defaultValue();
					$('#sign_in_form').ajaxForm({
						dataType : "json",
						beforeSubmit : OP.validateSignIn,
						success : function (data, statusText, xhr, $form){
							if(!data)return;
							if(data.fieldErrors){
								$.each(data.fieldErrors, function(key, value) {
									if(value){
										$('#sign_in input[name='+key+']')
											.data('failed' ,value.join(';'));
									}
								});
								OP.tipFormError($('#sign_in'), 'topleft');
							}else if(data.isError){
								$('#sign_in input[name=firstEmail]')
									.data('failed' ,data.errorMsg);
								OP.tipFormError($('#sign_in'), 'topleft');
							}else if(data.loginUser){
								$('#const_loginId').text(data.loginUser.id);
								BC.Template.T({
									name: 'sign_in_header',
				   			   	    domId: '#welcome_bar',
				   			      	data: data.loginUser,
				   			      	callback: function(){
				   			      		$('.sign_in_clean').remove();
				   			      	}
								});
								$.modal.close();
							}
						},
						error: function(){
							$('#sign_in input[name=firstEmail]')
								.data('failed' ,'服务器出小差了，请稍后再试。');
							OP.tipFormError($('#sign_in'), 'topleft');
						},
						complete: function(){
							$('#sign_in_form .loading').hide();
						}
					});
			    },
			    onClose: function(){
			    	$.modal.close();
			    }
    	    });
        },
        refreshLoginHeader: function(loginUser){
        	if(loginUser!=null){
        		$('#const_loginId').text(loginUser.id);
				BC.Template.T({
					name: 'sign_in_header',
   			   	    domId: '#welcome_bar',
   			      	data: loginUser,
   			      	callback: function(){
   			      		$('.sign_in_clean').remove();
   			      	}
				});
        	}else{
        		$('#const_loginId').text('');
        		var staticContext = $("#const_staticContext").text(); 
        		$('#welcome_bar').html(
        			'<li class="wel_li"><div class="clean">' +
        				'<a title="注册" class="ahelpc_re r" href="'+contextPath+'/sign_up.bc" id="login">注册</a>' +
        					'<span style="text-align:center" class="r mr4">|</span>' +
        				'<a title="登录" class="ahelpc r mr4" href="'+contextPath+'/login.bc" id="login">登录</a></div></li>' +
        			'<li class="wel_li"><div class="r">'+
        				'<a title="用新浪微博帐户登陆" href="javascript:void(0);" style="margin-top:20px;margin-left:2px\9">'+
        					'<img alt="微博帐号登陆" title="用新浪微博帐户登陆" id="sina_weibo" src="'+staticContext+'/images/sina_16.png"/></a></div></li>');
        	}
        },
        showBokeListError: function(error){
			var len = $('#boke_error').length;
			if(len == 0){
				$("#bokes").prepend("<li id='boke_error' class='error cerror' style='display:none;'></li>");
			}
			$("#boke_error").show();
			$("#boke_error").html(error);
		},
		updateBokeListPager: function(at, total, type){
			BC.WG.pager.pageClkFunc = function(pageNo){
				if(type=="search"){
					$('#pageAt_hidden_field').val(pageNo);
					$('#boke_search_form').submit();
				}else{
					getUsers(type, pageNo);
				}
			};
			BC.WG.pager.pagination("divpagingup",at,total); 
		},
		popUpSelectCityPanel:function(){
			CityPicker.show('#location', function(cityname){
				$('#loc_hidden_field').val(cityname);
				BC.hideLocations();
			});
		},
		replyTo: function(spotId, targetUserId, targetUserName, cmtId){
			$('#comment_reply_hidden_' + spotId).val(targetUserId+':'+targetUserName + ':' + cmtId);
			$('#comment_text_review_' + spotId).val('回复@'+targetUserName+': ');
			$('#comment_text_review_' + spotId).focus();
		},
		dashboardReplyTo: function(spotId, type, targetUserId, targetUserName, cmtId){
			$('#spot_cmt_reply_hidden_' + type+ '_' + spotId).val(targetUserId+':'+targetUserName + ':' + cmtId);
			$('#spot_cmt_ta_' + type+ '_' + spotId).val('回复@'+targetUserName+': ');
			$('#spot_cmt_ta_' + type+ '_' + spotId).focus();
		}
};