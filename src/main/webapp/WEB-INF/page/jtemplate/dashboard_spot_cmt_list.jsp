<form id="spot_cmt_form_{$P.type}_{$T.id}" class="cmt clean f12 mb10" method="post" action="{$P.context}/spot/ajax_add_comment.bc" accept-charset="UTF-8">
	<div class="mt8 clean">
		<div id="spot_cmt_ta_out_{$P.type}_{$T.id}"  class="l bg2 bor1 br5 curt p1 pr p5" style="width:450px;">
			<textarea id="spot_cmt_ta_{$P.type}_{$T.id}" 
				class="l f12 ffyh" name="content"
				onpropertychange="OP.countInputStrByte('#spot_cmt_ta_{$P.type}_{$T.id}','#input_num_count_{$P.type}_{$T.id}',140);"
				oninput="OP.countInputStrByte('#spot_cmt_ta_{$P.type}_{$T.id}','#input_num_count_{$P.type}_{$T.id}',140);"
				onfocus="OP.focusDBCmt('{$P.type}', {$T.id})" onblur="OP.blurDBCmt('{$P.type}', {$T.id})"></textarea>
		</div>
		<div class="r">
			<input class="h30 bcbutton comment-sumbit" id="spot_cmt_sub_{$P.type}_{$T.id}"  type="submit" value="添加评论" name="commit"/>
			<div id="input_num_count_out_{$P.type}_{$T.id}" class="tl none"><span class="fb ctitle ml10 h20 lh20"><span id="input_num_count_{$P.type}_{$T.id}">0</span>/140</span></div>
		</div>
	</div>
	<input id="spot_cmt_hidden_{$P.type}_{$T.id}" name="spotId" type="hidden" value="{$T.id}" ></input>
	<input id="spot_cmt_reply_hidden_{$P.type}_{$T.id}" name="replyStr" type="hidden" ></input>
</form>
<ul id="spot_cmt_list_{$P.type}_{$T.id}">
	{#if $T.comments != null}
	{#foreach $T.comments as comment}
	<li id="cmt_item_{$P.type}_{$T.comment.id}" class="pt10 {#if !$T.comment$last}pb10{#/if} bts-d btw-1 bc-gray clean" >
		<a class="l br3 mr5" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">
			<img class="br3" src="{$T.comment.avatar}" alt="{$T.comment.name}" height=32 width=32></img>
		</a>
		<div class="l lh150 cdesc clean" style="width:505px;">
			<div><a class="l mr8" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">{$T.comment.name}</a>
				{$T.comment.content}
				<span class="cg">(<span id="timeAgo">{$T.comment.commentAt}</span>)</span></div>
			<div class="ta-r lh120">
				<a class="theme" href="javascript:void(0);" onclick="OP.dashboardReplyTo({$T.id}, '{$P.type}', {$T.comment.userId}, '{$T.comment.name}', {$T.comment.id});">回复</a>
			</div>
		</div>
	</li>
	{#/for}
	{#else}
	<li id="cmt_item_empty_{$P.type}_{$T.id}" class="mt10 lhl20 tc ctitle">暂时没有评论</li>
	{#/if}
</ul>
