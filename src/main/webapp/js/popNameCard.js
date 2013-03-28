/**
 * pop out name card model
 */
BC.WG = BC.WG || {};
BC.WG.popNameCard = {
	opt:{
			picSrc_:"",
			name_:"未知",
			gender_:"保密",
			city_:"",
			foodno_:"99",
			fans_:"99",
			followers_:"",
			userId_:"",
			isFollowed_:false,
			isFans_:"",
			sign_:""
	},
	stayTime:500,
	cardObj:(function(){return G("popCard");})(),
	//config the content of 'pop_name_card'
	setContent:function(opt){
			var el_cardpic = G("card_pic");
			el_cardpic.src = opt.picSrc_;
			el_cardpic.parentNode.title = opt.name_;
			el_cardpic.parentNode.href =  contextPath+"/user/profile.bc?id="+opt.userId_;
			
			var el_cardname = G("card_name");
			el_cardname.href =  contextPath+"/user/profile.bc?id="+opt.userId_;
			el_cardname.title = opt.name_;
			
			el_cardname.innerHTML = opt.name_.length>31?opt.name_.substring(0,28)+"...":opt.name_;
			
			G("card_gender").innerHTML = opt.gender_;
			if(opt.city_!=""){
				G("card_city").innerHTML =", "+opt.city_;
			}else{
				G("card_city").innerHTML = "";
			}
			G("card_foodNo").innerHTML = opt.foodno_;
			G("card_foodNo").parentNode.children[0].href = contextPath+"/user/profile.bc?id="+opt.userId_;
			G("card_fans").innerHTML = opt.fans_;
			G("card_fans").parentNode.children[0].href = contextPath+"/user/follower.bc?id="+opt.userId_;
			G("card_followers").innerHTML = opt.followers_;
			G("card_followers").parentNode.children[0].href = contextPath+"/user/following.bc?id="+opt.userId_;
			G("card_sign").innerHTML = opt.sign_;
			
			if(opt.isFollowed_ == false){
				G("popNameCard_follow").innerHTML = "+关注";
				$(G("popNameCard_follow")).removeClass("unfollow-button").addClass("follow-button");
			}else{
				G("popNameCard_follow").innerHTML = "取消关注";
				$(G("popNameCard_follow")).removeClass("follow-button").addClass("unfollow-button");
			}
	},
	popLeft:function(position,c_w,obj){
		//arrow-->right, pop at left side
		BC.Dom.appCssText("card_arrow","background-position:-262px -67px;left:auto;right:-2px;_right:-3px;top:22px;height:16px;width:8px;");
		var _left = position.x-c_w+"px";
		var _top = position.y-obj.offsetHeight/2+"px";
		this.cardObj.style.left =_left;
		this.cardObj.style.top = _top;
	},
	popUp:function(position,c_h,obj){
		//arrow-->down, pop up
		BC.Dom.appCssText("card_arrow","background-position:-223px -71px;left:22px;top:auto;bottom:-2px;_bottom:-8px;height:8px;width:16px;");
		var _left = position.x+obj.offsetWidth/2-29+"px";
		var _top = position.y-c_h+"px";
		this.cardObj.style.left = _left;
		this.cardObj.style.top = _top;			
	},
	popDown:function(position,obj){
		//arrow-->up, pop down
		BC.Dom.appCssText("card_arrow","background-position:-142px -71px;left:22px;top:-2px;height:8px;width:16px;");
		var _left = position.x+obj.offsetWidth/2-29+"px";
		var _top = position.y+obj.offsetHeight+"px";
		this.cardObj.style.left = _left;
		this.cardObj.style.top = _top;				
	},
	type:"",
	selectType:function(position,isList,c_h){
		if(!isList && position.x*2>document.body.scrollWidth){
			this.type="left";
		}else if((position.y-document.documentElement.scrollTop-document.body.scrollTop)>=c_h){
			this.type="up";
		}else{
			this.type="down";
		}
	},
	//function mouseover
	showCard:function(me,isList){
		this.cardObj = G("popCard");
		this.cardObj.style.display = "block";
		this.setContent(this.opt);
		var	position = BC.Dom.getXY(me);
		var card_height = this.cardObj.offsetHeight;
		var card_width = this.cardObj.offsetWidth;
		this.selectType(position,isList,card_height);
		if(this.type == "left"){
			this.popLeft(position,card_width,me);
		}
		if(this.type == "up"){
			this.popUp(position,card_height,me);
		}
		if(this.type == "down"){
			this.popDown(position, me);
		}
		this.initial(me);
	},
	//card to show
	cardFunc:function(elem,userId,isList){
		var el_peoplepic = elem;
		var overTime = 0;
		var outTime = 0;
		var ajax = null;
		elem.onmouseover = function(){
			overTime = (new Date()).getTime();
			
			ajax = setTimeout(function(){
				if(BC.WG.popNameCard.mouseoutFunc){
					clearTimeout(BC.WG.popNameCard.mouseoutFunc);
				}
				G("card_panel").style.display = "none";
		 		G("card_loading").style.display = "block";
		 		if(userId == BC.WG.popNameCard.userId){
					G("card_panel").style.display = "block";
					G("card_loading").style.display = "none";
					BC.WG.popNameCard.showCard(elem,isList);
		 		}else{
					$.get(contextPath+"/user/user_info.bc?id="+userId,function(data){
						var model = data.model;
						if(model){
							G("card_panel").style.display = "block";
							G("card_loading").style.display = "none";
							BC.WG.popNameCard.userId = userId;
							var sexy;
							if(model.sexy==1){
								sexy = "男";
							}else if(model.sexy==2){
								sexy = "女";
							}else{
								sexy = "保密";
							}
							BC.WG.popNameCard.opt = {
									picSrc_:model.avatar,
									name_:model.name||"",
									city_:model.cityName||"",
									foodno_:model.totalSpotCount,
									fans_:model.fansNum,
									gender_:sexy,
									followers_:model.followingNum,
									userId_:model.id,
									isFollowed_:model.followed,
									isFans_:model.isFans,
									sign_:model.signature||""
							};
							
							BC.WG.popNameCard.showCard(elem,isList);
						}
					});
		 		}
			},BC.WG.popNameCard.stayTime);
		};
		elem.onmouseout = function(e){
			outTime = (new Date()).getTime();
			if(outTime - overTime < BC.WG.popNameCard.stayTime){
				clearTimeout(ajax);
				ajax = null;
			}else{
				var toEv = e ? e : window.event ;  
				var toEl = toEv.toElement ? toEv.toElement : toEv.relatedTarget;
				if((toEl != "card_arrow")&&(toEl != "bottomC")&&(toEl != "midR")){
					BC.WG.popNameCard.mouseoutFunc = setTimeout(function(){
						BC.WG.popNameCard.cardObj.style.display = "none";
					},1500);
				}
			}
		};
	},
	mouseoutFunc:null,
	userId:null,
	initial:function(me){
		this.cardObj.onmouseover = function(){
			if(BC.WG.popNameCard.mouseoutFunc){
				clearTimeout(BC.WG.popNameCard.mouseoutFunc);
			}
			BC.WG.popNameCard.cardObj.style.display = "block";
		};
		this.cardObj.onmouseout = function(e){
			var toEv = e ? e : window.event ;  
			var toEl = toEv.toElement ? toEv.toElement : toEv.relatedTarget;
			if(toEl != me){
				BC.WG.popNameCard.cardObj.style.display = "none";
			}
		};
		G("popNameCard_sendMsg").onclick = function(){
			var id = BC.WG.popNameCard.opt.userId_;
			var name = BC.WG.popNameCard.opt.name_;
			OP.sendPrivateMsg(id, name);
			return false;;
		};
		G("popNameCard_follow").onclick = function(){
			var loginId = parseFloat($('#const_loginId').text());
	    	if(isNaN(loginId) || loginId<=0){
	   	   	    global.needLogin();
	   	   		return;
	   	    }
			var id = BC.WG.popNameCard.opt.userId_;
			var btn = this;
			$.ajax({url: contextPath+'/user/changeFollow.bc',
				  type: 'POST',
				  dataType: 'json',
				  data: {'targetId':id,'targetType':'user'},
				  success: function(response){
					  if(response.saveResult){
						$(btn).html(response.returnMsg);
						if(response.returnMsg=="取消关注"){
							$(btn).removeClass("follow-button").addClass("unfollow-button");
						}else{
							$(btn).removeClass("unfollow-button").addClass("follow-button");
						}
					  }else{
						 global.showOperationTip($(btn),response.returnMsg,"top");
					  }
				  }
			});
			/*
			if(BC.MLang.trim(this.innerHTML) == "取消关注"){
				$.get('/follow/user!delete.action?id='+ id,function(data){
					if(data == "notlogin"){
						BC.util.login();
					}else if(data == "true"||data == "already"){
						btn.innerHTML = "加关注";
						BC.WG.popNameCard.opt.isFollowed_ = 0;
					}
				});
			}else if(BC.MLang.trim(this.innerHTML) == "+关注"){
				$.get('/follow/user!add.action?id='+ id,function(data){
					if(data == "notlogin"){
						BC.util.login();
					}else if(data == "true"||data == "already"){
						btn.innerHTML = "取消关注";
						BC.WG.popNameCard.opt.isFollowed_ = 1;
					}
				});
			}
			*/
		};
	}
};