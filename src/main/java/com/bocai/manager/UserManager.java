package com.bocai.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bocai.common.constant.ActivityType;
import com.bocai.common.constant.FollowingType;
import com.bocai.common.constant.UserTaskStatus;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Statistical;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.exception.FollowException;
import com.bocai.exception.SpotException;
import com.bocai.vo.SignUpWizardVo;

public interface UserManager extends BaseManager<User>{
	
	public User login(String email, String pwd);
	
	public User reload(User user);
			
	public User exist(String uniqueFieldValue, boolean isThirdparty);
	
	public User registerUser(User userVO);
		
	public void endThirdPartySession(User user);
	
	public void updateDishAlbum(Spot spot);
	
	/**
	 * Check if user already wanted a @Spot 
	 * @param user
	 * @param target can be @Spot
	 * @return
	 */
	public boolean isWantedByUser(Long userId,Long aggSpotId);
	
	/**
	 * User want a spot
	 * @param user
	 * @param spot
	 * @return void
	 */
	public void addWantedSpot(Long userId,Long aggSpotId) throws SpotException;
	
	/**
	 * Check is user already nommed a @Spot
	 * @param user
	 * @param spot
	 * @return
	 */
	public boolean isNommedByUser(Long userId,Long aggSpotId);
	/**
	 * User nommed a spot
	 * @param user
	 * @param spot
	 */
	public void addNomedSpot(Long userId,Long aggSpotId) throws SpotException;
		
	/**
	 * User follow to @param
	 * @param target  Could be one of @User|@Dish|@Place|@SpotGuide
	 * Implement following to an user firstly. 
	 * @return true when following to the target successfully, otherwise return false.
	 */
	public void makeFollow(User user,Object target) throws FollowException;
	
	/**
	 * Remove follow to @param
	 * @param user
	 * @param target
	 * @throws FollowException
	 */
	public void removeFollow(User user,Object target) throws FollowException;
	
	/**
	 * Check the current user if already following to the target.  
	 * @param user
	 * @param target can be @User, @Dish, @Place,@SpotGuide
	 * @return true if already following to.
	 */
	public boolean isFollowed(Long userId, Object target);
	
	/**
	 * Get the users which the @param user following to
	 * @param user
	 * @param targetType can be "user"|"place"|"spotGuide"
	 * @param pageAt
	 * @return 
	 */
	public Pagination getFollowed(Long userId, FollowingType type, int pageNo, int pageAt);
	
	/**
	 * Get the @param user's followers
	 * @param user
	 * @return
	 */
	public Pagination getUserFollow(Long userId, int pageNo, int pageAt);
	
	/**
	 * Get a user's spot
	 * @param userid
	 * @return
	 */
	public Pagination getSpotsByUser(Long userid, int pageNo, int pageAt);
	
	/**
	 * Get user's nommed aggSpots
	 * @param userid
	 * @param pageNo
	 * @param pageAt
	 * @return
	 */
	public Pagination getUserNomSpots(Long userid, int pageNo, int pageAt);
	
	/**
	 * Get user's wanted aggSpots
	 * @param userid
	 * @param pageNo
	 * @param pageAt
	 * @return
	 */
	public Pagination getUserWantedSpots(Long userid, int pageNo, int pageAt);
	
	public List<User> getBestUsers();
	
	public List<User> getLatestUsers();

	/**
	 * Check if user already wanted a @Spot 
	 * @param user
	 * @param target can be @Spot
	 * @return
	 */
	public boolean isGoodCountedByUser(Long userId, Long spotId);
	
	/**
	 * User want a spot
	 * @param user
	 * @param spot
	 * @return void
	 */
	public void goodCountSpot(User user, Spot spot) throws SpotException;
	
	/**
	 * Check if user already wanted a @Spot 
	 * @param user
	 * @param target can be @Spot
	 * @return
	 */
	public boolean isGreatCountedByUser(Long userId, Long spotId);
	
	/**
	 * User want a spot
	 * @param user
	 * @param spot
	 * @return void
	 */
	public void greatCountSpot(User user, Spot spot) throws SpotException;
	
	
	public Pagination getBestUsers(int pageNo, int pageAt);
	
	public Pagination getLatestUsers(int pageNo, int pageAt);
	
	public void weibo2Sina(Spot spot, ActivityType type);
	
	public Statistical getStatistical(User user);
	
	public void updateFeedFetchTimeline(User user);
	
	public void updateNotificationFetchTimeline(User user);
	
	public Pagination getWanttedUsers(Long aggSpotId, int at, int size);
	
	public Pagination getNomedUsers(Long aggSpotId, int at, int size);
	
	public Pagination getSpotsOnFollowingPeopleByUserId(Long userId, int at, int size);
	
	public Pagination getSpotsOnFollowingPlaceByUserId(Long userId, int at, int size);
	
	public Long getFollowTosCount(Long userId);
	
	public Long getFollowingPlacesCount(Long userId);
	
	public List<String> retrieveAllUserNames();
	
	public void resetMyPredefinedTags(Long userId, List<Long> tagIds);
	
	public void addTags(Long userId, List<Long> tagIds);
	
	public Set<Long> loadMyTags(Long userId);
	
	public boolean isFinishTask(Long userId, String taskCode);
	
	public void startTask(Long userId, String taskCode, boolean override);
	
	public void endTask(Long userId, String taskCode, UserTaskStatus status);
	
	public Map<String, UserTaskStatus> getTaskStatusMap(Long userId);
	
	public Pagination getSuggestedUsers(Long userId);
	
	public void saveSignUpWizardSetting(Long userId, SignUpWizardVo wizardVo);
}
