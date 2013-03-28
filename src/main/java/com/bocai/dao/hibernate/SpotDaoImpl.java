package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.SpotDao;
import com.bocai.dao.domain.Spot;

@Repository("spotDao")
public class SpotDaoImpl extends HibernateBaseDao<Spot, Long>implements SpotDao {

	@Override
	public Spot findById(Long id) {
		return super.get(id);
	}

	@Override
	public Spot findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot save(Spot bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

/*	@Override
	public Spot updateByUpdater(Updater<Spot> updater) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	protected Class<Spot> getEntityClass() {
		// TODO Auto-generated method stub
		return Spot.class;
	}

	@Override
	public List<Spot> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	@Override
	public Pagination getSpotsOnAggSpot(Long aggSpotId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.spots.by.aggspot");
		Finder f = Finder.create(hql);
		f.setParam("aggSpotID", aggSpotId);
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}
	
//	@Override
//	public Pagination getSpotsWithCommentsOnAggSpot(Long aggSpotId, int pageAt) {
//		String hql = HQLContainer.getHql("get.spots.with.comments.by.aggspot");
//		Finder f = Finder.create(hql);
//		f.setParam("aggSpotID", aggSpotId);
//		
//		Pagination p = find(f,pageAt,8);
//		return p;
//	}

	@Override
	public Pagination getSpotsByDish(Long dishId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.spot.by.dish");
		Finder f = Finder.create(hql);
		f.setParam("dishID", dishId);
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getSpotsByPlace(Long placeId,int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.spot.by.place");
		Finder f = Finder.create(hql);
		f.setParam("placeID", placeId);
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}
	
	@Override
	public Pagination getLatestSpotsByCity(String city,int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.latest.spots.by.city");
		Finder f = Finder.create(hql);
		f.setParam("city", city);
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

}
