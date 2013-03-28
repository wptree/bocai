package com.bocai.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.PlaceDao;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.vo.Area;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;
import com.bocai.vo.PlaceIndex;
import com.bocai.vo.Token;

@Repository("placeDao")
public class PlaceDaoImpl extends HibernateBaseDao<Place, Long> implements PlaceDao {

	@Override
	public Place findById(Long id) {
		Place place = super.get(id);
		return place;
	}

	@Override
	public Place findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public Place deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Place save(Place bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Place updateByUpdater(Updater<Place> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Place> getEntityClass() {
		// TODO Auto-generated method stub
		return Place.class;
	}

	@Override
	public List<Place> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	@Override
	public Pagination getDishesByPlace(Place place, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.dishes.by.place");
		Finder f = Finder.create(hql);
		f.setParam("placeID", place.getId(), new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getAggSpotsByPlace(Long placeId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.aggspot.by.place");
		Finder f = Finder.create(hql);
		f.setParam("placeID", placeId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getSpottedUserByPlace(Long placeID, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.spotteduser.by.place");
		Finder f = Finder.create(hql);
		f.setParam("placeID", placeID, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getFollowers(Long placeId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.follower.by.place");
		Finder f = Finder.create(hql);
		f.setParam("placeID", placeId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}
	
	@Override
	public Pagination getFollowingFans(Long placeId, Long userId, int pageNo, int pageAt){
		String hql = HQLContainer.getHql("get.following.fans.of.place");
		Finder f = Finder.create(hql);
		f.setParam("placeId", placeId, new LongType());
		f.setParam("userId", userId, new LongType());
	
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> retrieveAllPlaceNames() {
		Criteria crit = getSession().createCriteria(Place.class); 
		ProjectionList projList = Projections.projectionList(); 
		projList.add(Projections.property("fullName"));
		crit.setProjection(Projections.distinct(projList));
		List<String> results = crit.list();
		return results;
	}

	public Pagination getMostSpottedPlaces(Long uid, int at, int size){
		String hql = HQLContainer.getHql("get.most.spotted.place");
		Finder f = Finder.create(hql);
		f.setParam("userId", uid, new LongType());
		Pagination p = find(f, at, size);
		return p;
	}

	@Override
	public Pagination getPlacesByMap(int pageAt,int pageSize, LatLng sw, LatLng ne) {
		String hql = null;
		if (sw.getLng() < ne.getLng()) {
			hql = HQLContainer.getHql("get.place.by.map.1");
		} else{
			hql = HQLContainer.getHql("get.place.by.map.2");
		}
		Finder f = Finder.create(hql);
		f.setParam("lngmin", sw.getLng(),new DoubleType());
		f.setParam("lngmax", ne.getLng(),new DoubleType());
		f.setParam("latmin", sw.getLat(),new DoubleType());
		f.setParam("latmax", ne.getLat(),new DoubleType());
		
		Pagination p = find(f,pageAt,pageSize);
		
		return p;
	}

	@Override
	public List<PlaceIndex> buildIndex(int pageSize, int pageAt) {
		//select aggspot.id, dish.name, place.full_name, location.city 
		String hql = HQLContainer.getHql("get.place.index");
		Finder f = Finder.create(hql);
		Pagination p = find(f,pageAt,pageSize);
		List<AggregatedSpot> result =  (List<AggregatedSpot>) p.getList();
		LinkedList<PlaceIndex> list = new LinkedList<PlaceIndex>(); 
		String lastPlaceName = "";
		PlaceIndex lastIndex = null;
		
		for(AggregatedSpot aggSpot : result){
			Place place = aggSpot.getPlace();
			StringBuffer buf = new StringBuffer();
			Dish dish = aggSpot.getDish();
			String placeName = "未知";
			if(place!=null){
				placeName = place.getFullName();
			}
			buf.append(dish.getName()).append(" @ ").append(placeName);
			Token token = new Token(aggSpot.getId().toString(), buf.toString());
			if(lastPlaceName.equals(place.getFullName())){
				if(lastIndex!=null&&lastIndex.getAggSpotList()!=null){
					lastIndex.getAggSpotList().add(token);
				}
			}else{
				Location loc = place.getLocation();
				String city = "神秘地点";
				if(loc != null){
					city = loc.getCity();
				}
				PlaceIndex newIndex = new PlaceIndex(city,place.getFullName());
				LinkedList<Token> aggSpotList = new LinkedList<Token>();
				aggSpotList.add(token);
				newIndex.setAggSpotList(aggSpotList);
				list.add(newIndex);
				lastIndex = newIndex;
				lastPlaceName = place.getFullName();
			}
			buf = null;
		}
		
		return list;
	}
}
