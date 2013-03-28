{#foreach $T.list as spot}
<li id="aggspot" class="mb20 bbox">
	<div class="br5 p20">
		<div class="mb10 ffyh" >
			<span>
				<a class="f16 fb" href="javascript:void(0);" onclick="OP.getBySameDish('{$T.spot.dishName}')">
				<span class="c000">{$T.spot.dishName}</span></a>
				<span class="f16">@</span>
				<a class="f16 fb" href="{$P.context}/spot/toPlace.bc?id={$T.spot.placeId}">
				<span class="c000">{$T.spot.placeFullName}</span></a>
				<a class="address" href="{$P.context}/spot/toPlace.bc?id={$T.spot.placeId}">
					<span class="locality">{$T.spot.placeLocationCity}</span>
					,
					<span class="street-address">{$T.spot.placeLocationStreet}</span>
				</a>
			</span>
		</div>
		<div class="clean">
			<div id="review_{$P.type}_{$T.spot.id}" class="l mb10 w320 br5 bor2 bgc7 p8 pr">
				<div id="spot_view_{$P.type}_{$T.spot.id}" class="pr spot-view" spotId="{$T.spot.id}">
					<div id="review_photo_{$P.type}_{$T.spot.id}" class="pr w320 h320 z5" style="background-color:#fff;">
						<a href="{$P.context}/spot/detail_spot.bc?id={$T.spot.id}" class="disB">
							<img height="320" width="320" src="{$P.imageContext}/spot/{$T.spot.id}/480.{$T.spot.imgType}"/>
						</a>
					</div>
					<div id="review_map_{$P.type}_{$T.spot.id}" class="h320 w280 pa r0 t0 z1">
						<div class="map pr h320 w280 z3">
							<div id="loading" class="h320 w280" style="text-align: center; line-height:320px;">
								<img src="{$P.staticContext}/images/loading.gif"/> 
								<span class="fs-16 c-label ffyh ml3" style="vertical-align: 2px;">加载地图...</span></div></div>
					</div>
				</div>	
				<div id="review_action_{$P.type}_{$T.spot.id}" class="pt5 clean h16 lh16 ofh">
					<div class="l wsnw">
						<a class="l lh16 pl15 hlink2 disB pinonbg" onclick="OP.togglePRMap('{$P.type}', {$T.spot.id}); return false;" href="javascript:void(0);">
							<span>{$T.spot.placeAddress}</span></a>
					</div>
					<div class="r fadebg pl5 w70">
						<a id="nom_{$P.type}_{$T.spot.id}" class="r disB h16 ml5 w16 h16 nombg tin" href="#">喜欢</a>
						<a id="want_{$P.type}_{$T.spot.id}" class="r disB h16 ml5 w16 h16 wantbg tin" href="#">喜欢</a>
					</div>
				</div>
			</div>
		</div>
		<div class="spotted-by" style="position: absolute; top: 25px;left:15px;z-index:30; width:200px;">
			<span class="label">于{$T.spot.createAtStr}分享</span>
		</div>
		<div class="info clearfix">
			<div class="person-info">
				<div class="created-at">
					<a href="{$P.context}/user/profile.bc?id={$T.spot.spotedById}">{$T.spot.spotedByName}</a> <span id="timeAgo">{$T.spot.createAt}</span>发现：
					<span class="desc fb lh120">
					{$T.spot.description}
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<div id="spot_control_{$T.spot.id}" class="aggSpotPanel_control">
				<ul class="control-left">
                       <li id="sighting_btn_{$T.spot.id}" class="operator sighting sighting-off">
                       	<a href="javascript:void(0)" class="heihui" onclick="OP.switchComments('{$T.spot.id}')"><span class="cgray">评论</span></a>
                       	<span class="spanhui f_brackets">(<span id="comment_num_{$T.spot.id}">{$T.spot.commentNum}</span>)</span>
                       </li>
                       <li class="operator wantted">
		                <a href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('{$T.spot.aggSpotId}')"><span class="cgray">想吃</span></a>
		                <span class="spanhui f_brackets">(<span id="want_num_{$T.spot.aggSpotId}">{$T.spot.aggSpotWantedNum}</span>)</span>
	            	</li>
                       <li class="operator nom">
                        <a href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('{$T.spot.aggSpotId}');"><span class="cgray">喜欢</span></a>
                        <span class="spanhui f_brackets">(<span id="nom_num_{$T.spot.aggSpotId}">{$T.spot.aggSpotNomNum}</span>)</span>
                       </li>
                       <li class="operator correction">
                        <a href="javascript:void(0);" class="heihui"><span class="cgray">纠错</span></a>
                        <span></span>
                       </li>
                   </ul>
                   <ul class="control-right">
                   	<li><a href="javascript:void(0);" style="color:#8F7658" onclick="OP.getBySameDish('{$T.spot.dishName}')">同名菜</a></li>
                   </ul>
	</div>
	<ul id="spot_comment_{$T.spot.id}" class="reviews none"></ul>
	<script type="text/javascript">
		$('#review_map_{$P.type}_{$T.spot.id} .map').data('lat', {$T.spot.placeLocationLat});
		$('#review_map_{$P.type}_{$T.spot.id} .map').data('lng', {$T.spot.placeLocationLng});
		//OP.addDBMap('{$P.type}', {$T.spot.id}, {$T.spot.placeLocationLat}, {$T.spot.placeLocationLng});
	</script>
</li>
{#/for}