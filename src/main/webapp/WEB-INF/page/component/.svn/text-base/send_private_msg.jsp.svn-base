<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="send_private_msg" class="hm20 br5 bgc" style="display:none; width: 350px;">
	<div class="pr p10 clean" style="border-bottom: 1px solid #E1DCC8;">
		<div class="l tl"><span class="f14 ffyh">发私信</span></div>
	</div>
	<div id="spm_hidden-data" style="display:none;">
	</div>
	<form id="send_private_msg_form" 
		method="post" action="${pageContext.request.contextPath}/user/send_private_msg.bc" accept-charset="UTF-8">
		<div class="p10 bgc7 br5">
			<div class="mb10 clean">
				<div class="l mr10 mt3"><span class="tr ctitle f13 ffyh lh20 h20">发  给</span></div>
				<div class="l msgin bg2 bor1 br3 curt p1 pr p3" style="width:150px">
					<input id="spm_tn_input" class="text" type="text" name="targetName" value="" />
					<span id="spm_tn_input_mock" class="c888 pa ffyh lh20 h20" style="left:3px; top:3px;">请输入对方昵称</span>
				</div>
				<input id="spm_uid_hidden" class="text" type="hidden" name="targetId" value="" />
			</div>
			<div class="mb10 clean">
				<div class="l mr10 mt3"><span class="tr ctitle f13 ffyh lh20 h20">内 容</span></div>
				<div class="l msgta bg2 bor1 br3 curt p1 pr p3" style="width:280px">
					<textarea id="spm_content_ta" name="content"
						onpropertychange="OP.countInputStrByte('#spm_content_ta','#spm_num_count',300);"
						oninput="OP.countInputStrByte('#spm_content_ta','#spm_num_count',300);"></textarea>
				</div>
			</div>
			<div class="clean">
				<div class="r clean">
					<span id="spm_error_span" class="ctitle mr5 h20 lh20 none"></span>
					<div id="spm_loading" class="l mr5 mt3 none"><img src="${staticContext}/images/loading.gif"></div>
					<span class="fb ctitle mr5 h20 lh20"><span id="spm_num_count">0</span>/300</span>
					<input class="h25 bcbutton" type="submit" name="commit" onclick="" value="发送" />
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#spm_tn_input_mock').click(function(){
				$('#spm_tn_input').focus();
			});
			$('#spm_tn_input').focus(function(){
				$('#spm_tn_input_mock').hide();
			});
			$('#spm_tn_input').blur(function(){
				var val = $('#spm_tn_input').val();
				if(!val || val == ''){
					$('#spm_tn_input_mock').show();
				}
			});
			//register send private msg form
			$('#send_private_msg_form').ajaxForm({
     	    	dataType : "json",
    			beforeSubmit : function (arr, form, options){
   				   var content = $('#spm_content_ta').val();
   				   if(!content || $.trim(content).length == 0){
   					   return false;
   				   }
   				   $('#spm_loading').show();
    			},
    			success : function (response, statusText, xhr, $form){
   				   if(!response.isError){
   				      $.modal.close();
   				   }else{
   					   $('#spm_error_span').html(response.errorMsg);
   					   $('#spm_error_span').show();
   				   }
    			},
    			complete: function(){
    				$('#spm_loading').hide();
    			}
    		 });
		});
	</script>
</div>