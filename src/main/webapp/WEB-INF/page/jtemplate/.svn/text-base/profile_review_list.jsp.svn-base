<li class="review" style="">
	<form id="comment_form_review_{$T.id}" class="cmt clean f12" method="post" action="{$P.context}/spot/ajax_add_comment.bc" accept-charset="UTF-8">
		<div class="mb15 clean">
			<div id="comment_text_review_out_{$T.id}" class="l bg2 bor1 br5 curt p1 pr p5" style="width:535px;">
				<textarea id="comment_text_review_{$T.id}" 
					 name="content" class="l f12 ffyh" 
					 onpropertychange="OP.countInputStrByte('#comment_text_review_{$T.id}','#input_num_count_{$T.id}',140);"
					 oninput="OP.countInputStrByte('#comment_text_review_{$T.id}','#input_num_count_{$T.id}',140);"
					 onfocus="OP.focusCommentBox({$T.id})" onblur="OP.blurCommentBox({$T.id})"></textarea>
			</div>
			<div class="r">
				<input id="comment_submit_review_{$T.id}" class="h30 bcbutton comment-sumbit" type="submit" value="添加评论" name="commit"/>
				<div id="input_num_count_out_{$T.id}" class="tl none"><span class="fb ctitle ml10 h20 lh20"><span id="input_num_count_{$T.id}">0</span>/140</span></div>
			</div>
		</div>
		<input id="comment_spotId_hidden_{$T.id}" name="spotId" type="hidden" value="{$T.id}" ></input>
		<input id="comment_reply_hidden_{$T.id}" name="replyStr" type="hidden" ></input>
	</form>
	<ul id="comments_{$T.id}">
		{#if $T.comments != null}
		{#foreach $T.comments as comment}
		<li id="comment_item_{$T.comment.id}" class="pt10 {#if !$T.comment$last}pb10{#/if} bts-d btw-1 bc-gray clean" >
			<a class="l br3 mr5" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">
				<img class="br3" src="{$T.comment.avatar}" alt="{$T.comment.name}" height=32 width=32></img>
			</a>
			<div class="l lh150 cdesc clean" style="width:590px;">
				<div><a class="l mr8" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">{$T.comment.name}</a>
					{$T.comment.content}
					<span class="cg">(<span id="timeAgo">{$T.comment.commentAt}</span>)</span></div>
				<div class="ta-r lh120">
					<a class="theme" href="javascript:void(0);" onclick="OP.replyTo({$T.id}, {$T.comment.userId}, '{$T.comment.name}', {$T.comment.id});">回复</a>
				</div>
		    </div>
		</li>
		{#/for}
		{#else}
		<li id="comment_item_empty_{$T.id}" class="mt10 lhl20 tc ctitle">暂时没有评论</li>
		{#/if}
	</ul>
</li>