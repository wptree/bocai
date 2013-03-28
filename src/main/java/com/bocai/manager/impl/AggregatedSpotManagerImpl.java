package com.bocai.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.SpotManager;
import com.bocai.vo.LatLng;
import com.bocai.vo.MapRequest;

@Service("aggSpotManager")
@Transactional
public class AggregatedSpotManagerImpl extends ManagerHelper implements
		AggregatedSpotManager {
	
	private static final Logger logger = LoggerFactory.getLogger(AggregatedSpotManagerImpl.class);   
	
	@Override
	public Long save(AggregatedSpot bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(AggregatedSpot bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AggregatedSpot bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AggregatedSpot> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AggregatedSpot getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AggregatedSpot getDetailById(Long id) {
		return aggSpotDao.findById(id);
	}

	@Override
	public List<Comment> getBestSpotComments(AggregatedSpot bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAggSpotOnMap(MapRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Pagination getAggSpotsByMap(MapRequest request) {
		Pagination p = null;
		// just for test
		//request.setType(MapRequestType.FOLLOWING.toString());
		if (request.getUserId() == null || request.getType().equalsIgnoreCase(MapRequestType.LATEST.toString())) {
			p = aggSpotDao.getAggSpotsByMap(request);
		} else {
			p = userDao.getAggSpotsByMap(request);
		}
		return p;
	}

	@Override
	public Pagination getAggSpotsByLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AggregatedSpot aggregateSpot(Spot spot) {
		logger.info("try to aggregate spot...");
		AggregatedSpot aggSpot = retrieveAggSpotted(spot.getDish().getId(), spot.getPlace().getId());
		if(aggSpot == null){ //create a new AggregatedSpot
			logger.info("no such agg spot, creat it");
			aggSpot = new AggregatedSpot();
			aggSpot.getSpots().add(spot);
			aggSpot.setDescription(spot.getDescription());
			aggSpot.setDish(spot.getDish());
			aggSpot.setPlace(spot.getPlace());
			aggSpot.setSpottedNum(1);
			aggSpot.setStatus(0);
			aggSpot.setTastedNum(0);
			aggSpot.setAveragePrice(spot.getPrice());
			aggSpot.setCreatedBy(spot.getCreatedBy());
			aggSpot.setCreatedAt(new Date());
			aggSpot.setUpdatedAt(new Date());
			aggSpot.setLastSpot(spot);
			aggSpot.getSpottedBys().add(spot.getSpotedBy());
			aggSpotDao.save(aggSpot);
		} else { // aggregate
			logger.info("already exist agg spot, aggregate it");
			aggSpot.getSpots().add(spot);
			aggSpot.getSpottedBys().add(spot.getSpotedBy());
			aggSpot.setLastSpot(spot);
			aggSpot.setUpdatedAt(new Date());
			double averagePrice = (aggSpot.getAveragePrice()*aggSpot.getSpottedNum()+spot.getPrice())/(aggSpot.getSpottedNum()+1);
			BigDecimal b = new BigDecimal(averagePrice);  
			averagePrice = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();  
			aggSpot.setAveragePrice(averagePrice);
			aggSpot.setSpottedNum(aggSpot.getSpottedNum()+1);
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggSpot));
		}
		logger.info("set aggspot reference back to spot");
		spot.setAggSpot(aggSpot);
		spotDao.updateByUpdater(new Updater<Spot>(spot));
		
		User user = userDao.findById(spot.getSpotedBy().getId());
		logger.info("get spot user : " + user);
		user.getAggSpotted().add(aggSpot);
		logger.info("add aggspot reference to this user");
		user.setScore(user.getScore()==null?0:user.getScore() +SystemConfig.getActivityScore(ActivityType.SPOT_UPLOAD));
		logger.info("incr his/her score, currently it's:" + user.getScore());
		user.setTotalSpotCount(user.getTotalSpotCount()+1);
		logger.info("incr his/her spot count, currently it's:" + user.getTotalSpotCount());
		userDao.updateByUpdater(new Updater<User>(user));
	
		return aggSpot;
	}

	@Override
	public AggregatedSpot getById(Long id) {
		return aggSpotDao.findById(id);
	}

	@Override
	public Pagination getAggSpotsByPlace(Long placeId, int pageAt) {
		
		Pagination p = aggSpotDao.getAggSpotsByPlace(placeId);
		
		return p;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	private AggregatedSpot retrieveAggSpotted(Long dishID, Long placeID) {
		String hql = HQLContainer.getHql("check.aggspot.by.dish.and.place");
		List list = aggSpotDao.find(hql, dishID, placeID);
		if (list.size() > 0) {
			return (AggregatedSpot)list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public AggregatedSpot getMostSpottedByPlace(Long placeID) {
		String hql = HQLContainer.getHql("get.most.spotted.by.place");
		List list = aggSpotDao.find(hql,placeID);
		if (list.size() > 0) {
			return (AggregatedSpot)list.get(0);
		}
		return null;
	}

	@Override
	public Pagination getAggSpotsByDish(Long dishId, int pageSize, int pageAt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination searchAggspotByDishName(MapRequestType reqType,
			String dishName, Long userId, int pageAt, int pageSize, LatLng sw,
			LatLng ne) {
		return aggSpotDao.searchAggspotByDishName(reqType, dishName, userId, pageAt, pageSize, sw, ne);
	}

	@Override
	public Pagination searchAggspotByPlaceName(MapRequestType reqType,
			String placeName, Long userId, int pageAt, int pageSize, LatLng sw,
			LatLng ne) {
		return aggSpotDao.searchAggspotByPlaceName(reqType, placeName, userId, pageAt, pageSize, sw, ne);
	}

	@Override
	public Pagination searchAggspotByDishNameAndUserID(long userID,
			MapRequestType reqType, String dishName, int pageAt, int pageSize,
			LatLng sw, LatLng ne) {
		return aggSpotDao.searchAggspotByDishNameAndUserID(userID, reqType, dishName, pageAt, pageSize, sw, ne);
	}

	@Override
	public Pagination searchAggspotByPlaceNameAndUserID(long userID,
			MapRequestType reqType, String placeName, int pageAt, int pageSize,
			LatLng sw, LatLng ne) {
		return aggSpotDao.searchAggspotByPlaceNameAndUserID(userID, reqType, placeName, pageAt, pageSize, sw, ne);
	}
}
