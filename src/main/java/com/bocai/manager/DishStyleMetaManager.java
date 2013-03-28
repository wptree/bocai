package com.bocai.manager;

import java.util.Set;

import com.bocai.dao.domain.DishStyleMeta;

public interface DishStyleMetaManager extends BaseManager<DishStyleMeta> {
	/*
	 * Load all the records to cache
	 */
	public Set<DishStyleMeta> loadAll();
	
	public void initiazation();
}
