<#-- for suggestion list -->
<li>
	<dl></dl>
	<dt>美食</dt>
	<#list dishs as dish>
		<dd class="item">
			<a href="javascript:void(0);" class="dish">${dish}</a>
		</dd>
	</#list>
	<dt>餐厅</dt>
	<#list places as place>
		<dd class="item">
			<a href="javascript:void(0);" class="place">${place}</a>
		</dd>
	</#list>
	<dt>波客</dt>
	<#list users as user>
		<dd class="item">
			<a href="javascript:void(0);" class="boke">${user}</a>
		</dd>
	</#list>
</li>