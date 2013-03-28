package com.bocai.manager;

import java.util.List;

import com.bocai.dao.domain.SpotGuide;

public interface SpotGuideManager extends BaseManager<SpotGuide>{

	public List<SpotGuide> getHotestSpotGuides();
	
}
