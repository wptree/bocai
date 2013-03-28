/*
 * Licensed to the bocai007.com under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The bocai.com licenses this file to You under the bocai.com License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.bocai007.com/licenses/LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocai.manager.impl;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.constant.FollowingType;
import com.bocai.common.constant.UserStatus;
import com.bocai.common.constant.UserTaskCode;
import com.bocai.common.constant.UserTaskStatus;
import com.bocai.common.hibernate.Updater;
import com.bocai.common.page.Pagination;
import com.bocai.common.security.ActionRoleType;
import com.bocai.config.HQLContainer;
import com.bocai.config.SystemConfig;
import com.bocai.config.UserConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.SpotGuide;
import com.bocai.dao.domain.Statistical;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserTask;
import com.bocai.dao.domain.UserTaskCompKey;
import com.bocai.dao.domain.UserTaskXref;
import com.bocai.dao.domain.UserThirdparty;
import com.bocai.exception.FollowException;
import com.bocai.exception.ManagerException;
import com.bocai.exception.SpotException;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserManager;
import com.bocai.service.SinaService;
import com.bocai.util.CopyUtil;
import com.bocai.vo.LocationVo;
import com.bocai.vo.SignUpWizardVo;
import com.bocai.vo.UserVo;
import com.bocai.web.action.user.ThirdpartyAction;
import com.googlecode.ehcache.annotations.Cacheable;

/**
 * <p>
 * Manager class to operate User domain, it's contains the transaction
 * operations on business unit.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 *          Revision History DATE | REVISER | REASON
 *          --------------------------------------- 2011.03.20 | Shi,Tao |
 *          Creation. 2011.03.22 | Shi,Tao | Revise saveUser method.
 */
@Service("userManager")
@Transactional
public class UserManagerImpl extends ManagerHelper implements UserManager {

	@Resource
	private SinaService sinaService;

	public User login(String email, String pwd) {
		User user = userDao.findUniqueBy("firstEmail", email);
		if (user == null) {
			throw new ManagerException(email + "不存在，请先注册！");
		}
		if (pwd.equals(user.getPassword())) {
			user.setLoginTimes(user.getLoginTimes() + 1);
			user.setLastLoginTime(new Date());
			userDao.updateByUpdater(new Updater<User>(user));
			return user;
		} else {
			throw new ManagerException("用户名与密码不匹配，请重新登录。");
		}
	}

	public Long save(User user) {
		if (user != null) {
			userDao.save(user);
			return user.getId();
		} else {
			return null;
		}

	}

	public Long update(User user) {
		userDao.updateByUpdater(new Updater<User>(user));
		return user.getId();
	}

	public User reload(User user) {
		return userDao.findById(user.getId());
	}

	public User getUserByEmail(String email) {
		return userDao.findUniqueBy("firstEmail", email);
	}

