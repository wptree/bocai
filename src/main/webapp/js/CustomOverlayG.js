/**
 * customize the google map overlay Class
 */
BC.WG = BC.WG || {};
if(typeof google ==="undefined"){
	//alert("google map not downloaded yet!");
}
else{
	BC.WG.CustomOverlayG = function(data){
		if(!google){alert("google maps not download yet!");}
		this.latlng_=new google.maps.LatLng(data.position.lat,data.position.lng);
		this.div_=null;
		this.isMulti = data.data.length > 1 ? true : false;
		this.markerVo = data;
		this.crtIdx = 0;
		this.crtMarker = data.data[this.crtIdx];
		this.setMap(BC.WG.CustomOverlaysManage.myMap);
	};
	
	//initial the prototype and functions of this class
	BC.WG.CustomOverlayG.prototype=new google.maps.OverlayView();
	
	BC.WG.CustomOverlayG.prototype.onAdd =function(){
		var _div=document.createElement("DIV");
		var _img=document.createElement("img");
		_img.src=this.crtMarker.photo;
		_div.appendChild(_img);
		this.div_=_div;
		var panes=this.getPanes();
		panes.overlayImage.appendChild(_div);
		//append the css style to the lay
		if(!this.isMulti){
			BC.Dom.appCssText(_div,"position:absolute;border-width:0;border-style:none;width:46px;height:55px;background-image:url(images/map-shadow.png);");
			BC.Dom.appCssText(_img,"margin-left:3px;margin-top:3px;width:40px;height:40px;");
		}else{
			BC.Dom.appCssText(_div,"position:absolute;border-width:0;border-style:none;width:54px;height:58px;background-image:url(images/map-shadow-multi.png);");
			BC.Dom.appCssText(_img,"margin-left:7px;margin-top:6px;width:40px;height:40px;");
		}
	};
	
	BC.WG.CustomOverlayG.prototype.show=function(){
		var _M = BC.WG.CustomOverlaysManage;
		var _MinfoPanel = G(_M.infoPanel.id);
		var overlayProjection=this.getProjection();
		var pixPosition=overlayProjection.fromLatLngToContainerPixel(this.latlng_);
		//set up the content of popup div
		_MinfoPanel.style.display="block";
		G(_M.infoPanel.imgId).src=this.crtMarker.photo;
		G(_M.infoPanel.titleId).innerHTML = this.crtMarker.title;
		G(_M.infoPanel.addressId).innerHTML = this.crtMarker.address;
		G(_M.infoPanel.descId).innerHTML = "“" + this.crtMarker.desc + "”";
		G(_M.infoPanel.aggSpotId).value = this.crtMarker.id;
		//set up the position of popup div
		var _height=_MinfoPanel.offsetHeight;
		var _adjustY = -25;
		var _adjustX = 0;
		if(this.isMulti){
			_adjustY = -21;
			_adjustX = 5;
		}
		_MinfoPanel.style.left=pixPosition.x + _adjustX - 150 + "px";
		_MinfoPanel.style.top=pixPosition.y + _adjustY - _height+"px";
		this.div_.children[0].src = this.crtMarker.photo;
	};
	
	BC.WG.CustomOverlayG.prototype.move=function(){
		var _M = BC.WG.CustomOverlaysManage;
		var _MinfoPanel = G(_M.infoPanel.id);
		var overlayProjection=this.getProjection();
		var pixPosition=overlayProjection.fromLatLngToContainerPixel(this.latlng_);
		var _height=_MinfoPanel.offsetHeight;
		var _adjustY = -25;
		var _adjustX = 0;
		if(this.isMulti){
			_adjustY = -21;
			_adjustX = 5;
		}
		_MinfoPanel.style.left=pixPosition.x + _adjustX - 150 + "px";
		_MinfoPanel.style.top=pixPosition.y + _adjustY - _height+"px";
	};

	BC.WG.CustomOverlayG.prototype.draw=function(){
		var me=this;

		var overlayProjection=this.getProjection();
		var pixPosition=overlayProjection.fromLatLngToDivPixel(this.latlng_);
		this.div_.style.left =(pixPosition.x-23) + "px";
		this.div_.style.top = (pixPosition.y-55) + "px";
		
		var _M = BC.WG.CustomOverlaysManage;
		var _MinfoPanel = G(_M.infoPanel.id);
		
		//add Event: mouseover
		google.maps.event.addDomListener(this.div_,'mouseover',function(event){   
			this.style.zIndex = ++_M.zCounter;
			clearTimeout(_M.timer);
			me.show();
		});
		
		//add Event: mouseout
		google.maps.event.addDomListener(this.div_,'mouseout',function(){
			clearTimeout(_M.timer);
			_M.timer = setTimeout(function(){
				_MinfoPanel.style.display="none";
			}, 3000);
		});
		
		//add Event: click
		google.maps.event.addDomListener(this.div_,'click',function(){
			me.crtIdx >= me.markerVo.data.length-1 ? me.crtIdx = 0 : me.crtIdx ++;
			me.crtMarker = me.markerVo.data[me.crtIdx];
			me.show();
		});
		
		//add Event: dblclick
		google.maps.event.addDomListener(this.div_,'dblclick',function(event){
			if (event.stopPropagation) {
				// this code is for Mozilla and Opera
				event.stopPropagation();
			}else if (window.event) {
				// this code is for IE
				window.event.cancelBubble = true;
			}
		});
	};

	BC.WG.CustomOverlayG.prototype.onRemove=function(){
		this.div_.parentNode.removeChild(this.div_);
		this.div_=null;
	};

}
