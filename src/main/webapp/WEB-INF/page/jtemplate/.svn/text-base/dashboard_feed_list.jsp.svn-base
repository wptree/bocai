{#foreach $T.list as spot}
{#if $T.spot == null} 
	{#continue}
{#/if}
{#if $T.spot$first} 
<li id="spot_{$P.type}_{$T.spot.id}" class="p15 borb1 clean">
{#else}
<li id="spot_{$P.type}_{$T.spot.id}" class="p15 bort1 borb1 clean">
{#/if}
	<a class="l h50 w50" href="{$P.context}/user/profile.bc?id={$T.spot.spotedById}">
		<img class="br5" src="{$T.spot.spotedByAvatar}" alt="{$T.spot.spotedByName}" width="50" height="50" />
	</a>
	<div class="ml65">
		<a class="fb" href="{$P.context}/user/profile.bc?id={$T.spot.spotedById}"><span class="c000">{$T.spot.spotedByName}</span></a>
		<span id="timeAgo" title="{$T.spot.createAt}">{$T.spot.createAt}</span>
		<h3 class="mt5 mb10 ffyh">
			<a class="hlink" href="{$P.context}/spot/detail_spot.bc?id={$T.spot.id}"><span>{$T.spot.dishName}@{$T.spot.placeFullName}</span></a>
		</h3>
		<div class="mb10 lh120">
			<p>{$T.spot.description}</p>
		</div>
		<div class="clean">
			<div id="review_{$P.type}_{$T.spot.id}" class="l mb10 w280 br5 bor2 bgc7 p8 pr">
				<div class="pr">
					<div id="spot_view_{$P.type}_{$T.spot.id}" class="pr spot-view" spotId="{$T.spot.id}">
						<div id="review_photo_{$P.type}_{$T.spot.id}" class="pr w280 h280 z5" style="background-color:#fff;">
							<a href="{$P.context}/spot/detail_spot.bc?id={$T.spot.id}" class="disB">
								<img height="280" width="280" src="{$P.imageContext}/spot/{$T.spot.id}/480.{$T.spot.imgType}"/>
							</a>
						</div>
						<div id="review_map_{$P.type}_{$T.spot.id}" class="h280 w265 pa r0 t0 z1">
							<div class="map pr h280 w265 z10"><div id="loading" class="h280 w265" style="text-align: center; line-height:280px;">
									<img src="{$P.staticContext}/images/loading.gif"/> 
									<span class="fs-16 c-label ffyh ml3" style="vertical-align: 2px;">加载地图...</span></div></div>
						</div>
					</div>
					<div id="review_action_{$P.type}_{$T.spot.id}" class="pt5 clean h16 lh16 ofh">
						<div class="l wsnw">
							<a class="l lh16 pl15 hlink2 disB pinonbg" onclick="OP.toggleDBMap('{$P.type}', {$T.spot.id}); return false;" href="javascript:void(0);">
								<span>{$T.spot.placeAddress}</span></a>
						</div>
						<div class="r fadebg pl5 pa r0 w70">
							<a id="nom_{$P.type}_{$T.spot.id}" class="r disB h16 ml5 w16 h16 nombg tin" href="#">喜欢</a>
							<a id="want_{$P.type}_{$T.spot.id}" class="r disB h16 ml5 w16 h16 wantbg tin" href="#">喜欢</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tr h16 lh16">
			<span class="c888">点击(<span class="viewNum">{$T.spot.viewTimes}</span>)</span>
			<span class="divider">·</span>
			<a class="hlink" href="javascript:void(0);" onclick="OP.toggleDBCmt('{$P.type}', {$T.spot.id});"><span>评论(<span id="cmt_num_{$P.type}_{$T.spot.id}">{$T.spot.commentNum}</span>)</span></a>
		</div>
		<div id="comments_out_{$P.type}_{$T.spot.id}" class="db-comments bgc1 br5 p10 mt10 pr none">
			<div class="arrow"><span>◆</span></div>
			<div id="cmt_loading_{$P.type}_{$T.spot.id}" class="pr p10 cdesc tc none">
				<img class="mr10" src="{$P.staticContext}/images/cream-medium-load.gif"/>正在加载评论...</div>
			<div id="comments_{$P.type}_{$T.spot.id}" class=""></div>
		</div>
	</div>
	<script type="text/javascript">
		$('#review_map_{$P.type}_{$T.spot.id} .map').data('lat', {$T.spot.placeLocationLat});
		$('#review_map_{$P.type}_{$T.spot.id} .map').data('lng', {$T.spot.placeLocationLng});
		//OP.addDBMap('{$P.type}', {$T.spot.id}, {$T.spot.placeLocationLat}, {$T.spot.placeLocationLng});
	</script>
</li>
{#/for}