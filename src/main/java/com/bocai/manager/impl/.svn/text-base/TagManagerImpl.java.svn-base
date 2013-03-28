package com.bocai.manager.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terracotta.ehcachedx.org.mortbay.log.Log;

import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.AggSpotTagXref;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Tag;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.TagManager;
import com.bocai.vo.PinVo;
import com.bocai.vo.Token;
import com.googlecode.ehcache.annotations.Cacheable;

@Service("tagManager")
@Transactional
public class TagManagerImpl extends ManagerHelper implements TagManager{

	private static final Logger logger = 
		LoggerFactory.getLogger(TagManagerImpl.class);   
	
	@Override
	public Long save(Tag bean) {
		if(bean!=null){
			tagDao.save(bean);
			return bean.getId();
		}
		return null;
	}

	@Override
	public Long update(Tag bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Tag bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tag> getByProperty(String prop, Object value) {
		return tagDao.findBy(prop, value);
	}

	@Override
	public Tag getUniqueByProperty(String prop, Object value) {
		if(prop!=null&&!"".equals(prop)){
			return tagDao.findUniqueBy(prop, value);
		}
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initiazation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tag getById(Long id) {
		return tagDao.findById(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Token> getTagMatchedByName(String queryStr) {
		List<Token> tokens = new ArrayList<Token>(); 
		String hql = HQLContainer.getHql("query.tag.like.name");
		List result = tagDao.find(hql, "%"+queryStr+"%");
		if(result != null){
			for(int i = 0; i<result.size(); i++){
				Object[] objects = (Object[]) result.get(i);
				tokens.add(new Token(objects[0].toString(), objects[1].toString()));
			}
		}
		return tokens;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(cacheName = "getLatestTagPins")
	public List<PinVo> getLatestTagPins(int no, int size) {
		logger.info("start get latest tag pins");
		List<PinVo> pins = new ArrayList<PinVo>();
		try{
			Pagination p = tagDao.getLatestTagsWithAggSpotXrefs(no, size);
			
			if(p!=null && p.getList()!=null){
				List<Tag> tags = (List<Tag>) p.getList();
				logger.info("tag number :" + tags.size());
//				Map<Tag, List<AggregatedSpot>> tagMap = new TreeMap<Tag, List<AggregatedSpot>>(
//						new Comparator<Tag>(){
//							@Override
//							public int compare(Tag o1, Tag o2) {
//								if(o1==null && o2==null){
//									return 0;
//								}else if(o1!=null && o2==null){
//									return -1;
//								}else if(o1 ==null && o2!=null){
//									return 1;
//								}
//								return 0-o1.getCreatedAt().compareTo(o2.getCreatedAt());
//							}
//						});
				Map<Tag, List<AggregatedSpot>> tagMap = new LinkedHashMap<Tag, List<AggregatedSpot>>();
				List<Long> tagIds = new ArrayList<Long>();
				for(int i=0; i<tags.size(); i++){
					tagIds.add(tags.get(i).getId());
					tagMap.put(tags.get(i), new ArrayList<AggregatedSpot>());
				}
				if(!tags.isEmpty()){
					List<AggSpotTagXref> xrefs = aggSpotTagXrefDao.getByTagIds(tagIds);
					if(xrefs!=null){
						for(int i=0; i<xrefs.size(); i++){
							AggSpotTagXref xref = xrefs.get(i);
							List<AggregatedSpot> aggSpots = tagMap.get(xref.getTag());
							if(aggSpots==null){
								aggSpots = new ArrayList<AggregatedSpot>();
								tagMap.put(xref.getTag(), aggSpots);
							}
							aggSpots.add(xref.getAggSpot());
						}
					}
				}
				for (Map.Entry<Tag, List<AggregatedSpot>> entry : tagMap.entrySet()){
					PinVo pin = PinVo.from(entry.getKey(), entry.getValue());
					pins.add(pin);
					//Log.info("add tag pin " + pin.getTagName() );
				}
			}else{
				logger.info("no more tags");
			}
		}catch(Exception e){
			logger.info("exception occurs" + e.getClass() + " " +e.toString());
			logger.error(e.getMessage(), e);
		}
		return pins;
	}
	
	
	public Pagination getAggSpotsForTag(Long tagId, int no, int size){
		return aggSpotTagXrefDao.getAggSpotsForTag(tagId, no, size);
	}

	public Pagination getFollowers(Long tagId, int pageNo, int pageAt) {
		return tagDao.getFollowers(tagId, pageNo, pageAt);
	}

	@Override
	public Pagination getCreators(Long tagId, int pageNo, int pageAt) {
		return tagDao.getCreators(tagId, pageNo, pageAt);
	}
}
