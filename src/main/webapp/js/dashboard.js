function usertab(x)
{
	for(var i=1;i<5;i++)
	
	{
		$id("userli_"+i).className="lioff";
		$id("userul_"+i).style.display="none";
	}
	$id("userli_"+x).className="lion";
	$id("userul_"+x).style.display="block";
}

function userhuiying(x)
{
	if(xiangling(x).style.display!="block")
	{
		xiangling(x).style.display="block";
		xiangling(xiangling(x)).style.display="block";
		x.innerHTML="隐藏回应";
	}
	else
	{
		xiangling(x).style.display="none";
		xiangling(xiangling(x)).style.display="none";
		x.innerHTML="回应";
	
	}
}

