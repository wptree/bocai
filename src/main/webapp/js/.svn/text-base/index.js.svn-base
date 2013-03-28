// JavaScript Document
function shurudianji(x,y)
{
	$id(x).style.display="block";
	y.value="";
}

function shurulikai(x)
{
	$id(x).style.display="none";
}


function onUploadImgChangend()
{
	//$id("divup0").style.display="block";
	$id("divup1").style.display="none";
	$id("meishitianjia").style.display="none";
	$(".index_content_left_a_2").css({height:"44px"});
}


function xiangling(x)
{
  if(x.nextSibling)
  {
  
	if (x.nextSibling.nodeType==1)
	{
	return x.nextSibling;
	}
	else
	{
	return x.nextSibling.nextSibling;
	}
  }
}

function faxian(x)
{
	if(xiangling(x.parentNode.parentNode.parentNode).style.display!="block")
	{
		ziduixiang(x.parentNode)[0].src="images/index_btm_58_1.jpg";
		xiangling(x.parentNode.parentNode.parentNode).style.display="block";
		
		xiangling(xiangling(x.parentNode.parentNode.parentNode)).style.display="none";
		
	}
	else
	{
		ziduixiang(x.parentNode)[0].src="images/index_btm_58.jpg";
		xiangling(x.parentNode.parentNode.parentNode).style.display="none";
		
		xiangling(xiangling(x.parentNode.parentNode.parentNode)).style.display="block";
	}
}

function  ziduixiang(x)
{
var arr=new Array();
var arr2=new Array();
arr2=x.childNodes;
  for(var i=0;i<arr2.length;i++)
  {
	if (arr2[i].nodeType==1)
	{
	arr.push(arr2[i]);
	}
  
  }
  return arr;
}

function hufufanye(x,y)
{
var sarrul=ziduixiang(y.parentNode.parentNode.parentNode)[0].getElementsByTagName("ul");
var sarrli=y.parentNode.getElementsByTagName("li");

	for(var i=0;i<sarrul.length;i++)
	{
		
		sarrul[i].style.display="none";
		sarrli[i].className="lixuanzheno";
		
		
	}
	sarrul[x].style.display="block";
	y.className="lixuanzhe";
}


function quxiaohuifu(x)
{
	xiangling(x).value="";
}