	/**
	 * For User, uniqueField could be "firstEmail", for UserThirdparty,
	 * uniqueField could be "outSourceUserId"
	 * 
	 * @param uniqueField
	 * @param isThirdparty
	 * @return
	 */
	public User exist(String uniqueFieldValue, boolean isThirdparty) {
		User entity = null;
		try {
			if (uniqueFieldValue != null) {
				if (isThirdparty) {
					UserThirdparty thrdpu = userThirdpartyDao.findUniqueBy(
							"outSourceUserId",
							URLDecoder.decode(uniqueFieldValue, "utf-8"));
					if (thrdpu != null) {
						entity = thrdpu.getUser();
					}
				} else {
					// firstEmail
					entity = userDao.findUniqueBy("firstEmail",
							URLDecoder.decode(uniqueFieldValue, "utf-8"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public User registerUser(User userVO) {
		User user = new User();
		user.setSource(UserConfig.USER_SOURCE_BOCAI);
		user.setFirstEmail(userVO.getFirstEmail());
		user.setName(userVO.getFirstEmail().split("@")[0]);
		user.setPassword(userVO.getPassword());
		user.setStatus(UserStatus.MAIL_UNVALIDATED);
		user.setActionRole(ActionRoleType.USER.name());
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setAvatar(SystemConfig.getUserDefaultAvatar());
		user.setSelfDescription("这个家伙很懒什么也没留下");
		user.setScore(SystemConfig.getBokeBaseScore());
		user.setFeedFetchTimeline(System.currentTimeMillis());
		user.setNotificatonFetchTimeline(System.currentTimeMillis());
		userDao.save(user);
		return user;
	}

	public void deleteUser(User user) {
		userDao.deleteById(user.getId());
	}

	public void endThirdPartySession(User user) {
		user = getById(user.getId());
		Set<UserThirdparty> set = user.getThirdpartyAccounts();
		if (set != null && set.size() > 0) {
			for (UserThirdparty thirdp : set) {
				if (UserConfig.USER_SOURCE_SINA.equals(thirdp.getSource())) {
					sinaService.endSession(thirdp);
					;
				}
			}
		}
	}

	@Override
	public void delete(User bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getByProperty(String prop, Object value) {
		return null;
	}

	@Override
	public User getUniqueByProperty(String prop, Object value) {
		if (prop != null && !"".equals(prop)) {
			return userDao.findUniqueBy(prop, value);
		}
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateDishAlbum(Spot spot) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getById(Long id) {
		if (id == null || id == 0L) {
			throw new ManagerException("Invalid parameter id:" + id);
		}
		return userDao.findById(id);
	}

	@Override
	public void addWantedSpot(Long userId,Long aggSpotId)
			throws SpotException {
		try {
			User user = userDao.findById(userId);
			AggregatedSpot aggSpot = aggSpotDao.findById(aggSpotId);
			user.getWantedAggSpots().add(aggSpot);
			user.setScore(user.getScore()
					+ SystemConfig.getActivityScore(ActivityType.SPOT_WANTED));

			aggSpot.getWantedBys().add(user);
			int wantedNum = aggSpot.getWantedNum();
			wantedNum++;
			aggSpot.setWantedNum(wantedNum);
			userDao.updateByUpdater(new Updater<User>(user));
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggSpot));

			// generate feed
			// feedDao.publish(user, ActivityType.SPOT_WANTED, aggSpot);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SpotException(e.getMessage());
		}
	}

	@Override
	public void addNomedSpot(Long userId,Long aggSpotId)
			throws SpotException {
		try {
			User user = userDao.findById(userId);
			AggregatedSpot aggSpot = aggSpotDao.findById(aggSpotId);
			user.getNomAggSpots().add(aggSpot);
			user.setScore(user.getScore()
					+ SystemConfig.getActivityScore(ActivityType.SPOT_NOMMED));

			aggSpot.getNomBys().add(user);
			int nomedNum = aggSpot.getNomNum();
			nomedNum++;
			aggSpot.setNomNum(nomedNum);
			userDao.updateByUpdater(new Updater<User>(user));
			aggSpotDao.updateByUpdater(new Updater<AggregatedSpot>(aggSpot));

			// generate feed
			// feedDao.publish(user, ActivityType.SPOT_NOMMED, aggSpot);
		} catch (Exception e) {
			throw new SpotException(e.getMessage());
		}

	}

	/* @param target Could be one of @User|@Dish|@Place|@SpotGuide */
	@Override
	public void makeFollow(User user, Object target) throws FollowException {
		user = userDao.findById(user.getId());
		if (target instanceof User) {
			user.getFollowTos().add((User) target);
			user.setScore(user.getScore()
					+ SystemConfig
							.getActivityScore(ActivityType.USER_FOLLOW_USER));

			Integer followTo = user.getFollowToNumber();
			if (followTo == null)
				followTo = 0;
			followTo++;
			user.setFollowToNumber(followTo);

			User targetUser = (User) target;
			targetUser.getFollowedBys().add(user);
			targetUser
					.setFollowedByNumber(targetUser.getFollowedByNumber() == null ? 1
							: targetUser.getFollowedByNumber() + 1);
			userDao.updateByUpdater(new Updater<User>(targetUser));

			// generate feed
			// feedDao.publish(user,targetUser);
			// notify target
			notificationDao.publish(user, targetUser.getId(),
					ActivityType.USER_FOLLOW_USER, null);

		} else if (target instanceof Place) {
			user.getFollowingPlaces().add((Place) target);
			user.setScore(user.getScore()
					+ SystemConfig
							.getActivityScore(ActivityType.USER_FOLLOW_PLACE));
			Place place = (Place) target;
			place.getFollowers().add(user);
			place.setFollowerCount(place.getFollowerCount() == null ? 1 : place
					.getFollowerCount() + 1);
		} else if (target instanceof Dish) {
			user.getFollowingDishs().add((Dish) target);
			user.setScore(user.getScore()
					+ SystemConfig
							.getActivityScore(ActivityType.USER_FOLLOW_DISH));
			Dish dish = (Dish) target;
			dish.getFollowers().add(user);
			dish.setFollowerCount(dish.getFollowerCount() == null ? 1 : dish
					.getFollowerCount() + 1);
		} else if (target instanceof Tag) {
			user.getFollowingTags().add((Tag) target);
			user.setScore(user.getScore()
					+ SystemConfig
							.getActivityScore(ActivityType.USER_FOLLOW_TAG));
			Tag tag = (Tag) target;
			tag.getFollowers().add(user);
			tag.setFollowerCount(tag.getFollowerCount() == null ? 1 : tag
					.getFollowerCount() + 1);
		}
		try {
			userDao.updateByUpdater(new Updater<User>(user));
		} catch (Exception e) {
			throw new FollowException(e.getMessage());
		}
	}

	@Override
	public boolean isFollowed(Long userId, Object target) {
		if (target instanceof User) {
			User toUser = (User) target;
			String hql = HQLContainer.getHql("is.user.followed.by.user");
			List list = userDao.find(hql, userId, toUser.getId());
			return list.size() > 0;
		} else if (target instanceof Place) {
			Place place = (Place) target;
			String hql = HQLContainer.getHql("is.tag.followed.by.user");
			List list = userDao.find(hql, userId, place.getId());
			return list.size() > 0;
		} else if (target instanceof Tag) {
			Tag tag = (Tag) target;
			String hql = HQLContainer.getHql("is.tag.followed.by.user");
			List list = userDao.find(hql, userId, tag.getId());
			return list.size() > 0;
		}
		return false;
	}

	@Override
	public Pagination getFollowed(Long userId, FollowingType type, int pageNo,
			int pageAt) {
		return userDao.getFollowed(userId, type, pageNo, pageAt);
	}

	@Override
	public Pagination getUserFollow(Long userId, int pageNo, int pageAt) {
		return userDao.getUserFollow(userId, pageNo, pageAt);
	}

	@Override
	public Pagination getSpotsByUser(Long userid, int pageNo, int pageAt) {
		return userDao.getSpotsByUser(userid, pageNo, pageAt);
	}

	@Override
	public boolean isWantedByUser(Long userId,Long aggSpotId) {
		String hql = HQLContainer.getHql("is.wanted.by.user");

		List<User> userList = userDao.find(hql, userId);
		if (userList.size() > 0) {
			AggregatedSpot aggSpot = aggSpotDao.findById(aggSpotId);
			return userList.get(0).getWantedAggSpots().contains(aggSpot);
		}
		return false;
	}

	@Override
	public boolean isNommedByUser(Long userId,Long aggSpotId) {
		String hql = HQLContainer.getHql("is.nommed.by.user");

		List<User> userList = userDao.find(hql, userId);
		if (userList.size() > 0) {
			AggregatedSpot aggSpot = aggSpotDao.findById(aggSpotId);
			return userList.get(0).getNomAggSpots().contains(aggSpot);
		}
		return false;
	}

	@Override
	public void removeFollow(User user, Object target) throws FollowException {
		user = userDao.findById(user.getId());
		if (target instanceof User) {
			user.getFollowTos().remove((User) target);
			Integer followTo = user.getFollowToNumber();
			if (followTo == null)
				followTo = 0;
			else
				followTo--;
			user.setFollowToNumber(followTo);

			User targetUser = (User) target;
			targetUser.getFollowedBys().remove(user);
			Integer followBy = targetUser.getFollowedByNumber();
			if (followBy == null)
				followBy = 0;
			else
				followBy--;
			targetUser.setFollowedByNumber(followBy);
			userDao.updateByUpdater(new Updater<User>(targetUser));
		} else if (target instanceof Place) {
			user.getFollowingPlaces().remove((Place) target);
			Place place = (Place) target;
			place.getFollowers().remove(user);
			place.setFollowerCount(Long.valueOf(place.getFollowers().size()));
			placeDao.updateByUpdater(new Updater<Place>(place));
		} else if (target instanceof Dish) {
			user.getFollowingDishs().remove((Dish) target);
			Dish dish = (Dish) target;
			dish.getFollowers().remove(user);
			dish.setFollowerCount(Long.valueOf(dish.getFollowers().size()));
			dishDao.updateByUpdater(new Updater<Dish>(dish));
		} else if (target instanceof Tag) {
			user.getFollowingTags().remove((Tag) target);
			Tag tag = (Tag) target;
			tag.getFollowers().remove(user);
			tag.setFollowerCount(Long.valueOf(tag.getFollowers().size()));
			tagDao.updateByUpdater(new Updater<Tag>(tag));
		}
		try {
			userDao.updateByUpdater(new Updater<User>(user));
		} catch (Exception e) {
			throw new FollowException(e.getMessage());
		}

	}

	@Override
	public Pagination getUserNomSpots(Long userid, int pageNo, int pageAt) {
		return userDao.getUserNomSpots(userid, pageNo, pageAt);
	}

	@Override
	public Pagination getUserWantedSpots(Long userid, int pageNo, int pageAt) {
		return userDao.getUserWantedSpots(userid, pageNo, pageAt);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isGoodCountedByUser(Long userId, Long spotId) {
		String hql = HQLContainer.getHql("is.good.counted.by.user");
		List userList = userDao.find(hql, userId, spotId);
		if (userList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void goodCountSpot(User user, Spot spot) throws SpotException {
		user = userDao.findById(user.getId());
		user.getGoodCountSpots().add(spot);
		user.setScore(user.getScore()
				+ SystemConfig.getActivityScore(ActivityType.COMMENT_GOOD));

		spot = spotDao.findById(spot.getId());
		Integer goodCount = spot.getGoodCount() == null ? 0 : spot
				.getGoodCount();
		goodCount++;
		spot.setGoodCount(goodCount);
		spot.getGoodCountUsers().add(user);

		userDao.updateByUpdater(new Updater<User>(user));
		spotDao.updateByUpdater(new Updater<Spot>(spot));

		// generate feed
		// feedDao.publish(user, ActivityType.COMMENT_GOOD, spot);

		// notify target
		notificationDao.publish(user, spot.getCreatedBy().getId(),
				ActivityType.COMMENT_GOOD, spot.getId());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isGreatCountedByUser(Long userId, Long spotId) {
		String hql = HQLContainer.getHql("is.great.counted.by.user");
		List userList = userDao.find(hql, userId, spotId);
		if (userList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void greatCountSpot(User user, Spot spot) throws SpotException {
		user = userDao.findById(user.getId());
		user.getGreatCountSpots().add(spot);
		user.setScore(user.getScore()
				+ SystemConfig.getActivityScore(ActivityType.COMMENT_GREAT));

		spot = spotDao.findById(spot.getId());
		Integer greatCount = spot.getGoodCount() == null ? 0 : spot
				.getGoodCount();
		greatCount++;
		spot.setGreatCount(greatCount);
		spot.getGreatCountUsers().add(user);

		userDao.updateByUpdater(new Updater<User>(user));
		spotDao.updateByUpdater(new Updater<Spot>(spot));

		// generate feed
		// feedDao.publish(user, ActivityType.COMMENT_GREAT, spot);
		// notify target
		notificationDao.publish(user, spot.getCreatedBy().getId(),
				ActivityType.COMMENT_GREAT, spot.getId());
	}

	@Override
	public Pagination getBestUsers(int pageNo, int pageAt) {
		Pagination p = userDao.getBestUsers(pageNo, pageAt);
		return p;
	}

	@Override
	public Pagination getLatestUsers(int pageNo, int pageAt) {
		return userDao.getLatestUsers(pageNo, pageAt);
	}

	@Override
	public List<User> getBestUsers() {
		String hql = HQLContainer.getHql("get.best.user");
		List list = userDao.find(hql);
		List<User> userList = new LinkedList<User>();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object[] objList = (Object[]) it.next();
			User user = (User) objList[0];
			userList.add(user);
		}
		return userList;
	}

	@Override
	public List<User> getLatestUsers() {
		String hql = HQLContainer.getHql("get.latest.user");
		List<User> list = userDao.find(hql);
		return list;
	}

	@Override
	public void weibo2Sina(Spot spot, ActivityType type) {
		User user = userDao.findById(spot.getSpotedBy().getId());
		UserThirdparty thrd = user.sinaAccount();
		if (thrd != null) {
			StringBuffer imguri = new StringBuffer(SystemConfig.getSpotImgUri());
			imguri.append(spot.getId()).append("/")
					.append(SystemConfig.getImgSize2Weibo()).append(".")
					.append(spot.getImgType());
			Location lc = spot.getPlace().getLocation();
			StringBuffer content = new StringBuffer("");
			content.append("#").append(lc.getCity()).append("旅游美食#").append(" 我在@波菜网 ");
			switch (type) {
			case SPOT_UPLOAD:
				content.append("通过").append(spot.getPostBy()).append("上传分享了 ");
				content.append(spot.getDish().getName()).append("@")
					   .append(spot.getPlace().getFullName())
					   .append("：")
				       .append(spot.getDescription())
				       .append(" ");
				String link = new StringBuilder().append(SystemConfig.getDomainUrl())
												 .append("/").append(SystemConfig.getContext()).append("/")
												 .append("spot/detail_spot.bc?").append("id=").append(spot.getId())
												 .toString();
				content.append(link);
				break;
			case SPOT_WANTED:
				break;
			}
			// http://localhost/bocai/spot/31/180.JPEG
			sinaService.sendImgwithText(thrd.getToken(), thrd.getTokenSecret(),
					content.toString(), imguri.toString());
			imguri = null;
			content = null;
		}

	}

	@Override
	public Statistical getStatistical(User user) {

		return statisticalDao.findUniqueBy("userId", user.getId());

	}

	@Override
	public void updateFeedFetchTimeline(User user) {
		user = userDao.findById(user.getId());
		userDao.updateFeedFetchTimeline(user);
	}

	@Override
	public void updateNotificationFetchTimeline(User user) {
		user = userDao.findById(user.getId());
		userDao.updateNotificationFetchTimeline(user);
	}

	@Override
	@Cacheable(cacheName = "wanttedUsersOfAggSpotCache")
	public Pagination getWanttedUsers(Long aggSpotId, int at, int size) {
		return userDao.getWanttedUsers(aggSpotId, at, size);
	}

	@Override
	@Cacheable(cacheName = "nomedUsersOfAggSpotCache")
	public Pagination getNomedUsers(Long aggSpotId, int at, int size) {
		return userDao.getNomedUsers(aggSpotId, at, size);
	}

	@Override
	public Pagination getSpotsOnFollowingPeopleByUserId(Long userId, int at,
			int size) {
		return userDao.getSpotsOnFollowingPeopleByUserId(userId, at, size);
	}

	@Override
	public Pagination getSpotsOnFollowingPlaceByUserId(Long userId, int at,
			int size) {
		return userDao.getSpotsOnFollowingPlaceByUserId(userId, at, size);
	}

	@Override
	public Long getFollowTosCount(Long userId) {
		return userDao.getFollowTosCount(userId);
	}

	@Override
	public Long getFollowingPlacesCount(Long userId) {
		return userDao.getFollowingPlacesCount(userId);
	}

	@Override
	public List<String> retrieveAllUserNames() {
		return userDao.retrieveAllUserNames();
	}

	public void resetMyPredefinedTags(Long userId, List<Long> tagIds) {
		if (userId == null || userId == 0L) {
			throw new ManagerException("parameter userId is empty");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist");
		}
		Set<Tag> tags = user.getMyTags();
		if (tags == null) {
			tags = new HashSet<Tag>();
			user.setMyTags(tags);
		}
		// clean all of the predefined tags
		Set<Tag> newTags = new HashSet<Tag>();
		for (Tag tag : tags) {
			if (tag == null)
				continue;
			if (!"predefined".equals(tag.getTagFrom())) {
				newTags.add(tag);
			}
		}
		if (tagIds != null) {
			// add the checked predefined tags
			for (Long tagId : tagIds) {
				if (tagId == null || tagId == 0L) {
					continue;
				}
				Tag tag = tagDao.findById(tagId);
				if (!"predefined".equals(tag.getTagFrom())) {
					continue;
				}
				newTags.add(tag);
			}
		}
		user.setMyTags(newTags);
		userDao.updateByUpdater(new Updater<User>(user));
	}

	public void addTags(Long userId, List<Long> tagIds) {
		if (userId == null || userId == 0L) {
			throw new ManagerException("parameter userId is empty");
		}
		if (tagIds == null || tagIds.isEmpty()) {
			return;
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist");
		}
		Set<Tag> tags = user.getMyTags();
		if (tags == null) {
			tags = new HashSet<Tag>();
			user.setMyTags(tags);
		}
		for (Long tagId : tagIds) {
			if (tagId == null || tagId == 0L) {
				continue;
			}
			Tag tag = tagDao.findById(tagId);
			tags.add(tag);
		}
		userDao.updateByUpdater(new Updater<User>(user));
	}

	public Set<Long> loadMyTags(Long userId) {
		if (userId == null || userId == 0L) {
			throw new ManagerException("parameter userId is empty");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist");
		}
		Set<Tag> tags = user.getMyTags();
		Set<Long> tagIds = new HashSet<Long>();
		if (tags != null) {
			for (Tag tag : tags) {
				if (tag == null)
					continue;
				tagIds.add(tag.getId());
			}
		}
		return tagIds;
	}

	public boolean isFinishTask(Long userId, String taskCode) {
		if (userId == null || userId == 0L || taskCode == null) {
			throw new ManagerException("parameter userId is empty");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist");
		}
		Set<UserTaskXref> myTasks = user.getMyTasks();
		if (myTasks != null) {
			for (UserTaskXref myTask : myTasks) {
				if (myTask == null)
					continue;
				UserTask task = myTask.getTask();
				if (task == null)
					continue;
				if (taskCode.equals(task.getCode())
						&& myTask.getStatus() != UserTaskStatus.STARTED) {
					return true;
				}
			}
		}
		return false;
	}

	public void startTask(Long userId, String taskCode, boolean override) {
		if (userId == null || userId == 0L || taskCode == null) {
			throw new ManagerException("parameter userId is empty");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist");
		}
		UserTask task = userTaskDao.findUniqueBy("code", taskCode);
		if (task == null) {
			throw new ManagerException("user task with code[" + taskCode
					+ "] does not exist.");
		}
		UserTaskXref myTask = userTaskXrefDao.load(new UserTaskCompKey(user,
				task));
		if (myTask == null) {
			UserTaskCompKey pk = new UserTaskCompKey();
			myTask = new UserTaskXref();
			myTask.setId(pk);
			myTask.setStatus(UserTaskStatus.STARTED);
			myTask.setStartedAt(new Date());
			myTask.setTask(task);
			myTask.setUser(user);
			Set<UserTaskXref> myTasks = user.getMyTasks();
			if (myTasks == null) {
				myTasks = new HashSet<UserTaskXref>();
				user.setMyTasks(myTasks);
			}
			myTasks.add(myTask);
			userTaskXrefDao.save(myTask);
			userDao.save(user);
		} else {
			myTask.setStatus(UserTaskStatus.STARTED);
			myTask.setStartedAt(new Date());
			myTask.setEndedAt(null);
			userTaskXrefDao.save(myTask);
		}
		userDao.evictCollection("com.bocai.dao.domain.User.myTasks", userId);
	}

	public void endTask(Long userId, String taskCode, UserTaskStatus status) {
		if (userId == null || userId == 0L || taskCode == null) {
			throw new ManagerException("parameter userId is empty.");
		}
		if (status == UserTaskStatus.STARTED) {
			throw new ManagerException("parameter status " + status
					+ " is invalid.");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist.");
		}
		UserTask task = userTaskDao.findUniqueBy("code", taskCode);
		if (task == null) {
			throw new ManagerException("user task with code[" + taskCode
					+ "] does not exist.");
		}
		UserTaskXref myTask = userTaskXrefDao.load(new UserTaskCompKey(user,
				task));
		if (myTask == null)
			return;
		myTask.setEndedAt(new Date());
		myTask.setStatus(status);
		userTaskXrefDao.save(myTask);
		userDao.evictCollection("com.bocai.dao.domain.User.myTasks", userId);
	}

	public Map<String, UserTaskStatus> getTaskStatusMap(Long userId) {
		if (userId == null || userId == 0L) {
			throw new ManagerException("parameter userId is empty.");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist.");
		}
		Map<String, UserTaskStatus> result = new HashMap<String, UserTaskStatus>();
		Set<UserTaskXref> myTasks = user.getMyTasks();
		if(myTasks!=null){
			for (UserTaskXref myTask : myTasks) {
				if (myTask == null)
					continue;
				UserTask task = myTask.getTask();
				if (task == null)
					continue;
				result.put(task.getCode(), myTask.getStatus());
			}
		}
		return result;
	}

	public Pagination getSuggestedUsers(Long userId) {
		return userDao.getMostSpottedUsers(userId, 0, 14);
	}

	public void saveSignUpWizardSetting(Long userId, SignUpWizardVo wizardVo) {
		if (userId == null || userId == 0L || wizardVo == null) {
			throw new ManagerException("parameter is empty.");
		}
		User user = userDao.findById(userId);
		if (user == null) {
			throw new ManagerException("user with id[" + userId
					+ "] does not exist.");
		}
		LocationVo location = wizardVo.getLocation();
		user.setCityName(location.getCity());
		user.setSexy(wizardVo.getSexy());
		List<Long> userIds = wizardVo.getFollowingUsers();
		if (userIds != null) {
			for (Long uid : userIds) {
				User target = userDao.findById(uid);
				if (target == null)
					continue;
				makeFollow(user, target);
			}
		}
		List<Long> placeIds = wizardVo.getFollowingPlaces();
		if (placeIds != null) {
			for (Long placeId : placeIds) {
				Place target = placeDao.findById(placeId);
				if (target == null)
					continue;
				makeFollow(user, target);
			}
		}
		List<Long> dishIds = wizardVo.getFollowingDishs();
		if (dishIds != null) {
			for (Long dishId : dishIds) {
				Dish target = dishDao.findById(dishId);
				if (target == null)
					continue;
				makeFollow(user, target);
			}
		}
		update(user);
		resetMyPredefinedTags(userId, wizardVo.getTags());
		endTask(userId, UserTaskCode.SIGN_UP_WIZARD, UserTaskStatus.COMPLETED);
	}
}
