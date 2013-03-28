BC.Event = BC.Event || {};
//
BC.Event.getEvent = function(e){
	return e||window.event;
};

BC.Event.getTarget = function(e){
	return e.target || event.srcElement;
};

BC.Event.preventDefault = function (event) {
	   if (event.preventDefault) {
	       event.preventDefault();
	   } else {
	       event.returnValue = false;
	   }
};
//取消冒泡
BC.Event.stopPropagation = function (event) {
	   if (event.stopPropagation) {
	       event.stopPropagation();
	   } else {
	       event.cancelBubble = true;
	   }
};

//绑定事件 --解决this的指向问题
BC.Event.addDomEvent = function(elem, type, fn){
	if(elem.attachEvent){
        var typeRef = "_" + type;
        if(!elem[typeRef]){
            elem[typeRef] = [];
        }
        for(var i in elem[typeRef]){
            if(elem[typeRef][i] == fn){
                return;
            }
        }
        elem[typeRef].push(fn);
        elem["on"+type] = function(){
            for(var i in this[typeRef]){
                this[typeRef][i].apply(this,arguments);
            }
        }   
    }else{
        elem.addEventListener(type,fn,false);
    }
};

//移除事件绑定
BC.Event.removeDomEvent = function(elem,type,fn){
    if(elem.detachEvent){
        if(elem["_"+type]){
            for(var i in elem["_"+type]){
                if(elem["_"+type][i] == fn){
                    elem["_"+type].splice(i,1);
                    break;
                }
            }
        }
    }else{
        elem.removeEventListener(type,fn,false);
    }
}

//兼容各个浏览器的用js来激发mouse事件
BC.Event.fireEvent = function(elem,type){
	if(document.all){
		elem["on"+type]();
	}else{
	    var evt = document.createEvent("MouseEvents");
	    evt.initEvent(type, true, true);
	    elem.dispatchEvent(evt);
	}
}

