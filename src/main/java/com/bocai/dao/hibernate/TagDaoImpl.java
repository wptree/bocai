package com.bocai.dao.hibernate;

import java.util.List;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.TagDao;
import com.bocai.dao.domain.Tag;

@Repository("tagDao")
public class TagDaoImpl extends HibernateBaseDao<Tag, Long> implements TagDao{

	@Override
	public Tag findById(Long id) {
		return super.get(id);
	}

	@Override
	public Tag findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public Tag deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag save(Tag bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public List<Tag> findBy(String property, Object value) {
		return super.findByProperty(property, value);
	}

	@Override
	protected Class<Tag> getEntityClass() {
		return Tag.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values){
		return super.find(queryString, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getTagsByNames(List<String> names) {
		String hql = HQLContainer.getHql("get.tags.by.names");
		return getSession().createQuery(hql).setParameterList("names", names).list();
	}

	@Override
	public Pagination getLatestTagsWithAggSpotXrefs(int no, int size) {
		return find(Finder.create(HQLContainer.getHql("get.latest.tags.with.aggspotxrefs")),no,size);
	}

	@Override
	public Pagination getFollowers(Long tagId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.follower.by.tag");
		Finder f = Finder.create(hql);
		f.setParam("tagId", tagId, new LongType());
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

	@Override
	public Pagination getCreators(Long tagId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.creator.for.tag");
		Finder f = Finder.create(hql);
		f.setParam("tagId", tagId, new LongType());
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}

}
