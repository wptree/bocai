package com.bocai.dao;

import java.util.List;

import com.bocai.common.hibernate.Updater;

public interface BaseDao<T>{
	public T findById(Long id);
	
	public T findUniqueBy(String prop, Object value);
	
	public T deleteById(Long id);
	
	public T save(T bean);
	
	public T updateByUpdater(Updater<T> updater);
	
	/**
	 * Find recodes by setting a specific property name and value
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findBy(String property, Object value);
	
	/**
	 * 通过HQL查询对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	@SuppressWarnings("rawtypes")
	public List find(String queryString, Object... values);
}
