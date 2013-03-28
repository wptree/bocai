package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.hibernate.Updater;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserThirdpartyManager;

@Service
@Transactional
public class UserThirdpartyManagerImpl extends ManagerHelper implements UserThirdpartyManager{
	
	@Override
	public Long save(UserThirdparty user){
		if(user!=null){
			userThirdpartyDao.save(user);
			return user.getId();
		}else{
			return null;
		}
		
	}
	
	@Override
	public UserThirdparty getUserById(long id) {
		return userThirdpartyDao.findById(id);
	}

	@Override
	public Long update(UserThirdparty bean) {
		userThirdpartyDao.updateByUpdater(new Updater<UserThirdparty>(bean));
		return bean.getId();
	}

	@Override
	public void delete(UserThirdparty bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserThirdparty> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserThirdparty getUniqueByProperty(String prop, Object value) {
		return userThirdpartyDao.findUniqueBy(prop, value);
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserThirdparty getById(Long id) {
		return userThirdpartyDao.findById(id);
	}	
	
	
}
