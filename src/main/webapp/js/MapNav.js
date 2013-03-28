BC.WG = BC.WG || {};
BC.WG.MapNav = {
	//update dish marker on google map
	updateMarkers : function(dataArr){
		BC.WG.CustomOverlaysManage.draw(dataArr);
	}
};