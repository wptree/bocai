package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.LocationManager;
import com.bocai.manager.PlaceManager;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.LatLng;
import com.bocai.vo.LocationVo;
import com.bocai.vo.PlaceVo;
import com.bocai.vo.Token;
import com.bocai.web.action.BaseAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@ParentPackage("spot")
@Results({
	@Result(name="place_input",location="place.jsp"),
	@Result(name="errorPage",location="/WEB-INF/page/404.jsp"),
	@Result(name = "info", type = "json", 
			params = {"includeProperties","locationVo.*"}
		),
	@Result(name = "ajax_get", type = "json", 
		params = {"includeProperties","tokenArray.*"}
	),
	@Result(name = "ajax_get_data_page", type = "json", 
			params = {"includeProperties","dataPage.*","excludeProperties",".*hibernateLazyInitializer"}
		),
	@Result(name = "ajax_save_result", type = "json", 
		params = {"includeProperties","returnMsg,newPlaceId,newPlaceName,saveResult,newPlaceAddr"}
	)
})
public class PlaceAction extends BaseAction<Object>{
	private static final long serialVersionUID = -4559991211535774993L;
	private Long id;
	
	private String newPlaceId;
	private String newPlaceName;
	private String name;
	private String secondaryName;
	private String provinceCity;
	private String street;
	private String venueType;
	//private String postalCode;
	private String link;
	private LatLng latLng;
	private boolean saveResult;
	private String query;
	private String newPlaceAddr;
	
	private List<Token> tokenArray;
	private Pagination dataPage;
	private String returnMsg;
	private Integer pageAt;
	private Place place;
	private LocationVo locationVo;
	
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private LocationManager locationManager;
	@Autowired
	private AggregatedSpotManager aggSpotManager;
	
	@Action("update_location")
	public String updateLocation(){
		place = placeManager.getById(id);
		Location loc = place.getLocation();
		if(latLng != null){
			loc.setLat(latLng.getLat());
			loc.setLng(latLng.getLng());
			locationManager.update(loc);
			setSaveResult(true);
		}else{
			setSaveResult(false);
			setReturnMsg("不能定位该店的地址，请重新确认");
		}
		
		return "ajax_save_result";
	}
	
	@Action("toPlace")
	public String place(){
		place = placeManager.getById(id);
		place.getLocation();
		request.setAttribute("place", place);
		
		AggregatedSpot bestAggSpot = aggSpotManager.getMostSpottedByPlace(id);
		request.setAttribute("bestAggSpot", bestAggSpot);
		
		Pagination activityUserPage = placeManager.getSpottedUserByPlace(id, 10, 0);
		request.setAttribute("activityUserPage", activityUserPage);
		
		Pagination followersPage = placeManager.getFollowers(id, 10, 0);
		request.setAttribute("followersPage", followersPage);

		return "place_input";
	}
	
	
	public String aggSpotPage(){
		place = placeManager.getById(getId());
		Pagination p = placeManager.getAggSpotsByPlace(place.getId(), 9, 0);
		dataPage = (Pagination)p.clone();
		if(dataPage.getList()!=null && 
				!dataPage.getList().isEmpty()){
			List spotList = dataPage.getList();
			List<AggregatedSpotVo> voList = new ArrayList<AggregatedSpotVo>();
			for(int i = 0; i<spotList.size();i++){
				AggregatedSpot spot = (AggregatedSpot) spotList.get(i);
				voList.add(AggregatedSpotVo.fromAggSpotMini(spot));
			}
			dataPage.setList(voList);
		}
		return "ajax_get_data_page";
	}
	
	@InputConfig(resultName = "ajax_get")
	public String instantQuery(){
		List<Token> tokens= new ArrayList<Token>(); 
		List<Place> result = placeManager.getPlacesMatchedByName(getQuery());
		if(result != null){
			for(Place place : result){
				StringBuffer bf = new StringBuffer(place.getName());
				if(place.getSecondaryName()==null || "".equals(place.getSecondaryName())){
					bf.append("");
				}else{
					bf.append(" - ");
					bf.append(place.getSecondaryName());
				}
				tokens.add(new Token(place.getId().toString(), bf.toString()));
				bf = null;
			}
		}
		tokenArray = tokens;
		return "ajax_get";
	}
	
	public String getLocation(){
		Location location = placeManager.getLocationOfPlace(id);
		locationVo = LocationVo.toBasic(location);
		return "info";
	}
	
	@InputConfig(resultName = "errorPage")
	public String addPlace(){
		try{
			List list = placeManager.checkPlaceExist(getName(), getSecondaryName());
			if(list.size()==0){
				Place newPlace = new Place();
				newPlace.setName(getName());
				newPlace.setLinks(getLink());
				newPlace.setSecondaryName(getSecondaryName());
				StringBuilder sb =  new StringBuilder();
				sb.append(newPlace.getName());
				if(!StringUtils.isBlank(newPlace.getSecondaryName())){
					sb.append(AppConstant.LEFT_PTS)
						.append(newPlace.getSecondaryName())
						.append(AppConstant.RIGHT_PTS);
				}
				newPlace.setFullName(sb.toString());
				newPlace.setVenueType(getVenueType());
				Location loc = new Location();
				loc.setCity(getProvinceCity());
				loc.setStreet(getStreet());
				if(getLatLng()!=null){
					loc.setLat(latLng.getLat());
					loc.setLng(latLng.getLng());
				}
				newPlace.setLocation(loc);
				placeManager.save(newPlace);
				setNewPlaceId(newPlace.getId() + "");
				setSaveResult(true);
				setNewPlaceAddr(getProvinceCity()+getStreet());
				setNewPlaceName(getName());
			}
		}catch(Exception e){
			setNewPlaceId(null);
			setSaveResult(false);
		}
		return "ajax_save_result";
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}

	public String getSecondaryName() {
		return secondaryName;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public void setPlaceManager(PlaceManager placeManager) {
		this.placeManager = placeManager;
	}


	public PlaceManager getPlaceManager() {
		return placeManager;
	}


	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}


	public boolean isSaveResult() {
		return saveResult;
	}


	public void setNewPlaceId(String newPlaceId) {
		this.newPlaceId = newPlaceId;
	}


	public String getNewPlaceId() {
		return newPlaceId;
	}


	public void setNewPlaceName(String newPlaceName) {
		this.newPlaceName = newPlaceName;
	}


	public String getNewPlaceName() {
		return newPlaceName;
	}

	public void setTokenArray(List<Token> tokenArray) {
		this.tokenArray = tokenArray;
	}

	public List<Token> getTokenArray() {
		return tokenArray;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}


	public Pagination getDataPage() {
		return dataPage;
	}


	public void setPageAt(Integer pageAt) {
		this.pageAt = pageAt;
	}


	public Integer getPageAt() {
		return pageAt;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setNewPlaceAddr(String newPlaceAddr) {
		this.newPlaceAddr = newPlaceAddr;
	}

	public String getNewPlaceAddr() {
		return newPlaceAddr;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}
	
}
