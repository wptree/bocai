if(typeof(BC) == 'undefined'){
	BC = {};
};
BC.WG = BC.WG || {};
BC.WG.pager = {
		anchor : "#",
		pageNo : 1,
		total : 100,
		el_pager : null,
		pageClkFunc :function(b){},
		createChildren : function(_fragment,_word,_type,_pageNo){
			if(!_fragment||!_word){
				return null;
			}
			if(_type == "current"){
				var ele = document.createElement("span");
				ele.className = "fr mr5 h20 plr7 tc lh20 currentPage follow_cafe";
				ele.innerHTML = _word;
			}else if(_type == "more"){
				var ele = document.createElement("span");
				ele.className = "fr mr5 h20 plr7 tc lh20 morePage follow_cafe";
				ele.innerHTML = _word;
			}else{
				var ele = document.createElement("a");
				ele.className = "fr mr5 h20 plr7 tc lh20 otherPage follow_cafe";
				ele.href = BC.WG.pager.anchor;//滚到最上面
				//ele.href = "javascript:void(0);";
				ele.innerHTML = _word;
				
				var pageNo = _pageNo;
				if(_word == "前一页"){
					pageNo = _pageNo-1;
				}else if(_word == "后一页"){
					pageNo = _pageNo+1;
				}
				ele.onclick = function(){
					BC.WG.pager.pageClkFunc(pageNo);
				};
			}
			_fragment.appendChild(ele);
			return _fragment;
		},

		pagination : function(_ele,_pageNo,_total,_anchor){
			if(!_ele){
				return ;
			}
			BC.WG.pager.pageNo = _pageNo||1;
			BC.WG.pager.total = _total||0;
			BC.WG.pager.anchor = _anchor || "#";
			
			var pageNo = BC.WG.pager.pageNo;
			var total = BC.WG.pager.total;
			
			var createChildren = BC.WG.pager.createChildren;
			
			var fragment = document.createDocumentFragment();
			if(total>5){
				if(pageNo==total){
					fragment = createChildren(fragment,"后一页","more",pageNo);
					fragment = createChildren(fragment,total,"current",total);
					for(var start=total-1,end=(total>4?total-2:2);start>=end;start--){
						fragment = createChildren(fragment,start,"other",start);
					}
					if(pageNo>4){
						fragment = createChildren(fragment,"...","more");
					}
					if(pageNo>1){
						fragment = createChildren(fragment,1,"other",1);
						fragment = createChildren(fragment,"前一页","other",pageNo);
					}else{
						fragment = createChildren(fragment,1,"current",1);
						fragment = createChildren(fragment,"前一页","more");
					}
				}else{
					fragment = createChildren(fragment,"后一页","otherPage",pageNo);
					fragment = createChildren(fragment,total,"otherPage",total);
					
					if(pageNo>total-4){
						for(var start =total-1,end = (pageNo>4?pageNo-2:2);start>=end;start--){
							if(start == pageNo){
								fragment = createChildren(fragment,start,"current",start);
							}else{
								fragment = createChildren(fragment,start,"other",start);
							}
						}
						if(pageNo>4){
							fragment = createChildren(fragment,"...","more");
						}
						fragment = createChildren(fragment,1,"other",1);
						fragment = createChildren(fragment,"前一页","other",pageNo);
					}else if(pageNo<total-3&&pageNo>4){
						fragment = createChildren(fragment,"...","more",pageNo);
						
						for(var start =(pageNo+2<total-1?pageNo+2:total-1),end = pageNo-2;end<=start;start--){
							if(start == pageNo){
								fragment = createChildren(fragment,start,"current",start);
							}else{
								fragment = createChildren(fragment,start,"other",start);
							}
						}
						if(pageNo+2<total-1){
							fragment = createChildren(fragment,"...","more",pageNo);
						}
						fragment = createChildren(fragment,1,"other",1);
						fragment = createChildren(fragment,"前一页","other",pageNo);
					}else if(pageNo<=4){
						if(pageNo+2<total-1){
							fragment = createChildren(fragment,"...","more",pageNo);
						}
						
						for(var start =(pageNo+2<total-1?pageNo+2:total-1),end = 2;end<=start;start--){
							if(start == pageNo){
								fragment = createChildren(fragment,start,"current",start);
							}else{
								fragment = createChildren(fragment,start,"other",start);
							}
						}
						if(pageNo>1){
							fragment = createChildren(fragment,1,"other",1);
							fragment = createChildren(fragment,"前一页","other",pageNo);
						}else{
							fragment = createChildren(fragment,1,"current",1);
							fragment = createChildren(fragment,"前一页","more");
						}
					}
				}
			}else if(total>0){
				if(pageNo == total){
					fragment = createChildren(fragment,"后一页","more",pageNo);
				}else{
					fragment = createChildren(fragment,"后一页","other",pageNo);
				}
				for(var start = total,end = 1;start>=end;start--){
					if(start == pageNo){
						fragment = createChildren(fragment,start,"current",start);
					}else{
						fragment = createChildren(fragment,start,"other",start);
					}
				}
				if(pageNo == 1){
					fragment = createChildren(fragment,"前一页","more",pageNo);
				}else{
					fragment = createChildren(fragment,"前一页","other",pageNo);
				}
			}else{
				
			}
			ele = typeof _ele =="string"?G(_ele):_ele;
			BC.WG.pager.el_pager = ele;
			if(ele.children.length!=0){
				for(var i=ele.children.length-1;i>-1;i--){
					ele.children[i].style.display = "none";
				}
			}
			ele.appendChild(fragment);
		}
};