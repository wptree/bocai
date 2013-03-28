package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Invitation;
import com.bocai.manager.InvitationManager;
import com.bocai.manager.ManagerHelper;

@Service("invitationManager")
@Transactional
public class InvitationManagerImpl extends ManagerHelper implements InvitationManager{

	@Override
	public Long save(Invitation bean) {
		if(bean!=null){
			invitationDao.save(bean);
			return bean.getId();
		}else{
			return null;
		}
	}

	@Override
	public Long update(Invitation bean) {
		invitationDao.updateByUpdater(new Updater<Invitation>(bean));
		return bean.getId();
	}

	@Override
	public void delete(Invitation bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Invitation> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitation getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Invitation getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination fetchInvited(Long userId, int pageSize, int pageAt) {
		return invitationDao.fetchInvited(userId, pageSize, pageAt);
	}

}
