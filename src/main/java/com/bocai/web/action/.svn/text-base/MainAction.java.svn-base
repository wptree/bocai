package com.bocai.web.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.constant.SearchKeywordType;
import com.bocai.dao.domain.CityMapping;
import com.bocai.manager.CityMappingManager;
import com.bocai.manager.DishManager;
import com.bocai.manager.DishStyleMetaManager;
import com.bocai.manager.DishTasteMetaManager;
import com.bocai.manager.DishTypeMetaManager;
import com.bocai.manager.PlaceManager;
import com.bocai.util.CommonUtil;
import com.bocai.vo.LatLng;
import com.bocai.vo.PlaceIndex;
import com.bocai.web.util.UrlUtils;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({
	@Result(name = "input", location = "main.jsp"),
	@Result(name = "about", location = "about.jsp"),
	@Result(name = "term", location = "term.jsp"),
	@Result(name = "aggSpotIndex", location = "aggSpot_index.jsp"),
	@Result(name = "ajax_check_user_login", type = "json", 
			params = {"includeProperties","checkResult"}
	)
})
public class MainAction extends BaseAction<Object> {
	
	private static final Logger logger = 
		LoggerFactory.getLogger(MainAction.class);
	
	private List<PlaceIndex> indexList;
	
	
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private DishManager dishManager;
	@Autowired
	private DishStyleMetaManager dishStyleMetaManager;
	@Autowired
	private DishTasteMetaManager dishTasteMetaManager;
	@Autowired
	private DishTypeMetaManager dishTypeMetaManager;
	@Autowired
	private CityMappingManager cityMappingManager;
	private CityMapping cityMapping;
	private String city;
	private boolean checkResult;
	
	@Action("main")
	public String execute(){
		checkCity();
		return "input";
	}
	
	@Action("aggSpotIndex")
	public String index(){
		indexList = placeManager.buildIndex(5000, 0);
		return "aggSpotIndex";
	}
	
	private void checkCity() {
		city = (String) session.get(AppConstant.SESSION_USER_CITY);
		if(city != null) return;
		logger.info("********User from: "+ request.getHeader("CITY")+"*********");
		String pinYin = CommonUtil.filterCity(request.getHeader("CITY"));
		if(pinYin!=null){
			cityMapping = cityMappingManager.getCityMappingByPinYin(pinYin.toLowerCase());
		}
		if(cityMapping == null){
			logger.debug("can not find city mapping for city '" + pinYin
					+ "', use 'quanguo' as default");
			pinYin = "quanguo";
			cityMapping = cityMappingManager.getCityMappingByPinYin(pinYin);
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loc", cityMapping.getCity());
		args.put("zoom", cityMapping.getZoom());
		args.put("center", new LatLng(cityMapping.getLat(), cityMapping.getLng())+"");
		args.put("at", 0);
		args.put("keyword", null);
		args.put("keywordType", SearchKeywordType.DISH);
		args.put("reqType", MapRequestType.LATEST );
		setCity(cityMapping.getCity());
		session.put(AppConstant.SESSION_MAP_QSTR, UrlUtils.genQstr(args));
		session.put(AppConstant.SESSION_USER_CITY, getCity());
		logger.debug("get city mapping " + cityMapping);
	}
	
	@Action("about")
	public String about(){
		return "about";
	}
	@Action("term")
	public String term(){
		return "term";
	}
	
	@Action("checkUserLogin")
	public String ajaxCheckUserLogin(){
		setCheckResult(getSessionUser()==null?false:true);
		return "ajax_check_user_login";
	}
	
	public String initiazation(){
		dishManager.initiazation();
		dishStyleMetaManager.initiazation();
		dishTasteMetaManager.initiazation();
		dishTypeMetaManager.initiazation();
		return "input";
	}

	@Override
	protected void prepareModel() throws Exception {}

	public boolean isCheckResult() {
		return checkResult;
	}
	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}
	
	public DishManager getDishManager() {
		return dishManager;
	}

	public void setDishManager(DishManager dishManager) {
		this.dishManager = dishManager;
	}

	public DishStyleMetaManager getDishStyleMetaManager() {
		return dishStyleMetaManager;
	}

	public void setDishStyleMetaManager(DishStyleMetaManager dishStyleMetaManager) {
		this.dishStyleMetaManager = dishStyleMetaManager;
	}

	public DishTasteMetaManager getDishTasteMetaManager() {
		return dishTasteMetaManager;
	}

	public void setDishTasteMetaManager(DishTasteMetaManager dishTasteMetaManager) {
		this.dishTasteMetaManager = dishTasteMetaManager;
	}

	public DishTypeMetaManager getDishTypeMetaManager() {
		return dishTypeMetaManager;
	}

	public void setDishTypeMetaManager(DishTypeMetaManager dishTypeMetaManager) {
		this.dishTypeMetaManager = dishTypeMetaManager;
	}
	
	public CityMapping getCityMapping() {
		return cityMapping;
	}

	public void setCityMapping(CityMapping cityMapping) {
		this.cityMapping = cityMapping;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public Object getModel() {
		return this;
	}

	public void setIndexList(List<PlaceIndex> indexList) {
		this.indexList = indexList;
	}

	public List<PlaceIndex> getIndexList() {
		return indexList;
	}
}
