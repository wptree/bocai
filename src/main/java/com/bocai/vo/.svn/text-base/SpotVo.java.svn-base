package com.bocai.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Comment;
import com.bocai.dao.domain.Spot;
import com.bocai.util.DateUtil;

@SuppressWarnings("serial")
public class SpotVo implements Serializable{
	private Long id;
	private String description;
	private Date createAt;
	private String createAtStr;
	private String postBy;
	private String imgType;
	private String imgPath120;
	private Integer viewTimes = 0;
	private Integer goodCount = 0;
	private Integer greatCount = 0;
	
	private Long dishId;
	private String dishName;
	
	private Long placeId;
	private String placeName;
	private String placeSecondaryName;
	private String placeFullName;
	private String placeLocationCity;
	private String placeLocationStreet;
	private Double placeLocationLat;
	private Double placeLocationLng;
	private String placeAddress;
	
	
	private Long aggSpotId;
	private Integer aggSpotNomNum = 0;
	private Integer aggSpotWantedNum = 0;
	
	private Long spotedById;
	private String spotedByName;
	private String spotedByAvatar;
	
	private List<CommentVO> comments;
	private Integer commentNum;
	private int cmtTotalPage; //for the main page spot structure
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
		this.createAtStr = DateUtil.getDateStr(createAt,
				DateUtil.LABEL_CN_FORMAT);
	}

	public String getPostBy() {
		return postBy;
	}
	
	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return filterNull(placeName);
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceSecondaryName() {
		return filterNull(placeSecondaryName);
	}

	public void setPlaceSecondaryName(String placeSecondaryName) {
		this.placeSecondaryName = placeSecondaryName;
	}

	public String getPlaceLocationCity() {
		return filterNull(placeLocationCity);
	}

	public void setPlaceLocationCity(String placeLocationCity) {
		this.placeLocationCity = placeLocationCity;
	}

	public String getPlaceLocationStreet() {
		return filterNull(placeLocationStreet);
	}

	public void setPlaceLocationStreet(String placeLocationStreet) {
		this.placeLocationStreet = placeLocationStreet;
	}

	public Long getAggSpotId() {
		return aggSpotId;
	}

	public void setAggSpotId(Long aggSpotId) {
		this.aggSpotId = aggSpotId;
	}

	public Integer getAggSpotNomNum() {
		return aggSpotNomNum;
	}

	public void setAggSpotNomNum(Integer aggSpotNomNum) {
		this.aggSpotNomNum = aggSpotNomNum;
	}

	public Integer getAggSpotWantedNum() {
		return aggSpotWantedNum;
	}

	public void setAggSpotWantedNum(Integer aggSpotWantedNum) {
		this.aggSpotWantedNum = aggSpotWantedNum;
	}

	public void setSpotedById(Long spotedById) {
		this.spotedById = spotedById;
	}

	public Long getSpotedById() {
		return spotedById;
	}

	public void setSpotedByName(String spotedByName) {
		this.spotedByName = spotedByName;
	}

	public String getSpotedByName() {
		return spotedByName;
	}
	
	public String getImgPath120() {
		return imgPath120;
	}

	public void setImgPath120(String imgPath120) {
		this.imgPath120 = imgPath120;
	}
	
	public String getSpotedByAvatar() {
		return spotedByAvatar;
	}

	public void setSpotedByAvatar(String spotedByAvatar) {
		this.spotedByAvatar = spotedByAvatar;
	}
	
	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount==null? 0 : goodCount;
	}

	public Integer getGreatCount() {
		return greatCount;
	}

	public void setGreatCount(Integer greatCount) {
		this.greatCount = greatCount == null ? 0 : greatCount;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}
	
	public void addComment(CommentVO comment){
		if(comments==null){
			comments = new ArrayList<CommentVO>();
		}
		comments.add(comment);
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum == null? 0 : commentNum;
	}

	public int getCmtTotalPage() {
		return cmtTotalPage;
	}

	public void setCmtTotalPage(int cmtTotalPage) {
		this.cmtTotalPage = cmtTotalPage;
	}

	public String getCreateAtStr() {
		return createAtStr;
	}

	public void setCreateAtStr(String createAtStr) {
		this.createAtStr = createAtStr;
	}

	public String getPlaceAddress() {
		return filterNull(placeAddress);
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public Double getPlaceLocationLat() {
		return placeLocationLat;
	}

	public void setPlaceLocationLat(Double placeLocationLat) {
		this.placeLocationLat = placeLocationLat;
	}

	public Double getPlaceLocationLng() {
		return placeLocationLng;
	}

	public void setPlaceLocationLng(Double placeLocationLng) {
		this.placeLocationLng = placeLocationLng;
	}
	
	private String filterNull(String orig){
		return orig == null?"":orig;
	}
	
	public static SpotVo fromSpotMin(Spot spot){
		if(spot == null) return null;
		SpotVo vo = new SpotVo();
		vo.setId(spot.getId());
		vo.setDescription(spot.getDescription());
		vo.setCreateAt(spot.getCreatedAt());
		vo.setPostBy(spot.getPostBy());
		vo.setImgType(spot.getImgType());
		vo.setImgPath120(spot.getSpotImgPath(120));
		vo.setViewTimes(spot.getViewTimes());
		vo.setGoodCount(spot.getGoodCount());
		vo.setGreatCount(spot.getGreatCount());
		vo.setCommentNum(spot.getCommentNum());
		return vo;
	}

	public static SpotVo fromSpotBase(Spot spot){
		SpotVo vo = SpotVo.fromSpotMin(spot);
		if(vo == null) return null;
		
		if(spot.getSpotedBy()!=null){
			vo.setSpotedById(spot.getSpotedBy().getId());
			vo.setSpotedByName(spot.getSpotedBy().getName());
			vo.setSpotedByAvatar(spot.getSpotedBy().getAvatar());
		}
		
		if(spot.getDish()!=null){
			vo.setDishId(spot.getDish().getId());
			vo.setDishName(spot.getDish().getName());
		}
		
		if(spot.getPlace()!=null){
			vo.setPlaceId(spot.getPlace().getId());
			vo.setPlaceName(spot.getPlace().getName());
			vo.setPlaceSecondaryName(spot.getPlace().getSecondaryName());
			vo.setPlaceFullName(spot.getPlace()!=null?spot.getPlace().getFullName():null);
			if(spot.getPlace().getLocation()!=null){
				vo.setPlaceLocationCity(spot.getPlace().getLocation().getCity());
				vo.setPlaceLocationStreet(spot.getPlace().getLocation().getStreet());
				StringBuilder sb = new StringBuilder();
				sb.append(vo.getPlaceLocationCity()!=null?vo.getPlaceLocationCity() + " " : "").
					append(vo.getPlaceLocationStreet()!=null?vo.getPlaceLocationStreet():"");
				vo.setPlaceAddress(sb.toString());
				vo.setPlaceLocationLat(spot.getPlace().getLocation().getLat());
				vo.setPlaceLocationLng(spot.getPlace().getLocation().getLng());
			}
		}
		
		if(spot.getAggSpot()!=null){
			vo.setAggSpotId(spot.getAggSpot().getId());
			vo.setAggSpotNomNum(spot.getAggSpot().getNomNum());
			vo.setAggSpotWantedNum(spot.getAggSpot().getWantedNum());
		}
		return vo;
	}
	
	public static SpotVo fromSpotWithComment(Spot spot){
		SpotVo vo = SpotVo.fromSpotBase(spot);
		if(vo == null) return null;
		Set<Comment> comments = spot.getComments();
		if(comments!=null && comments.size()!=0){
			for(Comment comment : comments){
				vo.addComment(CommentVO.fromComment(comment));
			}
		}
		return vo;
	}

	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}

	public String getPlaceFullName() {
		return placeFullName;
	}

}
