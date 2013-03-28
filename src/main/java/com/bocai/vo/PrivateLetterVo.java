package com.bocai.vo;

import java.io.Serializable;

import com.bocai.dao.domain.PrivateLetter;
import com.bocai.util.DateUtil;

@SuppressWarnings("serial")
public class PrivateLetterVo implements Serializable {

	private Long id;
	private String content;
	private String createdAtStr;
	private int status;
	private String sendWay;
	private boolean fromMe;
	private UserVo fromWho;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAtStr() {
		return createdAtStr;
	}
	public void setCreatedAtStr(String createdAtStr) {
		this.createdAtStr = createdAtStr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSendWay() {
		return sendWay;
	}
	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}
	public boolean isFromMe() {
		return fromMe;
	}
	public void setFromMe(boolean fromMe) {
		this.fromMe = fromMe;
	}
	public UserVo getFromWho() {
		return fromWho;
	}
	public void setFromWho(UserVo fromWho) {
		this.fromWho = fromWho;
	}
	
	public static PrivateLetterVo toBasic(Long sourceId, PrivateLetter privateLetter){
		if(privateLetter==null)return null;
		PrivateLetterVo vo = new PrivateLetterVo();
		vo.setId(privateLetter.getId());
		vo.setContent(privateLetter.getContent());
		if(privateLetter.getSendWay()==0){
			vo.setSendWay("web");
		}else if(privateLetter.getSendWay()==1){
			vo.setSendWay("andriod");
		}else if(privateLetter.getSendWay()==2){
			vo.setSendWay("iphone");
		}else{
			vo.setSendWay("ipad");
		}
		vo.setCreatedAtStr(DateUtil.getDateTimeShortStr(privateLetter.getCreatedAt()));
		if(privateLetter.getDialog()!=null){
			if("a".equals(privateLetter.getSendSide())){
				vo.setFromWho(UserVo.fromUserBasic(privateLetter.getDialog().getUa()));
			}else if("b".equals(privateLetter.getSendSide())){
				vo.setFromWho(UserVo.fromUserBasic(privateLetter.getDialog().getUb()));
			}else{
				return null;
			}
		}
		vo.setStatus(privateLetter.getStatus());
		if(sourceId.equals(vo.getFromWho().getId())){
			vo.setFromMe(true);
		}else{
			vo.setFromMe(false);
		}
		return vo;
	}
}
