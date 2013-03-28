BC.Lang = BC.Lang || {};

BC.Lang.trim = function(str){
	return str.replace(/^\s+|\s+$/g,''); 
}

BC.Lang.strLen = function(str){
	//计算汉字字符，英语字符只算半个
	var re = BC.Lang.strCodeArray(str);
	return re[0]/2 + re[1] + re[2] ;
}

BC.Lang.isEmpty = function(o){
   for(var prop in o) {
	   if(o.hasOwnProperty(prop))
       return false;
   }
   return true;
}

//截取指定个数的个字符，两个英文字符算一个
BC.Lang.subChar = function(str,len){
	var i,charCode,tlen=0;
    for (i = 0; i < str.length; i++) {
      charCode = str.charCodeAt(i);
      if (charCode < 0x007f) {
    	  tlen +=0.5;
      } else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
    	  tlen +=1;
      } else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
    	  tlen +=1;
      }
      if(tlen==len){
    	  return str.substring(0,i+1);
      }else if(tlen-len==0.5){
    	  return str.substring(0,i);
      }
    }
    return str;
}

BC.Lang.strCodeArray = function(str){
	//一个字节，二个字节，三个字节字符对应的个数。
	var re=[0,0,0];
    var i,charCode;
    for (i = 0; i < str.length; i++) {
      charCode = str.charCodeAt(i);
      if (charCode < 0x007f) {
        re[0] = re[0] + 1;
      } else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
    	re[1] = re[1] + 1;
      } else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
    	re[2] = re[2] + 1;
      }
    }
    return re;
}

//前缀匹配
BC.Lang.preMatch = function(arr,field,preWord){
	var result = [];
	var pattern = new RegExp("^"+preWord,"i"); 
	for(var i=0;i<arr.length;i++){
		if(pattern.test(arr[i][field])){
			result[result.length] = arr[i];
		}
	}
	return result;
}
//字符串全局所有替换
BC.Lang.replaceAll = function(Source,stringToFind,stringToReplace){
	var temp = Source;
	var index = temp.indexOf(stringToFind);
    while(index != -1){
    	temp = temp.replace(stringToFind,stringToReplace);
        index = temp.indexOf(stringToFind);
    }
    return temp;
}
//把字符串转成xml对象
BC.Lang.strToXML = function(str){
	if(document.all){ 
	　　var xmlDom=new ActiveXObject("Microsoft.XMLDOM"); 
	　　xmlDom.loadXML(str);
	　　return xmlDom;
　　}else {
		return new DOMParser().parseFromString(str, "text/xml")
　　}　
}