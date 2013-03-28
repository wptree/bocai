package com.bocai.web.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.FollowingType;
import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.User;
import com.bocai.manager.CommentManager;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.SpotGuideManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.CommentVO;
import com.bocai.vo.SpotVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
		@Result(name = "input", location = "profile.jsp"),
		@Result(name = "userpanel", location = "userpanel.jsp"),
		@Result(name = "user_list", location = "userlist.jsp"),
		@Result(name = "ajax_save_result", type = "json", params = {
				"includeProperties", "saveResult,returnMsg" }),
		@Result(name = "ajax_get_data_page", type = "json", params = {
				"includeProperties",
				"sessionUserId,userLogin,dataPage.*,followShip.*",
				"excludeProperties", ".*hibernateLazyInitializer" })

})
public class UserProfileAction extends BaseAction<User> {

	@Autowired
	private UserManager userManager;
	@Autowired
	private DishManager dishManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private SpotGuideManager spotGuideManager;
	@Autowired
	private CommentManager commentManager;
	@Autowired
	private TagManager tagManager;

	private User model;
	private Long id;
	private Pagination dataPage;
	private static final int PAGE_SIZE = 10;
	private static final int CMT_PAGE_SIZE = 20;
	private Map<String, String> followShip;
	private boolean userLogin;
	private Integer pageAt = 0;
	private Integer active;
	private String pageType;

	private String returnMsg;
	private boolean saveResult;

	// below three fields work for "changeFollow"
	private String targetId;
	private String targetType;

	@Action("profile")
	public String input() {
		if (id == null && id == 0L) {
			throw new RuntimeException("Invalid parameter id:" + id);
		}
		User user = userManager.getById(id);
		request.setAttribute("user", user);

		return INPUT;
	}

	 @Action("user_panel")
	 public String userPanel(){
	 //获取user关注的人
	 Pagination followUserPage = userManager.getFollowed(id,
	 FollowingType.USER, 8, 0);
	 request.setAttribute("followUserPage", followUserPage);
	 //获取user关注的店
	 Pagination followPlacePage = userManager.getFollowed(id,
	 FollowingType.PLACE, 8, 0);
	 request.setAttribute("followPlacePage", followPlacePage);
	 //获取user的粉丝
	 Pagination followByUserPage = userManager.getUserFollow(id,8, 0);
	 request.setAttribute("followByUserPage", followByUserPage);
	
	 return "userpanel";
	 }
	//
	// public String userList(){
	// User user = userManager.getById(id);
	// request.setAttribute("user", user);
	//
	// //获取user关注的人
	// Pagination followUserPage = userManager.getFollowed(user.getId(),
	// FollowingType.USER, 4, 0);
	// request.setAttribute("followUserPage", followUserPage);
	// //获取user关注的店
	// Pagination followPlacePage = userManager.getFollowed(user.getId(),
	// FollowingType.PLACE, 4, 0);
	// request.setAttribute("followPlacePage", followPlacePage);
	// //获取user的粉丝
	// Pagination followByUserPage = userManager.getUserFollow(user.getId(), 4,
	// 0);
	// request.setAttribute("followByUserPage", followByUserPage);
	//
	// request.setAttribute("active", getActive());
	//
	// return "user_list";
	// }

	// private void constructFollowshipMap(List list){
	// followShip = new HashMap<String,String>();
	// if(getSessionUser()!=null){
	// setUserLogin(true);
	// for(int i = 0; i< list.size(); i++){
	// User u = (User) list.get(i);
	// String temp = userManager.isFollowed(getSessionUser().getId(), u)?
	// "取消关注":"+关注";
	// followShip.put(u.getId().toString(), temp);
	// }
	// }else{
	// setUserLogin(false);
	// for(int i = 0; i< list.size(); i++){
	// User u = (User) list.get(i);
	// followShip.put(u.getId().toString(), "+关注");
	// }
	// }
	// }

	// /**
	// * 获取粉丝分页数据
	// * @return
	// */
	// public String fanPage(){
	// dataPage = userManager.getUserFollow(id, 20, pageAt);
	// List fanlist = dataPage.getList();
	// constructFollowshipMap(fanlist);
	// dataPage.setList(toUserVoList(fanlist));
	//
	// return "ajax_get_data_page";
	// }

	// /**
	// * 获取user所关注的所有的人的分页数据
	// * @return
	// */
	// public String followToUserPage(){
	// Pagination p = userManager.getFollowed(id, FollowingType.USER, 20,
	// pageAt);
	// dataPage = (Pagination) p.clone();
	// List userlist = dataPage.getList();
	// constructFollowshipMap(userlist);
	// dataPage.setList(toUserVoList(userlist));
	//
	// return "ajax_get_data_page";
	// }

