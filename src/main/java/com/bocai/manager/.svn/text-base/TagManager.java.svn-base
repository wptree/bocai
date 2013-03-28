package com.bocai.manager;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Tag;
import com.bocai.vo.PinVo;
import com.bocai.vo.Token;

public interface TagManager extends BaseManager<Tag>{
	
	public void initiazation();
	
	public List<Token> getTagMatchedByName(String queryStr);
	
	public List<PinVo> getLatestTagPins(int no, int size);
	
	public Pagination getAggSpotsForTag(Long tagId, int no, int size);
	
	public Pagination getFollowers(Long tagId, int pageNo, int pageAt);
	
	public Pagination getCreators(Long tagId, int pageNo, int pageAt);
}
