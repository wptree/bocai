//JavaScript Document
var contextPath = $("#const_contextPath").text();
function settab(x)
{
	for(var i=1;i<4;i++)
	{
		$id("liid"+i).className="lioff";
		$id("mysetid"+i).style.display="none";
	}
	$id("liid"+x).className="lion";
	$id("mysetid"+x).style.display="block";

}



function changeBundle(x)
{
	$.ajax({url: contextPath+"/login!thirdPartyRequest.bc",
		  type: "GET",
		  data: {"needClean":true,"source":"sina","backUrl":"http:\/\/www.bocai007.com/bocai/user/my_set!bundleToSina.bc"},
		  success: function(jsonObj){
			window.location.href=jsonObj.backUrl;
		  }
		});
	
	if($id("tongbu"+x).style.display=="none")
	{
		$id("tongbu"+x).style.display="block";
		$id("yibangding"+x).style.display="none";
	}
	else
	{
		$id("tongbu"+x).style.display="none";
		$id("yibangding"+x).style.display="block";
		
	}
}

var avatarModel;
function updateAvatar(){
	var src = contextPath + "/user/my_avatar.bc";
	var div = "<div id='iframeDiv'><div id='map-loading-box'></div></div>";
	avatarModel = $.modal(div, {
		closeHTML: "<div class='h36 w36 modalCloseBg cp' style='position:absolute;right:-18px;top:-18px;z-index:1000;'></div>",
		containerCss:{
			boxShadow: "0 0 90px 5px #000000",
			backgroundColor:"#fff",
			borderColor:"#fff",
			height:"422px",
			width:"732px",
			padding:0
		},
		onShow:beforeIframeLoad(),
		overlayClose:true
	});
	
	$("<iframe src='"+ src + "' height='420' width='730'>").load(function(){
		$('#map-loading-box').hide();
	}).appendTo("#iframeDiv");
}

function closeIframeModal(){
	if(avatarModel!=null){
		avatarModel.close();
	}
}

function beforeIframeLoad(){
	var html = "";
	$("#simplemodal-data").html(html);
}

$(document).ready(function() {
	$("#selectYear").empty();
	$("#selectYear").append('<option value="">--</option>');
	for(var i = 2005;i>1970;i--){
		$("#selectYear").append("<option value='"+i+"'>"+i+"</option>");
	}
	$("#selectMonth").empty();
	$("#selectMonth").append('<option value="">--</option>');
	for(var i = 12;i>0;i--){
		$("#selectMonth").append("<option value='"+i+"'>"+i+"</option>");
	}
	$("#selectDate").empty();
	$("#selectDate").append('<option value="">--</option>');
    for(var i = 31;i>0;i--){
    	$("#selectDate").append("<option value='"+i+"'>"+i+"</option>");
	}
	
	function setSecret(i){
		//-1:仅自己可见 1:我关注的人可见  0:所有人可见
		return "<ul onmouseover=\"selectyes('listdata"+i+"')\" onmouseout=\"selectno('listdata"+i+"')\"><li onmouseover=\"effect_over(this)\" onmouseout=\"effect_out(this)\" onclick=\"selectvalue('仅自己可见','-1','gongkai"+i+"','hgongkai"+i+"');changedisplay('listdata"+i+"')\">仅自己可见</li><li onmouseover=\"effect_over(this)\" onmouseout=\"effect_out(this)\" onclick=\"selectvalue('我关注的人可见','1','gongkai"+i+"','hgongkai"+i+"');changedisplay('listdata"+i+"')\">我关注的人可见</li><li onmouseover=\"effect_over(this)\" onmouseout=\"effect_out(this)\" onclick=\"selectvalue('所有人可见','0','gongkai"+i+"','hgongkai"+i+"');changedisplay('listdata"+i+"')\">所有人可见</li></ul>";
	}
	
	for(var i = 1; i<7; i++){
		$("#listdata"+i).html(setSecret(i));
	}
});