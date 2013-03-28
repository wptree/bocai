package com.bocai.manager.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.image.ImageInfo;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.manager.AggregatedSpotManager;
import com.bocai.manager.DishManager;
import com.bocai.manager.DishTypeMetaManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.FrontResultSet;
import com.bocai.vo.MapRequest;
import com.bocai.vo.SpotUploadRequest;
import com.bocai.web.util.ContentUtil;
import com.bocai.web.util.ImageHelper;
import com.googlecode.ehcache.annotations.Cacheable;

@Service("spotManager")
@Transactional
public class SpotManagerImpl extends ManagerHelper implements SpotManager{
	
	private static final Logger logger = LoggerFactory.getLogger(SpotManagerImpl.class);   
	
	@Resource
	private AggregatedSpotManager aggSpotManager; 
	@Resource
	private DishManager dishManager;
	@Resource
	private PlaceManager placeManager;
	@Resource
	private UserManager userManager;
	
	@Override
	public boolean persistSpot(SpotUploadRequest request){
		try {
			Spot spot = new Spot();
			spot.setCreatedBy(request.getCurrentUser());
			spot.setSpotedBy(request.getCurrentUser());
			spot.setStatus(0);
			spot.setPostBy(request.getPostBy());
			spot.setCreatedAt(new Date());
			spot.setUpdatedAt(spot.getCreatedAt());
			spot.setGrade(0);
			//在表单上一定需要验证
			spot.setPrice(Double.parseDouble(request.getSpotPrice()));
			spot.setTitle("");
			spot.setDescription(request.getDescription());
			Dish dish = null; 
			Place place = null;
			
			if("-9999".equals(request.getSpotDishName())){
				dish = dishManager.getUniqueByProperty("name", request.getNewSpotDishName());
				if(dish==null){
					dish = new Dish();
					dish.setName(request.getNewSpotDishName());
					dish.setType(request.getSpotDishTypeName());
					dishManager.save(dish);
				}
			}else{
				dish = dishManager.getUniqueByProperty("name", request.getSpotDishName());

			}
			
			spot.setDish(dish);

			if("-9999".equals(request.getSpotPlaceId())){
				place = placeManager.getById(Long.valueOf(request.getNewSpotPlaceId()));
			}else{
				place = placeManager.getById(Long.valueOf(request.getSpotPlaceId()));
			}
			spot.setPlace(place);
			
			Long spotId = spotDao.save(spot).getId();
			
			StringBuffer root = new StringBuffer(request.getDishImageRoot());
			root.append(File.separator).append(spotId);
			ImageInfo info = ImageHelper.resizeSpotImg(request.getSpotImg(),root.toString(),request.getImgSizeArray(),SystemConfig.getImgMark(),0);
			root = null;
			
			spot.setImgType(info.getFormatName());
			spotDao.updateByUpdater(new Updater<Spot>(spot));
			
			AggregatedSpot aggSpot = aggSpotManager.aggregateSpot(spot);
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggSpot));
			
			dish.getAggSpots().add(aggSpot);
			dishManager.update(dish);
			
			//feedDao.publish(request.getCurrentUser(), ActivityType.SPOT_UPLOAD, spot);
			
