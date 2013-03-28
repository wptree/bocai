package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.Correction;
import com.bocai.manager.CorrectionManager;
import com.bocai.manager.ManagerHelper;

@Service("correctionManager")
@Transactional
public class CorrectionManagerImpl extends ManagerHelper implements
		CorrectionManager {

	@Override
	public Long save(Correction bean) {
		if(bean!=null){
			Correction cr = correctionDao.save(bean);
			if(cr!=null){
				return cr.getId();
			}
		}
		return null;
	}

	@Override
	public Long update(Correction bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Correction bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Correction> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Correction getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Correction getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
