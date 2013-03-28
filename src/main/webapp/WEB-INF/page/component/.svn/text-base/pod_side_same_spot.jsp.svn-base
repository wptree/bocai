<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="same_spot_gallery" class="pod cpod more_at clean">
	<div id="header" class="clean">
		<div id="title">其他
			<a href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=<s:property value="model.id" />">
				<s:property value="model.dish.name" />@<s:property value="model.place.fullName" />
			</a>
		</div>
		<div id="loading" style="display:none;"></div>
		<div id="controls">
			<a id="next_page" class="" href="javascript:void(0);"></a>
			<a id="prev_page" class="disabled" href="javascript:void(0);"></a>
			<div id="entries"><span id="range">1/1</span></div>
		</div>
	</div>
	<div id="sightings-wrap">
	<div id="sightings">
		<div class="sighting-set" style="width:266px;" >
			<s:iterator value="spotPager.list" var="spot">
				<a class="fl pr" style="text-decoration: none;padding:9px !important;background-color:#fff;height:113px;width:113px;margin:1px" target="_blank" 
				    href="${pageContext.request.contextPath}/spot/detail_spot.bc?id=<s:property value="%{#spot.id}"/>">
					<img title="<s:property value="%{#spot.dish.name}"/>" alt="<s:property value="%{#spot.dish.name}"/>" src="${imageContext}/<s:property value="%{#spot.getSpotImgPath(120)}"/>" style="height:113px;width:113px;">
					<div class="pa" style="left: 9px; top: 9px; background-color: rgb(255, 255, 255); opacity: 0.7; display: none;">
						<div class="fb tc vm c000" style="display: table-cell;height:120px;padding: 0 5px;width:110px;"><s:property value="%{#spot.commentNum}"/>评论</div>
					</div>
				</a>
			</s:iterator>
		</div>
	</div>
	</div>
	<div id="hidden-data" style="display:none;">
		<input id="at_hidden_field" name="at" type="hidden" value="<s:property value="spotPager.pageNo" />"/>
		<input id="total_hidden_field" name="total" type="hidden" value="<s:property value="spotPager.totalPage" />"/>
		<input id="aggSpot_id_hidden_field" name="id" type="hidden" value="<s:property value="model.id" />"/>
	</div>
	<div id="sighting-set-template" style="display:none;"></div>
	<script type="text/javascript">
		$(function() {
			//declare
			function setPage(at,total){
				var range = $('#range').html(at + "/" + total);
				$('#prev_page').removeClass("disabled");
				$('#next_page').removeClass("disabled");
				if(at==1){
					$('#prev_page').addClass("disabled");
				}
				if(at==total){
					$('#next_page').addClass("disabled");
				}
			}
			function setPhotos(){
				$('.sighting-set > a').mouseover(function(){
					this.children[1].style.display="block";
				});
				$('.sighting-set > a').mouseout(function(){
					this.children[1].style.display="none";
				});
			}
			function bindingPage(){
				$('#prev_page').click(function(){
					var at = $('#at_hidden_field').val();
					var total = $('#total_hidden_field').val();
					at--;
					if(at < 1)return;
					$('#at_hidden_field').val(at);
					setPage(at, total);
					setViewPoint();
				});
				$('#next_page').click(function(){
					var at = $('#at_hidden_field').val();
					var total = $('#total_hidden_field').val();
					at++;
					if(at > total)return;
					$('#at_hidden_field').val(at);
					setPage(at, total);
					setViewPoint();
				});
			}
			function setViewPoint(){
				var at = $('#at_hidden_field').val();
				var widthStr = $('#sightings').css("width");
				var width = widthStr.replace("px","");
				//$('#sightings').css("margin-left", -1*(at-1)*266 + "px");
				if(width < at*266){
					$('#sightings').css("width", at*266 + "px");
					pageData(at);
				}else{
					moveViewPoint();
				}
			}
			function moveViewPoint(){
				var at = $('#at_hidden_field').val();
				$('#sightings').animate({
					marginLeft : -1*(at-1)*266 + "px"
				}, 500);
			}
			function pageData(at){
				var id = $('#aggSpot_id_hidden_field').val();
				$('#same_spot_gallery #loading').show();
				$.get('${pageContext.request.contextPath}/spot/ajax_same_spot_gallery.bc?id='+ id + "&at=" + at, 
					function(response){
						if(!response.isError){
							BC.Template.T({
								name: 'pod_same_spot_gallery',
			   			   	    domId: '#sighting-set-template',
			   			   	    hintId: '#same_spot_gallery #loading',
			   			      	data: response.spotPager,
			   			      	params: {
			   			      		
			   			      	},
			   			      	callback: function(){
			   			      		$('#at_hidden_field').val(response.spotPager.pageNo);
			   			      		$('#total_hidden_field').val(response.spotPager.totalPage);
			   			      		setPage(response.spotPager.pageNo, response.spotPager.totalPage);
			   			      		$('#sightings').append($('#sighting-set-template').html());
			   			      		setPhotos();
			   			      		moveViewPoint();
			   			      	}
							});
						}
				    }).complete(function(){
				    	$('#same_spot_gallery #loading').hide();
				    });
			}
			
			//execute
			setPage(<s:property value="spotPager.pageNo" />, <s:property value="spotPager.totalPage" />);
			bindingPage();
			setPhotos();
		});
	</script>
</div>