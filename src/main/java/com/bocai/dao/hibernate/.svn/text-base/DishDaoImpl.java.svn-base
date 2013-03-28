package com.bocai.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.DishDao;
import com.bocai.dao.domain.Dish;

@Repository("dishDao")
public class DishDaoImpl extends HibernateBaseDao<Dish, Long> implements DishDao {

	@Override
	public Dish findById(Long id) {
		return super.get(id);
	}

	@Override
	public Dish findUniqueBy(String prop, Object value) {
		return findUniqueByProperty(prop, value);
	}

	@Override
	public Dish deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dish save(Dish bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Dish updateByUpdater(Updater<Dish> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Dish> getEntityClass() {
		return Dish.class;
	}
	@Override
	public List<Dish> findBy(String property, Object value){
		return super.findByProperty(property, value);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}
	
	@Override
	public List<String> retrieveAllDishNames() {
		Criteria crit = getSession().createCriteria(Dish.class); 
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.distinct(Projections.property("name")));
		crit.setProjection(projList);
		List<String> result = crit.list(); 						
		return result;
	}

	@Override
	public Pagination getAggSpotsByDish(Long dishId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.aggspots.by.dish");
		Finder f = Finder.create(hql);
		f.setParam("dishID", dishId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}
	
	public Pagination getMostSpottedDishs(Long userId, int at, int size){
		String hql = HQLContainer.getHql("get.most.spotted.dish");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId, new LongType());
		Pagination p = find(f, at, size);
		return p;
	}
}
