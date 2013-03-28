{#foreach $T.list as dialog}
{#if $T.dialog == null}
	{#continue}
{#/if}
<li class="p15 borb1 bort1 clean">
	<div class="l mr10"><a class="br5" href="{$P.context}/user/profile.bc?id={$T.dialog.counterParty.id}">
		<img class="br5" src="{$T.dialog.counterParty.avatar}" alt="{$T.dialog.counterParty.name}" width="50" height="50" >
	</a></div>
    <div class="l" style="width:560px;">
    	<p class="mb10 f13 lh120  "><a class="hlink" href="{$P.context}/user/profile.bc?id={$T.dialog.counterParty.id}">{$T.dialog.counterParty.name}</a>:
    	{$T.dialog.lastLetter.content}</p>
    	<div class="clean">
    		<p class="l f12 lh150 c888" >{$T.dialog.updatedAtStr}</p>
    		<ul class="r">
    			<li class="l"><a class="hlink mr2" href="javascript:void(0);" onclick="OP.getDialogDetail({$T.dialog.counterParty.id},0);return false;">
    				共{$T.dialog.letterCount}条私信
    				{#if $T.dialog.unreadCount != 0}
    					<span class="cerror f_brackets">({$T.dialog.unreadCount}未读)</span>
    				{#/if}
    				</a></li>
    			<li class="l"><span class="divider">·</span></li>
    			<li class="l"><a class="hlink ml2" href="javascript:void(0);" onclick="OP.sendPrivateMsg({$T.dialog.counterParty.id}, '{$T.dialog.counterParty.name}');return false;">回复</a></li>
    		</ul>
    	</div>
    </div>
</li>  
{#/for}