package com.bocai.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggSpotTagXref;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.dao.domain.Spot;
import com.bocai.dao.domain.Tag;
import com.bocai.web.util.ImageHelper;

@SuppressWarnings("serial")
public class PinVo implements Serializable {
	
	private Long tagId;
	private String tagName;
	private PinBoxVo thumb;
	private List<PinBoxVo> tails;
	
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public PinBoxVo getThumb() {
		return thumb;
	}
	public void setThumb(PinBoxVo thumb) {
		this.thumb = thumb;
	}
	public List<PinBoxVo> getTails() {
		return tails;
	}
	public void setTails(List<PinBoxVo> tails) {
		this.tails = tails;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(tagId)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		else if(!(obj instanceof TagVo)){
			return false;
		}
		return new EqualsBuilder()
				.append(tagId, ((PinVo)obj).getTagId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(tagId)
				.append(" ")
				.append(tagName)
				.toString();
	}
	
	public static PinVo from(Tag tag, List<AggregatedSpot> aggSpots){
		if(tag == null || aggSpots ==null) return null;
		PinVo vo = new PinVo();
		vo.setTagId(tag.getId());
		vo.setTagName(tag.getTagName());
		List<PinBoxVo> boxes = new ArrayList<PinBoxVo>();
		PinBoxVo thumb = null;
		Spot thumbSpot = null;
		for(int i=0; i<aggSpots.size(); i++){
			AggregatedSpot aggSpot = aggSpots.get(i);
			if(aggSpot==null) continue;
			PinBoxVo box = new PinBoxVo();
			box.setAggSpotId(aggSpot.getId());
			Spot spot = null;
			if(aggSpot.getLastSpot()!=null){
				spot = aggSpot.getLastSpot();
				box.setImageUrl( SystemConfig.imageContext() + "/" +
						ImageHelper.getSpotImagePath(spot.getId(), 
								32, spot.getImgType()));
			}
			StringBuilder sb = new StringBuilder();
			if(aggSpot.getDish()!=null)
				sb.append(aggSpot.getDish().getName());
			if(aggSpot.getPlace()!=null)
				sb.append("@");
				sb.append(aggSpot.getPlace().getName());
			box.setFullName(sb.toString());
			box.setNommedNum(aggSpot.getNomNum()!=null?aggSpot.getNomNum():0);
			box.setSpottedNum(aggSpot.getSpottedNum()!=null?aggSpot.getSpottedNum():0);
			box.setTastedNum(aggSpot.getTastedNum()!=null?aggSpot.getTastedNum():0);
			box.setViewedNum(aggSpot.getViewedNum()!=null?aggSpot.getViewedNum():0);
			if(thumb==null || (box.getViewedNum() > 
				thumb.getViewedNum())){
				thumb = box;
				thumbSpot = spot;
			}
			boxes.add(box);
		}
			
		if(thumb!=null && thumbSpot!=null){
			thumb.setImageUrl( SystemConfig.imageContext() + "/" +
					ImageHelper.getSpotImagePath(thumbSpot.getId(), 
							180, thumbSpot.getImgType()));
		}
		vo.setThumb(thumb);
		boxes.remove(thumb);
		vo.setTails(boxes);
		return vo;
	}
}
