package com.bocai.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.Dialog;
import com.bocai.dao.domain.PrivateLetter;
import com.bocai.dao.domain.User;
import com.bocai.exception.ManagerException;
import com.bocai.manager.DialogManager;
import com.bocai.manager.ManagerHelper;

@Service("dialogManager")
@Transactional
public class DialogManagerImpl extends ManagerHelper implements DialogManager {

	private final static String GET_DIALOG = 
			"select distinct dialog from Dialog as dialog " +
				"left join dialog.ua as a " +
				"left join dialog.ub as b " +
				"where (a.id=? and b.id=?) or (a.id=? and b.id=?)";
		
	@Override
	public Long save(Dialog bean) {
		dialogDao.save(bean);
		return bean.getId();
	}

	@Override
	public Long update(Dialog bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Dialog bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Dialog> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dialog getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dialog getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Dialog getDialog(Long sourceId, Long targetId){
		String hql = GET_DIALOG;
		List<Dialog> dialogs = dialogDao.find(hql, sourceId, targetId, targetId, sourceId);
		if(dialogs == null || dialogs.isEmpty()){
			return null;
		}else{
			return dialogs.get(0);
		}
	}

	@Override
	public Pagination getDialogDetail(Long sourceId, Long targetId, int at,
			int size) {
		return dialogDao.getDialogDetail(sourceId, targetId, at, size);
	}

	@Override
	public Pagination getDialogsOfPeople(Long userId, int at, int size) {
		return dialogDao.getDialogsOfPeople(userId, at, size);
	}
	
	@Override
	public Pagination getUnreadDialogsOfPeople(Long userId, int at, int size) {
		return dialogDao.getUnreadDialogsOfPeople(userId, at, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendPrivateLetter(Long sourceId, Long targetId, String content, int way) {
		if(sourceId==null || targetId==null || content==null){
			throw new ManagerException("Invalid parameter");
		}
		String hql = GET_DIALOG;
		List<Dialog> dialogs = dialogDao.find(hql, sourceId, targetId, targetId, sourceId);
		Dialog dialog = null;
		String side = null;
		if(dialogs == null || dialogs.isEmpty()){
			User ua = userDao.findById(sourceId);
			User ub = userDao.findById(targetId);
			if(ua==null || ub == null){
				throw new ManagerException("source user or target user not exist");
			}
			dialog = new Dialog();
			dialog.setCreatedAt(new Date());
			dialog.setUa(ua);
			dialog.setUb(ub);
			dialog.setLetterCount(0);
			dialogDao.save(dialog);
			side = "a";
		}else{
			dialog = dialogs.get(0);
			if(sourceId.equals(dialog.getUa().getId())){
				side = "a";
			}else{
				side = "b";
			}
		}
		PrivateLetter pl = new PrivateLetter();
		pl.setDialog(dialog);
		pl.setContent(content);
		pl.setCreatedAt(new Date());
		pl.setStatus(0);
		pl.setSendWay(way);
		pl.setSendSide(side);
		privateLetterDao.save(pl);
		if("a".equals(side)){
			dialog.setLastLetterA(pl);
		}else{
			dialog.setLastLetterB(pl);
		}
		int letterCount = dialog.getLetterCount();
		dialog.setLetterCount(++letterCount);
		dialog.setUpdatedAt(pl.getCreatedAt());
		dialogDao.save(dialog);
	}
	
	public Long newPrivateLetterCount(Long userId){
		if(userId==null){
			throw new ManagerException("Invalid parameter");
		}
		String hql = HQLContainer.getHql("new.private.letter.count");
		Long result = (Long) dialogDao.find(hql, userId, userId).get(0);
		return result != null ? result : 0L;
	}
	
	public void updatePlAsReaded(List<Long> newPlIds){
		dialogDao.updatePlAsReaded(newPlIds);
	}

}
