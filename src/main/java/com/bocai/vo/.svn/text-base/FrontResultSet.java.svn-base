package com.bocai.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bocai.common.page.Pagination;

@SuppressWarnings("serial")
public class FrontResultSet<T> implements Serializable {
	
	private int at = 1;
	private int total = 1;
	private int allCount = 0;
	
	private List<T> list;
	
	private Map<Long, Pagination>  childPageMap;
	
	public int getAt() {
		return at;
	}

	public void setAt(int at) {
		this.at = at;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public void addItem(T item){
		if(list == null){
			list = new ArrayList<T>();
		}
		list.add(item);
	}
	
	public void putEntry(Long key, Pagination value){
		if(childPageMap == null){
			childPageMap = new HashMap<Long, Pagination>();
		}
		childPageMap.put(key, value);
	}

	public Map<Long, Pagination> getChildPageMap() {
		return childPageMap;
	}

	public void setChildPageMap(Map<Long, Pagination> childPageMap) {
		this.childPageMap = childPageMap;
	}
}
