package com.bocai.search.engine;

public interface Index {
	
    /**
     * Index can be built when system initialization
     */
	public void buildIndex();
	
    /**
     * Index can be updated at runtime
     */
	public void updateIndex();
}
