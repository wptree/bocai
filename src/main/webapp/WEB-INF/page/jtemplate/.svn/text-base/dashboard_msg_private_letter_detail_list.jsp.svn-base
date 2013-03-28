<li class="p15 borb1 clean">
	<div class="l mr10">
		<a id="re_private_msg" class="hlink" href="javascript:void(0);" onclick="OP.returnDialogList(); return false;">返回所有私信</a>
	    &gt;
	    <span class="ctitle p13 lh20 h20">共<span>{$T.totalCount}</span>条私信</span>
	</div>
	<div class="r">
	      <a class="tool msg" href="javascript:void(0);" onclick="OP.sendPrivateMsg({$P.cp.id},'{$P.cp.name}');return false;">私信TA</a>
	</div>
</li>
{#foreach $T.list as pl}
{#if $T.pl == null}
	{#continue}
{#/if} 
<li class="p10 
{#if $T.pl$first}
pt20 bort1 
{#/if}
{#if $T.pl$last}
pb20 borb1 
{#/if}
 clean">
	{#if $T.pl.fromMe == true}
	    <div class="l ml65 p10 br5 bgc3 pr" style="width:491px;">
	    	<div class="pa f20" style="right:-11px;top:12px;"><span style="color:#F1EEE5">◆</span></div>
	    	<p class="mb10 f13 lh150" style="overflow: hidden;">
	    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.pl.fromWho.id}">我</a>:
	    	{$T.pl.content}
	    	<span class="f_brackets cg">(<span>{$T.pl.createdAtStr}</span>)</span></p>
	    </div>
	    <a class="r h50 br5 w50" href="{$P.context}/user/profile.bc?id={$T.pl.fromWho.id}">
			<img class="br5" src="{$T.pl.fromWho.avatar}" alt="{$T.pl.fromWho.name}" width="50" height="50" >
		</a>
	{#else}
	    <a class="l mr15 h50 br5 w50" href="{$P.context}/user/profile.bc?id={$T.pl.fromWho.id}">
			<img class="br5" src="{$T.pl.fromWho.avatar}" alt="{$T.pl.fromWho.name}" width="50" height="50" >
		</a>
	    <div class="l p10 br5 bgc7 pr" style="width:491px;">
	    	<div class="pa f20" style="left:-11px;top:12px;"><span class="cfff">◆</span></div>
	    	<p class="mb10 f13 lh150" style="overflow: hidden;">
		    	<a class="hlink" href="{$P.context}/user/profile.bc?id={$T.pl.fromWho.id}">{$T.pl.fromWho.name}</a>:
		    	{$T.pl.content}
		    	<span class="f_brackets cg">(<span>{$T.pl.createdAtStr}</span>)</span>
		    	{#if $T.pl.status != 1}
		    	<span class="f_brackets cerror">new</span>
		    	{#/if}
	    	</p>
	    </div>
    {#/if}
</li>  
{#/for}