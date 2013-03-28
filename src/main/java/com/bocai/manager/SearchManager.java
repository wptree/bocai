package com.bocai.manager;

import com.bocai.common.constant.MapRequestType;
import com.bocai.common.constant.SearchKeywordType;
import com.bocai.common.constant.SearchTargetType;
import com.bocai.common.page.Pagination;
import com.bocai.vo.LatLng;

public interface SearchManager {
	
	/**
	 * Search SPOT, BOKE and PLACE by keywords
	 * @param keywords user input keywords
	 * @param type search type
	 * @param pageAt the number of the page looked up at
	 * @param pageSize the number of the records per page
	 * @return {@link Pagination} which contains the list of search result
	 */
	public Pagination search(String keywords, SearchKeywordType keywordType, SearchTargetType targetType, int pageAt, int pageSize);
	
	public Pagination searchAggspotByPlaceName(MapRequestType reqType,String placeName, Long userId, int pageAt, int pageSize, LatLng sw,LatLng ne);
	public Object[] mapSearch(String keywords, SearchKeywordType keywordType, MapRequestType reqType, int pageAt, int pageSize, LatLng sw, LatLng ne, Long userId);

}
