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
 * Metadata for Dish Style. 菜系："杭帮菜","川菜","粤菜","清真"...
 * All the records should be loaded to cache when application starts up.
 * Should collect new record during application running
 * @author wpeng
 *
 */

@Entity
@Table(name="BC_DISH_STYLE_META")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishStyleMeta implements Serializable{
	private static final long serialVersionUID = -2625887060889460699L;
	
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
