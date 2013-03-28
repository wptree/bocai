package com.bocai.dao.hibernate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.bocai.common.hibernate.Finder;
import com.bocai.common.hibernate.HibernateBaseDao;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.CommentDao;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;

@Repository("commentDao")
public class CommentDaoImpl extends HibernateBaseDao<Comment, Long> implements CommentDao {

	@Override
	public Comment findById(Long id) {
		return super.get(id);
	}

	@Override
	public Comment findUniqueBy(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment save(Comment bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Comment updateByUpdater(Updater<Comment> updater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findBy(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Comment> getEntityClass() {
		return Comment.class;
	}

	@Override
	public Pagination getCommentsOnSpot(Long spotId, int pageNo, int pageAt) {
		String hql = HQLContainer.getHql("get.comments.by.post");
		Finder f = Finder.create(hql);
		f.setParam("postId", spotId);
		
		Pagination p = find(f,pageAt,pageNo);
		return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getCommentsOnPeople(Long uid, int size, int at){
		String hql = HQLContainer.getHql("get.comments.on.people");
		Finder f = Finder.create(hql);
		f.setParam("userId", uid);
		
		Pagination p = find(f,at,size);
		Map<Long, Set<Comment>> spotMap = null;
		if(p.getList()!=null && !p.getList().isEmpty()){
			spotMap = new HashMap<Long, Set<Comment>>();
			List<Comment> comments = (List<Comment>) p.getList();
			for (Comment cmt : comments){
				if(cmt!=null && cmt.getPost()!=null){
					Set<Comment> cmts = spotMap.get(cmt.getPost().getId());
					if(cmts==null){
						cmts = new HashSet<Comment>();
						spotMap.put(cmt.getPost().getId(), cmts);
					}
					cmts.add(cmt);
				}
			}
		}
		if(spotMap != null && !spotMap.isEmpty()){
			hql = HQLContainer.getHql("get.spot.of.comment");
			List<Spot> spots = (List<Spot>)getSession()
				.createQuery(hql).setParameterList("ids", spotMap.keySet()).list();
			if(spots!=null && !spots.isEmpty()){
				for (Spot spot : spots){
					Set<Comment> cmts = spotMap.get(spot.getId());
					if(cmts!=null){
						for(Comment cmt: cmts){
							if(cmt!=null){
								cmt.setPost(spot);
							}
						}
					}
				}
			}
		}
		return p;
	}

}
