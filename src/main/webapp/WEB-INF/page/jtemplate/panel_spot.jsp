{#foreach $T.spotVos as spot}
{#if $T.spot$last && $P.type == 'main' && $T.total == 1}
<li class="spot nbb" id="spot-{$T.spot.id}">
{#else}
<li id="spot-{$T.spot.id}" class="spot clean">
{#/if}
	<a class="photo t-120" href="{$P.context}/spot/detail_spot.bc?id={$T.spot.id}">
		<img src="{$P.imageContext}/{$T.spot.imgPath120}" alt="{$T.spot.dishName}" width="125px" height="125px">
	</a>
	<div class="spot-container">
		<div class="mb8 clean">
			<div class="l mr8">
				<a class="br3" href="{$P.context}/user/profile.bc?id={$T.spot.spotedById}">
					<img class="br3" src="{$T.spot.spotedByAvatar}" alt="{$T.spot.spotedByName}" height=32 width=32>
				</a>
			</div>
			<div class="l wr-wb" style="width: 438px;">
				<div class="clean" style="height: 22px;">
					<a class="l mr5" href="{$P.context}/user/profile.bc?id={$T.spot.spotedById}">{$T.spot.spotedByName}</a>
					<span class="cchrome1">上传于<span class="cg"> <span id="timeAgo">{$T.spot.createAt}</span> 通过{$T.spot.postBy } </span></span> 
					<a class="r bg1 br3 p3 curd txtdecN"  href="javascript:void(0);"
						style="color:#717171; position: absolute; right: 0px; top: 0px;">
						<span id="review_count_{$T.spot.id}">{$T.spot.viewTimes}</span>&nbsp;点击</a>
				</div>
				<div class="cchrome1 lh150 pr">{$T.spot.description}</div>
			</div>
		</div>
		<div class="br5 bg1 p15 clean">
			<form id="comment_form_review_{$T.spot.id}" class="cmt" method="post" action="{$P.context}/spot/ajax_add_comment.bc" accept-charset="UTF-8">
				<div class="clean">
					<div id="comment_text_review_out_{$T.spot.id}" class="l bg2 bor1 br5 curt p1 mb10 pr p5 w360">
						<textarea id="comment_text_review_{$T.spot.id}" 
							class="l ffyh f12" name="content"
							onpropertychange="OP.countInputStrByte('#comment_text_review_{$T.spot.id}','#input_num_count_{$T.spot.id}',140);"
							oninput="OP.countInputStrByte('#comment_text_review_{$T.spot.id}','#input_num_count_{$T.spot.id}',140);"
							onfocus="OP.focusCommentBox({$T.spot.id})" onblur="OP.blurCommentBox({$T.spot.id})"></textarea>
					</div>
					<div class="r" style="width:72px;">
						<input id="comment_submit_review_{$T.spot.id}" class="h30 bcbutton comment-sumbit" type="submit" value="添加评论" onclick="" name="commit"></input>
						<div id="input_num_count_out_{$T.spot.id}" class="none"><span class="fb ctitle ml10 h20 lh20"><span id="input_num_count_{$T.spot.id}">0</span>/140</span></div>
					</div>
				</div>
				<input id="comment_tNo_hidden_{$T.spot.id}" name="tNo" type="hidden" value="{$T.spot.cmtTotalPage}" ></input>
				<input id="comment_spotId_hidden_{$T.spot.id}" name="spotId" type="hidden" value="{$T.spot.id}" ></input>
				<input id="comment_reply_hidden_{$T.spot.id}" name="replyStr" type="hidden" ></input>
			</form>
			<ul id="comments_review_{$T.spot.id}" class="wr-wb">
			{#if $T.spot.comments != null}
				{#foreach $T.spot.comments as comment}
				<li id="review_comment_{$T.comment.id}" class="pt8 {#if !$T.comment$last}pb8{#/if} bts-d btw-1 bc-gray clean">
					<a class="l br3 mr8" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">
						<img class="br3" src="{$T.comment.avatar}" alt="{$T.comment.name}" height=32 width=32>
					</a>
					<div class="l lh150 cdesc clean" style="width:408px;">
						<div><a class="l mr8" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">{$T.comment.name}</a>
							{$T.comment.content}
							<span class="cg">(<span id="timeAgo">{$T.comment.commentAt}</span>)</span></div>
						<div class="ta-r lh120">
							<a class="theme" href="javascript:void(0);" onclick="OP.replyTo({$T.spot.id}, {$T.comment.userId}, '{$T.comment.name}', {$T.comment.id});">回复</a>
						</div>
					</div>
				</li>
				{#/for}
			{#else}
				<li class="p5 tc cg empty">暂时没有评论</li>
			{#/if}
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</li>
{#/for}
{#if $P.type == 'detail_agg_spot'}
<li class="spot clean">
	<div id="divpagingup" class="fr mr-5"></div>
</li>
{#/if}
{#if $P.type == 'main' && $T.total > 1}
<li class="spot nbb clean">
	<div class="getmore"><a href="{$P.context}/spot/detail_agg_spot.bc?id={$T.id}">查看所有{$T.allCount}次分享</a></div>
</li>
{#/if}