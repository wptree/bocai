<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="more_at_place" class="pod cpod more_at clean">
	<div id="header" class="clean">
		<div id="title">更多&nbsp;@&nbsp;<s:property value="model.fullName" /></div>
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
			<s:iterator value="aggSpotPager.list" var="aggSpot">
				<a class="fl pr" style="text-decoration: none;padding:9px !important;background-color:#fff;height:113px;width:113px;margin:1px" target="_blank" 
				    href="${pageContext.request.contextPath}/spot/detail_agg_spot.bc?id=<s:property value="%{#aggSpot.id}"/>">
					<img title="<s:property value="%{#aggSpot.dish.name}"/>" alt="<s:property value="%{#aggSpot.dish.name}"/>" src="${imageContext}/<s:property value="%{#aggSpot.getSpotImgPath(120)}"/>" style="height:113px;width:113px;">
					<div class="pa" style="left: 9px; top: 9px; background-color: rgb(255, 255, 255); opacity: 0.7; display: none;">
						<div class="fb tc vm c000" style="display: table-cell;height:120px;padding: 0 5px;width:110px;"><s:property value="%{#aggSpot.dish.name}"/></div>
					</div>
				</a>
			</s:iterator>
		</div>
	</div>
	</div>
	<div id="hidden-data" style="display:none;">
		<input id="at_hidden_field" name="at" type="hidden" value="<s:property value="aggSpotPager.pageNo" />"/>
		<input id="total_hidden_field" name="total" type="hidden" value="<s:property value="aggSpotPager.totalPage" />"/>
		<input id="place_id_hidden_field" name="id" type="hidden" value="<s:property value="model.id" />"/>
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
				var id = $('#place_id_hidden_field').val();
				$('#more_at_place #loading').show();
				$.get('${pageContext.request.contextPath}/spot/ajax_more_at_place.bc?id='+ id + "&at=" + at, 
					function(response){
						if(!response.isError){
							BC.Template.T({
								name: 'pod_place_more_sighting',
			   			   	    domId: '#sighting-set-template',
			   			   	    hintId: '#more_at_place #loading',
			   			      	data: response.aggSpotPager,
			   			      	params: {
			   			      		
			   			      	},
			   			      	callback: function(){
			   			      		$('#at_hidden_field').val(response.aggSpotPager.pageNo);
			   			      		$('#total_hidden_field').val(response.aggSpotPager.totalPage);
			   			      		setPage(response.aggSpotPager.pageNo, response.aggSpotPager.totalPage);
			   			      		$('#sightings').append($('#sighting-set-template').html());
			   			      		setPhotos();
			   			      		moveViewPoint();
			   			      	}
							});
						}
				    }).complete(function(){
				    	$('#more_at_place #loading').hide();
				    });
			}
			
			//execute
			setPage(<s:property value="aggSpotPager.pageNo" />, <s:property value="aggSpotPager.totalPage" />);
			bindingPage();
			setPhotos();
		});
	</script>
</div>