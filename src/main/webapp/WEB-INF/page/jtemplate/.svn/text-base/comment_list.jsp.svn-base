{#foreach $T.list as cmt}
<div id="review_comment_{$T.cmt.id}" class="comment-row">
	<a class="avatar" href="{$P.contextPath}/user/profile.bc?id={$T.cmt.userId}">
		<img src="{$T.cmt.avatar}" alt="{$T.cmt.name}" height=30 width=30>
	</a>
	<div class="comment">
		<div class="body">
			<a href="{$P.contextPath}/user/profile.bc?id={$T.cmt.userId}" style="float:left;">{$T.cmt.name}：</a>
			<div>{$T.cmt.content}  <span class="timestamp cg" id="timeAgo">{$T.cmt.commentAt}</span></div>
		</div>
	</div>
	<div class="clear"></div>
</div>
{#/for}