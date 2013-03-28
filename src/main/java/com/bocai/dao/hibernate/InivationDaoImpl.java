package com.bocai.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.InvitationDao;
import com.bocai.dao.domain.Invitation;

@Repository("invitationDao")
public class InivationDaoImpl extends HibernateBaseDao<Invitation, Long> implements InvitationDao  {

	@Override
	public Invitation findById(Long id) {
		return super.get(id);
	}

	@Override
	public Invitation findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitation deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitation save(Invitation bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public List<Invitation> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Invitation> getEntityClass() {
		// TODO Auto-generated method stub
		return Invitation.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	public Pagination fetchInvited(Long userId, int pageSize, int pageAt) {
		String hql = HQLContainer.getHql("get.invited.user.page");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);		
		Pagination p = find(f,pageAt,pageSize);
		return p;
	}

}
