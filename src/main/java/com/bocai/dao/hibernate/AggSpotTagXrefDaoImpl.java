package com.bocai.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.AggSpotTagXrefDao;
import com.bocai.dao.domain.AggSpotTagXref;
import com.bocai.dao.domain.AggSpotTagXrefCompKey;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Tag;

@Repository("aggSpotTagXrefDao")
public class AggSpotTagXrefDaoImpl extends HibernateBaseDao<AggSpotTagXref, AggSpotTagXrefCompKey> implements
		AggSpotTagXrefDao {
	
	public AggSpotTagXref load(AggSpotTagXrefCompKey id){
		return super.get(id);
	}

	@Override
	public AggSpotTagXref findById(Long id) {
		return null;
	}

	@Override
	public AggSpotTagXref findUniqueBy(String prop, Object value) {
		return super.findUniqueByProperty(prop, value);
	}

	@Override
	public AggSpotTagXref deleteById(Long id) {
		return null;
	}

	@Override
	public AggSpotTagXref save(AggSpotTagXref bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}

	@Override
	public AggSpotTagXref updateByUpdater(Updater<AggSpotTagXref> updater) {
		return super.updateByUpdater(updater);
	}

	@Override
	public List<AggSpotTagXref> findBy(String property, Object value) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AggSpotTagXref> find(String queryString, Object... values) {
		return super.find(queryString, values);
	}

	@Override
	protected Class<AggSpotTagXref> getEntityClass() {
		return AggSpotTagXref.class;
	}

	@Override
	public void makeSureXref(AggregatedSpot aggSpot, Tag tag) {
		AggSpotTagXrefCompKey key = new AggSpotTagXrefCompKey(
				tag, aggSpot);
		AggSpotTagXref xref = load(key);
		if(xref == null){
			xref = new AggSpotTagXref();
			xref.setId(key);
			xref.setStatus(1L);
			xref.setCount(1);
			xref.setCreatedAt(new Date());
			save(xref);
		}else{
			int count = xref.getCount()!=null ?  xref.getCount().intValue() : 0;
			xref.setCount(++count);
			xref.setUpdatedAt(new Date());
			updateByUpdater(new Updater<AggSpotTagXref>(xref));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AggSpotTagXref> getByTagIds(List<Long> tagIds) {
		return getSession().createQuery(HQLContainer.getHql("get.agg.spot.tag.xrefs.by.tag.ids"))
			.setParameterList("tagIds", tagIds).list();
	}

	@Override
	public Pagination getAggSpotsForTag(Long tagId, int no, int size) {
		String hql = HQLContainer.getHql("get.agg.spot.tag.xrefs.by.tag.id");
		hql = hql.replace("ORDER_BY_TERM", "xref.updatedAt");
		Finder f = Finder.create(hql);
		f.setParam("tagId", tagId, new LongType());
		Pagination p = find(f, no, size);
		return p;
	}

}
