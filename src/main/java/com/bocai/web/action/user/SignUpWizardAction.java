package com.bocai.web.action.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.constant.UserTaskCode;
import com.bocai.common.constant.UserTaskStatus;
import com.bocai.common.page.Pagination;
import com.bocai.common.security.ActionRole;
import com.bocai.common.security.ActionRoleType;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.dao.domain.Tag;
import com.bocai.dao.domain.User;
import com.bocai.exception.NeedLoginException;
import com.bocai.manager.DishManager;
import com.bocai.manager.PlaceManager;
import com.bocai.manager.TagManager;
import com.bocai.manager.UserManager;
import com.bocai.vo.LocationVo;
import com.bocai.vo.SignUpWizardVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("user")
@Results({
	@Result(name = "basic", location = "/WEB-INF/page/wizard/basic_info.jsp"),
	@Result(name = "saveBasic", type="redirectAction", location = "wizard_teste"),
	@Result(name = "teste", location = "/WEB-INF/page/wizard/teste.jsp"),
	@Result(name = "saveTeste", type="redirectAction", location = "wizard_following"),
	@Result(name = "following", location = "/WEB-INF/page/wizard/following.jsp"),
	@Result(name = "submit", type="redirectAction", location = "mydashboard"),
	@Result(name = "delay", type = "json", params = {"includeProperties",""}
	)
})
public class SignUpWizardAction extends BaseAction<Object> {
	
	private SignUpWizardVo wizardVo;
	@Autowired
	private TagManager tagManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private DishManager dishManager;
	private Map<String, Map<Tag, Boolean>> suggestedTags ;
	private Map<User, Boolean> suggestedUsers;
	private Map<Place, Boolean> suggestedPlaces;
	private Map<Dish, Boolean> suggestedDishs;
	private String city;
	private int sexy;
	private List<Long> selectedTags;
	private List<Long> selectedUsers;
	private List<Long> selectedPlaces;
	private List<Long> selectedDishs;
	
	@SuppressWarnings("unchecked")
	@Action("wizard_basic")
	@ActionRole( ActionRoleType.USER )
	public String basic() {
		if (userManager.isFinishTask(getSessionUserId(),
				UserTaskCode.SIGN_UP_WIZARD)) {
			throw new RuntimeException("sign up wizard is already completed");
		}
		userManager.startTask(getSessionUserId(), UserTaskCode.SIGN_UP_WIZARD,
				false);
		Map<String, UserTaskStatus> taskStatusMap = (Map<String, UserTaskStatus>) session
				.get(AppConstant.SESSION_TASK_STATUS);
		if(taskStatusMap==null){
			throw new NeedLoginException("task status map should be logged in session");
		}
		taskStatusMap.put(UserTaskCode.SIGN_UP_WIZARD, UserTaskStatus.COMPLETED);
		city = getSessionUser().getCityName();
		sexy = getSessionUser().getSexy() == null ? 0 : getSessionUser().getSexy();
		return "basic";
	}
	
	@Action("wizard_save_basic")
	@ActionRole( ActionRoleType.USER )
	public String saveBasic(){
		LocationVo location = wizardVo.getLocation();
		if(location==null){
			location = new LocationVo();
			wizardVo.setLocation(location);
		}
		location.setCity(city);
		wizardVo.setSexy(sexy);
		return "saveBasic";
	}
	
	@Action("wizard_teste")
	@ActionRole( ActionRoleType.USER )
	public String teste(){
		List<Tag> tags = tagManager.getByProperty("tagFrom", "predefined");
		if(tags!=null){
			suggestedTags = new HashMap<String, Map<Tag, Boolean>>();
			Set<Long> myTagIds = userManager.loadMyTags(getSessionUserId());
			Set<Long> checkedTagIds = new HashSet<Long>();
			if(wizardVo.getTags()!=null){
				checkedTagIds.addAll(wizardVo.getTags());
			}
			if(myTagIds!=null){
				for (Long myTagId: myTagIds){
					checkedTagIds.add(myTagId);
				}
			}
			for(Tag tag: tags){
				if(tag==null) continue;
				String tagClass = tag.getTagClass();
				Map<Tag, Boolean> classTags = suggestedTags.get(tagClass);
				if(classTags==null){
					classTags = new HashMap<Tag, Boolean>();
					suggestedTags.put(tagClass, classTags);
				}
				Boolean checked = false;
				for (Long checkedTagId : checkedTagIds){
					if(checkedTagId==null) continue;
					if(checkedTagId.equals(tag.getId())){
						checked = true;
						break;
					}
				}
				classTags.put(tag, checked);
			}
		}
		return "teste";
	}
	
	@Action("wizard_save_teste")
	@ActionRole( ActionRoleType.USER )
	public String saveTeste(){
		wizardVo.setTags(selectedTags);
		return "saveTeste";
	}
	
