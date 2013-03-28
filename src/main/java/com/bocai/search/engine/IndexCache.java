package com.bocai.search.engine;

public interface IndexCache {
	
	public Index getIndex(String indexName);
	
	public void setIndex(String indexName, Index index);
}
