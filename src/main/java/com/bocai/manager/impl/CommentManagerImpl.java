package com.bocai.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Reply;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.manager.CommentManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.web.util.ContentUtil;

@Service("commentManager")
@Transactional
public class CommentManagerImpl extends ManagerHelper implements CommentManager {

	@Override
	public Long save(Comment bean, String contextPath) {
		
		List<String> tagStrs = ContentUtil.parseTag(bean.getContent());
		User user = userDao.findById(bean.getCreatedBy().getId());
		if(tagStrs!=null){
			AggregatedSpot aggSpot = ((Spot) bean.getPost()).getAggSpot();
			List<Tag> tags = tagDao.getTagsByNames(tagStrs);
			// save new tags
			List<String> newTags = new ArrayList<String>(tagStrs);
			if(tags!=null){
				for(Tag tag : tags){
					newTags.remove(tag.getTagName());
				}
			}
			if(user.getCreatedTags()==null){
				user.setCreatedTags(new HashSet<Tag>());
			}
			for(String newTag: newTags){
				Tag tag = new Tag();
				tag.setTagName(newTag);
				tag.setTagFrom("comment");
				tag.setCreatedAt(new Date());
				tag.setUpdatedAt(tag.getCreatedAt());
				tags.add(tagDao.save(tag));
			}
			// create xref between aggspot and tag
			Map<String, Long> ts = new HashMap<String, Long>();
			for (Tag tag: tags){
				aggSpotTagXrefDao.makeSureXref(aggSpot, tag);
				tag.setUpdatedAt(new Date());
				if(tag.getCreators()==null){
					tag.setCreators(new HashSet<User>());
				}
				tag.getCreators().add(user);
				user.getCreatedTags().add(tag);
				tagDao.updateByUpdater(new Updater<Tag>(tag));
				ts.put(tag.getTagName(), tag.getId());
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("content", bean.getContent());
			params.put("tags", ts);
			params.put("contextPath", contextPath);
			
			bean.setContent(ContentUtil.htmlTag(params));
		}
		// save comment bean
		if(bean instanceof Reply){
			replyDao.save((Reply)bean);
		}else{
			commentDao.save(bean);
		}
		
		// increase spot comment count;
		Spot spot = (Spot) bean.getPost();
		Integer commentNum = spot.getCommentNum();
		spot.setCommentNum(commentNum==null?1:++commentNum);
		spotDao.updateByUpdater(new Updater<Spot>(spot));
		
		// increase user score
		user.setScore(user.getScore()==null? 0 : user.getScore()+ SystemConfig.getActivityScore(ActivityType.COMMENT_SUMMIT));
		userDao.updateByUpdater(new Updater<User>(user));
		
		//notify target
		notificationDao.publish(user, 
				bean.getPost().getCreatedBy().getId(), 
				ActivityType.COMMENT_SUMMIT, 
				bean.getPost(),bean);
		if(bean instanceof Reply){
			notificationDao.publish(user, 
					((Reply)bean).getReplyTo().getId(), 
					ActivityType.COMMENT_REPLY, 
					((Reply)bean).getOnComment(),bean);
		}
		
		return bean.getId();
	}

	@Override
	public Long update(Comment bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Comment bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comment getById(Long id) {
		return commentDao.findById(id);
	}
	
	@Override
	public Pagination getUserCommentPage(Long userId, int pageSize, int pageAt) {
		return userDao.getUserCommentPage(userId, pageSize, pageAt);
	}

	@Override
	public Pagination getCommentsOnSpot(Long spotId, int pageSize, int pageAt) {
		return commentDao.getCommentsOnSpot(spotId, pageSize, pageAt);
	}
	
	@Override
	public Pagination getCommentsOnPeople(Long uid, int size, int at){
		return commentDao.getCommentsOnPeople(uid, size, at);
	}

	@Override
	public Long save(Comment bean) {
		return save(bean, null);
	}
}
