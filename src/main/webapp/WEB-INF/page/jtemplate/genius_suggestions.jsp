<li>
	<dl>
	<dt>
	<a href="javascript:void(0);" rel="{$P.query}" class="dishmore pl10">搜 "<span class="cerror">{$P.query}</span>" 相关美食
		{#if $T.dishCount >0}
			(还有<span class="cerror">{$T.dishCount}</span>道美食)
		{#/if}
		&gt;&gt;</a>
	</dt>
	{#if $T.dishs !=null }
		{#foreach $T.dishs as dish}
			<dd class="item">
				<a href="javascript:void(0);" class="dish">{$T.dish}</a>
			</dd>
		{#/for}
	{#/if}
	
	<dt>
	<a href="javascript:void(0);" rel="{$P.query}" class="placemore pl10">搜 "<span class="cerror">{$P.query}</span>" 相关餐厅
		{#if $T.placeCount >0 }
			(还有<span class="cerror">{$T.placeCount}</span>家餐厅)
		{#/if}
		&gt;&gt;</a>
	</dt>
	{#if $T.places !=null }
		{#foreach $T.places as place}
			<dd class="item">
				<a href="javascript:void(0);" class="place">{$T.place}</a>
			</dd>
		{#/for}
	{#/if}
	
	<dt>
	<a href="javascript:void(0);" rel="{$P.query}" class="bokemore pl10">搜 "<span class="cerror">{$P.query}</span>" 相关波客
		{#if $T.userCount > 0 }
			(还有<span class="cerror">{$T.userCount}</span>个波客)
		{#/if}
		&gt;&gt;</a>
	</dt>
	{#if $T.users !=null }
		{#foreach $T.users as user}
			<dd class="item">
				<a href="javascript:void(0);" class="boke">{$T.user}</a>
			</dd>
		{#/for}
	{#/if}
	</dl>
</li>