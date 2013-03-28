<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pod" style="background-color:#F5F3E7;">
    <form id="boke_search_form" action="${pageContext.request.contextPath}/boke_search.bc" method="post">
    	<div style='display:none'>
			<input id="pageAt_hidden_field" name="pageAt" type="hidden" value="0" />
		</div>
		<ul>
			<li class="ffyh">
				<input id="search_input" class="upload" label='找朋友，口味相似的人' type="text" size="20" name="keyword" style="width:190px; height:20px;" />
				<input type="submit" value="找人"  class="btn success" style="height: 34px; vertical-align: top;"/>
			</li>
		</ul>
    </form>
</div>
<script type="text/javascript">
	$(function(){
		$('#search_input').defaultValue();
		$('#boke_search_form').ajaxForm({
			dataType : "json",
			beforeSubmit : function (arr, form, options){
				$("#bestUserLink").removeClass("selected");
				$("#latestUserLink").removeClass("selected");
				$('#loading').show();
				return true;
			},
			success : function (response, statusText, xhr, $form){
				$('#page_title').html('搜索结果');
				if(response.isError){
					OP.showBokeListError("服务器出小差了，请稍后重试");
					return;
				}
				BC.Template.T({
					name: 'boke_list',
   			   	    domId: '#bokes',
   			      	data: response.dataPage,
   			     	params: { followship : response.followShip},
   			      	callback: function(){
					    if(response.dataPage.list.length==0){
							OP.showBokeListError("对不起，没有找到匹配的波客");
					    }
   			      		OP.updateBokeListPager(response.dataPage.pageNo, 
   			      				response.dataPage.totalPage, "search");
   			      	}
				});
			},
			complete: function(){
				$('#loading').hide();
			}
		});
	});
</script>