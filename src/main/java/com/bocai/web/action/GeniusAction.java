package com.bocai.web.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.UserManager;
import com.bocai.search.engine.IndexCache;
import com.bocai.search.engine.TokenIndex;
import com.bocai.vo.GeniusVo;

import static com.bocai.common.constant.AppConstant.GENIUS_SUGGEST_LIST_SIZE;
import static com.bocai.search.engine.IndexConstant.DISH_NAME_INDEX;
import static com.bocai.search.engine.IndexConstant.PLACE_NAME_INDEX;
import static com.bocai.search.engine.IndexConstant.USER_NAME_INDEX;

@SuppressWarnings("serial")
@ParentPackage("default")
@Results({ @Result(name = "ajax", type = "json", params = {
		"includeProperties", "genius.*,query" }) })
public class GeniusAction extends BaseAction<Object> {
	@Autowired
	private IndexCache indexCache;
	@Autowired
	private DishManager dishManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private UserManager userManager;

	private TokenIndex dishIndex;
	private TokenIndex placeIndex;
	private TokenIndex userIndex;

	private String query;
	private GeniusVo genius;

	public String ajaxGetSuggestions() {
		Set<String> tokens = dishIndex.doQuery(query);
		String[] results;
		if (tokens != null && tokens.size() != 0) {
			results = tokens.toArray(new String[] {});
			genius.setDishCount(results.length - GENIUS_SUGGEST_LIST_SIZE);
			if (results.length > GENIUS_SUGGEST_LIST_SIZE) {
				results = (String[]) ArrayUtils.subarray(results, 0,
						GENIUS_SUGGEST_LIST_SIZE);
			}
			genius.setDishs(results);
		}
		tokens = placeIndex.doQuery(query);
		if (tokens != null && tokens.size() != 0) {
			results = tokens.toArray(new String[] {});
			genius.setPlaceCount(results.length - GENIUS_SUGGEST_LIST_SIZE);
			if (results.length > GENIUS_SUGGEST_LIST_SIZE) {
				results = (String[]) ArrayUtils.subarray(results, 0,
						GENIUS_SUGGEST_LIST_SIZE);
			}
			genius.setPlaces(results);
		}
		tokens = userIndex.doQuery(query);
		if (tokens != null && tokens.size() != 0) {
			results = tokens.toArray(new String[] {});
			genius.setUserCount(results.length - GENIUS_SUGGEST_LIST_SIZE);
			if (results.length > GENIUS_SUGGEST_LIST_SIZE) {
				results = (String[]) ArrayUtils.subarray(results, 0,
						GENIUS_SUGGEST_LIST_SIZE);
			}
			genius.setUsers(results);
		}
		return AJAX;
	}

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		genius = new GeniusVo();
		dishIndex = (TokenIndex) indexCache.getIndex(DISH_NAME_INDEX);
		if (dishIndex == null) {
			dishIndex = new TokenIndex();
			indexCache.setIndex(DISH_NAME_INDEX, dishIndex);
			List<String> dishNames = dishManager.retrieveAllDishNames();
			Set<String> source = new HashSet<String>();
			if (dishNames != null) {
				Iterator<String> it = dishNames.iterator();
				while (it.hasNext()) {
					String s = it.next();
					if (s != "" || s != null) {
						source.add(s);
					}
				}
			}
			dishIndex.setSource(source);
			dishIndex.buildIndex();
		}
		placeIndex = (TokenIndex) indexCache.getIndex(PLACE_NAME_INDEX);
		if (placeIndex == null) {
			placeIndex = new TokenIndex();
			indexCache.setIndex(PLACE_NAME_INDEX, placeIndex);
			List<String> placeNames = placeManager.retrieveAllPlaceNames();
			Set<String> source = new HashSet<String>();
			if (placeNames != null) {
				Iterator<String> it = placeNames.iterator();
				while (it.hasNext()) {
					String s = it.next();
					if (s != "" || s != null) {
						source.add(s);
					}
				}
			}
			placeIndex.setSource(source);
			placeIndex.buildIndex();
		}
		userIndex = (TokenIndex) indexCache.getIndex(USER_NAME_INDEX);
		if (userIndex == null) {
			userIndex = new TokenIndex();
			indexCache.setIndex(USER_NAME_INDEX, userIndex);
			List<String> userNames = userManager.retrieveAllUserNames();
			Set<String> source = new HashSet<String>();
			if (userNames != null) {
				Iterator<String> it = userNames.iterator();
				while (it.hasNext()) {
					String s = it.next();
					if (s != "" || s != null) {
						source.add(s);
					}
				}
			}
			userIndex.setSource(source);
			userIndex.buildIndex();
		}
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public IndexCache getIndexCache() {
		return indexCache;
	}

	public void setIndexCache(IndexCache indexCache) {
		this.indexCache = indexCache;
	}

	public GeniusVo getGenius() {
		return genius;
	}

	public void setGenius(GeniusVo genius) {
		this.genius = genius;
	}
}
