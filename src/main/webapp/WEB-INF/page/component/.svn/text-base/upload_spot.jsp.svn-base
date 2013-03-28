<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- spot upload form -->
<!-- initial upload box -->
<div id="begin_upload_box" class="bbox pt10 pb10 pl30 pr30 bgc11 br5">
	<div id="init_upload_box" class="">
		<label id="upload_box_label" class="lh35 pa ml10 c888" style="height:37px;">上传美食照片（至少600×600，小于5M）</label>
		<input id="spot_img_input" name="fileInput" type="file" />
	</div>
	<div id="init_upload_queue"></div>
</div>
<div id="after_upload_box" class="bbox p15 bgc11 none ffyh">
	<div class="clean">
		<div class="borSpotImg br5 fl boxShadSpotImg clean" style="width:350px;height:200px">
			<div id="spot_img_wrap" class="l pr ofh">
				<div>
					<img id="spot_img" width="200" height="200" >
				</div>
				<div id="switch_upload_queue" class="pa p5 br5 none" style="width: 150px; height:80px;
					background: none repeat scroll 0pt 0pt rgb(255, 255, 255); opacity: 0.6; bottom:60px; left:25px"></div>
				<ul id="spot_img_ctrl" class="pa p3 f12 lh22" style="width:194px; bottom:-30px;
					background: none repeat scroll 0 0 #FFF; opacity: 0.9;">
					<li class="l mr5"><a class="rotateleftbg tinybtn" href="javascript:void(0);"></a></li>
					<li class="l mr5"><a class="rotaterightbg tinybtn" href="javascript:void(0);"></a></li>
					<li class="l p3 bor1 br3" style="width:16px; height:16px;">
						<input id="spot_img_switch_input" name="fileInput" type="file" /></li>
				</ul>
			</div>
			<div id="place_map" class="l" style="width:150px;height:200px">
			</div>
		</div>
		<form id="upload_spot_form" action="${pageContext.request.contextPath}/spot/add_spot.bc" method="post" enctype="application/x-www-form-urlencoded">
		<div class="fr">
			<!-- hidden fields-->
			<div class="none">
				<input name="struts.enableJSONValidation" type="hidden" value="true" />
				<input id="img_rotate_degree" name="imgRotateDegree" type="hidden" value="0" />
				<input id="org_img_name" name="orgImgName" type="hidden" value="true" />
		 		<input id="place_id" name="placeId" type="hidden" value="true" />
			</div>
			<ul style="width:240px;">
				<li class="mb15 ffyh">
					<input id="dish_input" class="upload" label='选择菜品名称' type="text" size="20" name="dishName" />
				</li>
				<li class="mt15 ffyh">
					<input id="place_input" class="upload" label='选择餐厅名称' type="text" size="20" />
				</li>
				<li class="mt15 ffyh">
					<textarea id="desc_ta" class="upload" label='我想说...' name="desc" ></textarea>
				</li>
				<li class="mt15 ffyh pr clean">
					<div class="pa lh32 tr" style="width:370px; height:32px; right:258px;">
						<span class="f14 ctitle ffyh">同步到：</span>
						<a href="javascript:void(0);">
							<input type="checkbox" value="true" name=sync2Sina />
							<img src="${staticContext}/images/sina_24x24.png" width="18" height="18" title="新浪微博"/>
						</a>
					</div>
					<div class="fl ffyh">
						<input id="price_input" class="upload" label='品尝价' type="text" size="6" name="price" style="width:60px; height:18px" />
					</div>
					<div class="fr clean">
			           	<p class="r"><input id="add_spot_cancel_btn" class="btn" type="button" value="取消" onclick=""></input>
			           		<input class="btn success" type="submit" value="分享" />
			           	</p>
			           	<p id="add_spot_spinner"  class="r mt8 mr5 none">
							<img src="${staticContext}/images/re/loading.gif" width="16" height="16"/></p>
			        </div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$('#spot_img_input').uploadify({
	 		'uploader': '${pageContext.request.contextPath}/js/uploadify.swf',
	        'script': '${pageContext.request.contextPath}/upload_spot.bc',//指定服务端处理类的入口
	        'folder': '${staticContext}/upload/original',
	        'buttonImg': '${staticContext}/images/uploadInput.png',
	        'cancelImg' : '${staticContext}/images/re/cancel.png',
	        'height': 37,
	        'width': 597,
	        'vmode' : 'transparent',
	        'fileDataName': 'fileInput',//和input的name属性值保持一致就好，Struts2就能处理了
	        'queueID': 'init_upload_queue',
	        'queueSizeLimit': 1,
	        'fileExt': '*.jpg;*.jpeg;*.bmp;*.png',
	        'fileDesc': 'Image Files',
	        'sizeLimit'   : 8388068, //in bytes, 8M
	        //'auto': true,//是否选取文件后自动上传   
	        'multi': false,//是否支持多文件上传
	        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比
	        'onSelect': function(event,id,fileObj) {
	        	global.checkUserLogin(function(result){
	        		if(result){
	        			$('#spot_img_input').uploadifyUpload();
	        		}else{
	        			$('#spot_img_input').uploadifyClearQueue();
	        			global.needLogin();
	        		}
	        	});
	         },
	        'onComplete': function (event, queueID, fileObj, response, data){
	        	var mydata = JSON.parse(response);
	        	if(mydata){
	        		$('#begin_upload_box').hide();
	        		$('#after_upload_box').show();
	        		$('#spot_img').attr('src', mydata.url);
	        		$('#org_img_name').val(mydata.fileName);
	        		var center = window.getInitCenter();
	        		var zoom =  window.getInitZoom();
	        		$("#place_map").gmap3({
	        			action: 'init',
	        		 	options: {
	        				center: center,
	        	     		zoom: zoom,
	        	     		disableDoubleClickZoom: false,
	        	     		scrollwheel: false,
	        	     		mapTypeControl: false,
	        	     		navigationControl: true,
	        	     		noClear: true,
	        	     		scaleControl: false,
	        				streetViewControl: false
	        		 	}
	        		});
	        	}
	            //$("#upload_result").html(response);//显示上传成功结果
	            //setInterval("showResult()",2000);//两秒后删除显示的上传成功结果
	        },
	        'onCancel': function(event,id,fileObj,data){
	        	 //$('#init_upload_box').show();
	        }
	        });
		$('#spot_img_switch_input').uploadify({
	 		'uploader': '${pageContext.request.contextPath}/js/uploadify.swf',
	        'script': '${pageContext.request.contextPath}/upload_spot.bc',//指定服务端处理类的入口
	        'folder': '${staticContext}/upload/original',
	        'buttonImg': '${staticContext}/images/re/folder_open_image.png',
	        'cancelImg' : '${staticContext}/images/re/cancel.png',
	        'height': 16,
	        'width': 16,
	        'vmode' : 'transparent',
	        'fileDataName': 'fileInput',//和input的name属性值保持一致就好，Struts2就能处理了
	        'queueID': 'switch_upload_queue',
	        'queueSizeLimit' : 1,
	        'fileExt': '*.jpg;*.jpeg;*.bmp;*.png',
	        'fileDesc': 'Image Files',
	        'sizeLimit'   : 8388068, //in bytes, 8M
	        //'auto': true,//是否选取文件后自动上传   
	        'multi': false,//是否支持多文件上传
	        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比
	        'onSelect': function(event,id,fileObj) {
	        	$('#switch_upload_queue').show();
	        	global.checkUserLogin(function(result){
	        		if(result){
	        			$('#spot_img_switch_input').uploadifyUpload();
	        		}else{
	        			$('#spot_img_switch_input').uploadifyClearQueue();
	        			global.needLogin();
	        		}
	        	});
	         },
	        'onComplete': function (event, queueID, fileObj, response, data){
	        	$('#switch_upload_queue').hide();
	        	var mydata = JSON.parse(response);
	        	if(mydata){
	        		$('#spot_img').replaceWith('<img id="spot_img" width="200" height="200" >');
	        		$('#spot_img').attr('src', mydata.url);
	        		$('#org_img_name').val(mydata.fileName);
	        		$('#img_rotate_degree').val('0');
	        	}
	        },
	        'onCancel': function(event,id,fileObj,data){
	        	$('#switch_upload_queue').hide();
	        }
	        });
		
		$('#spot_img_wrap').mouseenter(function(){
			$('#spot_img_ctrl').animate({bottom: '0px'});
		});
		$('#spot_img_wrap').mouseleave(function(){
			$('#spot_img_ctrl').animate({bottom: '-30px'});
		});
		$('#spot_img_wrap a.rotateleftbg').click(function(){
			rotateSpotImg(-90);
		});
		$('#spot_img_wrap a.rotaterightbg').click(function(){
			rotateSpotImg(90);
		});
		function rotateSpotImg(degree){
			var val = parseFloat($('#img_rotate_degree').val());
			val = val ? (val+degree) : degree;
			val = val%360;
			$('#spot_img').rotate({
				angle: val,
				animateTo: val
			});
			$('#img_rotate_degree').val(val);
		};
		
		$("#dish_input").tokenInput("${pageContext.request.contextPath}/spot/dish!queryResult.bc", {
            theme: "upload",
            method: "post",
            styleWidth:240,
            tokenInputId: "dish_token_input",
            firstLinePrefix:"+新菜品",
			firstLineId:-9999,
			onToken: function(data){
				if(data.id == -9999){
					$('#dish_input').val(data.name);
				}
			}
        });
		$("#place_input").tokenInput("${pageContext.request.contextPath}/spot/place!instantQuery.bc", {
            theme: "upload",
            method: "post",
            styleWidth:240,
            tokenInputId: "place_token_input",
            firstLinePrefix:"+新餐厅",
			firstLineId:-9999,
			firstLineOnClick: function(select){
				OP.addPlace({
					center: window.getInitCenter(),
					zoom: window.getInitZoom(),
					name: select.name,
					callback: function(data){
						if(!data.isError){
							$('#place_id').val(data.id);
							$('#place_input').val(data.fullName);
							$('#place_input').prev().find('#tokenDisplaceBlock').html(data.fullName);
							if(data.latLng){
								var loc = new google.maps.LatLng(data.latLng.lat,
										 data.latLng.lng);
								$("#place_map").gmap3({
									action: 'clear',
									name: 'marker'
								},{ 
									action: 'addMarker',
									latLng: loc
								},{
						  			action: "panTo",
									args: [loc]
								},{
								    action: "setZoom",
								    args: [13]
								});
							}
						}
					},
					drop: function(){
						$('#place_id').val('');
						$('#place_input').val('');
						$('#place_input').prev().find('.token-input-delete-token-upload').click();
					}
				});
			},
			onToken: function(data){
				if(data.id == -9999) return;
				$('#place_id').val(data.id);
				$.get('${pageContext.request.contextPath}/spot/place!getLocation.bc',
					{'id': data.id},
					function(data){
						if(data.isError || !data.locationVo) return;
						var loc = new google.maps.LatLng(data.locationVo.lat,
								 data.locationVo.lng);
						$("#place_map").gmap3({
							action: 'clear',
							name: 'marker'
						},{ 
							action: 'addMarker',
							latLng: loc
						},{
				  			action: "panTo",
							args: [loc]
						},{
						    action: "setZoom",
						    args: [13]
						});
					}, 'json');
			}
        });
		
		$('#dish_token_input').defaultValue();
		$('#place_token_input').defaultValue();
		$('#desc_ta').defaultValue();
		$('#price_input').defaultValue();
		
		$('#add_spot_cancel_btn').click(function(){
			clear();
			$('#begin_upload_box').show();
    		$('#after_upload_box').hide();
		});
		
		function clear(){
			$('#after_upload_box input[type="text"],'+
					'#after_upload_box input[type="hidden"],'+
					'#after_upload_box textarea').each(function(idx, ele){
					var label = $(ele).attr('label');
					if(label){
						$(ele).val(label);
						$(ele).addClass('def-val');
					}else{
						$(ele).val('');
					}
				});
			$('.token-input-token-upload').remove();
			$('.token-input-input-token-upload input').show();
			$('#spot_img').attr('src', '');
			var loc = window.getInitCenter();
    		var zoom =  window.getInitZoom();
			$("#place_map").gmap3({
				action: 'clear',
				name: 'marker'
			},{
	  			action: "panTo",
				args: [loc]
			},{
			    action: "setZoom",
			    args: [zoom]
			});
		};
		
		function validateSpot(formData, jqForm, options){
		    var failed = false;
		    $("#after_upload_box #price_input").data('failed', '');
	    	if($("#after_upload_box #price_input").val() && 
	    			isNaN(parseFloat($("#after_upload_box #price_input").val()))){
	    		$("#after_upload_box #price_input").data('failed', '请输入数字');
		    	failed = true;
		    }
		    
		    $('#after_upload_box input[label],'+
					'#after_upload_box textarea[label]').each(function(idx, ele){
					var val = $(ele).val();
					if(!val){
						$(ele).val($(ele).attr('label'));
						$(ele).addClass('def-val');
					}
			});
		
		    $('#after_upload_box #dish_token_input,'+
		    		'#after_upload_box #place_token_input')
	  	    	.each(function(idx, ele){
	  	    		$(ele).data('failed', '');
	  	    		var token = $(ele).parent().prev('.token-input-token-upload');
					if(token.length==0){
						var id = $(ele).attr('id');
						if(id.indexOf('dish')!=-1){
							$(ele).data('failed', 
									'请选定或添加美食');
						}else{
							$(ele).data('failed', 
									'请选定或添加餐厅');
						}
				    	failed = true;
					}
			});
		
		    if(failed){
		  	    OP.tipFormError($('#upload_spot_form'), 'topleft');
		  	}else{
		  		$('#add_spot_spinner').show();
		  	}
		    return !failed;
		}
		
		$('#upload_spot_form').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				return validateSpot(arr, form, options);
			},
			success : function (response, statusText, xhr, $form){
				if(response &&
					!response.isError &&
					response.spotId){
					window.location.replace("${pageContext.request.contextPath}/user/profile.bc?id=${session.sessionLoginUser.id}");
				}
			},
			error: function(){
				
			},
			complete: function(){
				$('#add_spot_spinner').hide();
			}
		});
	});
</script>