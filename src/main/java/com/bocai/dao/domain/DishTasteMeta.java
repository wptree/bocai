package com.bocai.dao.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Metadata for Dish Taste. 口味："酸","辣","甜","清淡","麻"
 * All the records should be loaded to cache when application starts up.
 * Should collect new record during application running.
 * @author wpeng
 *
 */
@Entity
@Table(name="BC_DISH_TASTE_META")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishTasteMeta implements Serializable{
	private static final long serialVersionUID = -1745044329494976609L;

	private Long id;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name",unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
