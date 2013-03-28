package com.bocai.dao.hibernate;

import java.util.List;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.DialogDao;
import com.bocai.dao.domain.Dialog;

@Repository("dialogDao")
public class DialogDaoImpl extends HibernateBaseDao<Dialog, Long> implements DialogDao {

	@Override
	public Dialog findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialog findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialog deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialog save(Dialog bean) {
		if(bean.getId()!=null){
			getSession().merge(bean);
		}else{
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public List<Dialog> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<Dialog> getEntityClass() {
		return Dialog.class;
	}

	@Override
	public Pagination getDialogDetail(Long sourceId, Long targetId, int at, int size) {
		String hql = HQLContainer.getHql("get.dialog.detail");
		Finder f = Finder.create(hql);
		f.setParam("sourceId", sourceId);
		f.setParam("targetId", targetId);
		Pagination p = find(f,at,size);
		return p;
	}

	@Override
	public Pagination getDialogsOfPeople(Long userId, int at, int size) {
		String hql = HQLContainer.getHql("get.dialogs.of.people");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		Pagination p = find(f,at,size);
		return p;
	}
	
	@Override
	public Pagination getUnreadDialogsOfPeople(Long userId, int at, int size){
		String hql = HQLContainer.getHql("get.unread.dialogs.of.people");
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		Pagination p = find(f,at,size);
		return p;
	}
	
	@Override
	public void updatePlAsReaded(List<Long> ids){
		String hql = HQLContainer.getHql("update.private.letter.as.readed");
		this.getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
	}
}
