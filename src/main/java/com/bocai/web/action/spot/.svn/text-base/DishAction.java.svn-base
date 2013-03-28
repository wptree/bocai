package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;


import com.bocai.manager.DishManager;
import com.bocai.manager.DishTypeMetaManager;
import com.bocai.manager.TagManager;
import com.bocai.search.engine.IndexCache;
import com.bocai.search.engine.IndexConstant;
import com.bocai.search.engine.TokenIndex;
import com.bocai.vo.Token;
import com.bocai.web.action.BaseAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@ParentPackage("spot")
@Results({
	@Result(name="errorPage",location="/WEB-INF/page/404.jsp"),
	@Result(name = "ajax_get", type = "json", 
		params = {"includeProperties","tokenArray.*"}
	)
})
public class DishAction extends BaseAction<DishAction>{
	private static final long serialVersionUID = -7030003365421642324L;
	@Autowired
	private DishManager dishManager;
	@Autowired
	private DishTypeMetaManager dishTypeMetaManager;
	@Autowired
	private TagManager tagManager;
	
	private List<Token> tokenArray;
	private String query;
	
	@Autowired 
	private IndexCache indexCache;
	private TokenIndex dishIndex = null ;


	@InputConfig(resultName = "ajax_get")
	public String queryResult(){
		if(query!=null){
			Set<String> result = dishIndex.doQuery(query);
			tokenArray = new ArrayList<Token>();
			if(result!=null){
				for(String re : result){
					tokenArray.add(new Token(re, re));
				}
			}
		}
		
		return "ajax_get";
	}
	
	@InputConfig(resultName = "ajax_get")
	public String instantQueryType(){
		tokenArray = dishTypeMetaManager.getTypeMatchedByName(getQuery());
		return "ajax_get";
	}
	
	@InputConfig(resultName = "ajax_get")
	public String instantQueryTag(){
		tokenArray = tagManager.getTagMatchedByName(getQuery());
		return "ajax_get";
	}

	@Override
	public DishAction getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		dishIndex = (TokenIndex)indexCache.getIndex(IndexConstant.DISH_NAME_INDEX);
		if (dishIndex == null) {
			dishIndex = new TokenIndex();
			indexCache.setIndex(IndexConstant.DISH_NAME_INDEX, dishIndex);
			//dishIndex = new TokenIndex();
			List<String> dishNames = dishManager.retrieveAllDishNames();
			Set<String> source = new HashSet<String>();
			if (dishNames != null) {
				Iterator<String> it = dishNames.iterator();
				while(it.hasNext()) {
					String s = it.next();
					if (s != "" || s != null) {
						source.add(s);
					}
				}
			}
			dishIndex.setSource(source);
			dishIndex.buildIndex();
		}

	}
	
	public List<Token> getTokenArray() {
		return tokenArray;
	}

	public void setTokenArray(List<Token> tokenArray) {
		this.tokenArray = tokenArray;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setDishManager(DishManager dishManager) {
		this.dishManager = dishManager;
	}

	public DishManager getDishManager() {
		return dishManager;
	}

	public void setDishTypeMetaManager(DishTypeMetaManager dishTypeMetaManager) {
		this.dishTypeMetaManager = dishTypeMetaManager;
	}

	public DishTypeMetaManager getDishTypeMetaManager() {
		return dishTypeMetaManager;
	}

	public void setTagManager(TagManager tagManager) {
		this.tagManager = tagManager;
	}

	public TagManager getTagManager() {
		return tagManager;
	}
	
	public IndexCache getIndexCache() {
		return indexCache;
	}

	public void setIndexCache(IndexCache indexCache) {
		this.indexCache = indexCache;
	}

}
