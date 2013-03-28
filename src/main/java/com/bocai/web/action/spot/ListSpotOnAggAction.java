package com.bocai.web.action.spot;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.manager.SpotManager;
import com.bocai.vo.CommentVO;
import com.bocai.vo.FrontResultSet;
import com.bocai.vo.SpotVo;
import com.bocai.vo.UserVo;
import com.bocai.web.action.BaseAction;

@SuppressWarnings({ "rawtypes", "serial" })
@ParentPackage("spot")
@Results({
	@Result(name = "ajax_list_spot_on_agg", type = "json", 
			params = {"includeProperties","id,pageAt,allCount,total,currentUser.*,succeed,spotVos.*"}
	)
})
public class ListSpotOnAggAction extends BaseAction<Object> {

	private static final String AJAX_LIST_SPOT_ON_AGG = "ajax_list_spot_on_agg";
	private static final int PAGE_SPOT_SIZE = 3;
	private static final int PAGE_CMT_SIZE = 4;
	@Autowired
	private SpotManager spotManager;
	private List<SpotVo> spotVos;
	private Long id;
	private int pageAt;
	private int total;
	private int allCount;
	private Boolean succeed;
	private UserVo currentUser;
	
	@Action("ajax_list_spot_on_agg")
	public String ajaxList(){
		try{
			FrontResultSet<Spot> rs = spotManager.getSpotsWithCommentsOnAggSpot(id, PAGE_SPOT_SIZE, PAGE_CMT_SIZE, pageAt);
			pageAt = rs.getAt();
			total = rs.getTotal();
			allCount = rs.getAllCount();
			List<Spot> spots = rs.getList();
			if(spots != null && spots.size() != 0){
				for (Spot spot : spots){
					SpotVo spotVo = SpotVo.fromSpotBase(spot);
					Pagination p = rs.getChildPageMap().get(spot.getId());
					Pagination pg = (Pagination)p.clone();
					if(pg.getList() != null &&
							!pg.getList().isEmpty()){
						List cmts = pg.getList();
						List<CommentVO> cmtVos = new ArrayList<CommentVO>();
						for (Object cmt : cmts){
							cmtVos.add(CommentVO.fromCommentBase((Comment)cmt));
						}
						spotVo.setComments(cmtVos);
						spotVo.setCmtTotalPage(pg.getTotalPage());
					}
					spotVos.add(spotVo);
				}
			}
			currentUser = UserVo.fromUserMin(getSessionUser());
			if(currentUser == null){
				currentUser = new UserVo();
				currentUser.setAvatar(SystemConfig.getUserDefaultAvatar());
			}
			setSucceed(true);
		}catch(Exception e){
			addActionError(e.getMessage());
			setSucceed(false);
		}
		return AJAX_LIST_SPOT_ON_AGG;
	}
	
	@Override
	public Object getModel() {
		return this;
	}

	@Override
	protected void prepareModel() throws Exception {
		spotVos = new ArrayList<SpotVo>();
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public List<SpotVo> getSpotVos() {
		return spotVos;
	}

	public void setSpotVos(List<SpotVo> spotVos) {
		this.spotVos = spotVos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPageAt() {
		return pageAt;
	}

	public void setPageAt(int pageAt) {
		this.pageAt = pageAt;
	}

	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public UserVo getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserVo currentUser) {
		this.currentUser = currentUser;
	}
	
	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
