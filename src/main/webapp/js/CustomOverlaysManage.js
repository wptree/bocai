/**
 * pop out info panel model
 */
BC.WG = BC.WG || {};
if(typeof google ==="undefined"){
	//alert("google map not downloaded yet!");
}
else{
	BC.WG.CustomOverlaysManage = {
		zCounter:1,
		infoPanel:{
			id:"foodtip",
			imgId:"ft_img",
			titleId:"ft_title",
			addressId:"ft_address",
			descId:"ft_desc",
			aggSpotId:"ft_aggspot_id"
		},
		flagDown:false,
		mapOpt:{
			zoom:4,
			center:new google.maps.LatLng(34.0096,115.0972),
			mapTypeId:google.maps.MapTypeId.ROADMAP,
			mapTypeControl: false,
			scrollwheel :false,
			streetViewControl: false
		},
		mapDivId:"bc_map",
		myMap: null,
		markers: [],
		timer: null,
		initialMap:function(){
//			var _mapDivId = this.mapDivId;
//			if(BC.Lang.trim(_mapDivId) == ""){
//				alert("map div id no initialled");
//				return null;
//			}
//			if(!this.myMap){
//				this.myMap = new google.maps.Map(G(_mapDivId),this.mapOpt);
//			}
			google.maps.event.addListener(this.myMap, "dragstart", function(){
				BC.WG.CustomOverlaysManage.flagDown = true;
				var _MinfoPanel = G(BC.WG.CustomOverlaysManage.infoPanel.id);
				_MinfoPanel.style.display="none";
			});
			google.maps.event.addListener(this.myMap, "dragend", function(){
				BC.WG.CustomOverlaysManage.flagDown = false;
			});
		},
		draw:function(dataArr){
			if(!dataArr){ return;}
			if(!this.myMap){return;}
			this.initialMap();
			var _len = dataArr.length;
			//clear the already exist markers...when page change
			this.remove();
			this.markers = [];
			for(var i=0;i<_len;i++){
				this.markers[i]=new BC.WG.CustomOverlayG(dataArr[i]);
			}
		},
		remove:function(){
			var m_len=this.markers.length;
			for(var j=0;j<m_len;j++){
				this.markers[j].setMap(null);
			}
		}
	};
}