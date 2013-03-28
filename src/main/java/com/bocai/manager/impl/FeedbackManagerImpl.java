package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.Feedback;
import com.bocai.manager.FeedbackManager;
import com.bocai.manager.ManagerHelper;
@Service("feedbackManager")
@Transactional
public class FeedbackManagerImpl extends ManagerHelper implements
		FeedbackManager {

	@Override
	public Long save(Feedback bean) {
		if(bean!=null){
			Feedback fb = feedbackDao.save(bean);
			if(fb!=null){
				return fb.getId();
			}
		}
		return null;
	}

	@Override
	public Long update(Feedback bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Feedback bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Feedback> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Feedback getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
