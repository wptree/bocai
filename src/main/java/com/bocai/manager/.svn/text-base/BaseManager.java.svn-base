package com.bocai.manager;

import java.util.List;

/**
 * BaseManager to define common function
 * @author wpeng
 *
 * @param <T> Domain entity
 */
public interface BaseManager<T> {	
	/**
	 * Save a entity
	 * @param bean
	 * @return
	 */
	public Long save(T bean);
	/**
	 * Update a entity
	 * @param bean
	 * @return
	 */
	public Long update(T bean);
	/**
	 * Delete a entity
	 * @param bean
	 */
	public void delete(T bean);
	/**
	 * Find entities by a specific property
	 * @param prop
	 * @param value
	 * @return
	 */
	public List<T> getByProperty(String prop, Object value);
	/**
	 * Find a entity by a unique property, e.g. 'id'
	 * @param prop
	 * @param value
	 * @return
	 */
	public T getUniqueByProperty(String prop,Object value);
	/**
	 * Count the total number of entity by a property
	 * @param prop
	 * @param value
	 * @return
	 */
	public int countByProperty(String prop,Object value);
	
	public T getById(Long id);
}
