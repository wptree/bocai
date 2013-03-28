<li id="cmt_item_{$P.type}_{$T.id}" class="pt10 {#if !$T.comment$last}pb10{#/if} bts-d btw-1 bc-gray clean" >
	<a class="l br3 mr5" href="{$P.context}/user/profile.bc?id={$T.userId}">
		<img class="br3" src="{$T.avatar}" alt="{$T.name}" height=32 width=32></img>
	</a>
	<div class="l lh150 cdesc clean" style="width:505px;">
		<div><a class="l mr8" href="{$P.context}/user/profile.bc?id={$T.userId}">{$T.name}</a>
			{$T.content}
			<span class="cg">(<span id="timeAgo">{$T.commentAt}</span>)</span></div>
		<div class="ta-r lh120">
			<a class="theme" href="javascript:void(0);" onclick="OP.dashboardReplyTo({$P.spotId}, '{$P.type}', {$T.userId}, '{$T.name}', {$T.id});">回复</a>
		</div>
	</div>
</li>