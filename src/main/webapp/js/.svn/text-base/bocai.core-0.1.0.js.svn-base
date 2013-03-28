var BC = {
	noEnter: function(event){
		if(event && (event.keyCode == 13)){
    		return false;
    	}else{
    		return true;
    	}
	},
	
	ipCity: "",
	globalLat: 35.9289,
	globalLng: 116.3883,
	globalZoom: 4,
	globalMapSetting:{
		city: '全国'
		,lat: 33.5044087794152
		,lng: 118.270087125
		,swLat: 46.8599037643345
		,swLng: 177.5523136875
		,neLat: 17.7273584663218
		,neLng: 58.9878605625
		,zoom: 4
	},
	browserSupportGeo :  new Boolean(),
	msgTimeOutHolder: new Object(),
	suggestionTimeOutHolder: new Object(),
	
	mapTooltipContent : function(spot) {
	 	var content = "<img src='" + spot.photo + "' style='margin: -3px 0 -3px -5px; float: left;padding-right:10px;' width='90' height='90' />";
	 	content += "<div class='title'>" + spot.title + "</div>";
		content += "<addr>" + spot.address + "</addr>";
		content += "<div class='desc'>" + spot.desc + "</div>";
		content += "<a href='javascript:void(0);' onclick='javascript:BC.scrollToAggSpot(" + spot.id + ")'>详细...</a>";
		content = "<div class='content'>" + content + "</div><div class='pointer'></div>";
		content = "<div id='tooltip' style='width:300px'>" + content + "</div>";
	  return content;
	},
	
	markerImage: function(mixed, isIcon){
      var size, anchor;
      if (!mixed || (typeof(mixed) === 'string') || !mixed.image){
        return null;
      }
      if (mixed.size && !(typeof(mixed.size)==='String')){
        size = new google.maps.Size(mixed.size.w, mixed.size.h);
      }else{
        size = new google.maps.Size(mixed.w ? mixed.w : 40, mixed.h ? mixed.h :40);
      }
      if(isIcon){
    	anchor = new google.maps.Point(size.width/2, 52);
      }
      return new google.maps.MarkerImage(
               mixed.image,
               size,
               null,
               isIcon? anchor: null,
               size
             );
	},
	 
    scrollToAggSpot: function(id){
    	var target = "#aggSpot_" + id;
    	$.scrollTo( target, 800, {easing:'easein'});
    	return false;
    },
    
    recordLocation:function(event) {
    	$('#loc_hidden_field').val($('#location').val());
    	if(BC.noEnter(event) == false){
    		$('#location').blur();
    		return false;
    	}
    },
    
    oldLoc: "",
    
    showLocations: function() {
      $("#"+BC.WG.CustomOverlaysManage.infoPanel.id).hide();
  	  sl = $('#saved_locations');
  	  if(sl) {
  		$('#set_location_label').hide();
  		var val = $('#loc_hidden_field').val();
  		BC.oldLoc = val;
  		if(!val || val=="current" || val=="global"){
  			$('#location').val("");
  		}else{
  			$('#location').val(val);
  		}
  	    sl.show();
  	    $('#location-input .text-box').addClass("focused");
  	    $('#location').blur(function(){
  	    	setTimeout(function(){
  	   	     BC.hideLocations();
  	   	   }, 100);
  	    });
  	  }
  	},
  	
  	hideLocations: function() {
  	  $('#saved_locations').hide();
	  $('#location-input .text-box').removeClass("focused");
	  var val = $('#loc_hidden_field').val();
      if(!val || $.trim(val)==""){
    	 BC.setLocationValue("");
      }else{
    	 BC.setLocationValue(val);
      }
      if(val != BC.oldLoc){
    	 var contextPath = $("#const_contextPath").text();
		 $.getJSON(contextPath+'/check_city.bc',{city: encodeURI(val,"utf-8")}, function(data) {
	       if(data && data.cityMapping){
	    	 BC.sendMsg("波菜为您定位\""+val+"\"...");
	    	 $('#bc_map').gmap3({
			   action: "panTo",
			   args: [new google.maps.LatLng(data.cityMapping.lat, data.cityMapping.lng)]
		     },{
		       action: "setZoom",
			   args: [data.cityMapping.zoom]
		     });
		   }else{
			 BC.geoLocation(val);
		   }
		 });
      }
  	},
  	
  	panLocation: function(loc, zoom, city){
  		BC.sendMsg("波菜为您定位\""+city+"\"...");
  		$('#bc_map').gmap3({
	  			action: "panTo",
				args: [loc]
			},{
			    action: "setZoom",
			    args: [zoom]
			});
  	},
  	
  	geoLocation: function(address){
  		if(address &&
  		   $.trim(address) != "" &&
  		   address != "current" &&
  		   address != "global"){
  			BC.sendMsg("波菜为您定位\""+address+"\"...");
	  		$('#bc_map').gmap3({
	  			action: "getLatlng",
	  			address: address,
	  			callback: function(geoResults){
	  				if(geoResults && geoResults.length != 0){
	  					var geoResult = geoResults[0];
	  					if(geoResult && 
	  						geoResult.geometry){
	  						if(geoResult.geometry.location){
	  							$('#center_hidden_field').val(geoResult.geometry.location.lat() + "_" +
	  									geoResult.geometry.location.lng());
	  						}
	  						if(geoResult.geometry.viewport){
	  							$(this).gmap3({
			  						action: "fitBounds",
			  						args: [geoResult.geometry.viewport]
			  				    },{
					 				action: 'getZoom',
					 				callback: function(zoom){
					 					$(this).gmap3({
					  				    	action: "setZoom",
					  				    	args: [zoom+1]
					  				    });
					 				}
					 			});
	  						}else if(geoResult.geometry.location){
	  						    $(this).gmap3({
			  						action: "panTo",
			  						args: [geoResult.geometry.location]
			  				    });
	  						}
	  					}
	  				}
	  			}
	  		});
  		}
  	},
  	
  	uploadMapArgs: function(){
  		
  	},
  	
  	checkCity: function(ip){
  		$.ajax({
			url	  : "check_city.bc",
			data  : {"ip": ip},
			type  : "GET",
			dataType: "json",
			success: function(result) {
				$('#center_hidden_field').val(BC.globalMapSetting.lat + "_" +
						BC.globalMapSetting.lng);
				$('#zoom_hidden_field').val(BC.globalMapSetting.zoom);
				if(result && result.cityMapping){
					BC.ipCity = result.city;
					BC.setLocationValue(BC.ipCity);
					$('#loc_hidden_field').val(result.cityMapping.city);
					$('#sw_hidden_field').val(result.cityMapping.swLat + "_" +
							result.cityMapping.swLng);
					$('#ne_hidden_field').val(result.cityMapping.neLat + "_" +
							result.cityMapping.neLng);
					$('#center_hidden_field').val(result.cityMapping.lat + "_" +
							result.cityMapping.lng);
					$('#zoom_hidden_field').val(result.cityMapping.zoom);
					// no matter google map loaded, anyway, load data first.
					BC.getAggSpots();
					BC.checkCityResultHandler(result);
//					BC.panLocation(center, result.cityMapping.zoom, BC.ipCity);
				}else{
					BC.checkCityResultHandler(null);
				}
			},
			error: function(){
				
				$('#center_hidden_field').val(BC.globalMapSetting.lat + "_" +
						BC.globalMapSetting.lng);
				$('#zoom_hidden_field').val(BC.globalMapSetting.zoom);
				BC.checkCityResultHandler(null);
			}
		});
  	},
  	
  	checkCityResult : {},
  	
  	checkCityResultHandler: function(result){
  		BC.checkCityResult = result;
  		Sid.js('http://ditu.google.cn/maps/api/js?v=3&sensor=false&language=zh_cn&callback=map_init');
	},
  	
  	parseAddress: function( mapDomId,
  			address,
  			successHandler,
  			errorHandler){
  		$(mapDomId).gmap3({
  			action: "getLatlng",
  			address: address,
  			callback: function(geoResults){
  				if(geoResults && geoResults.length != 0){
  					var geoResult = geoResults[0];
  					if(geoResult && 
  						geoResult.geometry &&
  						geoResult.geometry.location){
  						successHandler(geoResult.geometry.location);
  						return;
  					}
  				}
  				errorHandler();
  			}
  		});
  	},
  	
  	location_prefix : "设置您的位置：",
  	search_prefix : "搜索：",
  	
  	setLocationValue: function(value) {
  	  var form_lable, loc_val;
	  switch(value) {
	    case "current":
		  form_label = "<strong class=\"location-color\">当前位置</strong>";
		  loc_val = "current";
		  break;
		case "global":
		  form_label = "<strong class=\"location-color\">全局范围</strong>";
		  loc_val = "global";
		  break;
		case "":
		  form_label = "<strong class='c000'>城市，街区</strong>";
		  loc_val = "";
		  break;
		default:
		  form_label = "<strong class='c000'>" + value + "</strong>";
		  loc_val = value;
		}
	    $('#loc_hidden_field').val(loc_val);
	    $('#location').val("");
		$('#set_location_label').html(BC.location_prefix + form_label);
		$('#set_location_label').show();
		$('#location_clear_button').show();
	},
	
	getClientLocation: function() {
		BC.sendMsg("自动定位地址......");
		BC.getCurrentPostion(
		  BC.setClientLocation,
		  BC.clientLocationError);
	},
	
	clearLocation: function() {
		BC.setLocationValue("");
		$('#bc_map').gmap3({
			action: "panTo",
			args: [new google.maps.LatLng(BC.globalLat, BC.globalLng)]
		},{
			action: "setZoom",
			args: [BC.globalZoom]
		});
	},
	
	getCurrentPostion: function(success, error){
	  // Try W3C Geolocation (Preferred)
	  if(navigator && navigator.geolocation) {
		browserSupportGeo = true;
		navigator.geolocation.getCurrentPosition(
          success,
	      error
	    );
      }
	  // Try Google Gears Geolocation
	  else if (google.gears) {
        browserSupportGeo = true;
		var geo = google.gears.factory.create('beta.geolocation');
		geo.getCurrentPosition(
		  success,
		  error
		);
      }
	  //Browser doesn't support Geolocation
	  else {
		browserSupportGeo = false;
		error();
      }
	},
	
	setClientLocation: function(position, geo) {
		if(position) {
			if(position.coords){
				$('#center_hidden_field').val(position.coords.latitude + "" +
						position.coords.longitude);
			}
			if(position.address){
				var address = position.address.city;
				if(position.address.street)
					address += " " + position.address.street;
				BC.setLocationValue(address);
				if(geo){
					BC.geoLocation(address);
				}
			}else{
				BC.setLocationValue("");
				BC.clientLocationError();
			}
		} else {
			BC.setLocationValue("");
			BC.clientLocationError();
		}
	},
	
	clientLocationError: function() {
		BC.sendMsg("波菜无法自动解析您的位置信息，请使用表单在地图上定位地址！");
	},
	
	sendMsg: function(msg){
		clearTimeout(BC.msgTimeOutHolder);
		$('#map-message-box').text(msg);
		$('#map-message-box').show();
		BC.msgTimeOutHolder = setTimeout(function(){
			$('#map-message-box').fadeOut("slow", function(){
				$('#map-message-box').hide();
			});
		}, 1500);
	},
	
	showSearchHint: function(){
		$("#"+BC.WG.CustomOverlaysManage.infoPanel.id).hide();
		var content = "<li class='status'>美食推荐引擎已准备...</li>";
		$('#query').val($('#keyword_hidden_field').val());
		$('#query_from_hidden_field').val("");
		$('#submit_hidden_field').val("");
		var query = $('#query').val();
		BC.showGeniusResult(content);
		if(query && $.trim(query).length != 0){
			BC.runGenius(true, null);
		}
	},
	
	showSuggestions: function(html){
		BC.showGeniusResult(html);
	},
	
	showGeniusResult: function(html){
		gr = $('#genius_results');
		if(gr){
		  clearTimeout(BC.suggestionTimeOutHolder);
		  if(html){
			  $('#genius_results').html(html);
		  }
		  $('#genius-label').hide();	  
		  $('#genius-search').addClass("focused");
		  $('#genius_results').show();
		  $('#query').blur(function(){
		  	BC.suggestionTimeOutHolder = setTimeout(function(){
		  	      BC.hideGeniusResult();
		  	   }, 100);
		  });
		}
	},
	
	setGeniusValue: function(value) {
		var form_label, query_val;
		switch(value) {
			case "":
				form_label = "<strong class='c000'>美食, 餐厅 or 波客</strong>";
				query_val = "";
				break;
			default:
				form_label = "<strong class='c000'>" + value + "</strong>";
				query_val = value;
		}
		$('#query').val("");
		$('#keyword_hidden_field').val(query_val);
		$('#genius-label').html(BC.search_prefix + form_label);
		$('#genius-label').show();
		$('#query_clear_button').show();
	},
	
	clearGenius: function(){
		BC.setGeniusValue("");
	},
	
	hideGeniusResult: function(){
		clearTimeout(BC.suggestionTimeOutHolder);
		$('#genius_results').hide();
        $('#genius-search').removeClass("focused");
        var val = $('#keyword_hidden_field').val();
        if(!val || $.trim(val)==""){
        	BC.setGeniusValue("");
        }else{
        	BC.setGeniusValue(val);
        }
        if($('#submit_hidden_field').val() == "yes"){
        	$('#submit_hidden_field').val("");
        	$('#map_search').submit();
        }
	},
	
	// Keys "enum"
	KEY : {
	    BACKSPACE: 8,
	    TAB: 9,
	    ENTER: 13,
	    ESCAPE: 27,
	    SPACE: 32,
	    PAGE_UP: 33,
	    PAGE_DOWN: 34,
	    END: 35,
	    HOME: 36,
	    LEFT: 37,
	    UP: 38,
	    RIGHT: 39,
	    DOWN: 40,
	    NUMPAD_ENTER: 108,
	    COMMA: 188
	},
	
	iterateGeniusItem: function(isUp){
		var selected, next;
		selected = $('#genius_results a.selected');
		if(!selected || selected.length==0){
			if(isUp==false){
				$('#genius_results a:first').addClass('selected');
			}else{
				$('#genius_results a:last').addClass('selected');
			}
		}else{
			selected.removeClass('selected');
			if(isUp==false){
				next = selected.parent().nextAll(':has(a)');
			}else{
				next = selected.parent().prevAll(':has(a)');
			}
			if(!next || next.length==0){
				if(isUp==false){
					$('#genius_results a:first').addClass('selected');
				}else{
					$('#genius_results a:last').addClass('selected');
				}
			}else{
				next = $(next[0]).children(); 
				next.addClass('selected');
			}
		}
	},
	
	runGenius: function(force, event){
		var oldQuery = $('#keyword_hidden_field').val();
		var newQuery = $.trim($('#query').val());
		if(!force && oldQuery == newQuery){
			return;
		}
		$('#keyword_hidden_field').val($('#query').val());
		var delay = 700;
		setTimeout(
			function(){
				var query = $('#query').val();
				if(!query || $.trim(query).length == 0)return;
				$.ajax({
					url: "genius!ajaxGetSuggestions.bc"
					,data: {"query" : query}
					,type: "POST"
					,dataType: "json"
					,beforeSend: function(){
						$('#genius-hint').show();
					}
					,success: function(response) {
						if(!response.isError){
							var isHiden = $('#genius_results').css('display') == "none"? true : false;
							var query = response.query,
								genius = response.genius;
							if(isHiden==false){
								BC.Template.T({
									name: 'genius_suggestions',
				   			   	    domId: '#genius_results',
				   			   	    hintId: '#genius-hint',
				   			      	data: genius,
				   			      	params: {
				   			      		query: query
				   			      	},
				   			      	callback: function(){
				   			      		if(query && $.trim(query).length != 0){
				   			      			$('#genius_results dd > a').each(function(){
				   			      				var text = $(this).text();
				   			      				text = text.replace(new RegExp(query, 'g'),'<span class="cerror">'+query+'</span>');
				   			      				$(this).html(text);
				   			      			});
					   			      		$('#genius_results dd > span').each(function(){
				   			      				var text = $(this).text();
				   			      				text = text.replace(new RegExp(query, 'g'),'<span class="cerror">'+query+'</span>');
				   			      				$(this).html(text);
				   			      			});
				   			      		}
				   			      		BC.showSuggestions();
					   			      	$('#genius_results a.dish, #genius_results a.place').click(function(event){
											var keyword = $(event.target).text();
											var keywordType = $(event.target).attr('class');
											if(keywordType){
												$('#keyword_type_hidden_field').val(keywordType.toUpperCase());
											}else{
												$('#keyword_type_hidden_field').val('');
											}
											$('#keyword_hidden_field').val(keyword);
											$('#submit_hidden_field').val("yes");
										});
						   			    $('#genius_results a.dishmore').click(function(event){
						   			    	var keyword = $(event.target).attr('rel');
						   			    	$('#keyword_type_hidden_field').val('DISH');
						   			    	$('#submit_hidden_field').val("yes");
						   			    });
						   			    $('#genius_results a.placemore').click(function(event){
						   			    	var keyword = $(event.target).attr('rel');
						   			    	$('#keyword_type_hidden_field').val('PLACE');
						   			    	$('#submit_hidden_field').val("yes");
						   			    });
					   			      	$('#genius_results a.boke').click(function(event){
					   			      		var keyword = $(event.target).text();
					   			      		var url = encodeURI("boke.bc?keyword="+keyword);
											location.href= url;
										});
					   			      	$('#genius_results a.bokemore').click(function(event){
					   			      		var keyword = $(event.target).attr('rel');
					   			      		var url = encodeURI("boke.bc?keyword="+keyword);
											location.href= url;
						   			    });
				   			      	}
								});							
							}
						}
					}
					,complete: function(){
						$('#genius-hint').hide();
					}
				});
			}, delay
		);
	},
	
	updateMapZoom: function(zoom){
		$('#zoom_hidden_field').val(zoom);
	},
	
	updateMapCenter: function(center){
		$('#center_hidden_field').val(center.lat() + "_" + center.lng());
	},
	
	updateMapBounds: function(sw, ne){
		$('#sw_hidden_field').val(sw.lat() + "_" + sw.lng());
		$('#ne_hidden_field').val(ne.lat() + "_" + ne.lng());
	},
	
	updateReqType: function(type){
		$('#req_type_hidden_field').val(type);
	},
	
	checkSumbittable: function(){
		var args = new Array();
		args.push($('#sw_hidden_field').val());
		args.push($('#ne_hidden_field').val());
		args.push($('#req_type_hidden_field').val());
		var result = true;
		$.each(args, function(idx, value){
			if(result == true && (!value || $.trim(value)=="")){
				result = false;
			}
		});
		return result;
	},
	
	onQueryKeyDown: function(event){
		var isHiden = $('#genius_results').css('display') == "none"? true : false;
		if(event){
			switch(event.keyCode) {
				case BC.KEY.LEFT:
				case BC.KEY.UP:
					if(isHiden==false){
						BC.iterateGeniusItem(true);
					}
                	break;
				case BC.KEY.RIGHT:
                case BC.KEY.DOWN:
                	if(isHiden==false){
                		BC.iterateGeniusItem(false);
                	}
                	break;
                case BC.KEY.ESCAPE:
                	$('#query').blur();
                	break;
                case BC.KEY.ENTER:
                	var selected = $('#genius_results a.selected');
        			if(selected.length==0){
        				$('#submit_hidden_field').val("yes");
        				$('#query').blur();
        			}else{
        				selected.removeClass('selected');
        				selected.click();
        				$('#query').blur();
        			}
                	break;
                default:
                	$('#keyword_hidden_field').val($('#query').val());
        			$('#keyword_type_hidden_field').val('DISH');
                	break;
			}
		}
		return true;
	},
	
	clearQuery: function(){
		BC.setGeniusValue("");
		BC.getAggSpots();
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
	
	toLatLngStr: function(latLng){
		if(latLng){
			return latLng.lat() + "_"+latLng.lng();
		}
		return null; 
	},
	
	markUrl: function(url, params){
		var args = params, qStr, result;
		if(args && url){
			qStr = "/"+$.param(args);
			idx = url.indexOf('#');
			if(idx==-1){
				idx = url.length;
			}
			result = encodeURI(url.substring(0, idx) 
				+ "#" + qStr);
		}
		return result;
	},
	
	parseArgs: function(url){
		var args, qStr, idx;
		if(url){
			idx = url.indexOf('#');
			if(idx!=-1){
				qStr = decodeURI(url.substring(idx+1, url.length));
				idx = qStr.indexOf('/');
				qStr = qStr.substring(idx+1, qStr.length);
				args = $.deparam(qStr);
			}
		}
		return args;
	},
	
	needCityCheck: true,
	
	initPageArgs: function(qstr){
		var url = window.location.href;
		var args = $.extend({},this.parseArgs(qstr), this.parseArgs(url));
		this.needCityCheck = false;
		if(BC.Lang.isEmpty(args)){
			this.needCityCheck = true;
		}
		if(args.hasOwnProperty('keyword')){
			$('#keyword_hidden_field').val(args.keyword);
		}
		if(args.hasOwnProperty('keywordType')){
			$('#keyword_type_hidden_field').val(args.keywordType);
		}
		if(args.hasOwnProperty('reqType')){
			$('#req_type_hidden_field').val(args.reqType);
		}
		if(args.hasOwnProperty('loc')){
			$('#loc_hidden_field').val(args.loc);
		}
		if(args.hasOwnProperty('at')){
			$('#at_hidden_field').val(args.at);
		}
		if(args.hasOwnProperty('center')){
			$('#center_hidden_field').val(args.center);
		}
		if(args.hasOwnProperty('zoom')){
			$('#zoom_hidden_field').val(args.zoom);
		}
	},
	
	initPage: function(qstr){
		BC.initPageArgs(qstr);
		BC.setLocationValue($('#loc_hidden_field').val());
		BC.setGeniusValue($('#keyword_hidden_field').val());
		BC.setSortTypeValue($('#req_type_hidden_field').val());
	},
	
	mapBounds : null,
	
	needInitFit: false,
	
	initMapArgs: function(){
		var args = [], lat, lng, sw, ne, center, zoom;
		var centerStr = $('#center_hidden_field').val();
		if(centerStr){
			idx = centerStr.indexOf('_');
			if(idx!=-1){
				lat = parseFloat(centerStr.substring(0, idx));
				lng = parseFloat(centerStr.substring(idx+1, centerStr.length));
			}
		}
		if(!isNaN(lat) && !isNaN(lng)){
			center = [lat, lng];
			zoom = parseFloat($('#zoom_hidden_field').val());
			if(isNaN(zoom)){
				zoom = 11;
			}
		}else{
			center = [this.globalLat, this.globalLng];
			zoom = this.globalZoom;
		}

		args.push({
			action: 'init',
		 	options: {
				center: center,
	     		zoom: zoom,
	     		disableDoubleClickZoom: false,
	     		scrollwheel: false,
	     		mapTypeControl: false,
	     		mapTypeControlOptions : {
	     			mapTypeIds : [google.maps.MapTypeId.ROADMAP],
	     			position : google.maps.ControlPosition.TOP_LEFT
	     		},
	     		navigationControl: true,
	     		navigationControlOptions: {
	     			position : google.maps.ControlPosition.RIGHT,
	     			style : google.maps.NavigationControlStyle.DEFAULT
	     		},
	     		noClear: true,
	     		scaleControl: false,
				streetViewControl: false
		 	},
		 	events: {
		 		idle : function(){
		 			$('#bc_map').gmap3({
		 		    	action:'getBounds', 
		 		    	callback: function (bounds){
		 		    		BC.updateMapBounds(bounds.getSouthWest(), 
		 		    				bounds.getNorthEast());
		 		    	}
		 			},{
		 				action: 'getCenter',
		 				callback: function(center){
		 					BC.updateMapCenter(center);
		 				}
		 			},{
		 				action: 'getZoom',
		 				callback: function(zoom){
		 					BC.updateMapZoom(zoom);
		 					//BC.updateAddress();
		 		    		BC.getAggSpots();
		 				}
		 			});
		 		}
		 		,
		 		click : function(event){
		 			$('#map_search').blur();
		 			$('#bc_map').gmap3({ action : 'clear', list : 'overlay'});
		 		}
		 		,
		 		bounds_changed: function(event){
		 			$("#"+BC.WG.CustomOverlaysManage.infoPanel.id).hide();
		 		}
		 	},
		 	callback: function(){
		 		BC.WG.CustomOverlaysManage.myMap = $(this).gmap3('get');
		 		BC.initMapCallback();
		 		//BC.initFitBounds($(this));
		 	}
		});
		return args;
	},
	
	initMapCallback: function(){
		if(BC.checkCityResult && BC.checkCityResult.cityMapping){
			var center = BC.toLatlng(BC.checkCityResult.cityMapping.lat + "_" +
					BC.checkCityResult.cityMapping.lng);
			BC.panLocation(center, 
					BC.checkCityResult.cityMapping.zoom, BC.ipCity);
		}
	},
	
	initFitBounds: function($this){
		if(BC.needInitFit){
			var sw = $('#sw_hidden_field').val();
			var ne = $('#ne_hidden_field').val();
			var bounds = new google.maps.LatLngBounds(BC.toLatlng(sw), BC.toLatlng(ne));
			$this.gmap3({
				action: "fitBounds",
				args: [bounds],
				callback: function(){
					BC.needInitFit = false;
				}
			});
		}
	},
	
	getAggSpots: function(){
		$('#map_search').submit();
	},
	
	updateAggSpots: function(result){
		if(result){
//			$("#aggSpot_list span[id*=timeAgo]").each(function(){
//				$(this).html($.timeago($(this).html()));
//			});
			BC.updatePaginator(result.total, result.at);
			BC.updateAddress();
			if(!(typeof BC.WG.CustomOverlayG === "undefined")){
				BC.WG.MapNav.updateMarkers(result.markers);
			}
		}
	},
	
	updatePaginator: function(total, at){
		BC.WG.pager.pageClkFunc = function(pageNo){
			$('#at_hidden_field').val(pageNo);
			BC.getAggSpots();
		};
		BC.WG.pager.pagination("divpagingup",at,total); 
		$('#at_hidden_field').val(at);
		$('#total_hidden_field').val(total);
		$('#page-right').addClass("enabled");
		$('#page-left').addClass("enabled");
		if(at>=total){
			$('#page-right').removeClass("enabled");
		}
		if(at<=1){
			$('#page-left').removeClass("enabled");
		}
	},
	
	updateAddress: function(){
		var params = {
			keyword : $('#keyword_hidden_field').val(),
			keywordType: $('#keyword_type_hidden_field').val(),
			reqType : $('#req_type_hidden_field').val(),
			at : $('#at_hidden_field').val(),			
			loc : $('#loc_hidden_field').val(),
			center : $('#center_hidden_field').val(),
			zoom : $('#zoom_hidden_field').val()
		};
		var url = window.location.href;
		var newUrl = BC.markUrl(url, params);
		window.location.href = newUrl;
	},
	
	getShadowMarker: function(key){
		var markers = markerMap[key];
		if(markers && markers.length > 1){
			return markers[0];
		}
		return null;
	},
	
	setSortTypeValue: function(value){
		var type = value, id;
		switch(type){
			case "HOTEST":
				id = "map-sort-best";
				break;
			case "LATEST":
				id = "map-sort-latest";
				break;
			case "FOLLOWING":
				id = "map-sort-following";
				break;
			case "WANTTED":
				id = "map-sort-wanted";
				break;
			case "GUIDE":
				id = "map-sort-guides";
				break;
			default:
				type = null;
				break;
		}
		if(type){
			$('#req_type_hidden_field').val(type);
			$('#map-sorts .on').removeClass('on');
			$('#' + id).addClass('on');
		}
	},
	
	sortByType: function(event){
		if($(this).hasClass('on')){
			return;
		}
		var	id = this.id;
		switch(id){
			case "map-sort-best":
				type = "HOTEST"; break;
			case "map-sort-latest":
				type = "LATEST"; break;
			case "map-sort-following":
				type = "FOLLOWING"; break;
			case "map-sort-wanted":
				type = "WANTTED"; break;
			case "map-sort-guides":
				//type = "GUIDE"; 
				//global.showOperationTip(this.id, "即将推出，敬请期待～", "top");
				return;
			default:
				type = null;
		}
		var loginId = parseFloat($('#const_loginId').text());
  	    if((isNaN(loginId) || loginId<=0) &&
  	    		(type == "FOLLOWING" || type == "WANTTED")){
 	   	     global.needLogin();
 	   		 return;
 	   	}
		$('#req_type_hidden_field').val(type);
		var sw = $('#sw_hidden_field').val();
		var ne = $('#ne_hidden_field').val();
		$('#map-sorts .on').removeClass('on');
		$(this).addClass('on');
		$('#at_hidden_field').val('');
		$('#total_hidden_field').val('');
		if(type && sw && ne){
			BC.getAggSpots();
		}
	},
	
	nextPage: function(event){
		if($('#page-right').hasClass("enabled")){
			var at = $('#at_hidden_field').val() || 1;
			$('#at_hidden_field').val(++at);
			BC.getAggSpots();
		}
	},
	
	previousPage: function(event){
		if($('#page-left').hasClass("enabled")){
			var at = $('#at_hidden_field').val() || 1;
			$('#at_hidden_field').val(--at);
			BC.getAggSpots();
		}
	}
};