			if(request.isSend2Sina()){
				if(request.getCurrentUser().sinaConnected()){
					userManager.weibo2Sina(spot, ActivityType.SPOT_UPLOAD);
				}
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addSpot(Spot spot, File orgImg, double imgRotateDegree, String contextPath) {
		if (spot == null || orgImg == null || !orgImg.exists())
			return;
		logger.info("try to save new spot: " + spot);
		save(spot);
		
		aggSpotManager.aggregateSpot(spot);
		List<String> tagStrs = ContentUtil.parseTag(spot.getDescription());
		User user = userDao.findById(spot.getCreatedBy().getId());
		if(tagStrs!=null){
			AggregatedSpot aggSpot = spot.getAggSpot();
			List<Tag> tags = tagDao.getTagsByNames(tagStrs);
			// save new tags
			List<String> newTags = new ArrayList<String>(tagStrs);
			if(tags!=null){
				for(Tag tag : tags){
					newTags.remove(tag.getTagName());
				}
			}
			if(user.getCreatedTags()==null){
				user.setCreatedTags(new HashSet<Tag>());
			}
			for(String newTag: newTags){
				Tag tag = new Tag();
				tag.setTagName(newTag);
				tag.setTagFrom("spot");
				tag.setCreatedAt(new Date());
				tag.setUpdatedAt(tag.getCreatedAt());
				tags.add(tagDao.save(tag));
			}
			// create xref between aggspot and tag
			Map<String, Long> ts = new HashMap<String, Long>();
			for (Tag tag: tags){
				aggSpotTagXrefDao.makeSureXref(aggSpot, tag);
				tag.setUpdatedAt(new Date());
				if(tag.getCreators()==null){
					tag.setCreators(new HashSet<User>());
				}
				tag.getCreators().add(user);
				user.getCreatedTags().add(tag);
				tagDao.updateByUpdater(new Updater<Tag>(tag));
				ts.put(tag.getTagName(), tag.getId());
			}
			userDao.updateByUpdater(new Updater<User>(user));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("content", spot.getDescription());
			params.put("tags", ts);
			params.put("contextPath", contextPath);
			
			spot.setDescription(ContentUtil.htmlTag(params));
			aggSpot.setDescription(spot.getDescription());
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggSpot));
		}
		StringBuffer spotPath = new StringBuffer()
				.append(SystemConfig.getSpotImgPath()).append(File.separator)
				.append(spot.getId());
		logger.info("handle image via JMagick...");
		ImageInfo info = ImageHelper.resizeSpotImg(orgImg, spotPath.toString(),
				SystemConfig.getSpotImgSizeDefineArray(), SystemConfig.getImgMark(),
				imgRotateDegree);
		spot.setImgType(info.getFormatName());
		logger.info("update spot's image type");
		update(spot);
		logger.info("delete org image from temp directory");
		orgImg.delete();
	}
	
	@Override
	public Long save(Spot bean){
		if(bean!=null){
			spotDao.save(bean);
			return bean.getId();
		}else{
			return null;
		}
	}
	@Override
	public Long update(Spot spot){
		spotDao.updateByUpdater(new Updater<Spot>(spot));
		return spot.getId();
	}

	@Override
	public void delete(Spot bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spot getDetailById(Long id) {
		return spotDao.findById(id);
	}

	@Override
	public List<Comment> getComments(Spot bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination getSpotsByMap(MapRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spot> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Spot checkSpotByDishAndPlace(Long dishId, Long placeId) {
		String hql = HQLContainer.getHql("check.spot.by.dish.and.place");
		List list = spotDao.find(hql,dishId,placeId);
		if(list!=null && list.size()>0){
			Object[] objectss = (Object[]) list.get(0);
			Spot spot = (Spot) objectss[0];
			return spot;
		}else{
			return null;
		}
	}
	@Override
	public Spot getById(Long id) {
		return spotDao.findById(id);
	}
	@Override
	public Pagination getSpotsOnAggSpot(Long aggSpotId, int pageNo, int pageAt) {
		return spotDao.getSpotsOnAggSpot(aggSpotId,pageNo,pageAt);
	}
	
	@SuppressWarnings("rawtypes")
	@Cacheable(cacheName="spotsFrontCache")
	public FrontResultSet<Spot> getSpotsWithCommentsOnAggSpot (Long aggSpotId, int spotPageNo, int commentPageNo, int pageAt) {
		FrontResultSet<Spot> result  = new FrontResultSet<Spot>();
		Pagination spotPager = spotDao.getSpotsOnAggSpot(aggSpotId, spotPageNo, pageAt);
		result.setAt(spotPager.getPageNo());
		result.setTotal(spotPager.getTotalPage());
		result.setAllCount(spotPager.getTotalCount());
		if(spotPager != null && spotPager.getList() != null){
			List spots = spotPager.getList();
			for(Object o : spots){
				Spot spot = (Spot)o;
				result.addItem(spot);
				if(spot != null){
					Pagination commentPager = commentDao.getCommentsOnSpot(spot.getId(), commentPageNo, 0);
					result.putEntry(spot.getId(), commentPager);
				}
			}
		}
		return result;
	}
	
	@Override
	public Pagination getSpotsByDish(Long dishId, int pageNo, int pageAt) {
		return spotDao.getSpotsByDish(dishId,pageNo,pageAt);
	}
	@Override
	public Pagination getSpotsByPlace(Long placeId,int pageNo, int pageAt) {
		return spotDao.getSpotsByPlace(placeId,pageNo,pageAt);
	}

	@Override
	public Pagination getComments(Spot spot, int pageNo, int pageAt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spot getLastSpotByUser(Long userId) {
		String hql = HQLContainer.getHql("get.last.spot.by.user");
		List spotList= userDao.find(hql, userId);
		if (spotList.size() > 0) {
			return (Spot)spotList.get(0);
		}
		return null;
	}

	@Override
	public void removeSpot(User user, Spot spot) {
		spot.setStatus(-1);
		spot.setUpdatedAt(new Date());
		
		User thisUser = (User)userDao.find("from User as user left join fetch user.mySpots where user.id = ?", user.getId()).get(0);
		thisUser.getMySpots().remove(spot);
		
		AggregatedSpot aggspot = (AggregatedSpot)aggSpotDao.find("from AggregatedSpot as aggspot left join fetch aggspot.spots where aggspot.id = ?", spot.getAggSpot().getId()).get(0);
		aggspot.getSpots().remove(spot);
		if (aggspot.getSpots().size() == 0) {
			aggspot.setStatus(-1);
			
			thisUser = (User)userDao.find("from User as user left join fetch user.nomAggSpots where user.id = ?", user.getId()).get(0);
			thisUser.getNomAggSpots().remove(aggspot);
			
			thisUser = (User)userDao.find("from User as user left join fetch user.wantedAggSpots where user.id = ?", user.getId()).get(0);
			thisUser.getWantedAggSpots().remove(aggspot);
				
			thisUser = (User)userDao.find("from User as user left join fetch user.testedAggSpots where user.id = ?", user.getId()).get(0);
			thisUser.getTestedAggSpots().remove(aggspot);
			
			thisUser = (User)userDao.find("from User as user left join fetch user.aggSpotted where user.id = ?", user.getId()).get(0);
			thisUser.getAggSpotted().remove(aggspot);
			
			thisUser = (User)userDao.find("from User as user left join fetch user.sharedAggSpots where user.id = ?", user.getId()).get(0);
			thisUser.getSharedAggSpots().remove(aggspot);
			
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggspot));
		}
		
		userDao.updateByUpdater(new Updater<User>(thisUser));
		
		spotDao.updateByUpdater(new Updater<Spot>(spot));
	}
	

	@Override
	public Pagination getLatestSpotsByCity(String city, int pageNo, int pageAt) {
		return spotDao.getLatestSpotsByCity(city, pageNo, pageAt);
	}
	
}
