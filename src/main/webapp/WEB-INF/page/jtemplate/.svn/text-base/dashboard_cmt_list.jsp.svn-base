{#foreach $T.list as comment}
{#if $T.comment$first} 
<li class="p15 borb1 first clean">
{#elseif $T.comment$last}
<li class="p15 bort1 last clean">
{#else}
<li class="p15 borb1 bort1 clean">
{#/if}
	<a class="l mr10 h50 br5 w50" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">
		<img class="br5" src="{$T.comment.avatar}" alt="{$T.comment.name}" title="{$T.comment.name}" width="50" height="50" >
	</a>
   <div class="l" style="width:568px;">
       	<p class="mb5 f13 lh120">
       	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.comment.userId}">{$T.comment.name}</a>:
       	{$T.comment.content} <span class="f_brackets cg">(<span id="timeAgo">{$T.comment.commentAt}</span>)</span></p>
       	<p class="f12 lh150 c888" >评论我分享的
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.comment.spotId}">{$T.comment.dishName}@{$T.comment.placeFullName}</a>&nbsp;来自{$T.comment.spotPostBy}</p>
   </div>
</li>  
{#/for}
