package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.SpotGuide;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.SpotGuideManager;

@Service("spotGuideManager")
@Transactional
public class SpotGuideManagerImpl extends ManagerHelper implements
		SpotGuideManager {

	@Override
	public Long save(SpotGuide bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(SpotGuide bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SpotGuide bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SpotGuide> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpotGuide getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SpotGuide getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpotGuide> getHotestSpotGuides() {
		// TODO Auto-generated method stub
		return null;
	}

}
