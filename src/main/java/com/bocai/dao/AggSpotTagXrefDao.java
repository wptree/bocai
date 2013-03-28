package com.bocai.dao;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggSpotTagXref;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Tag;

public interface AggSpotTagXrefDao extends BaseDao<AggSpotTagXref> {
	
	public void makeSureXref(AggregatedSpot aggSpot, Tag tag);
	
	public List<AggSpotTagXref> getByTagIds(List<Long> tagIds);
	
	public Pagination getAggSpotsForTag(Long tagId, int no, int size);
}