	@SuppressWarnings("unchecked")
	@Action("wizard_following")
	@ActionRole( ActionRoleType.USER )
	public String following(){
		Pagination p = userManager.getSuggestedUsers(getSessionUserId());
		if(p!=null && p.getList()!=null && !p.getList().isEmpty()){
			List<User> users =  (List<User>) p.getList();
			suggestedUsers = new HashMap<User, Boolean>();
			for (User user : users){
				suggestedUsers.put(user, false);
			}
		}
		
		p = placeManager.getSuggestedPlaces(getSessionUserId());
		if(p!=null && p.getList()!=null && !p.getList().isEmpty()){
			List<Place> places =  (List<Place>) p.getList();
			suggestedPlaces = new HashMap<Place, Boolean>();
			for (Place place : places){
				suggestedPlaces.put(place, false);
			}
		}
		
		p = dishManager.getSuggestedDish(getSessionUserId());
		if(p!=null && p.getList()!=null && !p.getList().isEmpty()){
			List<Dish> dishs =  (List<Dish>) p.getList();
			suggestedDishs = new HashMap<Dish, Boolean>();
			for (Dish dish : dishs){
				suggestedDishs.put(dish, false);
			}
		}
		return "following";
	}
	
	@Action("wizard_submit")
	@ActionRole( ActionRoleType.USER )
	public String submit(){
		wizardVo.setFollowingUsers(selectedUsers);
		wizardVo.setFollowingPlaces(selectedPlaces);
		wizardVo.setFollowingDishs(selectedDishs);
		//persist wizard information
		userManager.saveSignUpWizardSetting(getSessionUserId(), getWizardVo());
		return "submit";
	}
	
	@SuppressWarnings("unchecked")
	@Action("wizard_delay")
	@ActionRole( ActionRoleType.USER )
	public String delay() {
		Map<String, UserTaskStatus> taskStatusMap = (Map<String, UserTaskStatus>) session
				.get(AppConstant.SESSION_TASK_STATUS);
		if (taskStatusMap == null) {
			throw new NeedLoginException(
					"task status map should be logged in session");
		}
		taskStatusMap
				.put(UserTaskCode.SIGN_UP_WIZARD, UserTaskStatus.DISCARDED);
		return "delay";
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(session == null){
			throw new RuntimeException("No session associate with request");
		}
		wizardVo = getSignUpWizard();
		if(wizardVo==null){
			User user = getSessionUser();
			if(user==null){
				throw new NeedLoginException("User not sign in");
			}
			wizardVo = new SignUpWizardVo();
			wizardVo.setUserId(user.getId());
			wizardVo.setUserName(user.getName());
			session.put(AppConstant.SESSION_SIGN_UP_WIZARD, wizardVo);
		}
	}
	
	public SignUpWizardVo getSignUpWizard() {
		if (session != null
				&& session.containsKey(AppConstant.SESSION_SIGN_UP_WIZARD)) {
			return (SignUpWizardVo) session.get(AppConstant.SESSION_SIGN_UP_WIZARD);
		}
		return null;
	}

	public SignUpWizardVo getWizardVo() {
		return wizardVo;
	}
	public void setWizardVo(SignUpWizardVo wizardVo) {
		this.wizardVo = wizardVo;
	}

	public Map<String, Map<Tag, Boolean>> getSuggestedTags() {
		return suggestedTags;
	}
	public void setSuggestedTags(Map<String, Map<Tag, Boolean>> suggestedTags) {
		this.suggestedTags = suggestedTags;
	}

	public Map<User, Boolean> getSuggestedUsers() {
		return suggestedUsers;
	}
	public void setSuggestedUsers(Map<User, Boolean> suggestedUsers) {
		this.suggestedUsers = suggestedUsers;
	}
	
	public Map<Place, Boolean> getSuggestedPlaces() {
		return suggestedPlaces;
	}
	public void setSuggestedPlaces(Map<Place, Boolean> suggestedPlaces) {
		this.suggestedPlaces = suggestedPlaces;
	}
	
	public Map<Dish, Boolean> getSuggestedDishs() {
		return suggestedDishs;
	}
	public void setSuggestedDishs(Map<Dish, Boolean> suggestedDishs) {
		this.suggestedDishs = suggestedDishs;
	}

	public List<Long> getSelectedTags() {
		return selectedTags;
	}
	public void setSelectedTags(List<Long> selectedTags) {
		this.selectedTags = selectedTags;
	}

	public List<Long> getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(List<Long> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public List<Long> getSelectedPlaces() {
		return selectedPlaces;
	}
	public void setSelectedPlaces(List<Long> selectedPlaces) {
		this.selectedPlaces = selectedPlaces;
	}

	public List<Long> getSelectedDishs() {
		return selectedDishs;
	}
	public void setSelectedDishs(List<Long> selectedDishs) {
		this.selectedDishs = selectedDishs;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSexy() {
		return sexy;
	}

	public void setSexy(int sexy) {
		this.sexy = sexy;
	}
}

