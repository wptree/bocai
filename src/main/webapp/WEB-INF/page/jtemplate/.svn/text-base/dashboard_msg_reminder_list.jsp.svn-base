{#foreach $T.list as noti}
{#if $T.noti == null}
	{#continue}
{#/if}
<li class="p15 borb1 bort1  
{#if $T.noti.status == null || $T.noti.status == 0}
 new 
{#/if}
 clean">
	{#if $T.noti.type=="USER_FOLLOW_USER"}
	<div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>
       	<span class="f13">开始关注你了</span>
       	<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span>
    </div>
    {#elseif $T.noti.type=="SPOT_WANTED"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>
    	<span class="f13">“想吃”你分享的</span>
    	<a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">{$T.noti.dishName}@{$T.noti.placeName}</a>
    	<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span>
    </div>
    {#elseif $T.noti.type=="SPOT_NOMMED"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>
    	<span class="f13">“喜欢”你分享的</span>
    	<a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">{$T.noti.dishName}@{$T.noti.placeName}</a>
    	<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span>
    </div>
    {#elseif $T.noti.type=="COMMENT_SUMMIT"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<p class="mb5 f13 lh120">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>:
    	{$T.noti.comment}&nbsp;<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span></p>
       	<p class="f12 lh150 c888" >评论我分享的
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">{$T.noti.dishName}@{$T.noti.placeName}</a>
    </div>
    {#elseif $T.noti.type=="COMMENT_REPLY"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<p class="mb5 f13 lh120">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>:
    	{$T.noti.comment}&nbsp;<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span></p>
       	<p class="f12 lh150 c888" >回复我的评论
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">"{$T.noti.baseCmt}"</a>
    </div>
    {#elseif $T.noti.type=="COMMENT_GOOD"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<p class="mb5 f13 lh120">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>:
    	不错&nbsp;<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span></p>
       	<p class="f12 lh150 c888" >评论我分享的
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">{$T.noti.dishName}@{$T.noti.placeName}</a>
    </div>
    {#elseif $T.noti.type=="COMMENT_GREAT"}
    <div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">
		<img class="br5" src="{$T.noti.userAvatar}" alt="{$T.noti.sourceUserName}" width="50" height="50" >
	</a></div>
    <div class="l" style="width: 560px;">
    	<p class="mb5 f13 lh120">
    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.noti.sourceUserId}">{$T.noti.sourceUserName}</a>:
    	非常好&nbsp;<span class="f_brackets cg">(<span id="timeAgo">{$T.noti.timelineStr}</span>)</span></p>
       	<p class="f12 lh150 c888" >评论我分享的
        <a href="{$P.context}/spot/detail_spot.bc?id={$T.noti.targetId}">{$T.noti.dishName}@{$T.noti.placeName}</a>
    </div>
    {#else}
    	{#continue}
    {#/if}
</li>  
{#/for}