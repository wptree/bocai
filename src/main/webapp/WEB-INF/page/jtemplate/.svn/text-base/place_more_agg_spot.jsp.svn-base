{#foreach $T.list as aggSpot}
{#if $T.aggSpot$index%3 == 2}
<li class="l mb20" style="width:202px;">
{#else}
<li class="l mr26 mb20" style="width:202px;">
{#/if}
	<div class="bor3 p10 br5 pb5 bgc10" style="width:180px;">
        <div class="youpiaoda_xihuan2">
        	<p class="pp1" id="left_nom_num_{$T.aggSpot.id}">{$T.aggSpot.nomNum}</p>
            <p class="pp2">喜欢</p>
        </div>
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.aggSpot.lastSpotId}">
        	<img src="{$P.imageContext}/spot/{$T.aggSpot.lastSpotId}/180.{$T.aggSpot.lastSpotImgType}" width="180" height="180" />
        </a>
        <div class="mt5 pr" style="height:16px;">
  			<a class="hlink2 lh120" style="margin-top:5px;cursor:pointer"
  				href="{$P.context}/spot/detail_spot.bc?id={$T.aggSpot.lastSpotId}">{$T.aggSpot.spottedNum}次分享</a>
            <div class="r" style="margin-top:-5px;">
            	<a href="javascript:void(0);" title="想吃" class="heihui" onclick="OP.wantSpot('{$T.aggSpot.id}')">
             		<img src="{$P.staticContext}/images/place_1_63.jpg" /></a>
             	<a href="javascript:void(0);" title="喜欢" class="heihui" onclick="OP.nomSpot('{$T.aggSpot.id}');">
             		<img src="{$P.staticContext}/images/place_1_65.jpg" /></a>
            </div>
        </div>
    </div>
    <div class="ml12 mt5 fb f14 lh14">
    	<a style="color:#333333 !important" href="{$P.context}/spot/detail_spot.bc?id={$T.aggSpot.lastSpotId}">{$T.aggSpot.dishName}</a>
    </div>
</li>
{#/for}