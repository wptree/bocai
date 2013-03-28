package com.bocai.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.constant.SearchKeywordType;
import com.bocai.common.constant.SearchTargetType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.User;
import com.bocai.manager.SearchManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.LatLng;
import com.bocai.vo.Marker;
import com.bocai.vo.UserVo;
import com.bocai.web.util.UrlUtils;

@SuppressWarnings({"serial","rawtypes"})
@ParentPackage("default")
@Results({
	@Result(name = "ajax_map_search", type = "json", 
			params = {"includeProperties","model.*,total,at,markers.*"}
	),
	@Result(name = "ajax_boke_search", type = "json", 
			params = {"includeProperties","dataPage.*,followShip.*"}
	)
})
public class SearchAction extends BaseAction<Pagination> {
	
	// constants
	private static final String AJAX_MAP_SEARCH = "ajax_map_search";
	private static final String AJAX_BOKE_SEARCH = "ajax_boke_search";
	private static final int PAGE_SIZE = 20;
	
	// parameters for map search
	private String keyword;
	private SearchKeywordType keywordType;
	private MapRequestType reqType;
	private int at;
	private String loc;
	private int zoom;
	private LatLng center;
	private LatLng sw;
	private LatLng ne;
	
	// parameters for boke search
	private int total;
	private int pageAt;
	
	// value object
	private Pagination model;
	private Pagination dataPage;
	private List<Marker> markers;
	private Map<String, String> followShip;

	// service object
	@Autowired
	private SearchManager searchManager;
	@Autowired
	private UserManager userManager;

	@Override
	public Pagination getModel() {
		return model;
	}

	@Override
	protected void prepareModel() throws Exception {
		markers = new ArrayList<Marker>();
		if(keywordType == null) keywordType = SearchKeywordType.DISH;
		if(reqType == null) reqType = MapRequestType.LATEST;
	}
	
	
	@SuppressWarnings("unchecked")
	@Action("map_search")
	public String ajaxMapSearch(){
		Object[] result = searchManager.mapSearch(keyword, keywordType,
				reqType, at, PAGE_SIZE, sw, ne, getSessionUserId());
		model = (Pagination) result[0];
		total = model.getTotalPage();
		at = model.getPageNo();
		markers = (List<Marker>) result[1];
		
		// update session
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loc", loc);
		args.put("zoom", zoom);
		args.put("center", center);
		args.put("at", at);
		//args.put("keyword", keyword);
		//args.put("keywordType", keywordType != null ? keywordType : SearchKeywordType.DISH);
		args.put("reqType", reqType != null ? reqType : MapRequestType.LATEST );
		session.put(AppConstant.SESSION_MAP_QSTR, UrlUtils.genQstr(args));
		return AJAX_MAP_SEARCH;
	}
	
	@Action("boke_search")
	public String ajaxBokeSearch(){
		Pagination page = searchManager.search(keyword, SearchKeywordType.BOKE, SearchTargetType.USER, pageAt, PAGE_SIZE);
		dataPage = (Pagination) page.clone();
		List userlist = dataPage.getList();
		followShip = new HashMap<String,String>();
		if(getSessionUser()!=null){
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				String temp = userManager.isFollowed(getSessionUser().getId(), user)? "取消关注":"+关注";
				followShip.put(user.getId().toString(), temp);
			}
		}else{
			for(int i = 0; i< userlist.size(); i++){
				User user = (User) userlist.get(i);
				followShip.put(user.getId().toString(), "+关注");
			}
		}
		
		List<UserVo> voList = new ArrayList<UserVo>();
		for(int i = 0; i<userlist.size();i++){
			User user = (User) userlist.get(i);
			voList.add(UserVo.fromUserBasic(user));
		}
		dataPage.setList(voList);
		return AJAX_BOKE_SEARCH;
	}

	public MapRequestType getReqType() {
		return reqType;
	}

	public void setReqType(MapRequestType reqType) {
		this.reqType = reqType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public SearchKeywordType getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(SearchKeywordType keywordType) {
		this.keywordType = keywordType;
	}

	public int getPageAt() {
		return pageAt;
	}

	public void setPageAt(int pageAt) {
		this.pageAt = pageAt;
	}

	public void setModel(Pagination model) {
		this.model = model;
	}

	public LatLng getSw() {
		return sw;
	}

	public void setSw(LatLng sw) {
		this.sw = sw;
	}

	public LatLng getNe() {
		return ne;
	}

	public void setNe(LatLng ne) {
		this.ne = ne;
	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public Map<String, String> getFollowShip() {
		return followShip;
	}

	public void setFollowShip(Map<String, String> followShip) {
		this.followShip = followShip;
	}

	public Pagination getDataPage() {
		return dataPage;
	}

	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public LatLng getCenter() {
		return center;
	}

	public void setCenter(LatLng center) {
		this.center = center;
	}
}
