package com.bocai.dao.hibernate;

import java.util.List;

import org.hibernate.type.DoubleType;
import org.springframework.stereotype.Repository;

import com.bocai.common.constant.MapRequestType;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.AggregatedSpotDao;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.exception.DaoException;
import com.bocai.vo.Area;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;

@Repository("aggSpotDao")
public class AggregatedSpotDaoImpl extends HibernateBaseDao<AggregatedSpot, Long> implements AggregatedSpotDao {

	@Override
	public AggregatedSpot findById(Long id) {
		return super.get(id);
	}

	@Override
	public AggregatedSpot findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AggregatedSpot deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AggregatedSpot save(AggregatedSpot bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		
		return bean;
	}

/*	@Override
	public AggregatedSpot updateByUpdater(Updater<AggregatedSpot> updater) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<AggregatedSpot> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	@Override
	protected Class<AggregatedSpot> getEntityClass() {
		return AggregatedSpot.class;
	}

	public Pagination getAggSpotsByMap(MapRequest request) {
		Area area = request.getArea();
		String hql = null;
		if (area.getSw().getLng() < area.getNe().getLng()) {
			hql = HQLContainer.getHql("get.aggspot.by.map.1");
		} else{
			hql = HQLContainer.getHql("get.aggspot.by.map.2");
		}
		String orderBy = "aggspot.createdAt";
		hql = hql.replace("ORDER_BY_TERM", orderBy);
		Finder f = Finder.create(hql);
		f.setParam("lngmin", area.getSw().getLng(),new DoubleType());
		f.setParam("lngmax", area.getNe().getLng(),new DoubleType());
		f.setParam("latmin", area.getSw().getLat(),new DoubleType());
		f.setParam("latmax", area.getNe().getLat(),new DoubleType());
		
		Pagination p = find(f,request.getPageAt(),20);
		
		return p;
	}
	
	@Override
	public Pagination getAggSpotsByPlace(Long placeId) {
		
		String hql = HQLContainer.getHql("get.aggspot.by.place");
		String orderBy = "aggspot.createdAt";
		hql = hql.replace("ORDER_BY_TERM", orderBy);
		Finder f = Finder.create(hql);
		f.setParam("placeID", placeId);
		
		Pagination p = find(f,0,20);
		return p;
	}

	@Override
	public Pagination searchAggspotByDishName(MapRequestType reqType, String dishName, Long userId, int pageAt,
			int pageSize, LatLng sw, LatLng ne) {
		String hql = null;
		Finder f = null;
		if(reqType == null){
			reqType = MapRequestType.LATEST;
		}
		switch(reqType){
			case HOTEST:
				if (sw.getLng() < ne.getLng()) {
					hql = HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.1");
				} else {
					hql = HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.2");
				}				
				f = Finder.create(hql);
				break;
			case LATEST:
				if (sw.getLng() < ne.getLng()) {
					hql = HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.1");
				} else {
					hql = HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.2");
				}
				f = Finder.create(hql);
				break;
			case FOLLOWING:
				if(userId == null){
					throw new DaoException("Invalid argument [UserId:" + userId + "]");
				}
				if (sw.getLng() < ne.getLng()) {
					hql = HQLContainer.getHql("search.following.aggspot.by.dish.name.and.map.1");
				} else {
					hql = HQLContainer.getHql("search.following.aggspot.by.dish.name.and.map.2");
				}
				f = Finder.create(hql);
				f.setParam("userId", userId);
				break;
			case WANTTED:
				if(userId == null){
					throw new DaoException("Invalid argument [UserId:" + userId + "]");
				}
				if (sw.getLng() < ne.getLng()) {
					hql = HQLContainer
							.getHql("search.wanted.aggspot.by.dish.name.and.map.1");
				} else {
					hql = HQLContainer
							.getHql("search.wanted.aggspot.by.dish.name.and.map.2");
				}			
				f = Finder.create(hql);
				f.setParam("userId", userId);
				break;
			case GUIDE:
//				hql = HQLContainer.getHql("search.aggspot.by.dish.name.guide");
//				f = Finder.create(hql);
//				break;
			default:
				throw new DaoException("Invalid argument [MapRequestType:" + reqType + "]");
		}
		f.setParam("dishname", "%" + dishName + "%");
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination searchAggspotByPlaceName(MapRequestType reqType, 
			String placeName, Long userId, int pageAt,
			int pageSize, LatLng sw, LatLng ne) {
		
		String hql;
		Finder f = null;
		if(reqType == null){
			reqType = MapRequestType.LATEST;
		}
		switch (reqType) {
		case HOTEST:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.aggspot.by.place.name.latest.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.aggspot.by.place.name.latest.and.map.2");
			}
			f = Finder.create(hql);
			break;
		case LATEST:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.aggspot.by.place.name.hotest.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.aggspot.by.place.name.hotest.and.map.2");
			}
			f = Finder.create(hql);
			break;
		case FOLLOWING:
			if(userId == null){
				throw new DaoException("Invalid argument [UserId:" + userId + "]");
			}
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.following.aggspot.by.place.name.and.map.1");
			} else {
				hql = HQLContainer.getHql("search.following.aggspot.by.place.name.and.map.2");
			}
			f = Finder.create(hql);
			f.setParam("userId", userId);
			break;
		case WANTTED:
			if(userId == null){
				throw new DaoException("Invalid argument [UserId:" + userId + "]");
			}
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.place.name.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.place.name.and.map.2");
			}			
			f = Finder.create(hql);
			f.setParam("userId", userId);
			break;
		case GUIDE:
			//hql = HQLContainer.getHql("search.aggspot.by.dish.name.guide");
			//break;
		default:
			throw new DaoException("Invalid argument [MapRequestType:"
					+ reqType + "]");
		}
		
		f.setParam("pname", "%" + placeName + "%");
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination searchAggspotByDishNameAndUserID(long userID,
			MapRequestType reqType, String dishName, int pageAt, int pageSize,
			LatLng sw, LatLng ne) {
		String hql = null;
		Finder f = null;
		switch (reqType) {

		case FOLLOWING:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.following.aggspot.by.dish.name.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.following.aggspot.by.dish.name.and.map.2");
			}
			f = Finder.create(hql);
			break;
		case WANTTED:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.dish.name.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.dish.name.and.map.2");
			}			
			f = Finder.create(hql);
			break;
		case GUIDE:
			/*
			 * hql = HQLContainer.getHql("search.aggspot.by.dish.name.guide"); f
			 * = Finder.create(hql); break;
			 */
		case HOTEST:
			/*
			 * if (sw.getLng() < ne.getLng()) { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.1"
			 * ); } else { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.2"
			 * ); } f = Finder.create(hql); break;
			 */
		case LATEST:
			/*
			 * if (sw.getLng() < ne.getLng()) { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.1"
			 * ); } else { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.2"
			 * ); } f = Finder.create(hql); break;
			 */
		default:
			throw new DaoException("Invalid argument [MapRequestType:"
					+ reqType + "]");
		}
		f.setParam("userID", userID);
		f.setParam("dishname", dishName);
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

	@Override
	public Pagination searchAggspotByPlaceNameAndUserID(long userID,
			MapRequestType reqType, String placeName, int pageAt, int pageSize,
			LatLng sw, LatLng ne) {
		String hql = null;
		Finder f = null;
		switch (reqType) {

		case FOLLOWING:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.following.aggspot.by.place.name.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.following.aggspot.by.place.name.and.map.2");
			}
			f = Finder.create(hql);
			break;
		case WANTTED:
			if (sw.getLng() < ne.getLng()) {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.place.name.and.map.1");
			} else {
				hql = HQLContainer
						.getHql("search.wanted.aggspot.by.place.name.and.map.2");
			}			
			f = Finder.create(hql);
			break;
		case GUIDE:
			/*
			 * hql = HQLContainer.getHql("search.aggspot.by.dish.name.guide"); f
			 * = Finder.create(hql); break;
			 */
		case HOTEST:
			/*
			 * if (sw.getLng() < ne.getLng()) { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.1"
			 * ); } else { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.hotest.and.map.2"
			 * ); } f = Finder.create(hql); break;
			 */
		case LATEST:
			/*
			 * if (sw.getLng() < ne.getLng()) { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.1"
			 * ); } else { hql =
			 * HQLContainer.getHql("search.aggspot.by.dish.name.latest.and.map.2"
			 * ); } f = Finder.create(hql); break;
			 */
		default:
			throw new DaoException("Invalid argument [MapRequestType:"
					+ reqType + "]");
		}
		f.setParam("userID", userID);
		f.setParam("placename", placeName);
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}
}
