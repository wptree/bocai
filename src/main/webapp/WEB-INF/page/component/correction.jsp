<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="correctionDiv" class="jc_tanchudivout" style="display:none">
    <div class="jc_tanchudiv">
        <div class="jc_tanchudivsh">
            <div class="jc_tanchudivsh_le"></div>
            <div class="jc_tanchudivsh_ce">
                <div class="jc_tanchudivsh_cezuo">
                <ul>
                    <li  class="li1">我来纠正</li>
                </ul>
                </div>
                <div class="jc_tanchudivsh_ceyou">
                <a href="javascript:void(0)" onclick="OP.correctionModal.close();"><img src="${staticContext}/images/shouyetanchu_6.jpg" width="32" height="33" /></a>
                </div>
            </div>
            <div class="jc_tanchudivsh_ri"></div>
        </div>
        <s:form id="correctionForm" name="correctionForm" method="post" action="spot/correction!save.bc">
        <div class="jc_tanchudivzh">
            <div class="jc_tanchudivzh_ri">
            <div>
            <input type="hidden" name="aggSpotId" id="aggSpotId"/>
		<ul class="denglu">
            <li class="li1">
            <input type="radio" name="title" id="radio1" value="价格相差太大" class="danxuan" checked="checked"/>
            <span class="jiucuospan" onclick="$('#radio1').attr('checked',true);">价格相差太大</span>
            </li>
          <li class="li1">
            <input type="radio" name="title" id="radio2" value="地址不正确" class="danxuan" />
            <span class="jiucuospan" onclick="$('#radio2').attr('checked',true);">地址不正确</span>
            </li>
            <li class="li1">
            <input type="radio" name="title" id="radio3" value="已经关门或者换名" class="danxuan" />
            <span class="jiucuospan" onclick="$('#radio3').attr('checked',true);">已经关门或者换名</span>
            </li>
            <li class="li1">
            <input type="radio" name="title" id="radio4" value="其他" class="danxuan" />
            <span class="jiucuospan" onclick="$('#radio4').attr('checked',true);">其他</span>
            </li>
            <li class="li1">
            	<label id="contentlabel" class="label" onclick="$('#contentlabel').hide();">我的描述</label>
              <textarea name="content" id="corrction_content" cols="45" rows="5" class="jiucuotext1" onfocus="$('#contentlabel').hide();$('#contentlabel').focus();"></textarea>
            </li>
            </ul>
            </div>
            </div>
      </div>
        <div class="jc_tanchudivxia">
            <div class="jc_tanchudivxia_le">
            </div>
            <div class="jc_tanchudivxia_ce">
            	<div class="anniu">
                  <span class="s1">
                  <input type="image" name="imageField4" id="imageField4" src="${staticContext}/images/index_dengluxiao_21.jpg" />
                  </span>
                  <span class="s2">
                    <a href="javascript:void(0);" onclick="OP.correctionModal.close();">取消</a>
                  </span>
              </div>
            </div>
            <div class="jc_tanchudivxia_ri">
            </div>
        </div>
        </s:form>
    </div>
</div>
<script type="text/javascript">
	$('#correctionForm').ajaxForm({
		dataType : "json",
		success : function(response) {
			OP.correctionModal.close();
			if(response.isLogin){
				global.showGlobalTip("感谢， 波币 +2 ^_^");
			}
			global.showOperationTip("#"+$("#aggSpotId").val()+"_correction", "Thanks，我们会尽快改正。","top");
			
		}
	});

</script>