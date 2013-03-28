{#foreach $T.list as user}
<li class="person 
{#if $T.user$first}
 first 
{#/if}
{#if $T.user$last}
 last 
{#/if}
 clean">
	<div class="right">
		<a id="{$T.user.id}_user_followTag" {#if $P.followship[$T.user.id] == "取消关注"} title="取消关注"  class="unfollow-button"{#else} title="加关注"  class="follow-button"{#/if}
			onclick="global.changeFollow('{$T.user.id}','user');"
			href="javascript:void(0);">
			{$P.followship[$T.user.id]}
		</a>
	</div>
	<a title="进入{$T.user.name}主页"  class="avatar" href="{$P.context}/user/profile.bc?id={$T.user.id}">
		<img alt="{$T.user.name}" title="进入{$T.user.name}主页" src="{$T.user.avatar}" alt="{$T.user.name}">
	</a>
	<div class="details">
		<div class="hsubgroup">
			<strong>
				<a title="进入{$T.user.name}主页" href="{$P.context}/user/profile.bc?id={$T.user.id}">{$T.user.name}</a>
			</strong>
			<span>
				·
				<span class="city">{$T.user.cityName}</span>
				·
				<span class="stat"><strong>{$T.user.totalSpotCount}</strong> 次分享，<strong>{$T.user.followedByNumber}</strong> 个粉丝 </span>
			</span>
		</div>
		<div class="bio">
			<p>{$T.user.selfDescription}</p>
		</div>
		<div id="boke_latest_spot_{$T.user.id}" class="w80c cg2"></div>
	</div>
</li>
{#/for}
