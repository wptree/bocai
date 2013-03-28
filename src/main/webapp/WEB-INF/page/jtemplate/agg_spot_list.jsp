{#foreach $T.list as aggSpot}
<div id="aggSpot_{$T.aggSpot.id}" class="aggSpotPanel bbox">
	<div class="aggSpotPanel_body">
		<div class="aggSpotPanel_body_left pr clean">
			<ul class="pb5 h32 mt10 ml-5 mb10 hpbg clean">
				<li class="l f13 pl14 cfff hrbbg h32 lh32 w90 mr5">他们也分享了</li>
				{#foreach $T.aggSpot.spottedBys as user}
                <li class="l mr5"><a href="{$P.contextPath}/user/profile.bc?id={$T.user.id}" title="进入{$T.user.name}主页"><img alt="{$T.user.name}" title="{$T.user.name}" src="{$T.user.avatar}" width="32" height="32" /></a></li>
                {#/for}
                {#if $T.aggSpot.spottedByHasMore}
                <li class="l w30 h32 pr">
					<a title="查看所有的分享" id="peer_more_{$T.aggSpot.id}" class="pa lh16" style="left:0; bottom:0;" href="{$P.contextPath}/spot/detail_agg_spot.bc?id={$T.aggSpot.id}" >更多</a>
				</li>
                {#/if}
			</ul>
            <div class="aggSpot_title">
                <span class="aggSpot_name ffyh">
                	<a title="查看所有的分享" href="javascript:void(0);" onclick="OP.switchSighting('{$T.aggSpot.id}')" class="spotName">{$T.aggSpot.dishName}</a>@<a class="spotName" title="查看{$T.aggSpot.placeName} - {$T.aggSpot.place2ndName}店内所有分享" href="{$P.contextPath}/spot/toPlace.bc?id={$T.aggSpot.placeId}">{$T.aggSpot.placeFullName}</a>
                </span>
                {#if $T.aggSpot.averagePrice > 0}
                <span class="spanhe2 spotPrice">参考价￥{$T.aggSpot.averagePrice}</span>
                {#/if}
                 
            </div>
            <div class="aggSpot_way">
            	<span class="aggSpot_post_way">
            		<span>最后由&nbsp;<a class="shenhei" title="查看{$T.aggSpot.lastSpotCreatedByName}所有的分享" href="{$P.contextPath}/user/profile.bc?id={$T.aggSpot.lastSpotCreatedById}">{$T.aggSpot.lastSpotCreatedByName}</a>&nbsp;</span>
            		<span id="timeAgo">{$T.aggSpot.lastSpotCreatedAt}</span>&nbsp;通过 &nbsp;{$T.aggSpot.lastSpotPostBy}&nbsp;发布
            	</span>
            </div>
            <div class="aggSpot_splitter_silk"></div>
            <div class="aggSpot_desc f14">{$T.aggSpot.lastSpotDescription}</div>
            <ul class="aggSpot_tag">
            	
            </ul>
		</div>
		<div class="aggSpotPanel_body_right">
			<div class="dish_photo">
				<div class="dish_photo_tip">
					<p class="pp1" id="left_nom_num_{$T.aggSpot.id}">{$T.aggSpot.nomNum}</p>
					<p class="pp2">喜欢</p>
                </div>
                <a href="spot/detail_agg_spot.bc?id={$T.aggSpot.id}"><img alt="{$T.aggSpot.dishName}@{$T.aggSpot.placeName} - {$T.aggSpot.place2ndName}" title="查看{$T.aggSpot.dishName}@{$T.aggSpot.placeName} - {$T.aggSpot.place2ndName}" src="{$P.imageContext}/spot/{$T.aggSpot.lastSpotId}/180.{$T.aggSpot.lastSpotImgType}" width="170px" height="170px" /></a>
			</div>
        </div>
        <div class="clear"></div>
	</div>
	<div id="aggSpotPanel_control_{$T.aggSpot.id}" class="aggSpotPanel_control bbr5">
		<ul class="control-left">
            <li id="sighting_btn_{$T.aggSpot.id}" class="operator sighting sighting-off">
            	<a title="查看所有分享" href="javascript:void(0)" class="heihui" onclick="OP.switchSighting('{$T.aggSpot.id}')"><span class="cgray">发现</span></a>
            	<span class="spanhui f_brackets">(<span id="comment_num_{$T.aggSpot.id}">{$T.aggSpot.spottedNum}</span>)</span>
            </li>
            <li class="operator wantted">
         		<a title="我想吃这道菜" href="javascript:void(0);" class="heihui" onclick="OP.wantSpot('{$T.aggSpot.id}')"><span class="cgray">想吃</span></a>
         		<span class="spanhui f_brackets">(<span id="want_num_{$T.aggSpot.id}">{$T.aggSpot.wantedNum}</span>)</span>
    		</li>
            <li class="operator nom">
                <a title="我喜欢这道菜" href="javascript:void(0);" class="heihui" onclick="OP.nomSpot('{$T.aggSpot.id}');"><span class="cgray">喜欢</span></a>
                <span class="spanhui f_brackets">(<span id="nom_num_{$T.aggSpot.id}">{$T.aggSpot.nomNum}</span>)</span>
            </li>
            <li class="operator correction">
                <a title="我来纠错" href="javascript:void(0);" class="heihui" onclick="OP.correction('#{$T.aggSpot.id}_correction','{$T.aggSpot.id}');"><span class="cgray">纠错</span></a>
                <span></span>
            </li>
        </ul>
        <ul class="control-right">
          	<li><a title="查看所有同名菜" href="javascript:void(0);" class="shenhe" onclick="OP.getBySameDish('{$T.aggSpot.dishName}');location.reload();">同名菜</a></li>
          	<li><span class="spanshenhe">|</span></li>
   			<li><a title="查看该店所有菜" href="{$P.contextPath}/spot/toPlace.bc?id={$T.aggSpot.placeId}" class="shenhe">同家店</a></li>
        </ul>
    </div>
    <ul id="aggSpot_loading_{$T.aggSpot.id}" class="aggSpotPanel_loading" style="display:none">
		<li class="review loading cchrome1">
			<img src="{$P.staticContext}/images/cream-medium-load.gif">加载中...
		</li>
	</ul>
	<ul id="aggSpot_spot_list_{$T.aggSpot.id}" class="spots" style="display:none"></ul>
</div>
{#/for}