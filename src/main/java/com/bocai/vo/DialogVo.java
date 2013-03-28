package com.bocai.vo;

import java.io.Serializable;

import com.bocai.dao.domain.Dialog;
import com.bocai.util.DateUtil;

@SuppressWarnings("serial")
public class DialogVo implements Serializable {
	
	private Long id;
	private UserVo counterParty;
	private PrivateLetterVo lastLetter;
	private String updatedAtStr;
	private int letterCount;
	private long unreadCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserVo getCounterParty() {
		return counterParty;
	}
	public void setCounterParty(UserVo counterParty) {
		this.counterParty = counterParty;
	}
	public PrivateLetterVo getLastLetter() {
		return lastLetter;
	}
	public void setLastLetter(PrivateLetterVo lastLetter) {
		this.lastLetter = lastLetter;
	}
	public String getUpdatedAtStr() {
		return updatedAtStr;
	}
	public void setUpdatedAtStr(String updatedAtStr) {
		this.updatedAtStr = updatedAtStr;
	}
	public int getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}
	public long getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(long unreadCount) {
		this.unreadCount = unreadCount;
	}
	
	public static DialogVo toBasic (Long sourceId, Dialog dialog){
		if(sourceId==null || dialog==null) return null;
		if(dialog.getUa()==null || dialog.getUb()==null) return null;
		DialogVo dialogVo = new DialogVo();
		if(sourceId.equals(dialog.getUa().getId())){
			dialogVo.setCounterParty(UserVo.fromUserBasic(dialog.getUb()));
			if(dialog.getLastLetterB()==null){
				return null;
			}
			dialogVo.setLastLetter(PrivateLetterVo.toBasic(sourceId, dialog.getLastLetterB()));
		}else if(sourceId.equals(dialog.getUb().getId())){
			dialogVo.setCounterParty(UserVo.fromUserBasic(dialog.getUa()));
			if(dialog.getLastLetterA()==null){
				return null;
			}
			dialogVo.setLastLetter(PrivateLetterVo.toBasic(sourceId, dialog.getLastLetterA()));
		}else{
			return null;
		}
		dialogVo.setId(dialog.getId());
		dialogVo.setUpdatedAtStr(DateUtil.getDateTimeShortStr(dialog.getUpdatedAt()));
		dialogVo.setLetterCount(dialog.getLetterCount());
		dialogVo.setUnreadCount(dialog.getUnreadCount()!=null?dialog.getUnreadCount():0L);
		
		return dialogVo;
	}
}
