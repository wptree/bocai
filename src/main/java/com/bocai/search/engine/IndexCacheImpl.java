package com.bocai.search.engine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("indexCache")
public class IndexCacheImpl implements IndexCache {

	private Map<String, Index> indexMap = new HashMap<String, Index>();
	
	@Override
	public Index getIndex(String indexName) {
		return indexMap.get(indexName);
	}

	@Override
	public void setIndex(String indexName, Index index) {
		indexMap.put(indexName, index);
	}

}
