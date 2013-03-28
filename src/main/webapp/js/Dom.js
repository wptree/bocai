if(typeof(BC) == 'undefined'){
	BC = {};
};
BC.Dom = BC.Dom || {};
BC.Dom.getElem = BC.Dom.G = G = function(elem){
	if(typeof elem =="string"){
		return document.getElementById(elem);
	}
	else if(typeof elem == "object"){
		return elem;
	}
	else{
		alert("G function: elem is invalid;");
	}
}

BC.Dom.appCssText = function(elem,cssText){
	var oElem = G(elem);
	if(oElem){
		oElem.style.cssText += ";"+cssText;
		return oElem;
	}
}

BC.Dom.getCommonWH = function(){
	_scrollWidth = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
    _scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    _scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
    _clientWidth = Math.max(document.body.clientWidth,document.documentElement.clientWidth);
    _clientHeight = Math.max(document.body.clientHeight,document.documentElement.clientHeight);
    _wAvailHeight = document.all?document.getElementsByTagName("html")[0].offsetHeight : window.innerHeight;
    return {
		scrollW:_scrollWidth,
		scrollH:_scrollHeight,
		scrollT:_scrollTop,
		clientW:_clientWidth,
		clientH:_clientHeight,
		wAvailH:_wAvailHeight
    }
}

BC.Dom.getElemPosition = function(elem){
	var actualLeft = elem.offsetLeft;
	var actualTop = elem.offsetTop;
    var current = elem.offsetParent;
    while (current !== null){
        actualLeft += current.offsetLeft;
        actualTop += current.offsetTop;
        current = current.offsetParent;
    }
    return {
    	x:actualLeft,
    	y:actualTop
    };
}
//兼容ie ff chrome的文本方法innerText, textContent
BC.Dom.getText = function(elem){
	if(elem.innerText){
		return elem.innerText;
	}else{
		return elem.textContent;
	}
}
//选择input type=text textarea里面的内容 或者让光标定在文本的后面
BC.Dom.selectText = function(textbox,startIndex,stopIndex){
	if(textbox.setSelectionRange){
		textbox.setSelectionRange(startIndex,stopIndex);
	}else if(textbox.createTextRange){
		var range = textbox.createTextRange();
		range.collapse(true);
		range.moveStart("character",startIndex);
		range.moveEnd("character",stopIndex-startIndex);
		range.select();
	}
	textbox.focus();
}

//错误提示
BC.Dom.ErrReminder_color = "#ffdcdc";
BC.Dom.ErrReminder_num = 3;
BC.Dom.ErrReminder = function(elemId,bg,rgb){
	var elem = G(elemId);
	if(!elem) return;
	if(!elem.style.backgroundColor){
		elem.style.backgroundColor = bg;
	}
	var _bgColor = elem.style.backgroundColor;
	if(_bgColor==bg||_bgColor==rgb){
		elem.style.backgroundColor = BC.Dom.ErrReminder_color;
		if(elem.num){
			if(elem.num>1){
				elem.num--;
				setTimeout("BC.Dom.ErrReminder('"+elemId+"','"+bg+"','"+rgb+"')",200);
			}
		}else{
			elem.num = BC.Dom.ErrReminder_num;
			setTimeout("BC.Dom.ErrReminder('"+elemId+"','"+bg+"','"+rgb+"')",200);
		}
	}else{
		elem.style.backgroundColor = bg;
		if(elem.num>1){
			setTimeout("BC.Dom.ErrReminder('"+elemId+"','"+bg+"','"+rgb+"')",200);
		}
	}
	
	
},

BC.Dom.errShow = function(id){
	BC.Dom.deleteProperty(G(id),"num");
	BC.Dom.ErrReminder(id,"#ffffff","rgb(255, 255, 255)");
//    var _err = G(iderr);
//    _err.style.display = "inline";
//    _err.innerHTML = str;
//    setTimeout(function(){
//        if(iderr=="namex_err"){
//            G("namex_err").style.color="grey";
//            G("namex_err").innerHTML = "4-20位字母或者汉字";
//        }else if(iderr=="pwd_err"){
//            G("pwd_err").style.color="grey";
//            G("pwd_err").innerHTML = "至少为6位";
//        }else{
//            _err.style.display = "none";
//        }
//    },10000);
};


//删除对象的属性的通用操作 兼容 IE FF chrome
BC.Dom.deleteProperty = function(elem,name){
	if(document.all){
		if(elem[name]){
			elem[name] = undefined;
		}
	}else{
		if(elem[name]){
			delete elem[name];
		}
	}
}
//get the current position of element in the page
BC.Dom.getXY = function(obj){
	 var x = 0, y = 0;
	 if (obj.getBoundingClientRect) {
	  var box = obj.getBoundingClientRect();
	  var D = document.documentElement;
	  x = box.left + Math.max(D.scrollLeft, document.body.scrollLeft) - D.clientLeft;
	  y = box.top + Math.max(D.scrollTop, document.body.scrollTop) - D.clientTop;
	 }
	 else {
	  for (; obj != document.body; x += obj.offsetLeft, y += obj.offsetTop, obj = obj.offsetParent) {
	  }
	 }
	 return {
	  x: x,
	  y: y
	 }
}