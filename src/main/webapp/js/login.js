// JavaScript Document


function qiehuandanxuan(x)
{
	
	if($id(x+"_zhi").value=="0")
	{
		$id(x).src="images/blogin_14.jpg";
		$id(x+"_zhi").value="true";
	}
	else
	{
		$id(x).src="images/blogin_25.jpg";
		$id(x+"_zhi").value="false";
	}
	var validator = jQuery.Validator;
	var option = validator.getOptionByTarget(x+"_zhi");
	if(option){
		validator.hideTipBox();
		validator.validateItem.call(validator,option);
	}
}

function  tab(x)
{
	
if(x==1)
	{
	document.getElementById("ldiv2").style.display="none";
	document.getElementById("ldiv1").style.display="block";
	document.getElementById("lli1").className="llion1";
	document.getElementById("lli2").className="llioff1";
	}
if(x==2)
	{
	document.getElementById("ldiv2").style.display="block";
	document.getElementById("ldiv1").style.display="none";
	document.getElementById("lli1").className="llioff2";
	document.getElementById("lli2").className="llion2";
	}

}