	public String spotPage() {
		Pagination p = null;
		if ("spot".equals(getPageType().toLowerCase())) {
			p = userManager.getSpotsByUser(id, PAGE_SIZE, getPageAt());
		} else if ("nom".equals(getPageType().toLowerCase())) {
			p = userManager.getUserNomSpots(id, PAGE_SIZE, getPageAt());
		} else if ("want".equals(getPageType().toLowerCase())) {
			p = userManager.getUserWantedSpots(id, PAGE_SIZE, getPageAt());
		} else if ("comment".equals(getPageType().toLowerCase())) {
			p = commentManager.getUserCommentPage(id, CMT_PAGE_SIZE,
					getPageAt());
		}
		dataPage = (Pagination) p.clone();

		if ("comment".equals(getPageType().toLowerCase())) {
			if (dataPage.getList() != null && !dataPage.getList().isEmpty()) {
				List commentList = dataPage.getList();
				List<CommentVO> voList = new ArrayList<CommentVO>();
				for (int i = 0; i < commentList.size(); i++) {
					Object obj = commentList.get(i);
					Comment comment = null;
					if (obj instanceof Comment) {
						comment = (Comment) commentList.get(i);
					}
					voList.add(CommentVO.fromComment(comment));
				}
				dataPage.setList(voList);
			}
		} else {
			if (dataPage.getList() != null && !dataPage.getList().isEmpty()) {
				List spotlist = dataPage.getList();
				List<SpotVo> voList = new ArrayList<SpotVo>();
				for (int i = 0; i < spotlist.size(); i++) {
					Object obj = spotlist.get(i);
					Spot spot = null;
					if (obj instanceof Spot) {
						spot = (Spot) spotlist.get(i);
					} else if (obj instanceof AggregatedSpot) {
						AggregatedSpot aggspot = (AggregatedSpot) spotlist
								.get(i);
						spot = aggspot.getLastSpot();
					}
					voList.add(SpotVo.fromSpotBase(spot));
				}
				dataPage.setList(voList);
			}
		}

		return "ajax_get_data_page";
	}

	// private List<UserVo> toUserVoList(List<User> list){
	// List<UserVo> voList = new ArrayList<UserVo>();
	// for(int i = 0; i<list.size();i++){
	// User u = (User) list.get(i);
	// voList.add(UserVo.fromUserBasic(u));
	// }
	// return voList;
	// }

	@Action("changeFollow")
	public String changeFollow() {
		try {
			Object target = null;
			if (FollowingType.USER.toString().equals(
					getTargetType().toUpperCase())) {
				target = userManager.getById(Long.parseLong(getTargetId()));
				if (((User) target).getId().equals(getModel().getId())) {
					setReturnMsg("您不能关注自己^_^");
					setSaveResult(false);
					return "ajax_save_result";
				}
			} else if (FollowingType.DISH.toString().equals(
					getTargetType().toUpperCase())) {
				target = dishManager.getById(Long.parseLong(getTargetId()));
			} else if (FollowingType.PLACE.toString().equals(
					getTargetType().toUpperCase())) {
				target = placeManager.getById(Long.parseLong(getTargetId()));
			} else if (FollowingType.GUIDE.toString().equals(
					getTargetType().toUpperCase())) {
				target = tagManager.getById(Long.parseLong(getTargetId()));
			} else if (FollowingType.SPOT_GUIDE.toString().equals(
					getTargetType().toUpperCase())) {
				target = spotGuideManager
						.getById(Long.parseLong(getTargetId()));
			} else {
				setSaveResult(false);
				setReturnMsg("Ta的状况未知，不能关注");
			}

			if (userManager.isFollowed(getModel().getId(), target)) {
				userManager.removeFollow(getModel(), target);
				setReturnMsg("+关注");
				setSaveResult(true);
			} else {
				userManager.makeFollow(getModel(), target);
				setReturnMsg("取消关注");
				setSaveResult(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSaveResult(false);
			setReturnMsg("出错了，稍后再试...(>_<)");
		}

		return "ajax_save_result";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			model = userManager.getById(id);
		} else {
			model = getSessionUser();
		}
	}

	@Override
	public User getModel() {
		return model;
	}

	public void setModel(User user) {
		this.model = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setSaveResult(boolean saveResult) {
		this.saveResult = saveResult;
	}

	public boolean isSaveResult() {
		return saveResult;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setDataPage(Pagination dataPage) {
		this.dataPage = dataPage;
	}

	public Pagination getDataPage() {
		return dataPage;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPageType() {
		if (pageType == null) {
			return "spot";
		}
		return pageType;
	}

	public void setPageAt(Integer pageAt) {
		this.pageAt = pageAt;
	}

	public Integer getPageAt() {
		if (pageAt == null) {
			return 0;
		}
		return pageAt;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setFollowShip(Map<String, String> followShip) {
		this.followShip = followShip;
	}

	public Map<String, String> getFollowShip() {
		return followShip;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getActive() {
		return active;
	}

}
