package com.bocai.dao;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Tag;

public interface TagDao extends BaseDao<Tag>{
	
	public List<Tag> getTagsByNames(List<String> names);
	
	public Pagination getLatestTagsWithAggSpotXrefs(int no, int size);

	public Pagination getFollowers(Long tagId, int pageNo, int pageAt);
	
	public Pagination getCreators(Long tagId, int pageNo, int pageAt);
}
